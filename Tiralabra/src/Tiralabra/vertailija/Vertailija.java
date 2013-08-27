package Tiralabra.vertailija;

import Tiralabra.domain.Puu;
import Tiralabra.util.ALista;
import Tiralabra.util.Listasolmu;
import java.util.Random;

/**
 * Rakentaa halutut tietorakenteet ja vertaa niiden tehokkuutta valituilla
 * algoritmeilla ja syötteillä.
 *
 * @author Pia Pakarinen
 */
public class Vertailija {

    /**
     * Puun sisältämät alkiot.
     */
    private ALista alkiot;
    /**
     * Syötteen koko: 100 = pieni, 10 000 = keskikokoinen, 1 000 000 = suuri;
     */
    private int syote;

    /**
     * Luo uuden vertailijan, oletuksena pieni syöte.
     */
    public Vertailija(int s) {
        syote = s;
    }

    /**
     * Asettaa syötteen koon.
     *
     * @param syote syötteen koko
     */
    public void setSyote(int syote) {
        this.syote = syote;
    }

    /**
     * Suorita puun eri operaatiot ja palauttaa tuloksista tekstiesityksen.
     *
     * @param pRand testattava puu, alkiot lisätään sattumanvaraisessa
     * järjestyksessä
     * @param pJarj testattava puu, alkiot lisätään suuruusjärjestyksessä
     * @return tulokset puun operaatioista
     */
    public String vertaile(Puu pRand, Puu pJarj) {
        Random r = new Random();
        String tulos = "";
        alkiot = new ALista();

        tulos += insert(pRand, pJarj, r);

        tulos += search(pRand);

        tulos += tulostus(pRand);

        tulos += delete(pRand);

        return tulos;
    }

    /**
     * Palauttaa tekstiesityksen puun rakentamisessa kuluneesta ajasta. Tietty
     * arvo voidaan sisällyttää puuhun vain kerran.
     *
     * @param p puu johon alkiot lisätään sattumanvaraisesti
     * @param pJarj puu johon alkiot lisätään järjestyksessä
     * @param r luo sattumanvaraisia numeroita
     * @return tulosesitys puuhun alkioiden lisäämisestä mikrosekunteina
     */
    private String insert(Puu p, Puu pJarj, Random r) {
        long insertRandom = insertRandomAika(r, p);
        long insertJarj = insertJarjestyksessaAika(pJarj);

        return "\nNoin " + syote + " alkion lisääminen puuhun sattumanvaraisessa järjestyksessä kesti "
                + ((insertRandom / 1000)) + " mikrosekuntia. \nNoin " + syote + " alkion lisääminen"
                + " puuhun järjestyksessä kesti " + ((insertJarj / 1000)) + " mikrosekuntia.\n";
    }

    /**
     * Etsii puusta kymmenen pienintä alkiota ja palauttaa tekstiesityksen
     * niiden tehokkuudesta.
     *
     * @param pR puu, johon alkiot on lisätty satunnaisjärjestyksessä
     * @return tulosesitys etsinnän kestosta mikrosekunteina
     */
    private String search(Puu pR) {
        long searchJarj = searchAika(pR);

        return "\nKaikkien alkioiden löytäminen noin " + syote + " alkioiselta listalta kesti " + ((searchJarj / 1000)) + " mikrosekuntia.\n";
    }

    /**
     * Palauttaa puun alkioiden tulostuksen kestävän ajan.
     *
     * @param p satunnaisjärjestyksessä oleva puu
     * @return puun alkoiden tulostuksen kestämä aika mikrosekunteina
     */
    private String tulostus(Puu p) {
        long tulostus = tulostaAika(p);

        return "\nAlkioiden tulostus noin " + syote + " alkioisella puulla kesti " + tulostus + " mikrosekuntia.\n";
    }

    /**
     * Poistaa puusta kaikki sen alkiot ja palauttaa operaatioiden kestämän ajan
     * mikrosekunteina.
     *
     * @param p puu, jonka alkiot on lisätty satunnaisjärjestyksesä
     * @return puun alkioiden poisto-operaatioiden kesto mikrosekunteina
     */
    private String delete(Puu p) {
        long deleteAika = deleteAika(p);

        return "\nKaikkien alkioiden poisto noin " + syote + " alkioiselta puulta kesti " + ((deleteAika) / 1000) + " mikrosekuntia.\n";
    }

    /**
     * Palauttaa ajan sattumanvaraisille puuhun lisäämisille.
     *
     * @param r satunnaisgeneraattori
     * @param p puu johon alkiot lisätään
     * @return operaatioiden kestämä aika
     */
    private long insertRandomAika(Random r, Puu p) {
        long insertRandom = 0;
        long aloita;
        long lopeta;
        int arvo;
        for (int i = 0; i < syote; i++) {
            arvo = r.nextInt();
            if (!p.search(arvo)) {
                alkiot.lisaa(arvo);
            }
            aloita = System.nanoTime();
            p.insert(arvo);
            lopeta = System.nanoTime();
            insertRandom += lopeta - aloita;
        }
        return insertRandom;
    }

