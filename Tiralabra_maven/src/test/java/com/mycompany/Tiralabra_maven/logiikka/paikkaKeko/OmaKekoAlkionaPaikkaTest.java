package com.mycompany.Tiralabra_maven.logiikka.paikkaKeko;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hannu
 */
public class OmaKekoAlkionaPaikkaTest {

    Paikka[][] paikkaTaulukko;
    OmaKekoAlkionaPaikka keko = new OmaKekoAlkionaPaikka();
    OmaKekoAlkionaPaikka isoKeko = new OmaKekoAlkionaPaikka();
//    Paikka paikkaP;

    public OmaKekoAlkionaPaikkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("OmaKekoAlkionaPaikkaTest.setUp");
        paikkaTaulukko = new Paikka[10][10];

//        paikkaP = new Paikka(0, 0, 0);

        for (int i = 0; i < paikkaTaulukko.length; i++) {
            for (int j = 0; j < paikkaTaulukko[0].length; j++) {
                paikkaTaulukko[i][j] = new Paikka(0, 0, 0);
                paikkaTaulukko[i][j].etaisyysAlkuun = i;
                paikkaTaulukko[i][j].etaisyysLoppuun = j;
            }
        }
        
        isoKeko.heapInsert(paikkaTaulukko[7][5]);
        isoKeko.heapInsert(paikkaTaulukko[2][7]);
        isoKeko.heapInsert(paikkaTaulukko[8][1]);
        isoKeko.heapInsert(paikkaTaulukko[0][9]);
        isoKeko.heapInsert(paikkaTaulukko[2][9]);
        isoKeko.heapInsert(paikkaTaulukko[4][2]);
        isoKeko.heapInsert(paikkaTaulukko[7][3]);
        isoKeko.heapInsert(paikkaTaulukko[2][4]);
        isoKeko.heapInsert(paikkaTaulukko[1][5]);
        isoKeko.heapInsert(paikkaTaulukko[2][8]);
        isoKeko.heapInsert(paikkaTaulukko[6][7]);
        isoKeko.heapInsert(paikkaTaulukko[9][6]);
//        isoKeko.heapInsert(paikkaTaulukko[7][5]);
        isoKeko.heapInsert(paikkaTaulukko[2][6]);
        isoKeko.heapInsert(paikkaTaulukko[3][2]);
        isoKeko.heapInsert(paikkaTaulukko[4][6]);
        isoKeko.heapInsert(paikkaTaulukko[5][7]);
        isoKeko.heapInsert(paikkaTaulukko[6][3]);
        isoKeko.heapInsert(paikkaTaulukko[7][2]);
        isoKeko.heapInsert(paikkaTaulukko[8][0]);
        isoKeko.heapInsert(paikkaTaulukko[9][8]);
        
        isoKeko.testTulostaKekoTaulukko();
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void tyhjaKekoOnTyhja() {
        assertEquals(true, keko.heapIsEmpty());
//                assertEquals(true, new PriorityQueue<Paikka>().isEmpty());
    }

    @Test
    public void tyhjanKeonKokoNolla() {
        assertEquals(0, keko.getHeapSize());
    }

    @Test
    public void lisaaYksiAlkioKeonKoko() {
        keko.heapInsert(paikkaTaulukko[3][2]);
        assertEquals(1, keko.getHeapSize());
    }

    @Test
    public void lisaaJaPoistaYksiAlkioKeonKoko() {
        keko.heapInsert(paikkaTaulukko[3][2]);
        Paikka pois = keko.heapDelMin();
        assertEquals(0, keko.getHeapSize());
    }

    @Test
    public void lisaaJaPoistaYksiAlkio1etaisyysAlkuun() {
        keko.heapInsert(paikkaTaulukko[3][2]);
        Paikka poistettavaPaikka = keko.heapDelMin();
        assertEquals(3, poistettavaPaikka.etaisyysAlkuun);
    }

