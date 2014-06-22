package Tietorakenteet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MinKekoTest {
    private MinKeko keko;
    private MinKeko keko2;
    
    @Before
    public void setUp() {
        this.keko = new MinKeko(10);
        this.keko2 = new MinKeko(7);
        
        keko2.lisaa(new Solmu("a", 7));
        keko2.lisaa(new Solmu("b", 8));
        keko2.lisaa(new Solmu("c", 5));
        keko2.lisaa(new Solmu("d", 4));
        keko2.lisaa(new Solmu("e", 7));
    }
    
    @Test
    public void kokoAlussa0() {
        assertEquals(0, keko.getKoko());
    }
    
    @Test
    public void testVanhempi() {
        assertEquals(2, keko.vanh(5));
        assertEquals(0, keko.vanh(2));
    }
    
    @Test
    public void testVasen() {
        assertEquals(7, keko.vasen(3));
    }
    
    @Test
    public void testOikea() {
        assertEquals(8, keko.oikea(3));
    }
    
    @Test
    public void kokoKasvaaKunSolmuLisataan() {
        keko.lisaa(new Solmu("a",0));
        assertEquals(1, keko.getKoko());
    }
    
    @Test
    public void ekaLisattavaSolmuMeneeKeonAlkuun() {
        keko.lisaa(new Solmu("a", 0));
        Solmu[] solmut = keko.getSolmut();
        assertEquals("a", solmut[0].getAvain());
    }
    
    @Test
    public void lisatytSolmutMenevatKekoonSitenEttaPieninEkana() {
        assertEquals("d", keko2.getSolmut()[0].getAvain());
    }
    
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void tyhjastaKeostaEiVoiPoistaaAlkiota() {
        MinKeko keko3 = new MinKeko(5);
        keko3.poistaHuippuSolmu();
    }
    
    @Test
    public void keonSolmuKokoTuplaantuuKunKekoonAsetetaanEnemmanSolmujaKuinMahtuisi() {
        MinKeko keko3 = luoKekoJossaSolmujenMaaraOnTuplattu();
        assertEquals(2, keko3.getSolmut().length);
    }
    
    @Test
    public void solmuKoonTuplaamisenJalkeenKeossaSailyvatSamatSolmutSamassaJarjestyksessa() {
        MinKeko keko3 = luoKekoJossaSolmujenMaaraOnTuplattu();
        assertEquals(1, keko3.getSolmut()[0].getEsiintymat());
        assertEquals(2, keko3.getSolmut()[1].getEsiintymat());
    }  
    
    private MinKeko luoKekoJossaSolmujenMaaraOnTuplattu() {
        MinKeko keko3 = new MinKeko(1);
        
        keko3.lisaa(new Solmu(1));
        keko3.lisaa(new Solmu(2));
        return keko3;
    }
    
    @Test
    public void lisattavanPaikanIndeksi() {
        Solmu solmu = new Solmu(3);
        assertEquals(0, keko.selvitaLisattavanPaikanIndeksi(0, solmu));
        keko.lisaa(solmu);
        
        Solmu solmu2 = new Solmu(5);
        assertEquals(1, keko.selvitaLisattavanPaikanIndeksi(1, solmu2));
        keko.lisaa(solmu2);
        
        Solmu solmu3 = new Solmu(1);
        assertEquals(0, keko.selvitaLisattavanPaikanIndeksi(2, solmu3));
        keko.lisaa(solmu3);
        
        Solmu solmu4 = new Solmu(1);
        assertEquals(1, keko.selvitaLisattavanPaikanIndeksi(3, solmu4));
    }
    
    @Test
    public void heapify() {
        MinKeko keko3 = new MinKeko(5);
        keko3.lisaa(new Solmu(2));
        
        kunVasenPienempiKuinKokoEiTehdaMitaan(keko3);
        kunVasenYhtaSuuriKuinKokoVaihdetaanJosTarve(keko3);
        kunOikeaVahintaanYhtaSuuriKuinKoko(keko3);
    }
    
    private void kunVasenPienempiKuinKokoEiTehdaMitaan(MinKeko keko3) {
        keko3.heapify(0);
        assertEquals(2, keko3.getSolmut()[0].getEsiintymat());
    }
    
    private void kunVasenYhtaSuuriKuinKokoVaihdetaanJosTarve(MinKeko keko3) {
        keko3.lisaa(new Solmu(3));
        keko3.heapify(0);
        assertEquals(2, keko3.getSolmut()[0].getEsiintymat());
        
        keko3.getSolmut()[0].setEsiintymat(5);
        keko3.heapify(0);
        assertEquals(3, keko3.getSolmut()[0].getEsiintymat());
    }
    
    private void kunOikeaVahintaanYhtaSuuriKuinKoko(MinKeko keko3) {
        keko3.lisaa(new Solmu(6));
        testaaPienempi(keko3, 0);
        
        keko3.heapify(0);
        assertEquals(3, keko3.getSolmut()[0].getEsiintymat());
        
        keko3.getSolmut()[0].setEsiintymat(10);
        
        keko3.heapify(0);
        assertEquals(5, keko3.getSolmut()[0].getEsiintymat());
        assertEquals(10, keko3.getSolmut()[1].getEsiintymat());
    }
    
    private void testaaPienempi(MinKeko keko3, int i) {
        assertEquals(1, keko3.pienempi(1,2));
        assertEquals(1, keko3.pienempi(2,1));
    }
}
