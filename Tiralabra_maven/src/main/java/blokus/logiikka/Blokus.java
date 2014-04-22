package blokus.logiikka;

import blokus.conf.GlobaalitMuuttujat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import minimiKeko.Keko;
import linkitettyLista.Lista;

/**
 * Luo peliin tarvittavat komponentit. Hallinnoi vuoronvaihtoa ja välittää
 * tietoa eteenpäin.
 *
 * @author Simo Auvinen
 */
public class Blokus {

    private PeliLauta peliLauta;
    private Lista<Pelaaja> pelaajat;
    private Pelaaja vuorossa;
    private Keko<Integer> pelaajienPisteet;
    /**
     * Boolean arvo, joka kertoo onko peli vielä käynnissä
     */
    public boolean onkoPeliOhi;

    /**
     * Luo pelille peli laudan ja pelaajat. Lisää ihmis- ja AI pelaajat ja
     * alustaa pelaajien pisteet ja käynnistää ensimmäisen vuoron.
     *
     * @param pelaaja1 true, jos AI
     * @param pelaaja2 true, jos AI
     * @param pelaaja4 true, jos AI
     * @param pelaaja3 true, jos AI
     */
    public Blokus(boolean pelaaja1, boolean pelaaja2, boolean pelaaja3, boolean pelaaja4) {
        onkoPeliOhi = false;
        peliLauta = new PeliLauta();
        pelaajat = new Lista<Pelaaja>();
        pelaajienPisteet = new Keko<Integer>(20);
        lisaaPelaajat(pelaaja1, pelaaja2, pelaaja3, pelaaja4);
        alustaPelaajienPisteet();


    }

    /**
     * Metodi aloittaa uuden vuoron ottamalla jonosta seurraavan pelaajan,
     * kunhan jono ei ole tyhjä.
     */
    public void aloitaVuoro() {
        if (!pelaajat.isEmpty()) {

            vuorossa = pelaajat.poll();
            if (vuorossa.getOlenkoAi()) {
                vuorossa.getPelaajaAI().aloitaVuoro(peliLauta);
                if (vuorossa.getLaatat().getJaljellaLaatat().isEmpty()) {
                    lopetaVuoro(false, true);
                } else {
                    lopetaVuoro(false, false);
                }

            }
        }
    }

    /**
     * Lopettaa nykyisen vuoron. Päivittää vuoron lopettavan pisteet ja vaihtaa
     * pelaajan "kädessä" olevaa laattaa seuraavaan. Jos laattaa ei löydy
     * pelaaja antautuu automaattisesti. Jos pelaaja ei antaudu hänet lisätään
     * takaisin jonoon.
     *
     * @param ohita Jos pelaaja haluaa ohittaa vuoronsa niin true
     * @param antautuu Jos pelaaja antautuu niin true
     *
     */
    public void lopetaVuoro(Boolean ohita, Boolean antautuu) {

        if (!ohita) {
            paivitaVuoroaLopettavanPisteet();
            if (!vuorossa.getOlenkoAi()) {
                vuorossa.vaihdaValittuaSeuraavaan();
            }

            if (vuorossa.getValittuna() == null || vuorossa.getLaatat().getJaljellaLaatat().isEmpty()) {
                antautuu = true;
            }
        }
        if (!antautuu) {
            pelaajat.lisaaListaan(vuorossa);
        }
        if (pelaajat.isEmpty()) {
            onkoPeliOhi = true;
        } else {
            aloitaVuoro();
        }

    }

    /**
     * Päivittää HashMappiin pelaajan sen hetkiset pisteet.
     */
    public void paivitaVuoroaLopettavanPisteet() {
        // pelaajienPisteet.put(vuorossa.getPelaajantID(), vuorossa.getPisteet());
        pelaajienPisteet.laskeAvaimenArvoa(pelaajienPisteet.dataIndeksi(vuorossa.getPelaajantID()), vuorossa.getPisteet());
    }

    /**
     *
     * @param pelaajanID
     * @return palauttaa kyseisen pelaajan pisteet.
     */
    public int getPelaajanPisteet(int pelaajanID) {
        return pelaajienPisteet.dataaVastaavaKey(pelaajanID);
    }

    public PeliLauta getPeliLauta() {
        return peliLauta;
    }

    public Lista<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    public Pelaaja getVuorossa() {
        return vuorossa;
    }

    /**
     * Metodo palauttaa ID:tä vastaavan värin tekstinä.
     *
     * @param pelaajanID
     * @return String värin nimi tekstinä
     */
    public String getIDVariTekstina(int pelaajanID) {
        switch (pelaajanID) {
            case GlobaalitMuuttujat.SININEN:
                return "Sininen";
            case GlobaalitMuuttujat.ORANSSI:
                return "Oranssi";
            case GlobaalitMuuttujat.PUNAINEN:
                return "Punainen";
            case GlobaalitMuuttujat.VIHREA:
                return "Vihreä";
            default:
                return "";
        }
    }

    
    public Keko<Integer> getLopputulokset() {
        return pelaajienPisteet;
    }

    private void lisaaPelaajat(boolean p1, boolean p2, boolean p3, boolean p4) {
        Pelaaja pelaaja1 = new Pelaaja(1, p1);
        Pelaaja pelaaja2 = new Pelaaja(2, p2);
        Pelaaja pelaaja3 = new Pelaaja(3, p3);
        Pelaaja pelaaja4 = new Pelaaja(4, p4);
        pelaajat.lisaaListaan(pelaaja1);
        pelaajat.lisaaListaan(pelaaja2);
        pelaajat.lisaaListaan(pelaaja3);
        pelaajat.lisaaListaan(pelaaja4);
        peliLauta.lisaaTarkastusLauta(1, pelaaja1.getTarkastusLauta());
        peliLauta.lisaaTarkastusLauta(2, pelaaja2.getTarkastusLauta());
        peliLauta.lisaaTarkastusLauta(3, pelaaja3.getTarkastusLauta());
        peliLauta.lisaaTarkastusLauta(4, pelaaja4.getTarkastusLauta());

    }

    private void alustaPelaajienPisteet() {
        pelaajienPisteet.lisaaKekoon(89, 2);
        pelaajienPisteet.lisaaKekoon(89, 1);
        pelaajienPisteet.lisaaKekoon(89, 3);
        pelaajienPisteet.lisaaKekoon(89, 4);
    }
}


