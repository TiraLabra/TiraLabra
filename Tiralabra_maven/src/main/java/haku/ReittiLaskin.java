/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haku;

import verkko.Reitti;
import verkko.Kaari;
//import verkko.Linja;
import verkko.Pysakki;
import verkko.Verkko;
import verkko.rajapinnat.Edge;
import verkko.rajapinnat.Graph;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;

/**
 * A*-haun apuolio. Arvioi jäljellä olevaa kustannusta ja laskee kustannuksen
 * kustakin siirtymästä
 *
 * @author E
 */
public class ReittiLaskin implements Laskin {

    /**
     * Verkko, jossa laskentaa suoritetaan
     */
    private Verkko verkko;
    /**
     * Paino matka-ajan kustannukselle
     */
    private double aikaPaino;
    /**
     * Paino matkan pituuden kustannukselle
     */
    private double matkaPaino;
    /**
     * Odotusajan lisäksi lisätään tämä kustannukseen
     */
    private double vaihtoPaino;
    /**
     * Paino matkan pituudelle heuristiikkaa varten
     */
    private double heurMatkaPaino;
    /**
     * Paino matka-ajalle heuristiikkaa varten
     */
    private double heurAikaPaino;
    /**
     * Matka metreinä, aika minuutteina. Yksikkönä m/min (tai
     * karttapistettä/min)
     */
    private double heurKulkunopeus;

    // WIP: parametrien tarkistukset
    /**
     * Konstruktori omien arvojen asettamiseen
     *
     * @param aikaPaino Kustannuksen ajan painotus. Arvot epänegatiivisia
     * @param matkaPaino Kustannuksen matkan painotus. Arvot epänegatiivisia
     * @param vaihtoPaino Kustannuksen vaihdon painotus. Arvot epänegatiivisia.
     * Tämä odotusajan lisäksi kustannukseen
     * @param heurAikaPaino Heuristiikan ajan painotus. Arvot epänegatiivisia
     * @param heurMatkaPaino Heuristiikan matkan painotus. Arvot epänegatiivisia
     * @param heurKulkunopeus Heuristiikan arvioitu matkustusnopeus. Arvot
     * positiivisia
     */
    public ReittiLaskin(double aikaPaino, double matkaPaino, double vaihtoPaino, double heurAikaPaino, double heurMatkaPaino, double heurKulkunopeus) {
        this.aikaPaino = aikaPaino;
        this.matkaPaino = matkaPaino;
        this.vaihtoPaino = vaihtoPaino;
        this.heurMatkaPaino = heurMatkaPaino;
        this.heurAikaPaino = heurAikaPaino;
        this.heurKulkunopeus = heurKulkunopeus;
    }

    /**
     * Oletuskonstruktorilla painotetaan vain matka-aikaa
     */
    public ReittiLaskin() {
        // this(1,0,0,0,0,400);
        this.aikaPaino = 1;
        this.matkaPaino = 0;
        this.vaihtoPaino = 0;       // aina kun vaihdetaan linjaa, lisätään kustannukseen
        this.heurMatkaPaino = 0;
        this.heurAikaPaino = 0;     // jos h(x) == 0, saadaan tavallinen BFS
        // jos heurPainot siis == 0, kyseessä BFS: voi käyttää 
        // heuristiikkojen oikeellisuuden arviointiin (BFS vs ASTAR)
        this.heurKulkunopeus = 400; // 250 m / min -> 15km/h, 660 m / min -> 40km/h
        // kävely 100m/min -> 6km/h        
    }

    /**
     * Palauttaa annetussa verkossa solmun ja maalin välisen etäisyyden arvion.
     * Jotta toimisi, tulee olla h(n) pienempi/yhtäsuuri kuin d(n,k)+h(k).
     * Painojen kanssa siis pitää olla tarkkana.
     *
     * @param solmu
     * @param maali
     * @return
     */
    public double heuristiikka(Pysakki solmu, Pysakki maali) {
        // double etaisyys = Math.pow(solmu.getX() - maali.getX(), 2) + Math.pow(solmu.getY() - maali.getY(), 2);
        // etaisyys = Math.pow(etaisyys, 0.5);
        double etaisyys = maali.etaisyys(solmu);
        return heurAikaPaino * etaisyys / heurKulkunopeus + heurMatkaPaino * etaisyys;
    }

