/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package algoritmogenetico;

import java.util.Comparator;

/**
 *
 * @author Victor
 */
public class ComparadorDeIndividuos  implements Comparator<Individuo> {

    @Override
    public int compare(Individuo o1, Individuo o2) {
        if(o1.getAdaptabilidadeRelativa() == o2.getAdaptabilidadeRelativa()){
            return 0;
        }
        if(o1.getAdaptabilidadeRelativa() > o2.getAdaptabilidadeRelativa()){
            return 1;
        }
        else{
            return -1;
        }
    }


}
