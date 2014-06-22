
package hakualgoritmit;

import apurakenteet.Kuvalukija;
import heuristiikat.*;
import junit.framework.TestCase;
import tietorakenteet.Alue;
import tietorakenteet.ArrayListOma;
import tietorakenteet.Node;

/**
 *
 * @author jsopakar
 */
public class AStarTest extends TestCase {
    
    public AStarTest(String testName) {
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
    
    public void testKustannusarvio() {

        //16x16-testialue
        Alue alue = new Alue(16);
        alue.luoEsimerkkiTaulukko();
        
        AStar a = new AStar(new Manhattan());
        Node n1 = new Node(1, 1, 0);
        Node n2 = new Node(1, 2, 0);
        Node n3 = new Node(2, 1, 4);
        Node n4 = new Node(2, 2, 2);
        
        assertEquals(10, a.laskeKustannus(n1, n2));
        assertEquals(80, a.laskeKustannus(n1, n3));
        assertEquals(28, a.laskeKustannus(n1, n4));
    }
    
    public void testVirheellisenKustannus() {
        AStar as = new AStar(new Manhattan());
        Node alku = new Node(1,1,1);
        Node loppu = new Node (5,5,6);
        
        int kustannus = as.laskeKustannus(alku, loppu);
        
        assertEquals(1414, kustannus);
    }
    
    public void testPerushaku() {
        AStar as = new AStar(new Manhattan());
        Alue a = new Alue(16);
        a.luoEsimerkkiTaulukko();
        
        as.AStarHaku(a, a.getnode(0, 0), a.getnode(14, 14));
        
        ArrayListOma reitti = as.kerroKuljettuReitti();
        
        assertEquals(22, reitti.koko());
        
        Node loppunode = (Node) reitti.palautaKohdasta(reitti.koko()-1);
        assertEquals(a.getnode(14, 14), loppunode);
        
        assertEquals(68, as.getAskelia());
    }
    
    public void testEiKuljettavissaAlku() {
        AStar as = new AStar(new Manhattan());
        Alue a = new Alue(16);
        a.luoEsimerkkiTaulukko();
        
        boolean b = as.AStarHaku(a, a.getnode(1, 9), a.getnode(14, 14));
        assertEquals(false, b);
    }
    
    public void testEiKuljettavissaLoppu() {
        AStar as = new AStar(new Manhattan());
        Alue a = new Alue(16);
        a.luoEsimerkkiTaulukko();
        
        boolean b = as.AStarHaku(a, a.getnode(0, 0), a.getnode(6, 8));
        assertEquals(false, b);
    }
    
    public void testLoppuSaavuttamattomissa() {
        AStar as = new AStar(new Manhattan());
        Alue a = new Alue(16);
        a.luoEsimerkkiTaulukko();
        
        boolean b = as.AStarHaku(a, a.getnode(0, 0), a.getnode(7, 11));
        assertEquals(false, b);
    }
    
    public void testLoppuAlueenUlkopuolella() {
        AStar as = new AStar(new Manhattan());
        Alue a = new Alue(16);
        a.luoEsimerkkiTaulukko();
        
        Node n = new Node(19,19,0);
        boolean b = as.AStarHaku(a, a.getnode(0, 0), n);
        assertEquals(false, b);
        
    }
    
    public void test100x100() {
        AStar as = new AStar(new Manhattan());
        Kuvalukija kl = new Kuvalukija("100x100.bmp");
        Alue alue = new Alue(kl.muodostaAlue(), kl.getKorkeus(), kl.getLeveys());
        
        as.AStarHaku(alue, alue.getnode(0, 0), alue.getnode(88, 88));
        ArrayListOma reitti = as.kerroKuljettuReitti();
        
        assertEquals(117, reitti.koko());
        
    }
    
    public void testYhteenveto() {
        AStar as = new AStar(new Manhattan());
        Alue a = new Alue(16);
        a.luoEsimerkkiTaulukko();
        
        String yv = as.yhteenveto();
        assertEquals(91, yv.length());
    }
    
}
