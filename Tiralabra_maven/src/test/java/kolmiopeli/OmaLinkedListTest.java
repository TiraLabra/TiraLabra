
package kolmiopeli;

import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet.OmaLinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eemi
 */
public class OmaLinkedListTest {
    private OmaLinkedList lista;
    private Koordinaatti testiK1;
    private Koordinaatti testiK2;
    
    public OmaLinkedListTest() {
    }
    
    @Before
    public void setUp() {
        this.lista = new OmaLinkedList();
        this.testiK1 = new Koordinaatti(1, 1);
        this.testiK2 = new Koordinaatti(2, 2);
    }
   
    @Test
    public void lisaaAlkuunPoistaAlusta() {
        lista.addFirst(testiK1);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeFirst();
        assertTrue("Alkuun lisatty ei palautunut listan alusta", testiK1.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaLoppuunPoistaLopusta() {
        lista.addLast(testiK1);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeLast();
        assertTrue("Loppuun lisatty ei palautunut listan lopusta", testiK1.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaAlkuunPoistaLopusta() {
        lista.addFirst(testiK1);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeLast();
        assertTrue("Ainoa alkuun lisatty alkio ei palautunut listan lopusta", testiK1.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaLoppuunPoistaAlusta() {
        lista.addLast(testiK1);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeFirst();
        assertTrue("Ainoa loppuun lisatty alkio ei palautunut listan alusta", testiK1.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaKaksiAlkuunPoistaYksiAlusta() {
        lista.addFirst(testiK1);
        lista.addFirst(testiK2);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeFirst();
        assertTrue("Lisattiin kaksi alkiota alkuun, ei palauttanut viimeisimpana eteen lisattya listan alusta", testiK2.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaKaksiAlkuunPoistaYksiLopusta() {
        lista.addFirst(testiK1);
        lista.addFirst(testiK2);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeLast();
        assertTrue("Lisattiin kaksi alkiota alkuun, ei palauttanut ensimmaisena eteen lisattya listan lopusta", testiK1.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaKaksiLoppuunPoistaYksiLopusta() {
        lista.addLast(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeLast();
        assertTrue("Lisattiin kaksi alkiota loppuun, ei palauttanut viimeisimpana loppuun lisattya listan lopusta", testiK2.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaKaksiLoppuunPoistaYksiAlusta() {
        lista.addLast(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeFirst();
        assertTrue("Lisattiin kaksi alkiota loppuun, ei palauttanut ensimmaisena loppuun lisattya listan alusta", testiK1.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaYksiAlkuunJaLoppuunPoistaYksiAlusta() {
        lista.addFirst(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeFirst();
        assertTrue("Lisattiin alkiot alkuun ja loppuun, ei palauttanut eteen lisattya listan edesta", testiK1.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaAlkuunJaLoppuunPoistaYksiLopusta() {
        lista.addFirst(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama = (Koordinaatti) lista.removeLast();
        assertTrue("Lisattiin alkiot alkuun ja loppuun, ei palauttanut loppuun lisattya listan lopusta", testiK2.equals(listanPalauttama));
    }
   
    @Test
    public void lisaaKaksiAlkuunPoistaKaksiAlusta() {
        lista.addFirst(testiK1);
        lista.addFirst(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeFirst();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeFirst();
        assertTrue("Listan eteen viimeisimpana lisatty ja edesta ensimmaisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama1));
        assertTrue("Listan eteen ensimmaisena lisatty ja edesta viimeisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama2));
    }
   
    @Test
    public void lisaaKaksiAlkuunPoistaKaksiLopusta() {
        lista.addFirst(testiK1);
        lista.addFirst(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeLast();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeLast();
        assertTrue("Listan eteen viimeisimpana lisatty ja lopusta viimeisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama2));
        assertTrue("Listan eteen ensimmaisena lisatty ja lopusta ensimmaisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama1));
    }
   
    @Test
    public void lisaaKaksiAlkuunPoistaYksiAlustaJaLopusta() {
        lista.addFirst(testiK1);
        lista.addFirst(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeFirst();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeLast();
        assertTrue("Listan eteen viimeisimpana lisatty ja edesta ensimmaisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama1));
        assertTrue("Listan eteen ensimmaisena lisatty ja lopusta viimeisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama2));
    }
   
   
    @Test
    public void lisaaKaksiLoppuunPoistaKaksiAlusta() {
        lista.addLast(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeFirst();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeFirst();
        assertTrue("Listan loppuun viimeisimpana lisatty ja edesta viimeisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama2));
        assertTrue("Listan loppuun ensimmaisena lisatty ja edesta ensimmaisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama1));
    }
   
    @Test
    public void lisaaKaksiLoppuunPoistaKaksiLopusta() {
        lista.addLast(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeLast();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeLast();
        assertTrue("Listan loppuun viimeisimpana lisatty ja lopusta ensimmaisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama1));
        assertTrue("Listan loppuun ensimmaisena lisatty ja lopusta viimeisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama2));
    }
   
    @Test
    public void lisaaKaksiLoppuunPoistaYksiAlustaJaLopusta() {
        lista.addLast(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeFirst();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeLast();
        assertTrue("Listan loppuun viimeisimpana lisatty ja lopusta viimeisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama2));
        assertTrue("Listan loppuun ensimmaisena lisatty ja edesta ensimmaisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama1));
    }
   
   
    @Test
    public void lisaaYksiAlkuunJaLoppuunPoistaKaksiAlusta() {
        lista.addFirst(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeFirst();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeFirst();
        assertTrue("Listan eteen ensimmaisena lisatty ja edesta ensimmaisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama1));
        assertTrue("Listan loppuun viimeisena lisatty ja edesta viimeisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama2));
    }
   
    @Test
    public void lisaaYksiAlkuunJaLoppuunPoistaKaksiLopusta() {
        lista.addFirst(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeLast();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeLast();
        assertTrue("Listan eteen ensimmaisena lisatty ja lopusta viimeisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama2));
        assertTrue("Listan loppuun viimeisena lisatty ja lopusta ensimmaisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama1));
    }
   
    @Test
    public void lisaaYksiAlkuunJaLoppuunPoistaYksiAlustaJaLopusta() {
        lista.addFirst(testiK1);
        lista.addLast(testiK2);
        Koordinaatti listanPalauttama1 = (Koordinaatti) lista.removeFirst();
        Koordinaatti listanPalauttama2 = (Koordinaatti) lista.removeLast();
        assertTrue("Listan eteen ensimmaisena lisatty ja edesta ensimmaisena poistettu ei palautunut oikein", testiK1.equals(listanPalauttama1));
        assertTrue("Listan loppuun viimeisena lisatty ja lopusta viimeisena poistettu ei palautunut oikein", testiK2.equals(listanPalauttama2));
    }
   
    
    @Test
    public void testaaKoonMuutoksia() {
        assertTrue("Listan koko ei aluksi ollut 0", lista.size()==0);
        lista.addFirst(testiK1);
        assertTrue("Listan koko ei yhden lisayksen jalkeen ollut 1", lista.size()==1);
        lista.addFirst(testiK2);
        assertTrue("Listan koko ei kahden lisayksen jalkeen ollut 2", lista.size()==2);
        lista.removeFirst();
        assertTrue("Listan koko ei kahden lisayksen ja yhden poiston jalkeen ollut 1", lista.size()==1);
        lista.removeFirst();
        assertTrue("Listan koko ei kahden lisayksen ja kahden poiston jalkeen ollut 0", lista.size()==0);
        
    }

    
    
    
    
    
}