    /**
     * Palauttaa ajan, joka kestää alkioiden lisäämisessä puuhun järjestyksessä.
     *
     * @param l lista alkioista
     * @param pJarj puu, johon alkiot lisätään
     * @return puuhun alkioiden lisäämisessä kestävä aika
     */
    private long insertJarjestyksessaAika(Puu pJarj) {
        long aloita = 0;
        long lopeta = 0;
        long insertJarj = 0;
        Listasolmu l2 = alkiot.getLista();
        while (l2 != null) {
            aloita = System.nanoTime();
            pJarj.insert(l2.getArvo());
            lopeta = System.nanoTime();
            insertJarj += lopeta - aloita;
            l2 = l2.getNext();
        }
        return insertJarj;
    }

    /**
     * Palauttaa alkioiden etsinnässä kuluneen ajan.
     *
     * @param pR puu satunnaisjärjestyksessä
     * @return operaatioiden kesto
     */
    private long searchAika(Puu pR) {
        long searchJarj = 0;
        Listasolmu l2 = alkiot.getLista();
        for (int i = 0; i < alkiot.getKoko(); i++) {
            long aloita = System.nanoTime();
            pR.search(l2.getArvo());
            long lopeta = System.nanoTime();
            searchJarj += lopeta - aloita;
        }
        return searchJarj;
    }

    /**
     * Poistaa puusta alkoit, palauttaa operaatioiden keston
     *
     * @param p käsiteltävä puu
     * @return operaatioiden kestämä aika
     */
    private long deleteAika(Puu p) {
        Listasolmu l2 = alkiot.getLista();
        long deleteAika = 0;
        long aloita;
        long lopeta;
        while (l2 != null) {
            aloita = System.nanoTime();
            p.delete(l2.getArvo());
            lopeta = System.nanoTime();
            deleteAika += (lopeta - aloita);
            l2 = l2.getNext();
        }
        return deleteAika;
    }

    /**
     * Palauttaa alkioiden tulostuksessa kulutetun ajan.
     *
     * @param p käsiteltävä puu
     * @return operaatioon mennyt aika
     */
    private long tulostaAika(Puu p) {
        long aloita = System.nanoTime();
        p.tulostaArvot();
        long lopeta = System.nanoTime();
        return lopeta - aloita;
    }

    public String vertaileKaikki(Puu pm, Puu th, Puu kk, Puu tr) {
        alkiot = new ALista();
        Random r = new Random();
        
        /*
         * Hakee ajat punamustapuille.
         */
        long pmIr = this.insertRandomAika(r, pm);
        long pmIJ = this.insertJarjestyksessaAika(pm);
        long pmD = this.deleteAika(pm);
        long pmS = this.searchAika(pm);
        long pmT = this.tulostaAika(pm);
        
        /*
         * Hakee ajat threaded-puille.
         */
        alkiot = new ALista();
        long thIr = this.insertRandomAika(r, th);
        long thIJ = this.insertJarjestyksessaAika(th);
        long thD = this.deleteAika(th);
        long thS = this.searchAika(th);
        long thT = this.tulostaAika(th);
        
        /*
         * Hakee ajat kaksikolme-puille.
         */
        alkiot = new ALista();
        long kkIr = this.insertRandomAika(r, kk);
        long kkIJ = this.insertJarjestyksessaAika(kk);
        long kkD = this.deleteAika(kk);
        long kkS = this.searchAika(kk);
        long kkT = this.tulostaAika(kk);
        
        /*
         * Hakee ajat Treap-rakenteille.
         */
        alkiot = new ALista();
        long trIr = this.insertRandomAika(r, tr);
        long trIJ = this.insertJarjestyksessaAika(tr);
        long trD = this.deleteAika(tr);
        long trS = this.searchAika(tr);
        long trT = this.tulostaAika(tr);
        
        return insertJarjKaikki(pmIJ, thIJ, kkIJ, trIJ);
    }

    /**
     * Palauttaa String-esityksen kaikkein nopeinten ja hitainten alkioita järjestyksessä lisäävistä puista.
     * @param pmIJ punamusta puun aika
     * @param thIJ threaded puun aika
     * @param kkIJ kaksikolme puun aika
     * @param trIJ treapin aika
     * @return hitain ja nopein
     */
    private String insertJarjKaikki(long pmIJ, long thIJ, long kkIJ, long trIJ) {
       long nopein = pmIJ;
       long hitain = pmIJ;
       if (thIJ > hitain){
           hitain = thIJ;
       }
    }
}