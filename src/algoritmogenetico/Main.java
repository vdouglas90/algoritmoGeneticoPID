/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package algoritmogenetico;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Victor
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     

     /*   Genetico genetico = new Genetico(5);
        Vector pop = genetico.criarPopulacao();
        System.out.println("PRIMEIRO INDIVIDUO -> "+((Individuo)pop.get(0)).genes.get(0)+" "+((Individuo)pop.get(0)).genes.get(1));
        System.out.println("SEGUNDO INDIVIDUO - > "+((Individuo)pop.get(1)).genes.get(0)+" "+((Individuo)pop.get(1)).genes.get(1));
        System.out.println("TERCEIRO INDIVIDUO - > "+((Individuo)pop.get(2)).genes.get(0)+" "+((Individuo)pop.get(2)).genes.get(1));
        System.out.println("QUARTO INDIVIDUO - > "+((Individuo)pop.get(3)).genes.get(0)+" "+((Individuo)pop.get(3)).genes.get(1));
        System.out.println("QUINTO INDIVIDUO - > "+((Individuo)pop.get(4)).genes.get(0)+" "+((Individuo)pop.get(4)).genes.get(1));

        genetico.avaliacaoIndividuos();

        System.out.println("PRIMEIRO, AVALIACAO -> "+ ((Individuo)pop.get(0)).getAvaliacao()+" "+((Individuo)pop.get(0)).getAdaptabilidade()+" "+((Individuo)pop.get(0)).getAdaptabilidadeRelativa());
        System.out.println("SEGUNDO, AVALIACAO - > "+((Individuo)pop.get(1)).getAvaliacao()+" "+((Individuo)pop.get(1)).getAdaptabilidade()+" "+((Individuo)pop.get(1)).getAdaptabilidadeRelativa());
        System.out.println("TERCEIRO, AVALIACAO  - > "+((Individuo)pop.get(2)).getAvaliacao()+" "+((Individuo)pop.get(2)).getAdaptabilidade()+" "+((Individuo)pop.get(2)).getAdaptabilidadeRelativa());
        System.out.println("QUARTO, AVALIACAO - > "+((Individuo)pop.get(3)).getAvaliacao()+" "+((Individuo)pop.get(3)).getAdaptabilidade()+" "+((Individuo)pop.get(3)).getAdaptabilidadeRelativa());
        System.out.println("QUINTO, AVALIACAO - > "+((Individuo)pop.get(4)).getAvaliacao()+" "+((Individuo)pop.get(4)).getAdaptabilidade()+" "+((Individuo)pop.get(4)).getAdaptabilidadeRelativa());

        System.out.println(((Individuo)genetico.roleta()).genes.get(0)+" "+((Individuo)genetico.roleta()).genes.get(1));

        System.out.println();

        Vector filhos = genetico.crossover((Individuo)pop.get(0),(Individuo)pop.get(3));
        System.out.println(((Individuo)filhos.get(0)).genes.get(0)+" "+((Individuo)filhos.get(0)).genes.get(1));
        System.out.println(((Individuo)filhos.get(1)).genes.get(0)+" "+((Individuo)filhos.get(1)).genes.get(1));
        System.out.println(((Individuo)filhos.get(2)).genes.get(0)+" "+((Individuo)filhos.get(2)).genes.get(1));
        System.out.println(((Individuo)filhos.get(3)).genes.get(0)+" "+((Individuo)filhos.get(3)).genes.get(1));

        Individuo temp = genetico.mutacao((Individuo)filhos.get(0), 2, 0.13);

        System.out.println();
        System.out.println(temp.genes.get(0)+" "+ temp.genes.get(1));

        System.out.println("AGORA CRIACAO DA NOVA POPULACAO");
        genetico.populacao.removeAllElements();
        genetico.populacao.addAll(filhos);
        genetico.populacao.add(temp);

        System.out.println("PRIMEIRO INDIVIDUO -> "+((Individuo)pop.get(0)).genes.get(0)+" "+((Individuo)pop.get(0)).genes.get(1));
        System.out.println("SEGUNDO INDIVIDUO - > "+((Individuo)pop.get(1)).genes.get(0)+" "+((Individuo)pop.get(1)).genes.get(1));
        System.out.println("TERCEIRO INDIVIDUO - > "+((Individuo)pop.get(2)).genes.get(0)+" "+((Individuo)pop.get(2)).genes.get(1));
        System.out.println("QUARTO INDIVIDUO - > "+((Individuo)pop.get(3)).genes.get(0)+" "+((Individuo)pop.get(3)).genes.get(1));
        System.out.println("QUINTO INDIVIDUO - > "+((Individuo)pop.get(4)).genes.get(0)+" "+((Individuo)pop.get(4)).genes.get(1));

        System.out.println("NOVAS AVALIACOES -----------------------------------------------");
        genetico.avaliacaoIndividuos();

        System.out.println("PRIMEIRO, AVALIACAO -> "+ ((Individuo)pop.get(0)).getAvaliacao()+" "+((Individuo)pop.get(0)).getAdaptabilidade()+" "+((Individuo)pop.get(0)).getAdaptabilidadeRelativa());
        System.out.println("SEGUNDO, AVALIACAO - > "+((Individuo)pop.get(1)).getAvaliacao()+" "+((Individuo)pop.get(1)).getAdaptabilidade()+" "+((Individuo)pop.get(1)).getAdaptabilidadeRelativa());
        System.out.println("TERCEIRO, AVALIACAO  - > "+((Individuo)pop.get(2)).getAvaliacao()+" "+((Individuo)pop.get(2)).getAdaptabilidade()+" "+((Individuo)pop.get(2)).getAdaptabilidadeRelativa());
        System.out.println("QUARTO, AVALIACAO - > "+((Individuo)pop.get(3)).getAvaliacao()+" "+((Individuo)pop.get(3)).getAdaptabilidade()+" "+((Individuo)pop.get(3)).getAdaptabilidadeRelativa());
        System.out.println("QUINTO, AVALIACAO - > "+((Individuo)pop.get(4)).getAvaliacao()+" "+((Individuo)pop.get(4)).getAdaptabilidade()+" "+((Individuo)pop.get(4)).getAdaptabilidadeRelativa());
*/
        Genetico genetico = new Genetico(200); //SE EU FOR MUDAR ISSO AQUI, TENHO QUE TOMAR CUIDADO LA NO METODO ALGORITMOGENETICO()
        Individuo individuo = new Individuo();
        individuo = genetico.algoritmoGenetico(200); //parametro numero de gerações
        System.out.println("GENES DO MELHOR INDIVIDUO -> " +individuo.genes.get(0)+" "+individuo.genes.get(1)+" "+individuo.genes.get(2));
    }

}
