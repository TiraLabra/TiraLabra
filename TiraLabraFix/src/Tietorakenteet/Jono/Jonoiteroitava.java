/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet.Jono;


/**
 *
 * Jonoiteroiava on Jonon iteroitava 
 */
public class Jonoiteroitava {

    private Jonoiteroitava edellinen;
    private Jonoiteroitava seuraava;
    private boolean jonossa;
    private Object objekti;
    
    /**
 *
 * Alustaa Jonoiteroitavan
     * @param objektir objekti
 */

    public Jonoiteroitava(Object objektir) {
        this.objekti = objektir;
    }
    
     /**
 *
 * Tarkistaa onko kyseinen jonoiteroitava jonossa
     * @return boolean palauattaa true jos jonossa  
 */

    public boolean onkoJonossa() {
        return jonossa;
    }
    

    /**
     * asettaa onkojonossa
     * @param k boolean
     */
    public void asetaJonossa(boolean k) {
        this.jonossa = k;
    }
   

    /**
     * Palautata seuraavan
     * @return Jonoiteroitava
     */
    public Jonoiteroitava palautaSeuraava() {
        return this.seuraava;
    }
    /**
     * Palautata edellsien
     * @return Jonoiteroitava
     */

    public Jonoiteroitava palauataEdellinen() {
        return this.edellinen;
    }
    /**
     * Asettaa seuraavan
     */

    public void asetaSeuraava(Jonoiteroitava seuraava) {
        this.seuraava = seuraava;
    }
      /**
     * Asettaa edellisen
     */

    public void asetaEdellinen(Jonoiteroitava edellinen) {
        this.edellinen = edellinen;
    }

     /**
     * Tyhjentää
     */
    public void clear() {
        this.edellinen = null;
        this.seuraava = null;
        this.jonossa = false;
        
    }
    
    
     /**
     * asetaa objektin
     */
    public void asetaObjekti(Object objektir)
    {
    this.objekti = objektir;
    }
      /**
     * palauttaa objektin
     */
    public Object palautaObjekti()
    {
    return this.objekti;
    }

}
