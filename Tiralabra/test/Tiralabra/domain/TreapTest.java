package Tiralabra.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
        } else {
            lapsi = p.getJuuri().getVasen();
        }

        assertEquals(true, p.getJuuri().getPrioriteetti() > lapsi.getPrioriteetti());
        assertEquals("10\n11\n", p.tulostaArvot());
    }

    @Test
    public void delete() {
    }

    @Test
    public void search() {
    }

    @Test
    public void tulosta() {
        assertEquals("10\n", p.tulostaArvot());
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
        } else if (lapsi.getOikea() != null){
            lapsenlapsi = lapsi.getOikea();
        }
        
        assertEquals(true, p.getJuuri().getPrioriteetti() > lapsi.getPrioriteetti());
        
        if (lapsenlapsi != null) {
            assertEquals(true, lapsi.getPrioriteetti() > lapsenlapsi.getPrioriteetti());
        }
        
        assertEquals("1\n3\n8\n10\n109\n", p.tulostaArvot());
    }
}
