
import junit.framework.TestCase;
import yleismetodeja.Taulukko;
import omamatriisipaketti.*;
import yleismetodeja.Peruslasku;
/**
 *
 * @author risto
 */
public class omamatriisipakettiTest extends TestCase {
    private LUPdecomposition lu = new LUPdecomposition();
    private double[][] matriisi1 = {{1,5,8},{12,5,2},{1,2,19}};
    private double[][] matriisi2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
    private double[][] matriisi3 = {{2,2,2,2},{5,2,2,2},{5,5,2,2},{5,5,5,2}};
    
    
    public omamatriisipakettiTest(String testName) {
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

    
    public void testLUPdekompositio() throws Exception {
        LUPdecomposition lu = new LUPdecomposition(matriisi1);
        double[][] l = lu.getL();
        double[][] u = lu.getU();
        double[][] p = lu.getPermutationArray();
        double[][] pa = Peruslasku.naivemultiply(p, matriisi1);
        double[][] lxu = Peruslasku.naivemultiply(l, u);
        System.out.println("PA");
        System.out.print(Taulukko.toString(pa));
        System.out.println("LU");
        System.out.println(Taulukko.toString(lxu));
        boolean testi = true;
        int m = lxu.length;
        int n = lxu[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.abs(lxu[i][j] - pa[i][j]) > 0.001)
                    testi = false;
            }
        }
        assertTrue(testi);
    }
    
    public void testKirjoitaLower() {
        double[][] lower = LUPdecomposition.kirjoitaLower(matriisi3);
        double[][] ratkaisu= {{1,0,0,0},{5,1,0,0},{5,5,1,0},{5,5,5,1}};
        System.out.println("Lower matriisi");
        System.out.print(Taulukko.toString(lower));
        assertTrue(Taulukko.toString(lower).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testKirjoitaUpper() {
        double[][] upper = LUPdecomposition.kirjoitaUpper(matriisi3);
        double[][] ratkaisu = {{2,2,2,2},{0,2,2,2},{0,0,2,2},{0,0,0,2}};
        System.out.println("Uppermatriisi");
        System.out.print(Taulukko.toString(upper));
        assertTrue( Taulukko.toString(ratkaisu).equals( Taulukko.toString(upper) ) );
    }
    
    public void testGetP() {
        int[] p = {0,1,2,3};
        lu.setP(p);
        double[][] tulos = lu.getPermutationArray();
        double[][] ratkaisu = {{1,0,0,0},{0,1,0,0},{0,0,1,0},{0,0,0,1}};
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testDet() throws Exception {
        double[][] m1 = {{1,2,3},{5,9,12},{11,11,19}};
        LUPdecomposition lu = new LUPdecomposition(m1);
        assertTrue(lu.det()+19 < 0.000001);
    }
    
}
