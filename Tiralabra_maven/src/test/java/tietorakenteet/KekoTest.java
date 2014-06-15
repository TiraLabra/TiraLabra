/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tietorakenteet;

import junit.framework.TestCase;

/**
 *
 * @author jpaxi
 */
public class KekoTest extends TestCase {
    
    public KekoTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    Node n0 = new Node(0,0,1);
    Node n1 = new Node(1,0,1);
    Node n2 = new Node(2,0,1);
    Node n3 = new Node(3,0,1);
    Node n4 = new Node(4,0,1);
    Node n5 = new Node(5,0,1);
    
    Node [] nodeTaulukko = { n0, n1, n2, n3, n4, n5 };
    
    private void alustuksia() {
        nodeTaulukko[0].setEtaisyysMaaliin(1);
        nodeTaulukko[1].setEtaisyysMaaliin(3);
        nodeTaulukko[2].setEtaisyysMaaliin(5);
        nodeTaulukko[3].setEtaisyysMaaliin(7);
        nodeTaulukko[4].setEtaisyysMaaliin(9);
        nodeTaulukko[5].setEtaisyysMaaliin(15);
    }
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    
    public void testVanhempi() {
        Keko k = new Keko();
        assertEquals(0, k.vanhempi(1));
        assertEquals(-1, k.vanhempi(0));
        assertEquals(1, k.vanhempi(2));
        assertEquals(1, k.vanhempi(3));
        assertEquals(2, k.vanhempi(4));
        assertEquals(4, k.vanhempi(9));
        
    }
    
    public void testLuontiTaulukosta() {
        alustuksia();
        Keko k = new Keko(nodeTaulukko);
        
        Comparable pienin = k.kerroPienin();
        
        assertEquals(n0, pienin);
        assertEquals(false, k.onTyhja());
        assertEquals(6, k.koko());
        assertEquals(true, onkoKelvollinenKeko(k));
        
    }
    
    public void testMontaPoistoa() {
        alustuksia();
        Keko k = new Keko(nodeTaulukko);
        
        Comparable c = k.poistaPienin();
        assertEquals(true, onkoKelvollinenKeko(k));
        assertEquals(5, k.koko());
        assertEquals(n0, c);
        
        c = k.poistaPienin();
        assertEquals(true, onkoKelvollinenKeko(k));
        assertEquals(4, k.koko());
        assertEquals(n1, c);
        
        c = k.poistaPienin();
        assertEquals(true, onkoKelvollinenKeko(k));
        assertEquals(3, k.koko());
        assertEquals(n2, c);

        c = k.poistaPienin();
        assertEquals(true, onkoKelvollinenKeko(k));
        assertEquals(2, k.koko());
        assertEquals(n3, c);

        
    }
    
    public void testLisaysValiin() {
        alustuksia();
        Keko k = new Keko(nodeTaulukko);
        
        Node n = new Node(8,8,1);
        n.setEtaisyysMaaliin(2);
        k.lisaa(n);
        
        assertEquals(7, k.koko());
    }
    
    public void testToimiikoTyhjanTestaus() {
        alustuksia();
        
        //tyhjä:
        Keko tyhjaKeko = new Keko();
        assertEquals(true, tyhjaKeko.onTyhja());
        
        //ei-tyhjä:
        Keko eityhjaKeko = new Keko(nodeTaulukko);
        assertEquals(false, eityhjaKeko.onTyhja());
        
    }
    
    public void testTulostaaJotain() {
        alustuksia();
        Keko k = new Keko(nodeTaulukko);
        
        String t = k.toString();
        assertEquals(true, t.length()>0);
    }
    
    
    /**
     * Apumetodi testaamiseen, onko keko kelvollinen.
     * @param k
     * @return 
     */
    private boolean onkoKelvollinenKeko(Keko k) {
        boolean ok = true;
        
        for (int i = 0; i < k.koko(); i++) {
            int vasen = k.vasenLapsi(i);
            int oikea = k.oikeaLapsi(i);
            if ( vasen > 0 && k.sisalto()[i].compareTo(k.sisalto()[vasen]) > 0 )
                return false;
            if ( oikea > 0 && k.sisalto()[i].compareTo(k.sisalto()[oikea]) > 0 )
                return false;
        }
        
        return ok;
    }
}
