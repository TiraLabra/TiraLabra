/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet.Jono;

/**
 *
 * @author Serafim
 */
public class Jono {

    private Jonoiteroitava ensimmainen;
    private Jonoiteroitava viimeinen;
    private int koko;

    public Jono() {
        this.koko = 0;
    }

    public int palautaKoko() {
        return this.koko;
    }

    public void lisaa(Object objekti) {
        lisaa(new Jonoiteroitava(objekti));
    }

    public void lisaa(Jonoiteroitava iter) {
        iter.asetaJonossa(true);
        if (this.viimeinen == null) {
            this.ensimmainen = iter;
            this.viimeinen = iter;
        } else {
            this.viimeinen.asetaSeuraava(iter);
            if (this.viimeinen != this.ensimmainen) {
                iter.asetaEdellinen(viimeinen);

            }
            this.viimeinen = iter;

        }
        this.koko++;

    }

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
                    Jonoiteroitava t2 = iter.palauataSeuraava();
                    ensimmainen = t2;
                    t2.asetaEdellinen(null);

                } else {
                    Jonoiteroitava t = iter.palauataEdellinen();
                    Jonoiteroitava t2 = iter.palauataSeuraava();
                    t2.asetaSeuraava(t);
                    t.asetaEdellinen(t2);

                }

            }

        }
        iter.clear();
        this.koko--;

    }

    public Jonoiteroitava palautaEnsimmainen() {
        return this.ensimmainen;
    }

    public Jonoiteroitava palautaViimeinen() {
        return this.viimeinen;
    }

}
