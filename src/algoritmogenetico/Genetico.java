/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmogenetico;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author Victor
 */
public class Genetico {

    int tamanhoPopulacao;               //valor escolhido pelo usuário para tamanho da pop. total
    public Vector populacao;            //vetor que vai guardar a populacao atual

    public Genetico(int tamanhoPopulacao) {
        populacao = new Vector();
        this.tamanhoPopulacao = tamanhoPopulacao;

    }

    public Vector criarPopulacao(){ 
        //POPULACAO SENDO CRIADA COM NUMEROS RANDOMICOS ENTRE 0 E 50 EM UM PLANO CARTESIANO
        for (int i = 0; i < tamanhoPopulacao; i++) {
            Individuo individuo = new Individuo();
            individuo.genes.add(Math.random() * 70); //VALOR ALEATÓRIO PRO KP (de 0 a 100)
            individuo.genes.add(Math.random() * 70);  //VALOR ALEATORIO PRO KI
            individuo.genes.add(Math.random() * 70);  //VALOR ALEATORIO PRO KD
            
            populacao.add(individuo); // adicionando um Individuo da classe Individuo

        }
        return populacao; // vetor de Individuos
    }

    public double funcaoAvaliacao(double kp, double ki, double kd) { //MEU PONTO MINIMO PRA APROXIMAR É X=2 E Y=2
        //IREI TESTAR A DISTANCIA EUCLIDIANA
        TreinamentoControle avaliacao = new TreinamentoControle(kp,ki,kd);
        //avaliacao.somaErros;
        return avaliacao.somaErros;


    }

    public void avaliacaoIndividuos(Vector populacao) { //SETA OS VALORES DE ADAPTABILIDADE E AVALIACAO!
        double somatorioAdaptabilidades = 0;

        for (int i = 0; i < populacao.size(); i++) {
            //descobre o valor de avaliacao e adaptabilidade de cada individuo, jogando cada individuo na funcao de Avaliacao


            ((Individuo) populacao.get(i)).setAvaliacao(funcaoAvaliacao((Double) ((Individuo) populacao.get(i)).genes.get(0) ,(Double) ((Individuo) populacao.get(i)).genes.get(1) ,(Double) ((Individuo) populacao.get(i)).genes.get(2)));//CHAMO A FUNCAO AVALIACAO PRA O PRIMEIRO INDIVIDUO, E DEPOIS JA SETO A AVALIACAO DELE
            ((Individuo) populacao.get(i)).setAdaptabilidade(1 / ((Individuo) populacao.get(i)).getAvaliacao());  //adatpabilidade é o inverso da avaliacao

            somatorioAdaptabilidades = somatorioAdaptabilidades + ((Individuo) populacao.get(i)).getAdaptabilidade();
        }

        //descobre o valor de adaptabilidade relativa de cada individuo
        for (int i = 0; i < populacao.size(); i++) {
           
            ((Individuo) populacao.get(i)).setAdaptabilidadeRelativa(((Individuo) populacao.get(i)).getAdaptabilidade() / somatorioAdaptabilidades);
        }
    }


    public Individuo roleta() {

        Collections.sort(populacao, new ComparadorDeIndividuos()); //ordena o vetor populacao de acordo com as aptidoes relativas(em ordem crescente)
        Collections.reverse(populacao); //ja que meu algoritmo trabalhara de forma decrescente, invertendo aqui a ordem!

        int i;
        double aux = 0;
        double limite = Math.random(); //roleta girará aqui, no random, de 0 a 1 que é o máximo(soma das adaptabilidades relativas)

        for (i = 0; (i < populacao.size()) && (aux < limite); i++) {
            aux = aux + ((Individuo) populacao.get(i)).getAdaptabilidadeRelativa();
        }
        // System.out.println(limite);
        i--;
        return (Individuo) populacao.get(i);
    }

    //essa taxa de mutacao eh ideal ser 0.13
    public Individuo mutacao(Individuo individuo, int tamanhoIndividuo, double taxaMutacao) { //pro primeiro exemplo vo ter tamanho 2 (x,y)
        int random = (int) (Math.random() * tamanhoIndividuo);
        individuo.genes.set(random, (Double) individuo.genes.get(random) + taxaMutacao);

        return individuo;
    }

