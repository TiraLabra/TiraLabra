
package tietorakenteet;

import junit.framework.TestCase;

/**
 *
 * @author jsopakar
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
}
