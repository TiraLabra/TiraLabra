/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.logiikka;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.HeuristiikkaTyyppi;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.Toiminto;
import com.mycompany.tiralabra_maven.gui.Paivitettava;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.Solmu;
import fileio.KuvanLukija;
import java.io.File;
import static junit.framework.Assert.*;
import org.junit.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author mikko
 */
public class SovellusOhjainTest {

    private SovellusOhjain sovellusOhjain;
    private Simulaatio sim;
    private KuvanLukija kuvanLukija;
    private Ruutu[][] ruudukko = {
        {Ruutu.LATTIA, Ruutu.LATTIA, Ruutu.LATTIA},
        {Ruutu.LATTIA, Ruutu.HIEKKA, Ruutu.LATTIA},
        {Ruutu.HIEKKA, Ruutu.HIEKKA, Ruutu.SEINA},
        {Ruutu.LATTIA, Ruutu.LATTIA, Ruutu.RUOHO}};

    @Before
    public void setUp() {
        sim = mock(Simulaatio.class);
        kuvanLukija = mock(KuvanLukija.class);
        this.sovellusOhjain = new SovellusOhjain(sim, kuvanLukija);
    }

    @Test
    public void alussaHiiriEiOleRuudunPaalla() {
        assertNull(sovellusOhjain.hiirenKoordinaatit());
    }

    @Test
    public void hiiriRuudunPaallaToimii() {
        sovellusOhjain.hiiriRuudunPaalla(2, 3);
        assertEquals(new Koordinaatit(2, 3), sovellusOhjain.hiirenKoordinaatit());
    }

    @Test
    public void hiirenPoistuessaRuudukostaSillaEiEnaaOleKoordinaatteja() {
        sovellusOhjain.hiiriRuudunPaalla(4, 2);
        sovellusOhjain.hiiriPoistunut();
        assertNull(sovellusOhjain.hiirenKoordinaatit());
    }

    @Test
    public void hiiriPainettuToimii() {
        sovellusOhjain.hiiriRuudunPaalla(3, 3);
        sovellusOhjain.hiiriPainettu(true);
        assertTrue(sovellusOhjain.onkoHiiriPainettu());
    }

    @Test
    public void valittuToimintoOnAlussaSeina() {
        assertEquals(Toiminto.SEINA, sovellusOhjain.getValittuToiminto());
    }

    @Test
    public void valitunToiminnanMuuttaminenVaihtaaToimintoa() {
        sovellusOhjain.setToiminto(Toiminto.LATTIA);
        assertEquals(Toiminto.LATTIA, sovellusOhjain.getValittuToiminto());
    }

    @Test
    public void seinanPiirtaminenHiirellaToimii() {
        sovellusOhjain.setToiminto(Toiminto.SEINA);
        ruutuTyypinPiirtaminenHiirellaToimii(Ruutu.SEINA);
    }

    @Test
    public void ruohonPiirtaminenHiirellaToimii() {
        sovellusOhjain.setToiminto(Toiminto.RUOHO);
        ruutuTyypinPiirtaminenHiirellaToimii(Ruutu.RUOHO);
    }

    @Test
    public void hiekanPiirtaminenHiirellaToimii() {
        sovellusOhjain.setToiminto(Toiminto.HIEKKA);
        ruutuTyypinPiirtaminenHiirellaToimii(Ruutu.HIEKKA);
    }

    @Test
    public void vedenPiirtaminenHiirellaToimii() {
        sovellusOhjain.setToiminto(Toiminto.VESI);
        ruutuTyypinPiirtaminenHiirellaToimii(Ruutu.VESI);
    }

    @Test
    public void lattianPiirtaminenHiirellaVedenPaalleToimii() {
        //piirretaan ensin vettä, lattiaa vasta päälle
        sovellusOhjain.setToiminto(Toiminto.VESI);
        ruutuTyypinPiirtaminenHiirellaToimii(Ruutu.VESI);
        sovellusOhjain.setToiminto(Toiminto.LATTIA);
        ruutuTyypinPiirtaminenHiirellaToimii(Ruutu.LATTIA);
    }

    private void ruutuTyypinPiirtaminenHiirellaToimii(Ruutu haluttuRuutu) {
        sovellusOhjain.hiiriRuudunPaalla(2, 2);
        sovellusOhjain.hiiriPainettu(true);
        verify(sim).setRuutu(2, 2, haluttuRuutu);
        sovellusOhjain.hiiriRuudunPaalla(2, 3);
        verify(sim).setRuutu(2, 3, haluttuRuutu);
        sovellusOhjain.hiiriRuudunPaalla(2, 4);
        verify(sim).setRuutu(2, 4, haluttuRuutu);
        sovellusOhjain.hiiriPainettu(false);
    }

