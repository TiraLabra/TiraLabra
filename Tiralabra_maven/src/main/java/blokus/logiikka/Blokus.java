package blokus.logiikka;

import blokus.conf.GlobaalitMuuttujat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Luo peliin tarvittavat komponentit. Hallinnoi vuoronvaihtoa ja välittää
 * tietoa eteenpäin.
 *
 * @author Simo Auvinen
 */
public class Blokus {

    private PeliLauta peliLauta;
    private Queue<Pelaaja> pelaajat;
    private Pelaaja vuorossa;
    private HashMap<Integer, Integer> pelaajienPisteet;
    /**
     * Boolean arvo, joka kertoo onko peli vielä käynnissä
     */
    public boolean onkoPeliOhi;

    /**
     * Luo pelille peli laudan ja pelaajat. Lisää pelaajat ja alustaa pelaajien
     * pisteet ja käynnistää ensimmäisen vuoron.
     */
    public Blokus(boolean pelaaja1, boolean pelaaja2, boolean pelaaja3, boolean pelaaja4) {
        onkoPeliOhi = false;
        peliLauta = new PeliLauta();
        pelaajat = new LinkedList<Pelaaja>();
        pelaajienPisteet = new HashMap<Integer, Integer>();
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
            
            if (vuorossa.getValittuna() == null) {
                antautuu = true;
            }
        }
        if (!antautuu) {
            pelaajat.add(vuorossa);
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
        pelaajienPisteet.put(vuorossa.getPelaajantID(), vuorossa.getPisteet());
    }

    /**
     *
     * @param pelaajanID
     * @return palauttaa kyseisen pelaajan pisteet.
     */
    public int getPelaajanPisteet(int pelaajanID) {
        return pelaajienPisteet.get(pelaajanID);
    }

    public PeliLauta getPeliLauta() {
        return peliLauta;
    }

    public Queue<Pelaaja> getPelaajat() {
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

    /**
     * Metodi järjestää HashMapissä oelvat pelaajien pisteet valuen mukaan
     * laskevaan järjestykseen.
     *
     * @return Järjestetty TreeMap
     */
    public TreeMap<Integer, Integer> getLopputulokset() {
        ValueComparator vertaaja = new ValueComparator(pelaajienPisteet);
        TreeMap<Integer, Integer> sorted_map = new TreeMap<Integer, Integer>(vertaaja);
        sorted_map.putAll(pelaajienPisteet);
        return sorted_map;

    }

    private void lisaaPelaajat(boolean p1, boolean p2, boolean p3, boolean p4) {
        Pelaaja pelaaja1 = new Pelaaja(1, p1);
        Pelaaja pelaaja2 = new Pelaaja(2, p2);
        Pelaaja pelaaja3 = new Pelaaja(3, p3);
        Pelaaja pelaaja4 = new Pelaaja(4, p4);
        pelaajat.add(pelaaja1);
        pelaajat.add(pelaaja2);
        pelaajat.add(pelaaja3);
        pelaajat.add(pelaaja4);
        peliLauta.lisaaTarkastusLauta(1, pelaaja1.getTarkastusLauta());
        peliLauta.lisaaTarkastusLauta(2, pelaaja2.getTarkastusLauta());
        peliLauta.lisaaTarkastusLauta(3, pelaaja3.getTarkastusLauta());
        peliLauta.lisaaTarkastusLauta(4, pelaaja4.getTarkastusLauta());

    }

    private void alustaPelaajienPisteet() {
        pelaajienPisteet.put(2, 89);
        pelaajienPisteet.put(1, 89);
        pelaajienPisteet.put(3, 89);
        pelaajienPisteet.put(4, 89);
    }
}

class ValueComparator implements Comparator<Integer> {

    Map<Integer, Integer> base;

    public ValueComparator(Map<Integer, Integer> base) {
        this.base = base;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        if (base.get(o1) <= base.get(o2)) {
            return -1;
        } else {
            return 1;
        }
    }
}
