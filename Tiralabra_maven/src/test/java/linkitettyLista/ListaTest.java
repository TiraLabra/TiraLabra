package linkitettyLista;


import junit.framework.TestCase;

/**
 *
 * @author Ilkimys
 */
public class ListaTest extends TestCase {
    
     Lista<Object> l;
    
    public ListaTest() {
    }
    
    @Override
    protected void setUp() throws Exception {
        l = new Lista<Object>();
    }
    
    @Override
    protected void tearDown() throws Exception {
       
    }

    /**
     * Test of lisaaListaan method, of class Lista.
     */
    public void testLisaaListaan() {
        Object o = null;
        l.lisaaListaan(o);
        assertEquals(l.getListanKoko(), 1);
       
    }

    /**
     * Test of getListanKoko method, of class Lista.
     */
    public void testGetListanKoko() {
        Object o = null;
        l.lisaaListaan(o);
        assertEquals(l.getListanKoko(), 1);
    }

    /**
     * Test of iterator method, of class Lista.
     */
    public void testIterator() {
          Object o = null;
        l.lisaaListaan(o);
        for (Object object : l) {
            System.out.println("jee");
        }
    }
}