    @Test
    public void lisaaJaPoistaYksiAlkio1etaisyysLoppuun() {
        keko.heapInsert(paikkaTaulukko[0][2]);
        Paikka poistettavaPaikka = keko.heapDelMin();
        assertEquals(2, poistettavaPaikka.etaisyysLoppuun);
    }

    @Test
    public void lisaaJaPoistaYksiAlkio2etaisyysAlkuun() {
        keko.heapInsert(paikkaTaulukko[0][0]);
        Paikka poistettavaPaikka = keko.heapDelMin();
        assertEquals(0, poistettavaPaikka.etaisyysAlkuun);
    }

    @Test
    public void lisaaJaPoistaYksiAlkio2etaisyysLoppuun() {
        keko.heapInsert(paikkaTaulukko[0][0]);
        Paikka poistettavaPaikka = keko.heapDelMin();
        assertEquals(0, poistettavaPaikka.etaisyysLoppuun);
    }

    @Test
    public void lisaa3SamaaAlkiotaKeonKoko() {
        keko.heapInsert(paikkaTaulukko[3][2]);
        keko.heapInsert(paikkaTaulukko[3][2]);
        keko.heapInsert(paikkaTaulukko[3][2]);
        assertEquals(3, keko.getHeapSize());
    }

    @Test
    public void lisaa4AlkiotaKeonKoko() {
        keko.heapInsert(paikkaTaulukko[3][2]);
        keko.heapInsert(paikkaTaulukko[0][2]);
        keko.heapInsert(paikkaTaulukko[3][0]);
        keko.heapInsert(paikkaTaulukko[9][9]);
        assertEquals(4, keko.getHeapSize());
    }

    @Test
    public void lisaa4AlkiotaJaPoista1Pienin02() {
        Paikka poistettavaPaikka;
        keko.heapInsert(paikkaTaulukko[3][2]);
        keko.heapInsert(paikkaTaulukko[0][2]);
        keko.heapInsert(paikkaTaulukko[3][0]);
        keko.heapInsert(paikkaTaulukko[9][9]);
        poistettavaPaikka = keko.heapDelMin();
        assertEquals(paikkaTaulukko[0][2], poistettavaPaikka);
    }

    @Test
    public void lisaa4AlkiotaJaPoista1Pienin02kahdesti() {
        Paikka poistettavaPaikka;
        keko.heapInsert(paikkaTaulukko[3][2]);
        keko.heapInsert(paikkaTaulukko[0][2]);
        keko.heapInsert(paikkaTaulukko[3][0]);
        keko.heapInsert(paikkaTaulukko[9][9]);
        keko.heapInsert(paikkaTaulukko[0][2]);
        poistettavaPaikka = keko.heapDelMin();
        assertEquals(paikkaTaulukko[0][2], poistettavaPaikka);
    }

    @Test
    public void lisaa4AlkiotaJaPoista1Pienin00() {
        Paikka poistettavaPaikka;
        keko.heapInsert(paikkaTaulukko[3][2]);
        keko.heapInsert(paikkaTaulukko[0][2]);
        keko.heapInsert(paikkaTaulukko[0][0]);
        keko.heapInsert(paikkaTaulukko[9][9]);
        poistettavaPaikka = keko.heapDelMin();
        assertEquals(paikkaTaulukko[0][0], poistettavaPaikka);
    }

    @Test
    public void lisaaJaPoistaAlkiotaJaTulosta() {
        System.out.println("-----------------------------------------");
        System.out.println("lisaaJaPoistaAlkiotaJaTulosta");
        keko.testTulostaKekoTaulukko();
        Paikka poistettavaPaikka;
        keko.heapInsert(paikkaTaulukko[3][2]);
        keko.heapInsert(paikkaTaulukko[0][2]);
        keko.heapInsert(paikkaTaulukko[3][0]);
        keko.heapInsert(paikkaTaulukko[9][0]);
        System.out.println("keon koko: " + keko.getHeapSize());
        keko.testTulostaKekoTaulukko();
        poistettavaPaikka = keko.heapDelMin();
        System.out.println("keon koko: " + keko.getHeapSize());
        keko.testTulostaKekoTaulukko();
        keko.heapInsert(paikkaTaulukko[1][8]);
        keko.heapInsert(paikkaTaulukko[7][2]);
        keko.heapInsert(paikkaTaulukko[5][4]);
        keko.heapInsert(paikkaTaulukko[3][7]);
        keko.heapInsert(paikkaTaulukko[0][9]);
        System.out.println("keon koko: " + keko.getHeapSize());
        keko.testTulostaKekoTaulukko();
        keko.testTulostaKeko();
        System.out.println("keon koko: " + keko.getHeapSize());
        assertEquals(paikkaTaulukko[0][2], poistettavaPaikka);
    }