    @Test
    public void aloitusPaikanRaahaaminenHiirellaToimii() {
        sovellusOhjain.setToiminto(Toiminto.ALKU);
        sovellusOhjain.hiiriRuudunPaalla(2, 2);
        sovellusOhjain.hiiriPainettu(true);
        verify(sim).setAlkuPiste(new Koordinaatit(2, 2));
        sovellusOhjain.hiiriRuudunPaalla(2, 3);
        verify(sim).setAlkuPiste(new Koordinaatit(2, 3));
        sovellusOhjain.hiiriRuudunPaalla(2, 4);
        verify(sim).setAlkuPiste(new Koordinaatit(2, 4));
    }

    @Test
    public void maaliPaikanRaahaaminenHiirellaToimii() {
        sovellusOhjain.setToiminto(Toiminto.MAALI);
        sovellusOhjain.hiiriRuudunPaalla(2, 2);
        sovellusOhjain.hiiriPainettu(true);
        verify(sim).setMaali(new Koordinaatit(2, 2));
        sovellusOhjain.hiiriRuudunPaalla(2, 3);
        verify(sim).setMaali(new Koordinaatit(2, 3));
        sovellusOhjain.hiiriRuudunPaalla(2, 4);
        verify(sim).setMaali(new Koordinaatit(2, 4));
    }

    @Test
    public void asetaAlgoritmiKutsuuSimulaatiotaOikein() {
        sovellusOhjain.asetaAlgoritmi(AlgoritmiTyyppi.GREEDY_BEST_FIRST);
        verify(sim).asetaAlgoritmi(AlgoritmiTyyppi.GREEDY_BEST_FIRST);
    }

    @Test
    public void getAlgoritmiTyyppiHakeeTiedonSimulaatiolta() {
        when(sim.getAlgoritmiTyyppi()).thenReturn(AlgoritmiTyyppi.DIJKSTRA);
        assertEquals(AlgoritmiTyyppi.DIJKSTRA, sovellusOhjain.getAlgoritmiTyyppi());
    }

    @Test
    public void asetaHeuristiikkaKutsuuSimulaatiota() {
        sovellusOhjain.asetaHeuristiikka(HeuristiikkaTyyppi.DIAGONAALINEN);
        verify(sim).asetaHeuristiikka(HeuristiikkaTyyppi.DIAGONAALINEN);
    }

    @Test
    public void getHeuristiikkaTyyppiHakeeTiedonSimulaatiolta() {
        when(sim.getHeuristiikkaTyyppi()).thenReturn(HeuristiikkaTyyppi.MANHATTAN);
        assertEquals(HeuristiikkaTyyppi.MANHATTAN, sovellusOhjain.getHeuristiikkaTyyppi());
    }

    @Test
    public void setHidasteKutsuuSimulaatiotaOikein() {
        sovellusOhjain.setHidaste(300);
        verify(sim).setHidaste(300);
    }

    @Test
    public void getHidasteHakeeTiedonSimulaatiolta() {
        when(sim.getHidaste()).thenReturn(123);
        assertEquals(123, sovellusOhjain.getHidaste());
    }

    @Test
    public void teeUusiRuudukkoKutsuuSimulaatiota() {
        sovellusOhjain.teeUusiRuudukko(23, 15);
        verify(sim).teeUusiRuudukko(23, 15);
    }

    @Test
    public void lataaRuudukkoKuvastaAsettaaRuudukonSimulaatiolle() {
        File tiedosto = new File("asdasd");
        when(kuvanLukija.lueMaailmaKuvasta(tiedosto)).thenReturn(ruudukko);
        sovellusOhjain.lataaRuudukkoKuvasta(tiedosto);
        verify(sim).setMaailma(ruudukko);

    }

    @Test
    public void lataaRuudukkoKuvastaEiAsetaRuudukkoaSimulaatiolleJosLatausEpaonnistuu() {
        File tiedosto = new File("asdasd");
        when(kuvanLukija.lueMaailmaKuvasta(tiedosto)).thenReturn(null);
        sovellusOhjain.lataaRuudukkoKuvasta(tiedosto);
        verify(sim, never()).setMaailma(null);

    }

    @Test
    public void getRuudukkoHakeeTiedonSimulaatiolta() {
        when(sim.getRuudukko()).thenReturn(ruudukko);
        Assert.assertArrayEquals(ruudukko, sovellusOhjain.getRuudukko());
    }

    @Test
    public void setRuutuKutsuuSimulaatiota() {
        sovellusOhjain.setRuutu(3, 2, Ruutu.RUOHO);
        verify(sim).setRuutu(3, 2, Ruutu.RUOHO);
    }

    @Test
    public void getAlkupisteHakeeTiedonSimulaatiolta() {
        Koordinaatit alku = new Koordinaatit(4, 4);
        when(sim.getAlkuPiste()).thenReturn(alku);
        assertEquals(alku, sovellusOhjain.getAlkuPiste());
    }

