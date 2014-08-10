/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet.Keko;

import Tietorakenteet.Solmu;
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
        Solmu[] solmut = new Solmu[1];
        solmut[0] = new Solmu(1, 1);
        keko.asetaTaulukko(solmut, 1);
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmut, solmud);

    }

    @Test
    public void VaihtaminenToimii() {
        Solmu[] solmut = new Solmu[2];
        solmut[0] = new Solmu(1, 1);
        solmut[1] = new Solmu(2, 2);
        keko.asetaTaulukko(solmut, 2);
        keko.vaihda(0, 1);
        Solmu s = (Solmu) keko.palautaTaulukko()[0];
        double x = s.PalautaX();
        assertEquals(x, 2, 0.01);

    }

    @Test
    public void VaihtaminenToimiiOsa2() {
        Solmu[] solmut = new Solmu[2];
        solmut[0] = new Solmu(1, 1);
        solmut[1] = new Solmu(2, 2);
        keko.asetaTaulukko(solmut, 2);
        keko.vaihda(0, 1);
        Solmu s2 = (Solmu) keko.palautaTaulukko()[1];
        double x2 = s2.PalautaX();
        assertEquals(x2, 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdellaAlkiolla() {
        Solmu[] solmut = new Solmu[1];
        Solmu lisattava = new Solmu(1, 1);
        lisattava.asetaArvo(1);
        solmut[0] = lisattava;
        keko.asetaTaulukko(solmut, 1);
        keko.Korjaa(0);
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmut, solmud);

    }

    @Test
    public void KorjaaTestYhdellaAlkiollaJossaKorjausEiTapahdu() {
        Solmu[] solmut = new Solmu[2];
        Solmu lisattava = new Solmu(1, 1);
        Solmu lisattava2 = new Solmu(2, 2);
        lisattava.asetaArvo(1);
        lisattava2.asetaArvo(2);
        solmut[0] = lisattava;
        solmut[1] = lisattava2;
        keko.asetaTaulukko(solmut, 2);
        keko.Korjaa(0);
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].PalautaX(), 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdellaAlkiollaJossaKorjausTapahtuuOsa1() {
        Solmu[] solmut = new Solmu[2];
        Solmu lisattava = new Solmu(1, 1);
        Solmu lisattava2 = new Solmu(2, 2);
        lisattava.asetaArvo(2);
        lisattava2.asetaArvo(1);
        solmut[0] = lisattava;
        solmut[1] = lisattava2;
        keko.asetaTaulukko(solmut, 2);
        keko.Korjaa(0);
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].Arvo(), 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdellaAlkiollaJossaKorjausTapahtuuOsa2() {
        Solmu[] solmut = new Solmu[2];
        Solmu lisattava = new Solmu(1, 1);
        Solmu lisattava2 = new Solmu(2, 2);
        lisattava.asetaArvo(2);
        lisattava2.asetaArvo(1);
        solmut[0] = lisattava;
        solmut[1] = lisattava2;
        keko.asetaTaulukko(solmut, 2);
        keko.Korjaa(0);
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[1].Arvo(), 2, 0.01);

    }

    @Test
    public void KorjaaTestKolmellaAlkiollaJossaKorjaustaEiTapahdu() {
        Solmu[] solmut = new Solmu[3];
        Solmu lisattava = new Solmu(1, 1);
        Solmu lisattava2 = new Solmu(2, 2);
        Solmu lisattava3 = new Solmu(3, 3);
        lisattava.asetaArvo(1);
        lisattava2.asetaArvo(2);
        lisattava3.asetaArvo(3);
        solmut[0] = lisattava;
        solmut[1] = lisattava2;
        solmut[2] = lisattava3;
        keko.asetaTaulukko(solmut, 3);
        keko.Korjaa(0);
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].Arvo(), 1, 0.01);

    }

    @Test
    public void KorjaaTestKolmellaAlkiollaTapahtuu() {
        Solmu[] solmut = new Solmu[3];
        Solmu lisattava = new Solmu(1, 1);
        Solmu lisattava2 = new Solmu(2, 2);
        Solmu lisattava3 = new Solmu(3, 3);
        lisattava.asetaArvo(1);
        lisattava2.asetaArvo(2);
        lisattava3.asetaArvo(3);
        solmut[1] = lisattava;
        solmut[0] = lisattava2;
        solmut[2] = lisattava3;
        keko.asetaTaulukko(solmut, 3);
        keko.Korjaa(0);
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].Arvo(), 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdeksallaKymmennellaYhdeksallaAlkiollaEiTapahdu() {
        Solmu[] solmut = new Solmu[99];
        for (int j = 0; j < 99; j++) {
            Solmu lisattava = new Solmu(j, j);
            lisattava.asetaArvo(j);
            solmut[j] = lisattava;

        }

        keko.asetaTaulukko(solmut, 99);
        keko.Korjaa(0);
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].Arvo(), 0, 0.01);

    }

    @Test
    public void MassiivisetKorjauksetTuovatTulosta() {
        Solmu[] solmut = new Solmu[99];
        for (int j = 0; j < 99; j++) {
            Solmu lisattava = new Solmu(j, j);
            lisattava.asetaArvo(j);
            solmut[j] = lisattava;

        }

        keko.asetaTaulukko(solmut, 99);
        keko.vaihda(0, 98);
        for (int i = 98; i >= 0; i--) {
            keko.Korjaa(i);

        }
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].Arvo(), 0, 0.01);

    }

    @Test
    public void testaaPoistaMinimiYhdellaAlkiolla() {
        Solmu[] solmut = new Solmu[1];
        solmut[0] = new Solmu(1, 1);
        solmut[0].asetaArvo(1);
        keko.asetaTaulukko(solmut, 1);
        keko.PoistaMinimi();
        assertEquals(keko.palautaNykyinenKoko(), 0);

    }

    @Test
    public void testaaPoistaMinimiKahdellaAlkiolla() {
        Solmu[] solmut = new Solmu[2];
        solmut[0] = new Solmu(1, 1);
        solmut[0].asetaArvo(1);
        solmut[1] = new Solmu(2, 2);
        solmut[1].asetaArvo(2);

        keko.asetaTaulukko(solmut, 2);
        keko.PoistaMinimi();
        assertEquals(keko.palautaNykyinenKoko(), 1);

    }

    @Test
    public void testaaPoistaMinimiKahdellaAlkiollaOsa2() {
        Solmu[] solmut = new Solmu[2];
        solmut[0] = new Solmu(1, 1);
        solmut[0].asetaArvo(1);
        solmut[1] = new Solmu(2, 2);
        solmut[1].asetaArvo(2);

        keko.asetaTaulukko(solmut, 2);
        keko.PoistaMinimi();
        assertEquals(keko.palautaTaulukko()[0].Arvo(), 2, 0.01);

    }

    @Test
    public void MassiivisetPoistotTuovatTulosta() {
        Solmu[] solmut = new Solmu[99];
        for (int j = 0; j < 99; j++) {
            Solmu lisattava = new Solmu(j, j);
            lisattava.asetaArvo(j);
            solmut[j] = lisattava;

        }

        keko.asetaTaulukko(solmut, 99);
        for (int i = 98; i >= 0; i--) {
            keko.PoistaMinimi();

        }
        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].Arvo(), 98, 0.01);

    }

    @Test
    public void LisaaminenOnnistuuTyhjaanTaulukkoon() {
        Solmu[] solmut = new Solmu[100];
        Solmu solmu = new Solmu(2, 2);
        solmu.asetaArvo(1);
        keko.asetaTaulukko(solmut, 0);
        Iteroitava iteroiva = solmu;
        keko.Lisaa(iteroiva);
        assertEquals(keko.palautaNykyinenKoko(), 1);

    }

    @Test
    public void LisaaminenOnnistuuTyhjaanTaulukkoonOsa2() {
        Solmu[] solmut = new Solmu[100];
        Solmu solmu = new Solmu(2, 2);
        solmu.asetaArvo(1);
        keko.asetaTaulukko(solmut, 0);
        Iteroitava iteroiva = solmu;
        keko.Lisaa(iteroiva);
        assertEquals(keko.palautaTaulukko()[0].Arvo(), 1, 0.01);

    }

    @Test
    public void LisaaminenOnnistuuTaulukkoonjossaValmiinayksialkio() {
        Solmu[] solmut = new Solmu[100];
        Solmu solmu = new Solmu(1, 1);
        solmu.asetaArvo(1);
        Solmu solmu2 = new Solmu(2, 2);
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
        Solmu[] solmut = new Solmu[100];
        Solmu solmu = new Solmu(1, 1);
        solmu.asetaArvo(1);
        Solmu solmu2 = new Solmu(2, 2);
        solmu2.asetaArvo(2);
        Iteroitava iteroiva = solmu;
        Iteroitava iteroiva2 = solmu2;

        keko.asetaTaulukko(solmut, 0);
        keko.Lisaa(iteroiva);
        keko.Lisaa(iteroiva2);

        assertEquals(keko.palautaTaulukko()[0].Arvo(), 1, 0.01);

    }

    @Test
    public void MassiivisetLisayksetTuovatTulosta() {
        Solmu[] solmut = new Solmu[200];
        keko.asetaTaulukko(solmut, 0);

        for (int j = 98; j >= 0; j--) {
            Solmu lisattava = new Solmu(j, j);
            lisattava.asetaArvo(j);
            Iteroitava iter = lisattava;
            keko.Lisaa(iter);
            
        }

        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].Arvo(), 0, 0.01);

    }

}
