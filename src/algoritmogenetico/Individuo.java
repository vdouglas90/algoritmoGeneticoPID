/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package algoritmogenetico;

import java.util.Vector;

/**
 *
 * @author Victor
 */
public class Individuo implements Comparable<Individuo> {
    private double avaliacao;                         //valor de cada individuo após passar pela funcao de avaliacao
    private double adaptabilidade;                    //adaptabilidade calculada, sendo o inverso da avaliacao : 1/avaliacao
    private double adaptabilidadeRelativa;            //adap.Relativa, é a divisao da adaptabilidade de um individuo dividido pela soma total das adaptabilidades


public Vector genes;

   public Individuo(){
       
       genes = new Vector();
       
   } 


    /**
     * @return the avaliacao
     */
    public double getAvaliacao() {
        return avaliacao;
    }

    /**
     * @param avaliacao the avaliacao to set
     */
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * @return the adaptabilidade
     */
    public double getAdaptabilidade() {
        return adaptabilidade;
    }

    /**
     * @param adaptabilidade the adaptabilidade to set
     */
    public void setAdaptabilidade(double adaptabilidade) {
        this.adaptabilidade = adaptabilidade;
    }

    /**
     * @return the adaptabilidadeRelativa
     */
    public double getAdaptabilidadeRelativa() {
        return adaptabilidadeRelativa;
    }

    /**
     * @param adaptabilidadeRelativa the adaptabilidadeRelativa to set
     */
    public void setAdaptabilidadeRelativa(double adaptabilidadeRelativa) {
        this.adaptabilidadeRelativa = adaptabilidadeRelativa;
    }

    @Override
    public int compareTo(Individuo o) {
        if(this.adaptabilidadeRelativa == o.getAdaptabilidadeRelativa()){
            return 0;}

        if(o.getAdaptabilidade() > this.adaptabilidadeRelativa){
            return 1;}
        else{
            return -1;}
    }

    }



