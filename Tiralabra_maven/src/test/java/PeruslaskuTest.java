

import junit.framework.TestCase;
import yleismetodeja.Peruslasku;
import jama.*;
import yleismetodeja.Taulukko;
/**
 * 
 * @author risto
 */
public class PeruslaskuTest extends TestCase {
    
    double[][] matriisi = {{1,2,3},{4,5,6},{7,8,9}};
    double[][] matriisi2 = {{2,2,2},{2,2,2},{2,2,2}};
    double[][] identity = {{1,0,0},{0,1,0},{0,0,1}};
    double[][] isompimatriisi;
    
    
    public PeruslaskuTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }
    
    private void laitaIsonmatriisinArvot() {
        this.isompimatriisi = new double[8][8];
        int luku = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                isompimatriisi[i][j] = luku;
                luku++;
            }
        }        
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testOnkoPlusLaskunTulosOikeinTavallisillaMatriiseilla() {
        boolean testi = true;
        double[][] tulos = Peruslasku.plus(matriisi, matriisi2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tulos[i][j] != matriisi[i][j]+matriisi2[i][j]) {
                    testi = false;
                }
            }
        }
        assertTrue(testi);
    }
    
    
    public void testAntaakoNaiiviKertominenSamanTuloksenKuinJama(){
        double[][] tulos = Peruslasku.naivemultiply(matriisi,matriisi2);
        Matrix m1 = new Matrix(matriisi);
        Matrix m2 = new Matrix(matriisi2);
        double[][] tulosjama = m1.times(m2).getArray();
        boolean testi = false;
        System.out.print(Taulukko.toString(tulos));
        System.out.print(Taulukko.toString(tulosjama));
        if(Taulukko.toString(tulos).equals(Taulukko.toString(tulosjama))) {
            testi = true;
        }
        assertTrue(testi);
    }
    
    public void testAntaakoNaiiviKertolaskuOikeanTuloksenIdentiteetille() {
        double[][] tulos = Peruslasku.naivemultiply(matriisi, identity);
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(matriisi)));
    }
    
    public void testLoytyykoSuuremmanLuvunLahinKahdenPotenssi() {
        System.out.println("Kahden potenssi: " + Peruslasku.etsiLahinSuurempiKahdenPotenssi(9));
        assertTrue(16==Peruslasku.etsiLahinSuurempiKahdenPotenssi(9));
    }
    
    public void testLoytyykoKahdenPotenssiLahinKahdenPotenssi() {
        assertTrue(8==Peruslasku.etsiLahinSuurempiKahdenPotenssi(8));
    }
    
    public void testAntaakoNaiiviKertolaskuOikeanTuloksen() {
        double[][] m1 = {{2,1},{5,7}};
        double[][] m2 = {{4,1},{2,3}};
        double[][] ratkaisu = {{10,5},{34,26}};
        double[][] tulos = Peruslasku.naivemultiply(m1,m2);
        Matrix m11 = new Matrix(m1);
        Matrix m22 = new Matrix(m2);
        double[][] jamatulos = m11.times(m22).getArray();
        System.out.println("Oman metodin tulos:");
        System.out.print(Taulukko.toString(tulos));
        System.out.println("");
        System.out.println("Käsin laskettu");
        System.out.print(Taulukko.toString(ratkaisu));
        System.out.println("");
        System.out.println("Jaman antama tulos");
        System.out.print(Taulukko.toString(jamatulos));
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
    
    
    
    public void testAntaakoStrassenSamanTuloksenKuinJama() {
        laitaIsonmatriisinArvot();
        System.out.print(Taulukko.toString(isompimatriisi));

        double[][] tulos = Peruslasku.strassen(isompimatriisi,isompimatriisi,8,2);
        Matrix m1 = new Matrix(isompimatriisi);
        Matrix m2 = new Matrix(isompimatriisi);
        double[][] tulos2 = m1.times(m2).getArray();
        boolean testi = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tulos2[i][j] != tulos[i][j]) {
                    testi = false;
                }
            }
        }
    }
    
    public void testTestaus() {
        assertTrue(true);
    }
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
}
