

import junit.framework.TestCase;
import yleismetodeja.Peruslasku;
import jama.*;
import yleismetodeja.Taulukko;
/**
 * 
 * @author risto
 */
public class YleismetodejaTest extends TestCase {
    
    double[][] matriisi = {{1,2,3},{4,5,6},{7,8,9}};
    double[][] matriisi2 = {{2,2,2},{2,2,2},{2,2,2}};
    double[][] identity = {{1,0,0},{0,1,0},{0,0,1}};
    double[][] isompimatriisi;
    
    
    public YleismetodejaTest(String testName) {
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
/*
    public void testGauss(){
        double[][] m1 = {{1,1,0},{3,7,8},{0,1,3}};
        double[][] g = Peruslasku.gauss(m1);
        double[][] ratkaisu = {{1,1,0},{0,4,8},{0,0,1}};
        System.out.println("Gauss: ");
        System.out.println(Taulukko.toString(g));
        assertTrue(Taulukko.toString(g).equals(Taulukko.toString(ratkaisu)));
    }
  
    

    public void testJordan() {
        double[][] m1 = {{1,1,0,1},{3,3,8,5},{0,0,3,6}};
        double[][] tulos = Peruslasku.jordan(Peruslasku.gauss(m1));
        System.out.println("Jordan:");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(false);
    
    }
    
    public void testGauss2(){
        double[][] m1 = {{1,1,0,1},{3,3,8,5},{0,0,3,6}};
        double[][] g = Peruslasku.gauss(m1);
        double[][] ratkaisu = {{1,1,0},{0,4,8},{0,0,1}};
        System.out.println("Gauss: ");
        System.out.println(Taulukko.toString(g));
        assertTrue(Taulukko.toString(g).equals(Taulukko.toString(ratkaisu)));
    }    
    */
    
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
    
    public void testToimiikoInverse() throws Exception {
        double[][] m1 = Taulukko.luoSatunnainenNelioMatriisi(10);
        double[][] tulos = Peruslasku.inv(m1);
        double[][] ratkaisu = new double[10][10];
        Taulukko.kirjoitaYkkosiaDiagonaalille(ratkaisu);
        double[][] tulos2 = Peruslasku.naivemultiply(tulos, m1);
        boolean testi = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (Math.abs(tulos2[i][j] - ratkaisu[i][j]) > 0.0001) {
                    testi = false;
                }
            }
        }
        assertTrue(testi);
    }
    
    public void testToimiikoGaussJordan() throws Exception {
        double[][] m1 = Taulukko.luoSatunnainenNelioMatriisi(10);
        double[][] ratkaisu = new double[10][10];
        Taulukko.kirjoitaYkkosiaDiagonaalille(ratkaisu);
        double[][] tulos = Peruslasku.gaussjordan(m1);
        System.out.print("GJ eliminoinnin tulos: \n" + Taulukko.toString(tulos));
        boolean testi = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                if (Math.abs(ratkaisu[i][j]-tulos[i][j]) > 0.00001) {
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
    
    public void testToimiikoAugmentKutenPitaa() {
        double[][] m1 = {{1,1},{1,1}};
        double[][] m2 = {{2,2},{2,2}};
        double[][] tulos = Taulukko.augment(m1, m2);
        System.out.println("Augmented matrix");
        System.out.print(Taulukko.toString(tulos));
        double[][] ratkaisu = {{1,1,2,2},{1,1,2,2}};
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
 
    public void testToimiikoRivienPoistamaninenVasemmalta() {
        double[][] m1 = {{1,1,2,2},{1,1,2,2}};
        double[][] tulos = Taulukko.poistaNSarakettaVasemmalta(m1,2);
        double[][] ratkaisu = {{2,2},{2,2}};
        assertTrue(Taulukko.toString(ratkaisu).equals(Taulukko.toString(tulos)));
    }
    
    public void testInverse() throws Exception {
        double[][] m1 = {{1,3},{2,4}};
        double[][] tulos = Peruslasku.inv(m1);
        double[][] ratkaisu = {{-2,1.5},{1,-0.5}};
        System.out.println("Kääntömatriisi");
        System.out.print(Taulukko.toString(tulos));
        System.out.println("Ratkaisu");
        System.out.print(Taulukko.toString(ratkaisu));
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
    
    
    
    public void testVaihdaRivit() {
        double[][] m1 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        Taulukko.vaihdaRivit(m1,0,2);
        double[][] ratkaisu = {{9,10,11,12},{5,6,7,8},{1,2,3,4}};
        assertTrue(Taulukko.toString(ratkaisu).equals(Taulukko.toString(m1)));
    }
    
    public void testAntaakoStrassenSamanTuloksenKuinJama() {
        laitaIsonmatriisinArvot();
        System.out.print(Taulukko.toString(isompimatriisi));

        double[][] tulos = Peruslasku.strassen(isompimatriisi,isompimatriisi,8,4);
        Matrix m1 = new Matrix(isompimatriisi);
        Matrix m2 = new Matrix(isompimatriisi);
        double[][] tulos2 = m1.times(m2).getArray();
        System.out.println(" \n Strassen  tulos:");
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
        double[][] tulos = Taulukko.yhdista(m1, m2, m3, m4, 4);
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testAugmentToimiiOsa2() {
        double[][] m1 = {{111,112,113},{121,122,123}};
        double[][] m2 = {{211,212,213},{221,222,223}};
        double[][] tulos = Taulukko.augment(m1, m2);
        double[][] ratkaisu = {{111,112,113,211,212,213},{121,122,123,221,222,223}};
        System.out.println("Augmentoinnin tulos:");
        System.out.print(Taulukko.toString(tulos));
        System.out.println("Oikea ratkaisu augmentointiin");
        System.out.print(Taulukko.toString(ratkaisu));
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testKirjoitaYkkosiaDiagonaalille() {
        double[][] m1 = new double[3][3];
        Taulukko.kirjoitaYkkosiaDiagonaalille(m1);
        double[][] ratkaisu = {{1,0,0},{0,1,0},{0,0,1}};
        assertTrue(Taulukko.toString(ratkaisu).equals(Taulukko.toString(m1)));
    }
    
    public void testKirjoitaOsamatriisiToimiiOikeinPisteessa00() {
        double[][] tulos = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j< 4; j++) {
                tulos[i][j] = 0;
            }
        }
        double[][] kirjoitettava = {{1,2},{3,4}};
        Taulukko.kirjoitaTaulukkoonOsataulukko(tulos, kirjoitettava, 0, 0);
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
        Taulukko.kirjoitaTaulukkoonOsataulukko(tulos, kirjoitettava, 0, 2);
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
        Taulukko.kirjoitaTaulukkoonOsataulukko(tulos, kirjoitettava, 2, 2);
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
        double[][] neljannes = Taulukko.ensimmainenNeljannes(testi);
        double[][] ratkaisu = {{1,2},{5,6}};
        assertTrue(Taulukko.toString(neljannes).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testToinenNeljannesOnOikein() {
        double[][] testi = luotestimatriisi();
        double[][] neljannes = Taulukko.toinenNeljannes(testi);
        double[][] ratkaisu = {{3,4},{7,8}};
        assertTrue(Taulukko.toString(neljannes).equals(Taulukko.toString(ratkaisu)));
    }
        
    public void testKolmasNeljannesOnOikein() {
        double[][] testi = luotestimatriisi();
        double[][] neljannes = Taulukko.kolmasNeljannes(testi);
        double[][] ratkaisu = {{9,10},{13,14}};
        System.out.println("\n kolmas neljannes:");
        System.out.print(Taulukko.toString(neljannes));
        assertTrue(Taulukko.toString(neljannes).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testNeljasNeljannesOnOikein() {
        double[][] testi = luotestimatriisi();
        double[][] neljannes = Taulukko.neljasNeljannes(testi);
        double[][] ratkaisu = {{11,12},{15,16}};
        assertTrue(Taulukko.toString(neljannes).equals(Taulukko.toString(ratkaisu)));
    }
    
    
    public void testYleistettyStrassenAntaaSamanTuloksenKuinNaiviKertolasku() {
        double[][] m1 = new double[7][7];
        int n = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++){
                m1[i][j] = n;
                n++;
            }
        }
        double[][] naivetulos = Peruslasku.naivemultiply(m1,m1);
        double[][] strassetulos = Peruslasku.yleinenStrassen(m1, m1, 7, 2);
        System.out.println("Strassen tulos");
        System.out.print(Taulukko.toString(strassetulos));
        System.out.println("Naivi tulos");
        System.out.print(Taulukko.toString(naivetulos));
        assertTrue(Taulukko.toString(naivetulos).equals(Taulukko.toString(strassetulos)));
        
    }
    
    public void testpoistaNUlointaRiviaToimiiOikein() {
        double[][] m1 = {{1,2,3},{4,5,6},{7,8,9}};
        double[][] ratkaisu = {{1,2},{4,5}};
        double[][] tulos = Taulukko.poistaUloimmatNrivia(m1, 1);
        System.out.println("Taulukko, josta on poistettu rivejä");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(Taulukko.toString(ratkaisu).equals(Taulukko.toString(tulos)));
    }
    
    public void testPoistaNUlointaRiviaToimiiHiemanIsommallaMatriisilla() {
        double[][] m1 = {{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}};
        double[][] ratkaisu = {{1,2},{1,2}};
        double[][] tulos = Taulukko.poistaUloimmatNrivia(m1, 3);
        System.out.println("Taulukko.josta on poistettu rivejä");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(Taulukko.toString(ratkaisu).equals(Taulukko.toString(tulos)));
    }
    
    public void testNollaRivienLisaaminenToimii() {
        double[][] m1 = {{1,2,3},{1,2,3},{1,2,3}};
        double[][] tulos = Taulukko.lisaaNollaRivejaHaluttuunKokoonSaakka(m1, 4, 3);
        double[][] ratkaisu = {{1,2,3,0},{1,2,3,0},{1,2,3,0},{0,0,0,0}};
        System.out.println("Nollariveilla täydennetty matriisi");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(ratkaisu)));
    }
    
    public void testDiagonaaliAlkioidenTuloOnOikein() {
        double[][] testi = {{2,0,0},{0,2,0},{0,0,2}};
        double tulos = Peruslasku.laskeDiagonaaliAlkioidenTulo(testi);
        assertEquals(tulos,(double)8);
    }
    
    
    public void testGaussJordanToimii() {
        double[][] testi = {{2,3},{1,9}};
        testi = Peruslasku.gaussjordan(testi);
        double[][] ratkaisu = {{1,0},{0,1}};
        assertTrue(Taulukko.toString(ratkaisu).equals(Taulukko.toString(testi)));
    }
    /*
    public void testGauss() {
        double[][] testi = {{1,2,7},{8,4,6},{1,24,8}};
        testi = Peruslasku.gaussjordan(testi);
        System.out.print(Taulukko.toString(testi));
        assertTrue(false);
    }*/
}
