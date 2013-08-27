package Tiralabra.domain;

import Tiralabra.util.ALista;
import Tiralabra.util.Listasolmu;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TreapTest {

    Treap p;

    public TreapTest() {
    }

    @Before
    public void setUp() {
        p = new Treap(10);
    }

    @Test
    public void insert() {
        p.insert(11);

        SolmuTreap lapsi;
        if (p.getJuuri().getOikea() != null) {
            lapsi = p.getJuuri().getOikea();
            assertTrue(p.getJuuri().getArvo() < p.getJuuri().getOikea().getArvo());
        } else {
            lapsi = p.getJuuri().getVasen();
            assertTrue(p.getJuuri().getArvo() > p.getJuuri().getVasen().getArvo());
        }

        assertEquals(true, p.getJuuri().getPrioriteetti() > lapsi.getPrioriteetti());
        assertEquals("10\n11\n", p.tulostaArvot());
    }

    @Test
    public void delete() {
        p.delete(10);
        assertTrue(p.getJuuri() == null);
    }

    @Test
    public void deleteUsea() {
        p.insert(89);
        p.insert(3);
        p.insert(7);

        p.delete(10);
        assertFalse(p.search(10));
        if (p.getJuuri().getOikea() != null) {
            assertTrue(p.getJuuri().getArvo() < p.getJuuri().getOikea().getArvo());
            assertTrue(p.getJuuri().getPrioriteetti() > p.getJuuri().getOikea().getPrioriteetti());
        } else if (p.getJuuri().getVasen() != null) {
            assertTrue(p.getJuuri().getArvo() > p.getJuuri().getVasen().getArvo());
            assertTrue(p.getJuuri().getPrioriteetti() > p.getJuuri().getVasen().getPrioriteetti());
        }

        p.delete(3);
        assertFalse(p.search(3));
        p.delete(89);
        assertFalse(p.search(89));
        p.delete(7);
        assertFalse(p.search(7));
        assertTrue(p.getJuuri() == null);
    }

    @Test
    public void search() {
        assertFalse(p.search(1119));
        assertTrue(p.search(10));
    }

    @Test
    public void searchUsea() {
        p.insert(1);
        p.insert(786);
        p.insert(67);
        assertFalse(p.search(777));
        assertTrue(p.search(1));
        assertTrue(p.search(786));
        assertTrue(p.search(67));
    }

    @Test
    public void tulosta() {
        assertEquals("10\n", p.tulostaArvot());
    }

    @Test
    public void tulostaUsea() {
        p.insert(6);
        p.insert(15);
        assertEquals("6\n10\n15\n", p.tulostaArvot());
    }

    @Test
    public void insertTyhjaan() {
        p = new Treap();
        p.insert(56);
        assertEquals(56, p.getJuuri().getArvo());
    }

    @Test
    public void insertUseampi() {
        p.insert(1);
        p.insert(8);
        p.insert(109);
        p.insert(3);

        SolmuTreap lapsi;
        if (p.getJuuri().getOikea() != null) {
            lapsi = p.getJuuri().getOikea();
        } else {
            lapsi = p.getJuuri().getVasen();
        }

        SolmuTreap lapsenlapsi = null;
        if (lapsi.getVasen() != null) {
            lapsenlapsi = lapsi.getVasen();
        } else if (lapsi.getOikea() != null) {
            lapsenlapsi = lapsi.getOikea();
        }

        assertEquals(true, p.getJuuri().getPrioriteetti() > lapsi.getPrioriteetti());

        if (lapsenlapsi != null) {
            assertEquals(true, lapsi.getPrioriteetti() > lapsenlapsi.getPrioriteetti());
        }

        assertEquals("1\n3\n8\n10\n109\n", p.tulostaArvot());
    }

    private ALista lisaaArvoja() {
        Random r = new Random();
        ALista l = new ALista(10);
        int x;
        for (int i = 0; i < 99; i++) {
            x = r.nextInt();
            if(!p.search(x)){
                l.lisaa(x);
            }
            p.insert(x);
        }
        return l;
    }

    @Test
    public void insertSailyttaaPuunArvojarjestyksen() {
        ALista l = lisaaArvoja();
        Listasolmu ls = l.getLista();
        for (int i = 0; i < l.getKoko(); i++) {
            testaaArvot(p.searchSolmu(ls.getArvo()));
            ls = ls.getNext();
        }
    }
    
    @Test
    public void insertSailyttaaPrioriteetin(){
        ALista l = lisaaArvoja();
        Listasolmu ls = l.getLista();
        for (int i = 0; i < l.getKoko(); i++) {
            testaaPrioriteetti(p.searchSolmu(ls.getArvo()));
            ls = ls.getNext();
        }
    }
    
    @Test
    public void deleteSailyttaaArvot(){
        ALista l = lisaaArvoja();
        Listasolmu ls = l.getLista();
        for (int i = 0; i < l.getKoko()/2; i++) {
            p.delete(i);
            ls = ls.getNext();
        }
        
        for (int i = l.getKoko()/ 2; i < l.getKoko(); i++) {
            testaaArvot(p.searchSolmu(ls.getArvo()));
            ls = ls.getNext();
        }
    }
    
    @Test
    public void deleteSailyttaaPrioriteetin(){
        ALista l = lisaaArvoja();
        Listasolmu ls = l.getLista();
        for (int i = 0; i < l.getKoko()/2; i++) {
            p.delete(i);
            ls = ls.getNext();
        }
        
        for (int i = l.getKoko()/ 2; i < l.getKoko(); i++) {
            testaaPrioriteetti(p.searchSolmu(ls.getArvo()));
            ls = ls.getNext();
        }
    }

    private void testaaArvot(SolmuTreap s) {
        if (s == null) {
            return;
        }
        if (s.getVanhempi() != null) {
            if (s.getArvo() < s.getVanhempi().getArvo()) {
                assertTrue(s.getVanhempi().getVasen() == s);
            } else {
                assertTrue(s.getArvo() > s.getVanhempi().getArvo());
            }
        }
        
        if (s.getOikea() != null) {
            assertTrue(s.getOikea().getArvo() > s.getArvo());
        }
        
        if (s.getVasen() != null) {
            assertTrue(s.getVasen().getArvo() < s.getArvo());
        }
    }
    
    private void testaaPrioriteetti(SolmuTreap s){
        if (s == null) {
            return;
        }
        if (s.getVanhempi() != null) {
            assertTrue(s.getPrioriteetti() < s.getVanhempi().getPrioriteetti());
        }
        
        if (s.getOikea() != null) {
            assertTrue(s.getOikea().getPrioriteetti() < s.getPrioriteetti());
        }
        
        if (s.getVasen() != null) {
            assertTrue(s.getVasen().getPrioriteetti() < s.getPrioriteetti());
        }
    }
}