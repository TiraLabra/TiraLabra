
package com.mycompany.tiralabra_maven.tietorakenteet;

import java.util.EmptyStackException;

/**
 * Linkitetty tietorakenne, joka tarjoaa saman perustoiminnallisuuden kuin
 * Javan <b>Stack</b>. Perustoiminnallisuus tarkoittaa tässä operaatioita
 * <tt>empty</tt>, <tt>push</tt>, <tt>peek</tt>, <tt>pop</tt> ja <tt>size</tt>.
 * (Tässä tietorakenteessa operaatioiden nimet ovat <tt>onTyhja</tt>,
 * <tt>lisaa</tt>, <tt>kurkista</tt>, <tt>poista</tt>, <tt>koko</tt>.)
 * 
 * @author John Lång
 * @param <T> Pinoon säilöttävän tietoalkion tyyppi.
 */
public final class Pino<T> {

    private Solmu<T> ylin;
    private int korkeus;
    
    public Pino() {
        this.korkeus = 0;
    }
    
    public boolean onTyhja() {
        return korkeus == 0;
    }
    
    /**
     * Vastaa Javan <b>Stack</b>-tietorakenteen samannimistä operaatiota sillä 
     * erotuksella ettei <b>Pino</b>n <tt>push</tt>-metodilla ole paluuarvoa.
     * @param value Pinoon lisättävä alkio.
     */
    public void lisaa(T value) {
        Solmu<T> uusiSolmu = new Solmu<T>(value);
        uusiSolmu.seuraaja = ylin;
        ylin = uusiSolmu;
        korkeus++;
    }
    
    public T kurkista() throws EmptyStackException {
        if (ylin == null) {
            throw new EmptyStackException();
        }
        return ylin.ARVO;
    }
    
    public T poista() throws EmptyStackException {
        if (ylin == null) {
            throw new EmptyStackException();
        }
        T paluuarvo = ylin.ARVO;
        ylin = ylin.seuraaja;
        korkeus--;
        return paluuarvo;
    }
    
    public int koko() {
        return korkeus;
    }
    
}
