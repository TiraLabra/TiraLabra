package pacman.peli.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.hahmot.Haamu;
import pacman.hahmot.Suunta;
import pacman.peli.Pacman;

public class PacmanTest {

    private Pacman pacman;

    public PacmanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        pacman = new Pacman();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kuoleekoManKunHaamuVahva() {
        liikutetaanManJaHaamuSamaanRuutuun();
        pacman.kuoleekoHaamuTaiMan();

        assertEquals(9, pacman.getMan().getX());
        assertEquals(11, pacman.getMan().getY());
    }

    @Test
    public void kuoleekoHaamuKunHaamuHeikko() {
        pacman.getKasittelija().getGreen().setTyyppi("heikko");
        liikutetaanManJaHaamuSamaanRuutuunKunHaamutHeikkoja();
        pacman.kuoleekoHaamuTaiMan();

        assertEquals(9, pacman.getKasittelija().getGreen().getX());
        assertEquals(9, pacman.getKasittelija().getGreen().getY());
    }

    @Test
    public void syokoManPistepallonOikein() {
        manLiikuJaSyoPistepallo();
        assertFalse(pacman.getAlusta().getPeliruutu(10, 11).getOnkoPallo());
        assertEquals(20, pacman.getLaskuri().getPisteet());
    }

    @Test
    public void rakennetaankoSeinaOikein1() {
        assertEquals(3, pacman.getAlusta().getPeliruutu(9, 8).getRuudunTyyppi());
    }

    @Test
    public void rakennetaankoSeinaOikein2() {
        pacman.getKasittelija().getCyan().setX(1);
        pacman.getKasittelija().getCyan().setY(1);
        pacman.getAlusta().getPeliruutu(8, 9).setOnkoHaamu(false);
        pacman.getKasittelija().getGreen().setX(1);
        pacman.getKasittelija().getGreen().setY(1);
        pacman.getAlusta().getPeliruutu(10, 9).setOnkoHaamu(false);
        pacman.getKasittelija().getMagenta().setX(1);
        pacman.getKasittelija().getMagenta().setY(1);
        pacman.getAlusta().getPeliruutu(9, 9).setOnkoHaamu(false);

        
        pacman.asetaSeina();
        assertEquals(0, pacman.getAlusta().getPeliruutu(9, 8).getRuudunTyyppi());
    }

    @Test
    public void EiAsetaHedelmaaKunEiTarpeeksiPisteita() {
        pacman.getLaskuri().kasvata(200);
        assertEquals(null, pacman.getHedelmanPaikka());
    }

    @Test
    public void asettaaHedelmanKunTarpeeksiPisteitaJaTarkistaaOnkoHedelmaAlustalla() {
        syodaanPistepalloJaKasvatetaanPisteet();
        pacman.arvoHedelma();
        assertEquals(10, pacman.getHedelmanPaikka().getX());
        assertEquals(11, pacman.getHedelmanPaikka().getY());
        assertTrue(pacman.onkoHedelmaAlustalla());
    }

    @Test
    public void manOsuuOikeinHedelmaan() {
        syodaanPistepalloJaKasvatetaanPisteet();
        pacman.arvoHedelma();
        pacman.getMan().setSuunta(Suunta.VASEN);
        pacman.getMan().liiku(false, pacman);
        assertTrue(pacman.manOsuuHedelmaan());

    }

    @Test
    public void loytaaKaikkiHedelmanPaikat() {
        manLiikuJaSyoPistepallo();
        manLiikuJaSyoPistepallo();
        manLiikuJaSyoPistepallo();
        pacman.etsiHedelmanPaikat();

        assertEquals(3, pacman.getHedelmanPaikat().koko());
    }

    @Test
    public void paattyykoPeliOikein() {
        for (int y = 0; y < pacman.getAlusta().getKorkeus(); y++) {
            for (int x = 0; x < pacman.getAlusta().getLeveys(); x++) {
                if (pacman.getAlusta().getPeliruutu(x, y).getOnkoPallo()) {
                    pacman.getAlusta().getPeliruutu(x, y).setOnkoPallo(false);
                } else if (pacman.getAlusta().getPeliruutu(x, y).getOnkoExtraPallo()) {
                    pacman.getAlusta().getPeliruutu(x, y).setOnkoExtraPallo(false);
                }
            }
        }
        pacman.paattyykoPeli();

        assertTrue(pacman.getTilanne());
        assertFalse(pacman.getJatkuu());
    }

    private void syodaanPistepalloJaKasvatetaanPisteet() {
        manLiikuJaSyoPistepallo();
        pacman.getMan().liiku(false, pacman);
        pacman.getLaskuri().kasvata(400);
    }

    private void manLiikuJaSyoPistepallo() {
        pacman.getMan().liiku(false, pacman);
        pacman.manSyoPistepallo();
    }

    private void liikutetaanManJaHaamuSamaanRuutuun() {
        pacman.getMan().setX(10);
        pacman.getMan().setY(7);
        pacman.getMan().setSuunta(Suunta.VASEN);
        pacman.getKasittelija().getGreen().setX(9);
        pacman.getKasittelija().getGreen().setY(8);
        pacman.getMan().liiku(false, pacman);
        pacman.getKasittelija().getGreen().liiku();
    }
    
    private void liikutetaanManJaHaamuSamaanRuutuunKunHaamutHeikkoja() {
        pacman.getMan().setX(10);
        pacman.getMan().setY(7);
        pacman.getMan().setSuunta(Suunta.VASEN);
        pacman.getKasittelija().getRed().setX(9);
        pacman.getKasittelija().getRed().setY(9);
        pacman.getKasittelija().getGreen().setX(9);
        pacman.getKasittelija().getGreen().setY(8);
        pacman.getMan().liiku(true, pacman);
        System.out.println(pacman.getMan().getX());
        System.out.println(pacman.getMan().getY());
        pacman.getKasittelija().getGreen().liiku();
    }
}
