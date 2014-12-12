package com.mycompany.tira;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListaSolmuTest {
    
    private ListaSolmu l;
    
    public ListaSolmuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.l = new ListaSolmu(new Kasipari(1,0), null);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getKasipariToimii() {
        assertEquals(new Kasipari(1,0).getKoneenKasi(), this.l.getKasipari().getKoneenKasi());
        assertEquals(new Kasipari(1,0).getPelaajanKasi(), this.l.getKasipari().getPelaajanKasi());
    }
    
    @Test
    public void getSeuraavaToimiiKunNull() {
        assertEquals(null, this.l.getSeuraavaListaSolmu());
    }
    
    @Test
    public void setSeuraavaToimii() {
        ListaSolmu s = new ListaSolmu(new Kasipari(1,1), null);
        this.l.setSeuraavaListaSolmu(s);
        assertEquals(s.getKasipari().getPelaajanKasi(), this.l.getSeuraavaListaSolmu().getKasipari().getPelaajanKasi());
        assertEquals(s.getKasipari().getKoneenKasi(), this.l.getSeuraavaListaSolmu().getKasipari().getKoneenKasi());
    }

}
