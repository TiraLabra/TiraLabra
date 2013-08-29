/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OhjelmaLogiikka;

import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import java.util.Comparator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Omistaja
 */
public class SorttaajaTest {

    public SorttaajaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaSorttaaIntegeritOikein() {
        OmaList<Integer> lukuja = new OmaArrayList<Integer>();
        lukuja.add(5);
        lukuja.add(4);
        lukuja.add(1);
        lukuja.add(3);
        lukuja.add(2);
        lukuja.add(6);
        lukuja.add(10);
        lukuja.add(7);
        lukuja.add(9);
        lukuja.add(8);

        Sorttaaja sorttaaja = new Sorttaaja();
        lukuja = sorttaaja.sorttaaKasvavaanJarjestykseen(lukuja, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i = 0; i < 10; ++i) {
            assertEquals("Väärä järjestys", new Integer(i + 1), lukuja.get(i));
        }

    }

    // teknisesti komparaattorin & metodin väärinkäyttöä mutta pitäisi toimia
    @Test
    public void testaaSorttaaIntegeritOikeinToisinPain() {
        OmaList<Integer> lukuja = new OmaArrayList<Integer>();
        lukuja.add(5);
        lukuja.add(4);
        lukuja.add(1);
        lukuja.add(3);
        lukuja.add(2);
        lukuja.add(6);
        lukuja.add(10);
        lukuja.add(7);
        lukuja.add(9);
        lukuja.add(8);

        Sorttaaja sorttaaja = new Sorttaaja();
        lukuja = sorttaaja.sorttaaKasvavaanJarjestykseen(lukuja, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < 10; ++i) {
            assertEquals("Väärä järjestys", new Integer(10 - i), lukuja.get(i));
        }

    }

    @Test
    public void testaaSorttaaDoubletOikein() {
        OmaList<Double> lukuja = new OmaArrayList<Double>();
        lukuja.add(5.3);
        lukuja.add(4.12);
        lukuja.add(25.9);
        lukuja.add(2349.0);
        lukuja.add(1.0);
        lukuja.add(-4.23);
        lukuja.add(3.1415926435);
        lukuja.add(123.0);
        lukuja.add(91.213);
        lukuja.add(8.11111111);

        Sorttaaja sorttaaja = new Sorttaaja();
        lukuja = sorttaaja.sorttaaKasvavaanJarjestykseen(lukuja, new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if (o1 < o2) {
                    return -1;
                }
                if (o1 > o2) {
                    return 1;
                }

                return 0;
            }
        });
        Double arvo = -(Double.MAX_VALUE);

        for (int i = 0; i < 10; ++i) {
            assertTrue("Väärä järjestys", arvo <= lukuja.get(i));
            arvo = lukuja.get(i);
        }

    }

    @Test
    public void testaaSorttaaStringOikein() {
        OmaList<String> tekstia = new OmaArrayList<String>();
        tekstia.add("Iglu");
        tekstia.add("puu");
        tekstia.add("AAA");
        tekstia.add("Xyzzy");
        tekstia.add("foobar");
        
        Sorttaaja sorttaaja = new Sorttaaja();
        tekstia = sorttaaja.sorttaaKasvavaanJarjestykseen(tekstia, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        });
        
        assertEquals("Väärä järjestys", "AAA", tekstia.get(0));
        assertEquals("Väärä järjestys", "foobar", tekstia.get(1));
        assertEquals("Väärä järjestys", "Iglu", tekstia.get(2));
        assertEquals("Väärä järjestys", "puu", tekstia.get(3));
        assertEquals("Väärä järjestys", "Xyzzy", tekstia.get(4));
    }
}
