/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import br.ufrn.dca.controle.QuanserClient;
import br.ufrn.dca.controle.QuanserClientException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class TreinamentoControle {

    private double kp;
    private double ki;
    private double kd;
    private Timer timer;
    private double t = 0, //tempo
            Ts = 0.1;
    private double valorCalculado, valorEscrito;
    private double saida = 0;
    private double erro = 0;           //Variavel utilizada no calculo do erro do sistema em malha fechada
    private double erroAnterior = 0; //usado no controlador Derivativo para conhecimento do erro anterior
    private double nivel = 0;
    private QuanserClient client;
    private double sp = 7;
    double acaoI = 0;
    public double somaErros = 0;

    public  TreinamentoControle(final double kp, final double ki, final double kd) throws QuanserClientException {
       /* this.kp = kp;
        this.ki = ki;
        this.kd = kd;*/
        client = new QuanserClient("localhost", 20081);

        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                if(t == 2){ //AQUI EU VEJO SE JA ATINGI 20 AMOSTRAS DE VALORES DE VALIDAÇÃO(NO MEU CASO ERROS)
                    sp = sp + 7;
                }
                else if(t == 4){
                    sp = sp + 7;
                }
                else if(t == 6){ //SE ATINGI 60 AMOSTRAR, PARO TUDO AQUI E RETORNO
                    timer.cancel();
                    return;
                }

                try {
                
                    saida = client.read(0) * 6.25;
                
                } catch (QuanserClientException ex) {
                    Logger.getLogger(TreinamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                }
                erro = sp - saida;
                somaErros = somaErros + erro;
                
                valorCalculado = kp * erro + kd * ((erro - erroAnterior) / Ts) + (acaoI = acaoI + ki * Ts * erro);
                valorEscrito = valorCalculado;
                erroAnterior = erro;
                if (valorEscrito > 3) { //SATURAÇÃO DO ATUADOR
                    valorEscrito = 3;
                }
                if (valorEscrito < -3) {
                    valorEscrito = -3;
                }
                
                try {
                
                    client.write(0, valorEscrito);

                } catch (QuanserClientException ex) {
                    Logger.getLogger(TreinamentoControle.class.getName()).log(Level.SEVERE, null, ex);
                }

                t += Ts;
            }
        },
                0, //initial delay
                (long) (Ts * 1000));
    }
}
