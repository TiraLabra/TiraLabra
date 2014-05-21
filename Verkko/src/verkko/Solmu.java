
package verkko;

import java.util.HashMap;

/**
 * Verkon solmu joka ei sisällä mitään tietoa paitsi siihen liitettyjen muiden solmujen viitteet ja painot
 * @author Arvoitusmies
 */
public class Solmu {

    private HashMap<Solmu,Double> naapurit;
    
    /**
     * Alustaa hajautustaulun naapureille
     */
    public Solmu(){
        this(new HashMap<Solmu, Double>());
    }
    
    /**
     * Käyttää olemassaolevaa naapuri hajautustaulua sellaisenaan
     * @param naapurit
     */
    public Solmu(HashMap<Solmu, Double> naapurit){
        this.naapurit=naapurit;
    }
    
    /**
     * Lisää solmun painolla paino naapureihin.
	 * Huom: ei lisää tätä solmua solmun s naapureihin!
     * @param s lisättävä solmu
     * @param paino
     */
    public void lisaaNaapuri(Solmu s, Double paino){
        naapurit.put(s, paino);
    }
    
    /**
     * Kyselee onko annettu solmu naapuri
     * @param s
     * @return
     */
    public Boolean onkoNaapuri(Solmu s){
        return naapurit.containsKey(s);
    }
    
    /**
     * Palauttaa painon solmulle s. Ei tarkistele mitään.
     * @param s
     * @return
     */
    public Double paino(Solmu s){
        return naapurit.get(s);
    }
    
    /**
     * Palauttaa naapurit ja painot
     * @return
     */
    public HashMap<Solmu, Double> getNaapurit(){
        return naapurit;
    }
}
