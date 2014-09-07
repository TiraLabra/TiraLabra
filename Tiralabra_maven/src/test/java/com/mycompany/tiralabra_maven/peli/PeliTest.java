package com.mycompany.tiralabra_maven.peli;

import com.mycompany.tiralabra_maven.AI.EkaAI;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author noora
 */
public class PeliTest {

    private Peli peli;
    private Pelaaja valkoinen;
    private Pelaaja musta;

    private class StubPelaaja implements Pelaaja {

        private boolean jatketaanko = false;
        private int palautettavaSiirto = -1;
        public int kutsuttu = 0;

        @Override
        public Siirto seuraavaSiirto(Siirto[] sallitutSiirrot) {
            kutsuttu++;
            while (palautettavaSiirto == -1) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            }
            Siirto palautus = sallitutSiirrot[palautettavaSiirto];
            palautettavaSiirto = -1;
            return palautus;
        }

        public void palautaSiirto(int nro) {
            palautettavaSiirto = nro;
        }

    };

    public PeliTest() {
    }

    @Before
    public void setUp() {
        peli = new Peli();
    }

    @Test
    public void pelinLuontiLuoPelilaudanJaAsettaaNappulatAlkuasetelmaan() {
        assertEquals(Nappula.MUSTA, peli.getPelilauta().getNappula(0, 1));
        assertEquals(Nappula.VALKOINEN, peli.getPelilauta().getNappula(5, 0));
    }

    @Test
    public void pelinLuontiEiKaynnistaPelia() {
        assertEquals(false, peli.isPeliKaynnissa());
    }

    @Test
    public void pelinLuontiKysyyEnsimmaisetSallitutSiirrot() {
        assertEquals(7, peli.getSallitutSiirrot().length);
    }

    @Test
    public void getMustaPalauttaaOikeanPelaajan() {
        EkaAI pelaaja = new EkaAI(peli, null, true, 0);
        peli.setMusta(pelaaja);
        assertEquals(pelaaja, peli.getMusta());
    }

    @Test
    public void getValkoinenPalauttaaOikeanPelaajan() {
        EkaAI pelaaja = new EkaAI(peli, null, true, 0);
        peli.setValkoinen(pelaaja);
        assertEquals(pelaaja, peli.getValkoinen());
    }

    @Test
    public void peliEiKaynnistyJosPelaajiaEiOleAsetettu() {
        new Thread(peli).start();
        assertEquals(false, peli.isPeliKaynnissa());
    }

    @Test
    public void peliEiKaynnistyJosOnVainYksiPelaaja() {
        peli.setMusta(new EkaAI(peli, null, true, 0));
        new Thread(peli).start();
        assertEquals(false, peli.isPeliKaynnissa());
    }

    @Test
    public void alussaOnValkoisenVuoroSiirtaa() {
        StubPelaaja musta = new StubPelaaja();
        StubPelaaja valkoinen = new StubPelaaja();

        peli.setMusta(musta);
        peli.setValkoinen(valkoinen);

        new Thread(peli).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }

        assertEquals(1, valkoinen.kutsuttu);
        assertEquals(0, musta.kutsuttu);
        assertEquals(true, peli.isValkoisenVuoroSiirtaa());

        peli.luovutaPeli();
        valkoinen.palautaSiirto(0);
        musta.palautaSiirto(0);
    }

    @Test
    public void valkoisenSiirronJalkeenOnMustanVuoroSiirtaa() {
        StubPelaaja musta = new StubPelaaja();
        StubPelaaja valkoinen = new StubPelaaja();

        peli.setMusta(musta);
        peli.setValkoinen(valkoinen);

        new Thread(peli).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }

        valkoinen.palautaSiirto(0);

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }

        assertEquals(false, peli.isValkoisenVuoroSiirtaa());
        assertEquals(1, valkoinen.kutsuttu);
        assertEquals(1, musta.kutsuttu);

        peli.luovutaPeli();
        valkoinen.palautaSiirto(0);
        musta.palautaSiirto(0);
    }
    
    @Test
    public void valkoisenSiirronJalkeenNappulaaSiirretaanLaudalla() {
        StubPelaaja musta = new StubPelaaja();
        StubPelaaja valkoinen = new StubPelaaja();

        peli.setMusta(musta);
        peli.setValkoinen(valkoinen);

        new Thread(peli).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }

        valkoinen.palautaSiirto(0);

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }

        assertEquals(Nappula.VALKOINEN, peli.getPelilauta().getNappula(4, 1));
        assertEquals(null, peli.getPelilauta().getNappula(5, 0));

        peli.luovutaPeli();
        valkoinen.palautaSiirto(0);
        musta.palautaSiirto(0);
    }
    
    @Test
    public void hypynJalkeenNappulaaSiirretaanLaudallaJaNappulaKatoaaLaudalta() {
        StubPelaaja musta = new StubPelaaja();
        StubPelaaja valkoinen = new StubPelaaja();

        peli.setMusta(musta);
        peli.setValkoinen(valkoinen);

        new Thread(peli).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }

        valkoinen.palautaSiirto(0);

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
        
        musta.palautaSiirto(3);
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
        
        valkoinen.palautaSiirto(0);
        
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }

        assertEquals(Nappula.VALKOINEN, peli.getPelilauta().getNappula(2, 3));
        assertEquals(null, peli.getPelilauta().getNappula(3, 2));

        peli.luovutaPeli();
        valkoinen.palautaSiirto(0);
        musta.palautaSiirto(0);
    }

}
