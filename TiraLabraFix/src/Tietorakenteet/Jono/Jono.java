/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet.Jono;

/**
 *
 * Jono luokka
 */
public class Jono {

    private Jonoiteroitava ensimmainen;
    private Jonoiteroitava viimeinen;
    private int koko;

    
    /**
 *
 * Konstruoi uuden jonon ja laittaa sen kooksi 0
 */
    public Jono() {
        this.koko = 0;
    }
  /**
 *
 *Palauttaa koon
 */
    public int palautaKoko() {
        return this.koko;
    }
    
      /**
 *
 * Lisää objektin jonoon
     * @param objekti mikä tahansa objekti
 */

    public void lisaa(Object objekti) {
        lisaa(new Jonoiteroitava(objekti));
    }
    
      /**
 *
 * Lisää Jonoiteroitva alkion jonoon
     * @param iter Jonoiteroitava alkio
 */

    public void lisaa(Jonoiteroitava iter) {
        iter.asetaJonossa(true);
        if (this.viimeinen == null) {
            this.ensimmainen = iter;
            this.viimeinen = iter;
        } else {
            this.viimeinen.asetaSeuraava(iter);

            iter.asetaEdellinen(viimeinen);

            this.viimeinen = iter;

        }
        this.koko++;

    }
    
      /**
 *
 * Poistaa Jonoiteroitava alkion
 */

    public void poista(Jonoiteroitava iter) {
        if (iter.onkoJonossa() == false) {
            return;
        }
        iter.asetaJonossa(false);
        if ((iter == ensimmainen) && (iter == viimeinen)) {
            ensimmainen = null;
            viimeinen = null;
        } else {
            if (iter == viimeinen) {
                Jonoiteroitava t = iter.palauataEdellinen();
                viimeinen = t;
                t.asetaSeuraava(null);

            } else {
                if (iter == ensimmainen) {
                    Jonoiteroitava t2 = iter.palautaSeuraava();
                    ensimmainen = t2;
                    t2.asetaEdellinen(null);

                } else {
                    Jonoiteroitava t = iter.palauataEdellinen();
                    Jonoiteroitava t2 = iter.palautaSeuraava();
                    t2.asetaSeuraava(t);
                    t.asetaEdellinen(t2);

                }

            }

        }
        iter.clear();
        this.koko--;

    }
    
     /**
 *
 * Palauttaa ensimmaisen
     * @return Jonoiteroitava ensimmainen
 */

    public Jonoiteroitava palautaEnsimmainen() {
        return this.ensimmainen;
    }
 /**
 *
 * Palauttaa viimeisen
     * @return Jonoiteroitava viimeinen
 */
    public Jonoiteroitava palautaViimeinen() {
        return this.viimeinen;
    }
    
     /**
 *
 * Palauttaa n:n
 */

    public Jonoiteroitava palautaNs(int n) {
        if (n > this.koko) {
            return null;
        } else {
            Jonoiteroitava iteroitava = this.ensimmainen;
            for (int i = 1; i < n; i++) {
                iteroitava = iteroitava.palautaSeuraava();
            }
            return iteroitava;
        }
    }
    
     /**
 *
 * Etsii Objektin n
     * @param n
     * @return palauttaa true jos se on jonossa, muuten falsen
 */

    public boolean etsi(Object n) {
        Jonoiteroitava iter = this.ensimmainen;
        while (iter != null) {
            if (iter.palautaObjekti().equals(n)) {
                return true;
            }
            iter = iter.palautaSeuraava();
        }
        return false;
    }

}
