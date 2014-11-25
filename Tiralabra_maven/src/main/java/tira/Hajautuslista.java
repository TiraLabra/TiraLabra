/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

import java.util.Iterator;

/**
 * Hajautuslistalla on samoja toiminnallisuuksia kuin javan HashSetillä.
 * Toteutustapana oma Hajautustaulu. Ideana on avainten nopea tallennus ja contains-
 * ja remove-toiminta.
 * 
 * @author E
 * @param <K> Hajautettavien avainten tyyppi
 */
public class Hajautuslista <K> {
    /**
     * Käyttää hajautustaulua avainten tallennukseen. 
     * Avain-arvo -parina K K jotta get-metodi toimii;
     * muutoin voisi olla vaikka Object tai null
     */
    private Hajautustaulu<K, K> hajautustaulu;
    
    ///////////////////
    // KONSTRUKTORIT //
    ///////////////////
    
    public Hajautuslista() {
        hajautustaulu = new Hajautustaulu();
    }
    public Hajautuslista( int aloitusKoko ) { 
        hajautustaulu = new Hajautustaulu( aloitusKoko );
    }
    
    //////////////////////
    // JULKISET METODIT //
    //////////////////////
    /**
     * Lisätään hajautustauluun
     * 
     * @param k lisättävä avain
     * @return Vanha vastaava avain tai null
     */
    public K add( K k ) {
        return hajautustaulu.put(k, k);
    }
    
    /**
     * Haetaan hajautustaulusta avainta vastaava avain
     * 
     * @param k Poistettava avain
     * @return Avain tai null
     */
    public K get( K k ) {
        return hajautustaulu.get(k);
    }    
    /**
     * Sisältääkö hajautustaulu avaimen
     * 
     * @param k
     * @return 
     */
    public boolean contains( K k ) {
        return hajautustaulu.contains(k);
    }
    /**
     * Poistetaan hajautustaulusta avainta vastaava avain
     * 
     * @param k Poistettava avain
     * @return  Poistettu avain
     */
    public K remove( K k ) {
        return hajautustaulu.remove(k);
    }
    
    /**
     * 
     * @return Avaimet listassa
     */
    public Lista<K> keySet() {
        return this.hajautustaulu.keySet();
    }
    /**
     * Tehdään hajautustaulun debug-printti
     * 
     * @return 
     */
    public String debugPrint() {
        return this.hajautustaulu.debugPrint();
    }
    /**
     * Avainten iterointiin
     * 
     * @return Keysetin iteraattori
     */
    public Iterator<K> iterator() {
        return this.keySet().iterator();
    }
}