    //meu crossover vai dividir os pais no meio, QUEM SABE, colocar parametro de escolha desse corte!
    //LEMBRANDO, A CADA 2 PAIS, SÃO GERADOS 2 FILHOS!
    public Vector crossover(Individuo pai1, Individuo pai2) {
       // int taxaCrossover = pai1.genes.size() / 2; //dividir na metade!
        int taxaCrossover = 1; //Como agora no caso do PID tenho 3 genes, se dividisse 3/2 ia da merda! Vo logo jogar 1 como taxa!
        Vector novosFilhos = new Vector();
        Individuo filho1 = new Individuo();
        Individuo filho2 = new Individuo();
        //  Individuo filho3 = new Individuo();
        //  Individuo filho4 = new Individuo();

        /*    for (int i = 0; i < pai1.genes.size(); i++) {
        filho1.genes.add(pai1.genes.get(i));
        filho2.genes.add(pai2.genes.get(i));
        filho3.genes.add(pai1.genes.get(i));
        filho4.genes.add(pai2.genes.get(i));

        }*/
        filho1.genes.addAll(pai1.genes);
        filho2.genes.addAll(pai2.genes);
        //   filho3.genes.addAll(pai1.genes);
        //   filho4.genes.addAll(pai2.genes);


        for (int i = 0; i < taxaCrossover; i++) {
            filho1.genes.set(i, (Double) pai2.genes.get(i));
            filho2.genes.set(i, (Double) pai1.genes.get(i));
        }

        // for (int i = taxaCrossover; i < pai1.genes.size(); i++) {
        //     filho3.genes.set(i, (Double) pai2.genes.get(i));
        //     filho4.genes.set(i, (Double) pai1.genes.get(i));
        // }

        //ADICIONANDO EM UM VETOR, OS NOVOS FILHOS, Q SERÃO ADICIONADOS NA NOVA POPULAÇÃO!
        novosFilhos.add(filho1);
        novosFilhos.add(filho2);
        //novosFilhos.add(filho3);
        // novosFilhos.add(filho4);

        return novosFilhos;
    }

    public Vector crossoverComMutacao(Individuo pai1, Individuo pai2) {
       // int taxaCrossover = pai1.genes.size() / 2; //dividir na metade!
        int taxaCrossover = 1;//Como agora no caso do PID tenho 3 genes, se dividisse 3/2 ia da merda! Vo logo jogar 1 como taxa!
        Vector novosFilhos = new Vector();
        Individuo filho1 = new Individuo();
        Individuo filho2 = new Individuo();
        //     Individuo filho3 = new Individuo();
        //     Individuo filho4 = new Individuo();

        for (int i = 0; i < pai1.genes.size(); i++) {
            filho1.genes.add(pai1.genes.get(i));
            filho2.genes.add(pai2.genes.get(i));
            //          filho3.genes.add(pai1.genes.get(i));
            //          filho4.genes.add(pai2.genes.get(i));

        }


        for (int i = 0; i < taxaCrossover; i++) {
            filho1.genes.set(i, (Double) pai2.genes.get(i));
            filho2.genes.set(i, (Double) pai1.genes.get(i));
        }

        //ADICIONANDO EM UM VETOR, OS NOVOS FILHOS, Q SERÃO ADICIONADOS NA NOVA POPULAÇÃO! JA COM MUTACAO!
        novosFilhos.add(mutacao(filho1, 2, 0.13)); //COLOCAR AQUI 0.13
        novosFilhos.add(mutacao(filho2, 2, 0.13));
    

        return novosFilhos;
    }

    public Individuo algoritmoGenetico(int numeroGeracoes) {
        int iterador = 0;
        criarPopulacao(); //50 individuos!
        Vector novaPopulacao = new Vector();
        avaliacaoIndividuos(populacao);

        while (iterador < numeroGeracoes) {

            for (int j = 0; /*j < 40*/ j<80; j++) { //ISSO SO VAI DA CERTO SE A POPULACAO FOR DE 100 INDIVIDUOS

                novaPopulacao.addAll(crossover(roleta(), roleta())); //o valor de filhos criados aqui, vai ser o valor ali em cima(40) x 2
            }

            //foram adicionados 80 individuos por crossover, 18 por crossover com mutacao, e 2 por roleta direta!

            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
            novaPopulacao.addAll(crossoverComMutacao(roleta(), roleta()));
          
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());
            novaPopulacao.add(roleta());

      
            populacao = (Vector) novaPopulacao.clone();
            novaPopulacao.removeAllElements(); //apaguei toda a NovaPopulacao para começar tudo de novo
    /*        System.out.println("SEGUNDA GERACAO");
            System.out.println(((Individuo)populacao.get(0)).genes.get(0)+" "+((Individuo)populacao.get(0)).genes.get(1));
            System.out.println(((Individuo)populacao.get(1)).genes.get(0)+" "+((Individuo)populacao.get(1)).genes.get(1));
            System.out.println(((Individuo)populacao.get(2)).genes.get(0)+" "+((Individuo)populacao.get(2)).genes.get(1));
            System.out.println(((Individuo)populacao.get(3)).genes.get(0)+" "+((Individuo)populacao.get(3)).genes.get(1));
            System.out.println(((Individuo)populacao.get(4)).genes.get(0)+" "+((Individuo)populacao.get(4)).genes.get(1));
             */
            avaliacaoIndividuos(populacao); //FAZ NOVAMENTE A AVALIACAO!!!!


            //AQUI VOU TESTAR SE MEU INDIVIDUO JA ATINGIU  O ERRO MINIMO!!!!!!!! se atingiu, termina o algoritmo, senão continua o while!
            for (int j = 0; j < populacao.size(); j++) {
                double avaliacao = ((Individuo) populacao.get(j)).getAvaliacao();
                if (avaliacao < 0.5) {
                    System.out.println("ATINGI O LIMIAR MINIMO EXIGIDO");
                    return ((Individuo) populacao.get(j));
                }
            }

            iterador++;
        }
        Collections.sort(populacao, new ComparadorDeIndividuosAvaliacao()); //ordena o vetor populacao de acordo com as aptidoes relativas(em ordem crescente)
        return (Individuo) populacao.get(0);
    }
}
