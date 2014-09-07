package com.mycompany.tiralabra_maven.logiikka.algoritmi;

import com.mycompany.tiralabra_maven.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.Koordinaatit;
import com.mycompany.tiralabra_maven.gui.RuudunTila;
import com.mycompany.tiralabra_maven.gui.Ruutu;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.DiagonaalinenHeuristiikka;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.DiagonaalinenTieBreakingHeuristiikka;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.ManhattanHeuristiikka;
import com.mycompany.tiralabra_maven.logiikka.algoritmi.heuristiikka.ManhattanTieBreakingHeuristiikka;
import static junit.framework.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author mikko
 */
public class AStarAlgoritmiTest {

    private AlgoritmiTest testi;

    @Before
    public void setUp() {
        testi = new AlgoritmiTest(AlgoritmiTyyppi.A_STAR);
    }

    @Test
    public void algoritmiLoytaaSuoranReitinPerille() {
        testi.algoritmiLoytaaSuoranReitinPerille();
    }

    @Test
    public void algoritmiLoytaaReitinPerilleNopeasti() {
        testi.algoritmiLoytaaReitinPerilleNopeasti(210, 1500);
    }

    @Test
    public void algoritmiOsaaKiertaaSeinan() {
        testi.algoritmiOsaaKiertaaSeinan();
    }

    @Test
    public void algoritmiLoytaaLyhimmanReitinEikaTutkiVaaraanSuuntaan() {
        testi.algoritmiLoytaaLyhimmanReitinEikaTutkiVaaraanSuuntaan();
    }

    @Test
    public void algoritmiTutkiiPaljonJosHeuristiikkaOnManhattanJaEiSaaLiikkuaVinottain() {
        Ruutu[][] maailma = testi.alustaMaailma(20, 20);
        Koordinaatit alku = new Koordinaatit(1, 1);
        Koordinaatit maali = new Koordinaatit(18, 18);
        AStarAlgoritmi algoritmi = new AStarAlgoritmi(maailma, 0, alku, maali, false, new ManhattanHeuristiikka());
        testi.suoritaAlgoritmi(algoritmi);
        assertEquals(RuudunTila.TUTKITTU, algoritmi.getRuudunTila(9, 1));
        assertEquals(RuudunTila.TUTKITTU, algoritmi.getRuudunTila(4, 3));
    }

    @Test
    public void algoritmiTutkiiVahanJosHeuristiikkaOnManhattanTieBreakingJaEiSaaLiikkuaVinottain() {
        Ruutu[][] maailma = testi.alustaMaailma(20, 20);
        Koordinaatit alku = new Koordinaatit(1, 1);
        Koordinaatit maali = new Koordinaatit(18, 18);
        AStarAlgoritmi algoritmi = new AStarAlgoritmi(maailma, 0, alku, maali, false, new ManhattanTieBreakingHeuristiikka());
        testi.suoritaAlgoritmi(algoritmi);
        assertEquals(null, algoritmi.getRuudunTila(9, 1));
        assertEquals(null, algoritmi.getRuudunTila(4, 3));

    }

    @Test
    public void algoritmiTutkiiPaljonJosHeuristiikkaOnDiagonaalinenJaSaaLiikkuaVinottain() {
        Ruutu[][] maailma = testi.alustaMaailma(20, 20);
        Koordinaatit alku = new Koordinaatit(1, 1);
        Koordinaatit maali = new Koordinaatit(18, 11);
        AStarAlgoritmi algoritmi = new AStarAlgoritmi(maailma, 0, alku, maali, true, new DiagonaalinenHeuristiikka());
        testi.suoritaAlgoritmi(algoritmi);
        assertEquals(RuudunTila.TUTKITTU, algoritmi.getRuudunTila(2, 0));
        assertEquals(RuudunTila.TUTKITTU, algoritmi.getRuudunTila(7, 0));
        assertEquals(RuudunTila.TUTKITTU, algoritmi.getRuudunTila(10, 4));
        assertEquals(RuudunTila.TUTKITTU, algoritmi.getRuudunTila(15, 14));
    }

    @Test
    public void algoritmiTutkiiVahanJosHeuristiikkaOnDiagonaalinenTieBreakingJaSaaLiikkuaVinottain() {
        Ruutu[][] maailma = testi.alustaMaailma(20, 20);
        Koordinaatit alku = new Koordinaatit(1, 1);
        Koordinaatit maali = new Koordinaatit(18, 11);
        AStarAlgoritmi algoritmi = new AStarAlgoritmi(maailma, 0, alku, maali, true, new DiagonaalinenTieBreakingHeuristiikka());
        testi.suoritaAlgoritmi(algoritmi);
        assertEquals(RuudunTila.TUTKITTAVA, algoritmi.getRuudunTila(2, 0));
        assertEquals(RuudunTila.TUTKITTAVA, algoritmi.getRuudunTila(7, 0));
        assertEquals(RuudunTila.TUTKITTAVA, algoritmi.getRuudunTila(10, 4));
        assertEquals(null, algoritmi.getRuudunTila(15, 14));
    }
}
