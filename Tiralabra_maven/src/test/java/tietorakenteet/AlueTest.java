package tietorakenteet;

import apurakenteet.Kuvalukija;
import java.awt.image.BufferedImage;
import junit.framework.TestCase;

/**
 *
 */
public class AlueTest extends TestCase {
    
    public AlueTest(String testName) {
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

    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
    
    public void testEsimerkkiAlueenluonti() {
        Alue a = new Alue(16);
        a.luoEsimerkkiTaulukko();
        
        Node n = a.getnode(1, 9);
        
        assertEquals(9, n.getKustannus());
        
    }
    
    public void testPienenAlueenluonti() {
        Alue a = new Alue(8);
        a.luoPieniTestitaulukko();
        
        Node n = a.getnode(1, 3);
        
        assertEquals(9, n.getKustannus());
        
    }

    public void testToimiikoTaulukostaluonti() {
        Node[][] taulukko = new Node[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node n = new Node(i,j,0);
                taulukko[i][j] = n;
            }
        }
        Alue a = new Alue(taulukko, 10, 10);
        
        assertEquals(10, a.getKorkeus());
        assertEquals(10, a.getLeveys());
    }
    
    public void testToimiikoToString() {
        Alue a = new Alue(8);
        a.luoPieniTestitaulukko();
        a.getnode(0, 2).setOnReitilla(true);
        
        assertEquals(72, a.toString().length());
        assertEquals(true, a.toString().startsWith("00-0"));
        
    }
    
    public void testToimiikoKuvanAsetus() {
        Alue a = new Alue(8);
        a.luoPieniTestitaulukko();
        
        Kuvalukija kl = new Kuvalukija();
        kl.muodostaAlue();
        
        a.setAlueenKuva(kl.getKuva());
        BufferedImage kuva = a.getAlueenKuva();
        assertEquals(80, kuva.getHeight());
        assertEquals(80, kuva.getWidth());
        
    }
}