    @Test
    public void isoKekoKoko() {
        assertEquals(20, isoKeko.getHeapSize());
    }

    @Test
    public void isoKekoDelMinKerran() {
        System.out.println("-----------------------------------------");
        System.out.println("isoKekoDelMinKerran");
        isoKeko.testTulostaKekoTaulukko();
        Paikka poistettavaPaikka;
        poistettavaPaikka = isoKeko.heapDelMin();
        System.out.println("isoKeko yhden heapDelMin jalkeen eli 3, 2 poistettu");
        isoKeko.testTulostaKekoTaulukko();
        isoKeko.testTulostaKeko();
        assertEquals(paikkaTaulukko[3][2], poistettavaPaikka);
    }

    @Test
    public void isoKekoDecKeyKahdesti() {
        System.out.println("-----------------------------------------");
        System.out.println("Test isoKekoDecKeyKahdesti");
        Paikka decKeyPaikka=paikkaTaulukko[6][7];
        System.out.println("decKeyPaikka heapIndex: "+decKeyPaikka.heapIndex);
        decKeyPaikka.etaisyysAlkuun = 2;
        decKeyPaikka.etaisyysLoppuun = 1;
        isoKeko.heapDecreaseKey(decKeyPaikka);
//        ei tee mitaan koska [1][5] ei muutettu
//        isoKeko.heapDecreaseKey(paikkaTaulukko[1][5]);
        isoKeko.testTulostaKekoTaulukko();
        Paikka poistettavaPaikka = isoKeko.heapDelMin();
        isoKeko.testTulostaKeko();
        assertEquals(2 + 1, poistettavaPaikka.etaisyysAlkuun + poistettavaPaikka.etaisyysLoppuun);
    }


////////    @Test
////////    public void toimiikoHeapify01() {
////////        System.out.println("-----------------------------------------");
////////        System.out.println("toimiikoHeapify01");
////////        isoKeko.testTulostaKekoTaulukko();
////////        Paikka muutettavaPaikka=paikkaTaulukko[1][5];
////////        muutettavaPaikka.etaisyysLoppuun=3;
////////        isoKeko.testHeapify(4);
////////        System.out.println("isoKeko yhden muutoksen jalkeen eli 1, 5 -> 1, 3");
////////        isoKeko.testTulostaKekoTaulukko();
////////        isoKeko.testTulostaKeko();
////////        assertEquals(1,muutettavaPaikka.heapIndex);
////////    }
    
//    kehityksen aikaista testausta tasta loppuun
//    
//    @Test
//    public void toimiikoParent2() {
//        int parent=keko.testParent(2);
//        assertEquals(parent,1);
//    }
//    
//    @Test
//    public void toimiikoParent3() {
//        int parent=keko.testParent(3);
//        assertEquals(parent,1);
//    }
//    
//    @Test
//    public void toimiikoParent4() {
//        int parent=keko.testParent(4);
//        assertEquals(parent,2);
//    }
//    
//    @Test
//    public void toimiikoParent5() {
//        int parent=keko.testParent(5);
//        assertEquals(parent,2);
//    }
//    
//    @Test
//    public void toimiikoParent6() {
//        int parent=keko.testParent(6);
//        assertEquals(parent,3);
//    }
    
    
    
    
    
    
    
    
    
    
    

}