    @Test
    public void getMaaliHakeeTiedonSimulaatiolta() {
        Koordinaatit maali = new Koordinaatit(6, 6);
        when(sim.getMaali()).thenReturn(maali);
        assertEquals(maali, sovellusOhjain.getMaali());
    }

    @Test
    public void etsiReittiKutsuuSimulaatiota() {
        sovellusOhjain.etsiReitti();
        verify(sim).etsiReitti();
    }

    @Test
    public void lopetaReitinEtsiminenKutsuuSimulaatiota() {
        sovellusOhjain.lopetaReitinEtsiminen();
        verify(sim).lopetaReitinEtsiminen();
    }

    @Test
    public void getLeveysHakeeTiedonSimulaatiolta() {
        when(sim.getLeveys()).thenReturn(25);
        assertEquals(25, sovellusOhjain.getLeveys());
    }

    @Test
    public void getKorkeusHakeeTiedonSimulaatiolta() {
        when(sim.getKorkeus()).thenReturn(10);
        assertEquals(10, sovellusOhjain.getKorkeus());
    }

    @Test
    public void onkoSimulaatioKaynnissaHakeeTiedonSimulaatiolta() {
        when(sim.onkoSimulaatioKaynnissa()).thenReturn(Boolean.TRUE);
        assertTrue(sovellusOhjain.onkoSimulaatioKaynnissa());
        when(sim.onkoSimulaatioKaynnissa()).thenReturn(Boolean.FALSE);
        assertFalse(sovellusOhjain.onkoSimulaatioKaynnissa());
    }

    @Test
    public void asetaVinottainLiikkuminenSallituksiKutsuuSimulaatiota() {
        sovellusOhjain.asetaVinottainLiikkuminenSallituksi(false);
        verify(sim).asetaVinottainLiikkuminenSallituksi(false);
    }

    @Test
    public void saakoLiikkuaVinottainHakeeTiedonSimulaatiolta() {
        when(sim.saakoLiikkuaVinottain()).thenReturn(Boolean.TRUE);
        assertTrue(sovellusOhjain.saakoLiikkuaVinottain());
        when(sim.saakoLiikkuaVinottain()).thenReturn(Boolean.FALSE);
        assertFalse(sovellusOhjain.saakoLiikkuaVinottain());
    }

    @Test
    public void onkoValmisHakeeTiedonSimulaatiolta() {
        when(sim.onkoValmis()).thenReturn(Boolean.TRUE);
        assertTrue(sovellusOhjain.onkoValmis());
        when(sim.onkoValmis()).thenReturn(Boolean.FALSE);
        assertFalse(sovellusOhjain.onkoValmis());
    }

    @Test
    public void asetaRuudunKustannusAsettaaRuudunKustannuksen() {
        sovellusOhjain.asetaRuudunKustannus(Ruutu.RUOHO, 135);
        assertEquals(135, Ruutu.RUOHO.getHinta());
        sovellusOhjain.asetaRuudunKustannus(Ruutu.RUOHO, 2);
        assertEquals(2, Ruutu.RUOHO.getHinta());
    }

    @Test
    public void setPaivitettavaAsettaaSimulaatiollePaivitettavan() {
        Paivitettava paivitettava = mock(Paivitettava.class);
        sovellusOhjain.setPaivitettava(paivitettava);
        verify(sim).setPaivitettava(paivitettava);
    }

    @Test
    public void getMaailmaRuutuHakeeTiedonSimulaatiolta() {
        when(sim.getMaailmaRuutu(3, 5)).thenReturn(Ruutu.SEINA);
        assertEquals(Ruutu.SEINA, sovellusOhjain.getMaailmaRuutu(3, 5));
    }

    @Test
    public void getTilaRuutuHakeeTiedonSimulaatiolta() {
        when(sim.getTilaRuutu(3, 5)).thenReturn(RuudunTila.REITTI);
        assertEquals(RuudunTila.REITTI, sovellusOhjain.getTilaRuutu(3, 5));
    }
    
    @Test
    public void getReitinPituusHakeeTiedonSimulaatioltaKunReittiOnOlemassa() {
        Solmu s1 = new Solmu(new Koordinaatit(0, 0), 0, null);
        Solmu s2 = new Solmu(new Koordinaatit(0, 1), 1, s1);
        Solmu s3 = new Solmu(new Koordinaatit(0, 2), 4, s2);
        when(sim.getReitti()).thenReturn(s3);
        assertEquals(4, sovellusOhjain.getReitinPituus());
    }
    
    @Test
    public void getReitinPituusPalauttaaNollaKunReittiaEiOleOlemassa() {
        when(sim.getReitti()).thenReturn(null);
        assertEquals(0, sovellusOhjain.getReitinPituus());
    }

}
