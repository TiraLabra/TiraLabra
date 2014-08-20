

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

        double[][] tulos = Peruslasku.strassen(isompimatriisi,isompimatriisi,8,4);
        Matrix m1 = new Matrix(isompimatriisi);
        Matrix m2 = new Matrix(isompimatriisi);
        double[][] tulos2 = m1.times(m2).getArray();
        System.out.println(" \n Strassen tulos:");
        System.out.print(Taulukko.toString(tulos));
        System.out.println("\n Jaman tulos");
        System.out.print(Taulukko.toString(tulos2));
        boolean testi = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tulos2[i][j] != tulos[i][j]) {
                    testi = false;
                }
            }
        }
        assertTrue(testi);
    }

    public void testAntaakoStrassen2SamanTuloksenKuinJama() {
        laitaIsonmatriisinArvot();
        System.out.print(Taulukko.toString(isompimatriisi));

        double[][] tulos = Peruslasku.strassen2(isompimatriisi,isompimatriisi,8,4);
        Matrix m1 = new Matrix(isompimatriisi);
        Matrix m2 = new Matrix(isompimatriisi);
        double[][] tulos2 = m1.times(m2).getArray();
        System.out.println(" \n Strassen2 tulos:");
        System.out.print(Taulukko.toString(tulos));
        System.out.println("\n Jaman tulos");
        System.out.print(Taulukko.toString(tulos2));
        boolean testi = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tulos2[i][j] != tulos[i][j]) {
                    testi = false;
                }
            }
        }
        assertTrue(testi);
    }    

    public void testAntaakoStrassen3SamanTuloksenKuinJama() {
        laitaIsonmatriisinArvot();
        System.out.print(Taulukko.toString(isompimatriisi));

        double[][] tulos = Peruslasku.strassen3(isompimatriisi,isompimatriisi,8,4);
        Matrix m1 = new Matrix(isompimatriisi);
        Matrix m2 = new Matrix(isompimatriisi);
        double[][] tulos2 = m1.times(m2).getArray();
        System.out.println(" \n Strassen3 tulos:");
        System.out.print(Taulukko.toString(tulos));
        System.out.println("\n Jaman tulos");
        System.out.print(Taulukko.toString(tulos2));
        boolean testi = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tulos2[i][j] != tulos[i][j]) {
                    testi = false;
                }
            }
        }
        assertTrue(testi);
    }      
    
 
    public void testAntaakouusiStrassenSamanTuloksenKuinJama() {
        laitaIsonmatriisinArvot();
        System.out.print(Taulukko.toString(isompimatriisi));

        double[][] tulos = Peruslasku.uusiStrassen(isompimatriisi,isompimatriisi,8,4);
        Matrix m1 = new Matrix(isompimatriisi);
        Matrix m2 = new Matrix(isompimatriisi);
        double[][] tulos2 = m1.times(m2).getArray();
        System.out.println(" \n uuden strassen  tulos:");
        System.out.print(Taulukko.toString(tulos));
        System.out.println("\n Jaman tulos");
        System.out.print(Taulukko.toString(tulos2));
        boolean testi = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tulos2[i][j] != tulos[i][j]) {
                    testi = false;
                }
            }
        }
        assertTrue(testi);
    } 
    
    public void testMinusToimiiOikein() {
        double[][] m1 = {{1,2},{3,4}};
        double[][] m2 = {{1,1},{0,4}};
        double[][] ratkaisu = {{0,1},{3,0}};
        double[][] tulos = Peruslasku.minus(m1,m2);
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }

    public void testPlusToimiiOikein() {
        double[][] m1 = {{1,2},{3,4}};
        double[][] m2 = {{1,1},{0,4}};
        double[][] ratkaisu = {{2,3},{3,8}};
        double[][] tulos = Peruslasku.plus(m1,m2);
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
    
    
    
    public void testMatriisienYhdistaminenToimiiOikein() {
        double[][] ratkaisu = {{1,1,2,2},{1,1,2,2},{3,3,4,4},{3,3,4,4}};
        double[][] m1 = {{1,1},{1,1}};
        double[][] m2 = {{2,2},{2,2}};
        double[][] m3 = {{3,3},{3,3}};
        double[][] m4 = {{4,4},{4,4}};
        double[][] tulos = Peruslasku.yhdista(m1, m2, m3, m4, 4);
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testKirjoitaOsamatriisiToimiiOikeinPisteessa00() {
        double[][] tulos = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j< 4; j++) {
                tulos[i][j] = 0;
            }
        }
        double[][] kirjoitettava = {{1,2},{3,4}};
        Peruslasku.kirjoitaTaulukkoonOsataulukko(tulos, kirjoitettava, 0, 0);
        double[][] ratkaisu = {{1,2,0,0},{3,4,0,0},{0,0,0,0},{0,0,0,0}};
        System.out.println("\n osamatriisin kirjoitustulos");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));

    }

    public void testKirjoitaOsamatriisiToimiiOikeinPisteessa02() {
        double[][] tulos = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j< 4; j++) {
                tulos[i][j] = 0;
            }
        }
        double[][] kirjoitettava = {{1,2},{3,4}};
        Peruslasku.kirjoitaTaulukkoonOsataulukko(tulos, kirjoitettava, 0, 2);
        double[][] ratkaisu = {{0,0,1,2},{0,0,3,4},{0,0,0,0},{0,0,0,0}};
        System.out.println("\n osamatriisin kirjoitustulos");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));

    }
    
        public void testKirjoitaOsamatriisiToimiiOikeinPisteessaOikeaAla() {
        double[][] tulos = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j< 4; j++) {
                tulos[i][j] = 0;
            }
        }
        double[][] kirjoitettava = {{1,2},{3,4}};
        Peruslasku.kirjoitaTaulukkoonOsataulukko(tulos, kirjoitettava, 2, 2);
        double[][] ratkaisu = {{0,0,0,0},{0,0,0,0},{0,0,1,2},{0,0,3,4}};
        System.out.println("\n osamatriisin kirjoitustulos");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));

    }
     
    private double[][] luotestimatriisi(){
        double[][] palautettava = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        return palautettava;
    }    
    public void testEnsimmainenNeljannesOnOikein() {
        double[][] testi = luotestimatriisi();
        double[][] neljannes = Peruslasku.ensimmainenNeljannes(testi);
        double[][] ratkaisu = {{1,2},{5,6}};
        assertTrue(Taulukko.toString(neljannes).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testToinenNeljannesOnOikein() {
        double[][] testi = luotestimatriisi();
        double[][] neljannes = Peruslasku.toinenNeljannes(testi);
        double[][] ratkaisu = {{3,4},{7,8}};
        assertTrue(Taulukko.toString(neljannes).equals(Taulukko.toString(ratkaisu)));
    }
        
    public void testKolmasNeljannesOnOikein() {
        double[][] testi = luotestimatriisi();
        double[][] neljannes = Peruslasku.kolmasNeljannes(testi);
        double[][] ratkaisu = {{9,10},{13,14}};
        System.out.println("\n kolmas neljannes:");
        System.out.print(Taulukko.toString(neljannes));
        assertTrue(Taulukko.toString(neljannes).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testNeljasNeljannesOnOikein() {
        double[][] testi = luotestimatriisi();
        double[][] neljannes = Peruslasku.neljasNeljannes(testi);
        double[][] ratkaisu = {{11,12},{15,16}};
        assertTrue(Taulukko.toString(neljannes).equals(Taulukko.toString(ratkaisu)));
    }
    
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
}