    /**
     * Laskee kaaren kulkemisen kustannuksen ottaen huomioon valitut
     * preferenssit
     *
     * @param kuljettu Reitti jota pitkin on edetty tähän
     * @param uusi Seuraavaksi kuljettava kaari
     * @return
     */
    public double kustannus(Reitti kuljettu, Kaari uusi) {
        return this.getOdotusAika(kuljettu, uusi) * aikaPaino // odotusaika 
                + uusi.getKustannus() * aikaPaino // aika
                + uusi.getEtaisyys() * matkaPaino // jos halutaan minimoida kuljettua matkaa
                + this.getOnkoVaihto(kuljettu, uusi) // jos halutaan minimoida vaihtoja
                + this.getSopivuus(uusi);                //
    }

    /**
     * Voidaan asettaa vältettäväksi tietyn tyyppiset linjat
     *
     * @param uusi Seuraavaksi kuljettava kaari
     * @return
     */
    public double getSopivuus(Kaari uusi) {
        double sopivuus = 0;
        /* // WIP
         Linja linja = verkko.getLinja( uusi.getLinjanNimi() );
         if ( linja!=null && linja.getTyyppi()!=null ) {
         if ( sopivuudet.hasKey(linja.getTyyppi()) )
         sopivuus+=sopivuudet.get(linja.getTyyppi());
         }
         */
        return sopivuus;
    }

    /**
     * Laskee uuden kaaren kulkemiseen liittyvän odotusajan. Jos pysytään
     * samalla linjalla, ei tarvitse laskea.
     *
     * @param kuljettu
     * @param uusi
     * @return
     */
    public double getOdotusAika(Reitti kuljettu, Kaari uusi) {
        double odotusAika = 0;

        if (kuljettu.getKuljettuKaari() == null
                || uusi.getLinjanNimi() == null
                || !kuljettu.getKuljettuKaari().getLinjanNimi().equals(uusi.getLinjanNimi())) { // vaihdetaan pysäkillä linjaa
            // lisätään odotusAikaan kaaren linjan saapumisajan ja tämänhetkisen ajan erotus
            odotusAika = verkko.getOdotusAika(kuljettu.getAika(), kuljettu.getSolmu().getKoodi(), uusi.getLinjanNimi());
        }
        return odotusAika;
    }

    /**
     * Jos reitillä on vaihto, palautetaan vaihtopaino.
     *
     * @param kuljettu
     * @param uusi
     * @return
     */
    public double getOnkoVaihto(Reitti kuljettu, Kaari uusi) {
        if (kuljettu.getKuljettuKaari() == null) {    // ensimmäinen pysäkki, ei vaihto
            return 0;
        } else if (uusi.getLinjanNimi() == null
                || !kuljettu.getKuljettuKaari().getLinjanNimi().equals(uusi.getLinjanNimi())) { // vaihdetaan pysäkillä linjaa
            return this.vaihtoPaino;
        }
        return 0;
    }

    /**
     * Testataan, toimiiko heuristiikka: heuristinen arvio saa olla korkeintaan
     * kahden verkon solmun välinen kustannus
     *
     * @return Heuristiikan onnistumisprosentti
     */
    public double toimiikoHeuristiikka() {
        double succ = 0, fail = 0;
        double all = 0;
        double maksVirhe = 0, virheSumma = 0;
        if (verkko == null) {
            return 1;
        }
        for (Pysakki p : verkko.getPysakit()) {
            for (Pysakki c : verkko.getNaapurit(p)) {
                for (Kaari k : verkko.getKaaret(p, c)) {
                    // mukana ei vaihto tai odotusaika: saadaan pienin kustannus
                    double kustannus = this.aikaPaino * k.getKustannus() + this.matkaPaino * k.getEtaisyys();
                    double arvio = this.heuristiikka(p, c);

                    if (arvio <= kustannus) {
                        succ++;
                    } else {
                        System.out.println("C:" + kustannus + ", H:" + arvio);
                        double virhe = arvio - kustannus;
                        virheSumma += virhe;
                        if (virhe > maksVirhe) {
                            maksVirhe = virhe;
                        }
                    }
                    all++;
                }
            }
        }
        double succP = succ / all;
        System.out.println("Onnistumiset: " + succP);
        System.out.println("Suurin virhe: " + maksVirhe);
        if (fail > 0) {
            System.out.println("Keskivirhe: " + (virheSumma / fail));
        }

        return succP;
    }

