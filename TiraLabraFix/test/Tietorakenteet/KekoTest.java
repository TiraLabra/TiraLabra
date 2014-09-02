/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Serafim
 */
public class KekoTest {

    private Keko keko;

    @Before
    public void setUp() {
        this.keko = new Keko(true);

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
        double x = s.palautaX();
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
        double x2 = s2.palautaX();
        assertEquals(x2, 1, 0.01);

    }

    @Test
    public void KorjaaTestYhdellaAlkiolla() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[1];
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        lisattava.asetaArvo(1);
        solmut[0] = lisattava;
        keko.asetaTaulukko(solmut, 1);
        keko.korjaa(0);
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
        keko.korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].palautaX(), 1, 0.01);

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
        keko.korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].palautaSolmuMuisti().palautaFScore(), 1, 0.01);

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
        keko.korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[1].palautaSolmuMuisti().palautaFScore(), 2, 0.01);

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
        keko.korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].palautaSolmuMuisti().palautaFScore(), 1, 0.01);

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
        keko.korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].palautaSolmuMuisti().palautaFScore(), 1, 0.01);

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
        keko.korjaa(0);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].palautaSolmuMuisti().palautaFScore(), 0, 0.01);

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
            keko.korjaa(i);

        }
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].palautaSolmuMuisti().palautaFScore(), 0, 0.01);

    }

    @Test
    public void testaaPoistaMinimiYhdellaAlkiolla() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[1];
        solmut[0] = new DiskreettiSolmu(1, 1);
        solmut[0].asetaArvo(1);
        keko.asetaTaulukko(solmut, 1);
        keko.poistaMinimi();
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
        keko.poistaMinimi();
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
        keko.poistaMinimi();
        DiskreettiSolmu s = (DiskreettiSolmu) keko.palautaTaulukko()[0];
        assertEquals(s.palautaSolmuMuisti().palautaFScore(), 2, 0.01);

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
            keko.poistaMinimi();

        }
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        assertEquals(solmud[0].palautaSolmuMuisti().palautaFScore(), 98, 0.01);

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
        DiskreettiSolmu s = (DiskreettiSolmu) keko.palautaTaulukko()[0];

        assertEquals(s.palautaSolmuMuisti().palautaFScore(), 1, 0.01);

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
        DiskreettiSolmu s = (DiskreettiSolmu) keko.palautaTaulukko()[0];

        assertEquals(s.palautaSolmuMuisti().palautaFScore(), 1, 0.01);

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
        DiskreettiSolmu s = (DiskreettiSolmu) solmud[0];

        assertEquals(s.palautaSolmuMuisti().palautaFScore(), 0, 0.01);

    }

    @Test
    public void MassiivisetLisayksetTuovatTulostaTestaaPoistaMinmi() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[200];
        keko.asetaTaulukko(solmut, 0);

        for (int j = 98; j >= 0; j--) {
            DiskreettiSolmu lisattava = new DiskreettiSolmu(j, j);
            lisattava.asetaArvo(j);
            Iteroitava iter = lisattava;
            keko.Lisaa(iter);

        }

        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        DiskreettiSolmu s = (DiskreettiSolmu) keko.poistaMinimi();

        assertEquals(s.palautaSolmuMuisti().palautaFScore(), 0, 0.01);

    }

    @Test
    public void TestKasvataJossJokinmuuttuu() {

        DiskreettiSolmu[] solmut = new DiskreettiSolmu[200];
        keko.asetaTaulukko(solmut, 0);
        Iteroitava iter = new DiskreettiSolmu(0, 0);
        iter.asetaArvo(1);
        Iteroitava iter2 = new DiskreettiSolmu(1, 1);
        iter2.asetaArvo(0);
        keko.Lisaa(iter2);
        keko.Lisaa(iter);
        iter2.asetaArvo(9000);
        keko.kasvatettu(iter2.sijaintiKeossa());
        DiskreettiSolmu s = (DiskreettiSolmu) keko.poistaMinimi();
        DiskreettiSolmu d = (DiskreettiSolmu) iter;

        assertEquals(s.palautaSolmuMuisti().palautaFScore(), d.palautaSolmuMuisti().palautaFScore(), 0.01);

    }

    @Test
    public void KasvataEkaSolmu() {
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[200];
        keko.asetaTaulukko(solmut, 0);

        for (int j = 98; j >= 0; j--) {
            DiskreettiSolmu lisattava = new DiskreettiSolmu(j, j);
            lisattava.asetaArvo(j);
            Iteroitava iter = lisattava;
            keko.Lisaa(iter);

        }

        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) keko.palautaTaulukko();
        solmud[0].asetaArvo(9000);
        keko.kasvatettu(solmud[0].sijaintiKeossa());
        DiskreettiSolmu s = (DiskreettiSolmu) keko.poistaMinimi();

        assertEquals(s.palautaSolmuMuisti().palautaFScore(), 1, 0.01);

    }

    @Test
    public void DuplicateModeSimpleTestLisaaminenonnistuu() {
        Keko abc = new Keko(false);
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[200];
        abc.asetaTaulukko(solmut, 0);
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        lisattava.asetaArvo(1);
        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) abc.palautaTaulukko();
        assertEquals(abc.palautaNykyinenKoko(), 1);

    }

    @Test
    public void DuplikaatitonLisaaminentoimiiMassiivinentesti() {
        Keko abc = new Keko(false);
        DiskreettiSolmu[] solmut = new DiskreettiSolmu[200];
        abc.asetaTaulukko(solmut, 0);
        DiskreettiSolmu lisattava = new DiskreettiSolmu(1, 1);
        lisattava.asetaArvo(1);
        for (int j = 98; j >= 0; j--) {

            Iteroitava iter = lisattava;
            abc.Lisaa(iter);

        }

        DiskreettiSolmu[] solmud = (DiskreettiSolmu[]) abc.palautaTaulukko();

        assertEquals(abc.palautaNykyinenKoko(), 1);

    }

}
