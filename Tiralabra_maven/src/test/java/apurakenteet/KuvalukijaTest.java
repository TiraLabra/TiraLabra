/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apurakenteet;

import junit.framework.TestCase;
import tietorakenteet.Node;

/**
 *
 * @author jpaxi
 */
public class KuvalukijaTest extends TestCase {
    
    public KuvalukijaTest(String testName) {
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
    
    public void testToimiikoKuvanLukeminen() {
        Kuvalukija kl = new Kuvalukija();
        assertEquals(true, kl.getKuva() != null);
    }
    
    public void testToimiikoAlueenMuodostus() {
        Kuvalukija kl = new Kuvalukija();
        Node[][] alue = kl.muodostaAlue();
        assertEquals(80, alue.length);
        assertEquals(false, alue[9][15].kuljettavissa());
    }
    
    public void testToimiikoKorkeusLeveys() {
        Kuvalukija kl = new Kuvalukija();
        assertEquals(80, kl.getKorkeus());
        assertEquals(80, kl.getLeveys());
    }
}
