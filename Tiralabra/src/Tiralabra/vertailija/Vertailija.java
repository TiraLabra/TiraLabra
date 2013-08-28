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
     * Syötteen koko: 100 = pieni, 1000 = keskikokoinen, 5000 = suuri;
     */
    private int syote;
    /**
     * Syötettävien alkioiden järjestys.
     */
    private boolean jarjestys;

    /**
     * Luo uuden vertailijan, oletuksena pieni syöte ja satunnaisjärjestys.
     */
    public Vertailija(int s, boolean j) {
        syote = s;
        jarjestys = j;
    }

    /**
     * Suorita puun eri operaatiot ja palauttaa tuloksista tekstiesityksen.
     *
     * @param pRand testattava puu, alkiot lisätään sattumanvaraisessa
     * järjestyksessä
     * @param pJarj testattava puu, alkiot lisätään suuruusjärjestyksessä
     * @return tulokset puun operaatioista
     */
    public String vertaile(Puu p) {
        Random r = new Random();
        String tulos = "";
        alkiot = new ALista();

        tulos += insert(p, r);

        tulos += search(p);

        tulos += tulostus(p);

        tulos += delete(p);

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
    private String insert(Puu p, Random r) {
        long insert = insertAika(r, p);

        return "\nNoin " + syote + " alkion lisääminen puuhun kesti "
                + ((insert / 1000)) + " mikrosekuntia.\n";
    }

    /**
     * Etsii puusta kymmenen pienintä alkiota ja palauttaa tekstiesityksen
     * niiden tehokkuudesta.
     *
     * @param p puu
     * @return tulosesitys etsinnän kestosta mikrosekunteina
     */
    private String search(Puu p) {
        long search = searchAika(p);

        return "\nKaikkien alkioiden löytäminen noin " + syote + " alkioiselta listalta kesti " + ((search / 1000)) + " mikrosekuntia.\n";
    }

    /**
     * Palauttaa puun alkioiden tulostuksen kestävän ajan.
     *
     * @param p puu
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
     * @param p puu
     * @return puun alkioiden poisto-operaatioiden kesto mikrosekunteina
     */
    private String delete(Puu p) {
        long deleteAika = deleteAika(p);

        return "\nKaikkien alkioiden poisto noin " + syote + " alkioiselta puulta kesti " + ((deleteAika) / 1000) + " mikrosekuntia.\n";
    }

    /**
     * Palauttaa ajan puuhun alkioiden lisäämisille.
     *
     * @param r satunnaisgeneraattori
     * @param p puu johon alkiot lisätään
     * @return operaatioiden kestämä aika
     */
    private long insertAika(Random r, Puu p) {
        long insert = 0;
        if (jarjestys) {
            insert = insertJarjestyksessa(r, p, insert);
        } else {
            insert = insertSatunnaisesti(r, p, insert);
        }
        return insert;
    }

    /**
     * Palauttaa alkioiden etsinnässä kuluneen ajan.
     *
     * @param p puu
     * @return operaatioiden kesto
     */
    private long searchAika(Puu p) {
        long searchJarj = 0;
        Listasolmu l2 = alkiot.getLista();
        for (int i = 0; i < alkiot.getKoko(); i++) {
            long aloita = System.nanoTime();
            p.search(l2.getArvo());
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

    /**
     * Etsii nopeimmat ja hitaimmat puut kullekkin operaatiolle.
     *
     * @param pm punamusta
     * @param th threaded
     * @param kk kaksikolme
     * @param tr treap
     * @return tekstiesitys tuloksista
     */
    public String vertaileKaikki(Puu pm, Puu th, Puu kk, Puu tr) {
        alkiot = new ALista();
        Random r = new Random();

        //Hakee ajat punamustapuille.
        long pmI = this.insertAika(r, pm);
        long pmD = this.deleteAika(pm);
        long pmS = this.searchAika(pm);
        long pmT = this.tulostaAika(pm);


        // Hakee ajat threaded-puille.
        alkiot = new ALista();
        long thI = this.insertAika(r, th);
        long thD = this.deleteAika(th);
        long thS = this.searchAika(th);
        long thT = this.tulostaAika(th);


        //Hakee ajat kaksikolme-puille.
        alkiot = new ALista();
        long kkI = this.insertAika(r, kk);
        long kkD = this.deleteAika(kk);
        long kkS = this.searchAika(kk);
        long kkT = this.tulostaAika(kk);


        // Hakee ajat Treap-rakenteille
        alkiot = new ALista();
        long trI = this.insertAika(r, tr);
        long trD = this.deleteAika(tr);
        long trS = this.searchAika(tr);
        long trT = this.tulostaAika(tr);

        return "Alkioiden lisääminen : " + vertaaKaikki(pmI, thI, kkI, trI)
                + "\nAlkioiden poistaminen puusta: " + vertaaKaikki(pmD, thD, kkD, trD)
                + "\nAlkioiden etsiminen puusta: " + vertaaKaikki(pmS, thS, kkS, trS)
                + "\nAlkioiden tulostaminen: " + vertaaKaikki(pmT, thT, kkT, trT);
    }

    /**
     * Palauttaa String-esityksen nopeimmista ja hitaimmista puista jonkun
     * tietyn operaation aikana.
     *
     * @param pm punamusta puun aika
     * @param th threaded puun aika
     * @param kk kaksikolme puun aika
     * @param tr treapin aika
     * @return hitain ja nopein
     */
    private String vertaaKaikki(long pm, long th, long kk, long tr) {
        long nopein = pm;
        long hitain = pm;

        String tuloshitain = " Punamusta";
        String tulosnopein = " Punamusta";

        if (th > hitain) {
            hitain = th;
            tuloshitain = " Threaded";
        }
        if (kk > hitain) {
            hitain = kk;
            tuloshitain = " Kaksikolme";
        }
        if (tr > hitain) {
            hitain = tr;
            tuloshitain = " Treap";
        }

        if (th < nopein) {
            nopein = th;
            tulosnopein = " Threaded";
        }
        if (kk < nopein) {
            nopein = kk;
            tulosnopein = " Kaksikolme";
        }
        if (tr < nopein) {
            nopein = tr;
            tulosnopein = " Treap";
        }

        nopein = nopein / 1000;
        hitain = hitain / 1000;

        return "\nNopein puu:" + tulosnopein + ", ajalla " + nopein + " mikrosenkuntia. \n"
                + "Hitain puu:" + tuloshitain + ", ajalla " + hitain + " mikrosekuntia. \n";
    }

    /**
     * Lisää puuhun noin syötteen verran satunnaisia numeroita.
     *
     * @param r satunnaisgeneraattori
     * @param p puu
     * @param insert kulunut aika nanosekunteina
     * @return kulunut aika nansekunteina
     */
    private long insertJarjestyksessa(Random r, Puu p, long insert) {
        long aloita;
        long lopeta;
        for (int i = 0; i < syote; i++) {
            alkiot.lisaa(r.nextInt());
        }
        Listasolmu ls = alkiot.getLista();
        for (int i = 0; i < alkiot.getKoko(); i++) {
            aloita = System.nanoTime();
            p.insert(ls.getArvo());
            lopeta = System.nanoTime();
            insert += lopeta - aloita;
            ls = ls.getNext();
        }
        return insert;
    }

    /**
     * Lisää puuhun noin syötteen verran numeroita suuruusjärjestyksessä.
     *
     * @param r satunnaisgeneraattori
     * @param p puu
     * @param insert kulunut aika nanosekunteina
     * @return kulunut aika nansekunteina
     */
    private long insertSatunnaisesti(Random r, Puu p, long insert) {
        int arvo;
        long aloita = 0;
        long lopeta = 0;
        for (int i = 0; i < syote; i++) {
            arvo = r.nextInt();
            alkiot.lisaa(arvo);
            aloita = System.nanoTime();
            p.insert(arvo);
            lopeta = System.nanoTime();
            insert += lopeta - aloita;
        }
        return insert;
    }
}