    /**
     * Tuottaa uuden reitti-olion, jossa kuljettava kaari on käsitelty.
     *
     * @param kuljettu Mistä tullaan
     * @param kaariNaapuriin Uusi kaari
     * @param seuraavaSolmu Mihin mennään
     * @param maali
     * @return
     */
    public Reitti laskeSeuraava(Reitti kuljettu, Kaari kaariNaapuriin, Pysakki seuraavaSolmu, Pysakki maali) {
        /*
         Kaarten käsittely: 
         * voi olla useita mahdollisia kaaria: esim. tien yli voi mennä, tai alikulun kautta, pysäkiltä toiselle pääsee monella bussilla
         -> Kaaret listana OK!
         * entä jos kustannus vaihtuu sen mukaan, milloin mennään (esim. bussipysäkillä odotusaika)
         -> Tilaan aika-muuttuja, kaareen tieto siitä miten vaihtelee ajan mukaan OK!
         * entä jos edellinen kaari vaikuttaa kustannukseen ( esim. ollaan jo bussissa matkalla eteenpäin, esim2. autolla suoraan: ei tarvitse u-käännöstä yms)
         -> Kaareen tieto tyypistä OK!               
         */
        Reitti seuraava = new Reitti();
        if (kuljettu == null) {
            seuraava.setKustannus(0);
            seuraava.setPrevious(null);
            seuraava.setAika(0);
            seuraava.setMatka(0);
        } else {
            seuraava.setKustannus(kuljettu.getKustannus() + kustannus(kuljettu, kaariNaapuriin));
            seuraava.setPrevious(kuljettu);
            seuraava.setAika(kuljettu.getAika() + kaariNaapuriin.getKustannus() + getOdotusAika(kuljettu, kaariNaapuriin));
            seuraava.setMatka(kuljettu.getMatka() + kaariNaapuriin.getEtaisyys());
        }

        seuraava.setArvioituKustannus(heuristiikka(seuraavaSolmu, maali));
        seuraava.setKuljettuKaari(kaariNaapuriin);
        seuraava.setSolmu(seuraavaSolmu);
        return seuraava;
    }
    /**
     * Laskin-rajapinnan toteutus.
     * 
     * @param current
     * @param kuljettuKaari
     * @param seuraava
     * @param maali
     * @return 
     */
    public Node laskeSeuraava(Node current, Edge kuljettuKaari, Value seuraava, Value maali) {
        return this.laskeSeuraava((Reitti)current, (Kaari)kuljettuKaari, (Pysakki)seuraava, (Pysakki)maali);
    }
    /**
     * Laskin-rajapinnan toteutus
     * 
     * @param verkko 
     */
    public void setVerkko(Graph verkko) {
        this.setVerkko((Verkko)verkko);
    }    
    /////////////////////////////////////////////
    ///// automaattiset setterit & getterit /////
    ///////////////////////////////////////////// 
    public Verkko getVerkko() {
        return verkko;
    }

    public void setVerkko(Verkko verkko) {
        this.verkko = verkko;
    }

    public double getAikaPaino() {
        return aikaPaino;
    }

    public void setAikaPaino(double aikaPaino) {
        this.aikaPaino = aikaPaino;
    }

    public double getMatkaPaino() {
        return matkaPaino;
    }

    public void setMatkaPaino(double matkaPaino) {
        this.matkaPaino = matkaPaino;
    }

    public double getVaihtoPaino() {
        return vaihtoPaino;
    }

    public void setVaihtoPaino(double vaihtoPaino) {
        this.vaihtoPaino = vaihtoPaino;
    }

    public double getHeurMatkaPaino() {
        return heurMatkaPaino;
    }

    public void setHeurMatkaPaino(double heurMatkaPaino) {
        this.heurMatkaPaino = heurMatkaPaino;
    }

    public double getHeurAikaPaino() {
        return heurAikaPaino;
    }

    public void setHeurAikaPaino(double heurAikaPaino) {
        this.heurAikaPaino = heurAikaPaino;
    }

    public double getHeurKulkunopeus() {
        return heurKulkunopeus;
    }

    public void setHeurKulkunopeus(double heurKulkunopeus) {
        this.heurKulkunopeus = heurKulkunopeus;
    }

    @Override
    public String toString() {
        return "ReittiLaskin{" + "aikaPaino=" + aikaPaino + ", matkaPaino=" + matkaPaino + ", vaihtoPaino=" + this.vaihtoPaino + ", heurMatkaPaino=" + heurMatkaPaino + ", heurAikaPaino=" + heurAikaPaino + ", heurKulkunopeus=" + heurKulkunopeus + '}';
    }





}
