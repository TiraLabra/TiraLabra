package tietorakenteet;

import java.util.ArrayList;

/**
 * ArrayListin toiminnallisuuden toteuttava oma versio.
 * 
 */
public class ArrayListOma {
    
    /**
     * Uuden luodun ilmentymän oletuskoko.
     */
    private static final int OLETUSKOKO = 10;
    
    /**
     * Taulukko, jonne varsinainen sisältö tallennetaan.
     */
    private Object[] sisalto;
    
    //Testikäyttöön, tulee poistumaan...
    private ArrayList koe;

    /**
     * Taulukon tämänhetkinen koko.
     * Ei kerro sisältävien elementtien lukumäärää.
     */
    private int taulukonKoko;
    
    /**
     * Kertoo kuinka monta yksikköä on oikeasti tallennettu tällä hetkellä.
     */
    private int elementteja;
    
    /**
     * Konstruktori, joka luo halutulla alkukoolla olevan taulukon.
     * @param koko 
     */
    public ArrayListOma(int koko) {
        sisalto = new Object[koko];
        this.taulukonKoko = koko;
        this.elementteja = 0;
    }
    
    /**
     * Kutsutaan konstruktoria oletuskoolla, jos muuta ei annettu.
     */
    public ArrayListOma() {
        this(OLETUSKOKO);
    }
    
    /**
     * Lisää halutun objectin Arraylistiin.
     * Tarvittaessa kasvattaa sisäitä taulukon kokoa.
     * @param o 
     */
    public void lisaa(Object o) {
        if (elementteja >= taulukonKoko)
            kasvataTaulukkoa();
        sisalto[elementteja] = o;
        elementteja++;
        
    }
    
    //TODO
    public void poista(Object o) {
        
    }
    
    /**
     * Kertoo, sisältääkö ArrayList kysyttyä objectia.
     * @param o
     * @return 
     */
    public boolean sisaltaako(Object o) {
        for (int i = 0; i<elementteja; i++) {
            if (sisalto[i] == o)
                return true;
        }
        return false;
    }

    /**
     * Sisäinen apumetodi, joka tuplaa taulukon koon.
     */
    private void kasvataTaulukkoa() {
        
        Object[] uusiTaulukko = new Object[2*taulukonKoko];
        for (int i = 0; i < elementteja; i++) {
            uusiTaulukko[i] = sisalto[i];
        }
        sisalto = uusiTaulukko;
        taulukonKoko = 2*taulukonKoko;
    }
    
    /**
     * Kertoo, kuinka monta itemiä tähän sisältyy.
     * @return 
     */
    public int koko() {
        return this.elementteja;
    }
    
    /**
     * Kertoo, mikä on tämänhetkinen taulukon sisäinen koko.
     * Ei ole tarkoitus varsinaisesti käytettäväksi, tehty lähinnä testejä
     * varten.
     * @return 
     */
    public int taulukonSisainenKoko() {
        return this.taulukonKoko;
    }
    
    
    
    
    
    

}
