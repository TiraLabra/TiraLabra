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
    
    /**
     * Poistaa annetun objektin.
     * @param o 
     */
    public void poista(Object o) {
        int sijainti = sijainti(o);
        if (sijainti >= 0) {
            for (int i = sijainti; i < elementteja-1; i++) {
                sisalto[i] = sisalto[i+1];
            }
            elementteja--;
        }
        
    }
    
    /**
     * Kertoo, sisältääkö ArrayList kysyttyä objectia.
     * @param o
     * @return 
     */
    public boolean sisaltaako(Object o) {
        if (sijainti(o) < 0)
            return false;
        else
            return true;
    }
    
    /**
     * Kertoo, mistä indeksistä löytyy haluttu objekti.
     * Jos ei löydy, palautuksena on -1.
     * @param o
     * @return 
     */
    public int sijainti(Object o) {
        for (int i = 0; i<elementteja; i++) {
            if (sisalto[i] == o)
                return i;
        }
        return -1;      // jos ei löytynyt
        
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
     * Palauttaa alkion halutusta taulukon kohdasta.
     * Tuloksena null, jos indeksi on taulukon rajojen ulkopuolella.
     * @param i
     * @return 
     */
    public Object palautaKohdasta(int i) {
        if (i < elementteja && i >= 0)
            return this.sisalto[i];
        else
            return null;
    }
    
    /**
     * Kertoo, mikä on tämänhetkinen taulukon sisäinen koko.
     * Ei ole tarkoitus varsinaisesti käytettäväksi, tehty lähinnä testejä
     * varten.
     * @return 
     */
    protected int taulukonSisainenKoko() {
        return this.taulukonKoko;
    }
    
}
