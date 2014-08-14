/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet.Keko;

import Tietorakenteet.DiskreettiSolmu;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Serafim
 */
public class KekoTesti {

    private Keko keko;

    @Before
    public void setUp() {
        this.keko = new Keko();

    }

    @Test
    public void taulukonAsettaminenYlipaataanToimii() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[1];
        solmut[0] = new DiskreettiSolmu(1, 1);
        keko.asetaTaulukko(solmut, 1);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmut, solmud);

    }

    @Test
    public void VaihtaminenToimii() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[2];
        solmut[0] = new DiskreettiSolmu(1, 1);
        solmut[1] = new DiskreettiSolmu(2, 2);
        keko.asetaTaulukko(solmut, 2);
        keko.vaihda(0, 1);
        DiskreettiSolmu s = (DiskreettiSolmu) keko.palautaTaulukko()[0];
        double x = s.PalautaX();
        assertEquals(x, 2, 0.01);

    }

    @Test
    public void VaihtaminenToimiiOsa2() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[2];
        solmut[0] = new DiskreettiSolmu(1, 1);
        solmut[1] = new DiskreettiSolmu(2, 2);
        keko.asetaTaulukko(solmut, 2);
        keko.vaihda(0, 1);
        DiskreettiSolmu s2 = (DiskreettiSolmu) keko.palautaTaulukko()[1];
        double x2 = s2.PalautaX();
        assertEquals(x2, 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdellaAlkiolla() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[1];
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        lisattava.asetaArvo(1);
        solmut[0] = lisattava;
        keko.asetaTaulukko(solmut, 1);
        keko.Korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmut, solmud);

    }

    @Test
    public void KorjaaTestYhdellaAlkiollaJossaKorjausEiTapahdu() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[2];
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        DiskreettiSolmu lisattava2 = new DiskreettiSolmu(2, 2);
        lisattava.asetaArvo(1);
        lisattava2.asetaArvo(2);
        solmut[0] = lisattava;
        solmut[1] = lisattava2;
        keko.asetaTaulukko(solmut, 2);
        keko.Korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].PalautaX(), 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdellaAlkiollaJossaKorjausTapahtuuOsa1() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[2];
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        DiskreettiSolmu lisattava2 = new DiskreettiSolmu(2, 2);
        lisattava.asetaArvo(2);
        lisattava2.asetaArvo(1);
        solmut[0] = lisattava;
        solmut[1] = lisattava2;
        keko.asetaTaulukko(solmut, 2);
        keko.Korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].KekoArvo(), 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdellaAlkiollaJossaKorjausTapahtuuOsa2() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[2];
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        DiskreettiSolmu lisattava2 = new DiskreettiSolmu(2, 2);
        lisattava.asetaArvo(2);
        lisattava2.asetaArvo(1);
        solmut[0] = lisattava;
        solmut[1] = lisattava2;
        keko.asetaTaulukko(solmut, 2);
        keko.Korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[1].KekoArvo(), 2, 0.01);

    }

    @Test
    public void KorjaaTestKolmellaAlkiollaJossaKorjaustaEiTapahdu() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[3];
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        DiskreettiSolmu lisattava2 = new DiskreettiSolmu(2, 2);
        DiskreettiSolmu lisattava3 = new DiskreettiSolmu(3, 3);
        lisattava.asetaArvo(1);
        lisattava2.asetaArvo(2);
        lisattava3.asetaArvo(3);
        solmut[0] = lisattava;
        solmut[1] = lisattava2;
        solmut[2] = lisattava3;
        keko.asetaTaulukko(solmut, 3);
        keko.Korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].KekoArvo(), 1, 0.01);

    }

    @Test
    public void KorjaaTestKolmellaAlkiollaTapahtuu() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[3];
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        DiskreettiSolmu lisattava2 = new DiskreettiSolmu(2, 2);
        DiskreettiSolmu lisattava3 = new DiskreettiSolmu(3, 3);
        lisattava.asetaArvo(1);
        lisattava2.asetaArvo(2);
        lisattava3.asetaArvo(3);
        solmut[1] = lisattava;
        solmut[0] = lisattava2;
        solmut[2] = lisattava3;
        keko.asetaTaulukko(solmut, 3);
        keko.Korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].KekoArvo(), 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdeksallaKymmennellaYhdeksallaAlkiollaEiTapahdu() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[99];
        for (int j = 0; j < 99; j++) {
            DiskreettiSolmu lisattava = new DiskreettiSolmu(j, j);
            lisattava.asetaArvo(j);
            solmut[j] = lisattava;

        }

        keko.asetaTaulukko(solmut, 99);
        keko.Korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].KekoArvo(), 0, 0.01);

    }

    @Test
    public void MassiivisetKorjauksetTuovatTulosta() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[99];
        for (int j = 0; j < 99; j++) {
            DiskreettiSolmu lisattava = new DiskreettiSolmu(j, j);
            lisattava.asetaArvo(j);
            solmut[j] = lisattava;

        }

        keko.asetaTaulukko(solmut, 99);
        keko.vaihda(0, 98);
        for (int i = 98; i >= 0; i--) {
            keko.Korjaa(i);

        }
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].KekoArvo(), 0, 0.01);

    }

    @Test
    public void testaaPoistaMinimiYhdellaAlkiolla() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[1];
        solmut[0] = new DiskreettiSolmu(1, 1);
        solmut[0].asetaArvo(1);
        keko.asetaTaulukko(solmut, 1);
        keko.PoistaMinimi();
        assertEquals(keko.palautaNykyinenKoko(), 0);

    }

    @Test
    public void testaaPoistaMinimiKahdellaAlkiolla() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[2];
        solmut[0] = new DiskreettiSolmu(1, 1);
        solmut[0].asetaArvo(1);
        solmut[1] = new DiskreettiSolmu(2, 2);
        solmut[1].asetaArvo(2);

        keko.asetaTaulukko(solmut, 2);
        keko.PoistaMinimi();
        assertEquals(keko.palautaNykyinenKoko(), 1);

    }

    @Test
    public void testaaPoistaMinimiKahdellaAlkiollaOsa2() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[2];
        solmut[0] = new DiskreettiSolmu(1, 1);
        solmut[0].asetaArvo(1);
        solmut[1] = new DiskreettiSolmu(2, 2);
        solmut[1].asetaArvo(2);

        keko.asetaTaulukko(solmut, 2);
        keko.PoistaMinimi();
        assertEquals(keko.palautaTaulukko()[0].KekoArvo(), 2, 0.01);

    }

    @Test
    public void MassiivisetPoistotTuovatTulosta() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[99];
        for (int j = 0; j < 99; j++) {
            DiskreettiSolmu lisattava = new DiskreettiSolmu(j, j);
            lisattava.asetaArvo(j);
            solmut[j] = lisattava;

        }

        keko.asetaTaulukko(solmut, 99);
        for (int i = 98; i >= 0; i--) {
            keko.PoistaMinimi();

        }
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].KekoArvo(), 98, 0.01);

    }

    @Test
    public void LisaaminenOnnistuuTyhjaanTaulukkoon() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[100];
        DiskreettiSolmu solmu = new DiskreettiSolmu(2, 2);
        solmu.asetaArvo(1);
        keko.asetaTaulukko(solmut, 0);
        Iteroitava iteroiva = solmu;
        keko.Lisaa(iteroiva);
        assertEquals(keko.palautaNykyinenKoko(), 1);

    }

    @Test
    public void LisaaminenOnnistuuTyhjaanTaulukkoonOsa2() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[100];
        DiskreettiSolmu solmu = new DiskreettiSolmu(2, 2);
        solmu.asetaArvo(1);
        keko.asetaTaulukko(solmut, 0);
        Iteroitava iteroiva = solmu;
        keko.Lisaa(iteroiva);
        assertEquals(keko.palautaTaulukko()[0].KekoArvo(), 1, 0.01);

    }

    @Test
    public void LisaaminenOnnistuuTaulukkoonjossaValmiinayksialkio() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[100];
        DiskreettiSolmu solmu = new DiskreettiSolmu(1, 1);
        solmu.asetaArvo(1);
        DiskreettiSolmu solmu2 = new DiskreettiSolmu(2, 2);
        solmu.asetaArvo(2);
        Iteroitava iteroiva = solmu;
        Iteroitava iteroiva2 = solmu2;

        keko.asetaTaulukko(solmut, 0);
        keko.Lisaa(iteroiva);
        keko.Lisaa(iteroiva2);

        assertEquals(keko.palautaNykyinenKoko(), 2);

    }

    @Test
    public void LisaaminenOnnistuuTaulukkoonjossaValmiinayksialkio2() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[100];
        DiskreettiSolmu solmu = new DiskreettiSolmu(1, 1);
        solmu.asetaArvo(1);
        DiskreettiSolmu solmu2 = new DiskreettiSolmu(2, 2);
        solmu2.asetaArvo(2);
        Iteroitava iteroiva = solmu;
        Iteroitava iteroiva2 = solmu2;

        keko.asetaTaulukko(solmut, 0);
        keko.Lisaa(iteroiva);
        keko.Lisaa(iteroiva2);

        assertEquals(keko.palautaTaulukko()[0].KekoArvo(), 1, 0.01);

    }

    @Test
    public void MassiivisetLisayksetTuovatTulosta() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[200];
        keko.asetaTaulukko(solmut, 0);

        for (int j = 98; j >= 0; j--) {
            DiskreettiSolmu lisattava = new DiskreettiSolmu(j, j);
            lisattava.asetaArvo(j);
            Iteroitava iter = lisattava;
            keko.Lisaa(iter);
            
        }

        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].KekoArvo(), 0, 0.01);

    }

}
