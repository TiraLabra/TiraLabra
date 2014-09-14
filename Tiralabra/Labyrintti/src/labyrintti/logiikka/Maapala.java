/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logiikka;

/**
 * Tämä luokka edustaa maapaloja, jotka luovat labyrintin.
 * Maapaloilla on omat koordinaatit, jotka kertovat missä kohdassa
 * labyrinttia ne sijaitsevat.
 * Maapaloilla on myös heuristiset arvot, jotka kertovat kuinka monen
 * askelman päässä ne ovat labyrintin "uloskäynnistä."
 * 
 * @author Mikael Parvamo
 */
public class Maapala {
    private int x;
    private int y;
    private int hValue;
    private boolean seina;
    
    public Maapala(int x, int y, int hValue){
        this.x = x;
        this.y = y;
        this.hValue = hValue;
        this.seina = false;
    }
    
    /**
     * Tämä metodi palauttaa maapalan heuristisen arvon eli arvon, joka
     * kertoo kuinka kaukana kyseinen maapala on maalista.
     * 
     * @return this.hValue, joka on maapalan heuristinen arvo
     */
    
    public int getHValue(){
        return this.hValue;
    }
    
    /**
     * Tämä metodi palauttaa maapalan X-koordinaatin, joka kertoo missä 
     * maapala on labyrintin X-akselilla.
     * 
     * @return this.x, joka on maapalan X-koordinaatti.
     */
    
    public int getX(){
        return this.x;
    }
    
     /**
     * Tämä metodi palauttaa maapalan Y-koordinaatin, joka kertoo missä 
     * maapala on labyrintin Y-akselilla.
     * 
     * @return this.y, joka on maapalan Y-koordinaatti.
     */
    
    public int getY(){
        return this.y;
    }
    
     /**
     * Tämän metodin tarkoituksena on asettaa maapalalle heuristinen arvo
     * mikäli se muuttuu.
     * 
     * @param int hValue, joka on maapalalle asetettava uusi arvo. 
     */
    
    public void setHValue(int hValue){
        this.hValue = hValue;
    }
    
     /**
     * Tämän metodin tehtävänä on palauttaa boolean arvo (true/false)
     * riippuen siitä, onko maapala seinä eli lävitse pääsemätön, vai ei.
     * 
     * @return this.seina, joka on boolean arvo.
     */
    
    public boolean onkoSeina(){
        return this.seina;
    }
    
     /**
     * Tämä metodi asettaa maapalan this.seina- arvoksi true. Eli maapalasta
     * tulee käytännössä läpäisemätön.
     */
    
    public void asetaSeinaksi(){
        this.seina = true;
    }
    
     /**
     * Tämä metodi asettaa maapalan takaisin normaaliin tilaan eli poistaa sen
     * läpäisemättömyyden.
     */
    
    public void asetaNormaaliksi(){
        this.seina = false;
    }
    
     /**
     * Tämä metodi palauttaa maapalan tulostusmuodon, joka on maapalan
     * heuristinen arvo.
     * 
     * @return "" + this.hValue, eli String johon on liitetty maapalan heuristinen arvo
     */
    
    @Override
    public String toString(){
        return "" + this.hValue;
    }
}
