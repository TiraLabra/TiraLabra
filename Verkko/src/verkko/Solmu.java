
package verkko;

import java.util.HashMap;

/**
 *
 * @author Arvoitusmies
 */
public class Solmu {

    private HashMap<Solmu,Integer> naapurit;
    
    /**
     *
     */
    public Solmu(){
        this(new HashMap<>());
    }
    
    /**
     *
     * @param naapurit
     */
    public Solmu(HashMap<Solmu, Integer> naapurit){
        this.naapurit=naapurit;
    }
    
    /**
     *
     * @param s
     * @param paino
     */
    public void lisaaNaapuri(Solmu s, Integer paino){
        naapurit.put(s, paino);
    }
    
    /**
     *
     * @param s
     * @return
     */
    public Boolean onkoNaapuri(Solmu s){
        return naapurit.containsKey(s);
    }
    
    /**
     *
     * @param s
     * @return
     */
    public Integer paino(Solmu s){
        return naapurit.get(s);
    }
    
    /**
     *
     * @return
     */
    public HashMap<Solmu, Integer> getNaapurit(){
        return naapurit;
    }
}
