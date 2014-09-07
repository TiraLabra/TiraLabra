/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import Tietorakenteet.Jono.Jono;
import Tietorakenteet.Jono.Jonoiteroitava;

/**
 *
 * Jatkuvamonikulmio on laajennos Naivimonikulmiosta
 */
public class Jatkuvamonikulmio extends Naivimonikulmio implements Monikulmio {

    private Jono Abstraktisolmut;

    public Jatkuvamonikulmio(Jono kordinaatit) {
        super(kordinaatit);
        luoAbstraktiSolmut(kordinaatit);

    }
    

    /**
     * Tekee kordinaateista AbstraktiSolmuja
     * @param kordinaatit Jono kordinaatteja
     */
    public void luoAbstraktiSolmut(Jono kordinaatit) {
        this.Abstraktisolmut = new Jono();
        Jonoiteroitava iteraattori = kordinaatit.palautaEnsimmainen();
        while (iteraattori != null) {
            Kordinaatti k = (Kordinaatti) iteraattori.palautaObjekti();
            JatkuvaSolmu lisattava = new JatkuvaSolmu(k);
            this.Abstraktisolmut.lisaa(lisattava);
            iteraattori = iteraattori.palautaSeuraava();
        }

    }
     /**
 *
 *  Palauttaa jatkuvasolmut
 */

    public Jono palautaJatkuvasolmut() {
        return this.Abstraktisolmut;

    }

}
