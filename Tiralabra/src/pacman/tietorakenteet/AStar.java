package pacman.tietorakenteet;

import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Suunta;

/**
 * AStar on luokka, joka selvittää astar-haun avulla parhaan reitin lähtoruudusta maaliruutuun.
 * @author Hanna
 */
public class AStar {

    /**
     * Taulukko johon kerätään parasreitti.
     */
    private Peliruutu[] reitti;
    /**
     * Taulukko, johon kerätään kaikki käydyt ruudut.
     */
    private Peliruutu[] kaydyt;
    /**
     * Taulukko, johon on kerättynä kaikki käymättömät ruudut, jotko eivät ole seiniä.
     */
    private Peliruutu[] kaymattomat;

    public Peliruutu[] getReitti() {
        return this.reitti;
    }
    
    public Peliruutu[] getKaydyt() {
        return this.kaydyt;
    }
    
    public Peliruutu[] getKaymattomat() {
        return this.kaymattomat;
    }
    
    /**
     * astar on metodi, joka käynnistää haun.
     * Ensin etsitään sopivat ruudut pelialustalta listaan ja muodostetaan listasta taulukko jota myöhemmin järjestäjä osaa käsitellä.
     * Alustetaan ruuduille tarvittavat arvot ja asetataan lähtöruudun etäisyydeksi lähtöön 0.
     * Kutsutaan metodia liiku, joka varsinaisesti selvittää reittiä ja lopuksi muodostetaan varsinainen paras reitti.
     * @param alusta
     * @param lahto
     * @param maali
     */
    public void astar(Pelialusta alusta, Peliruutu lahto, Peliruutu maali) {
        Lista sopivatSolmut = new Lista();
        lisaaSopivatSolmut(alusta, sopivatSolmut);
        muunnaSopivatListaKaymattomatTaulukoksi(sopivatSolmut);

        alustus(maali);

        lahto.setEtaisyysAlkuun(0);
        kaydyt = new Peliruutu[0];
        liiku(maali, alusta);
        muodostaReitti(maali, lahto);
    }

    /**
     * Muodostetaan reitti aloittaen maali solmusta ja käymällä reittiä läpi peruuttaen.
     * Jokainen ruutu tietää edellisensä eli sen ruudun josta on tultu käsiteltävään ruutuun lyhyintä reittiä.
     * @param maali
     * @param lahto 
     */
    private void muodostaReitti(Peliruutu maali, Peliruutu lahto) {
        Peliruutu solmu = maali;
        reitti = new Peliruutu[0];

        while (!solmu.equals(lahto)) {
            lisaaRuutuReittiin(solmu);
            solmu = solmu.getEdellinen();
        }
    }

    /**
     * Muunnetaan lista taulukoksi, jotta käsiteltävää tietoa on helpompi käsitellä jatkossa.
     * @param sopivatSolmut
     */
    public void muunnaSopivatListaKaymattomatTaulukoksi(Lista sopivatSolmut) {
        kaymattomat = new Peliruutu[sopivatSolmut.koko()];
        for (int i = 0; i < kaymattomat.length; i++) {
            Peliruutu ruutu = (Peliruutu) sopivatSolmut.getAlkio(i);
            kaymattomat[i] = ruutu;
        }
    }

    /**
     * Lisätään ruutu reittiin.
     * Koska reitti on taulukko, täytyy joka lisäys kerralla luoda uusi taulukko, joka on yhtä suurempi.
     * Uuteen taulukkoon kopiodaan vanhat tiedot ja lisätään uuteen taulukon kohtaan uusi reitin osa.
     * @param solmu 
     */
    private void lisaaRuutuReittiin(Peliruutu solmu) {
        Peliruutu[] uusiReitti = new Peliruutu[reitti.length + 1];
        System.arraycopy(reitti, 0, uusiReitti, 0, reitti.length);
        uusiReitti[reitti.length] = solmu;
        reitti = uusiReitti;
    }

    /**
     * Metodi etenee pelialustaa pitkin tutkien mikä on paras reitti lähdöstä maaliin.
     * Alussa järjestetään käymättömät siten, että ensimmäiseen taulukon kohtaan tulee peliruutu, jolla on lyhin arvio matkasta lähdön ja maalin välillä.
     * Paras ruutu otetaan talteen, poistetaan se käymättömien taulukosta, lisätään se käytyihin ja etsitään ruudulle naapurit joille sitten asetetaan
     * maldolliset uudet etäisyystiedot.
     * @param maali
     * @param alusta 
     */
    private void liiku(Peliruutu maali, Pelialusta alusta) {
        while (!tarkistaSisaltaakoMaalin(maali)) {
            jarjestaKaymattomat(kaymattomat);

            Peliruutu lyhinMatka = kaymattomat[0];

            pollaaPienin();
            lisaaKaytyihin(lyhinMatka);

            Lista naapurit = new Lista();
            selvitaNaapurit(alusta, lyhinMatka, naapurit);
            relax(naapurit, lyhinMatka);

        }
    }

    /**
     * Luodaan uusi järjestäjä, jolle annetaan parametrina järjestettävä taulukko.
     * Tämän jälkeen järjestetään taulukko.
     * @param kaymattomat 
     */
    private void jarjestaKaymattomat(Peliruutu[] kaymattomat) {
        Jarjestaja jar = new Jarjestaja(kaymattomat);
        jar.mergeSort(0, kaymattomat.length - 1);
    }

    /**
     * Alustetaan jokaiselle sopiviin ruutuihin kuuluvalle ruudulle tarvittavat tiedot.
     * Etaisyysalkuun on ääretön, etäisyysmaaliin lasketaan arvioimalla etäisyys ja edellinen asetetaan null arvoksi, koska sitä ei vielä tiedetä.
     * @param maali
     */
    public void alustus(Peliruutu maali) {
        for (Peliruutu kaymaton : kaymattomat) {
            kaymaton.setEtaisyysAlkuun(2000000000);
            kaymaton.setEtaisyysMaaliin(etaisyys(maali, kaymaton));
            kaymaton.setEdellinen(null);
        }
    }

    /**
     * Jokaiselle käsiteltävän ruudun naapurille katsotaan voiko sen etäisyydeksi alkuun asettaa paremman arvon ja jos voi asetaan myös, että sen edellinen on käsiteltävä ruutu.
     * @param naapurit
     * @param lyhinMatka 
     */
    private void relax(Lista naapurit, Peliruutu lyhinMatka) {
        for (int i = 0; i < naapurit.koko(); i++) {
            Peliruutu naapuri = (Peliruutu) naapurit.getAlkio(i);
            if (naapuri.getEtaisyysAlkuun() > lyhinMatka.getEtaisyysAlkuun() + 1) {
                naapuri.setEtaisyysAlkuun(lyhinMatka.getEtaisyysAlkuun() + 1);
                naapuri.setEdellinen(lyhinMatka);
            }
        }
    }

    /**
     * Selvitetään käsiteltävän ruudun naapurit.
     * Sopivia naapureita ovat ruudut, jotka eivät ole tyypiltään seiniä.
     * @param alusta
     * @param lyhinMatka
     * @param naapurit
     */
    public void selvitaNaapurit(Pelialusta alusta, Peliruutu lyhinMatka, Lista naapurit) {
        for (Suunta suunta : Suunta.values()) {
            if (alusta.getPeliruutu(lyhinMatka.getX() + suunta.getX(), lyhinMatka.getY() + suunta.getY()).getRuudunTyyppi() != 0) {
                naapurit.lisaa(alusta.getPeliruutu(lyhinMatka.getX() + suunta.getX(), lyhinMatka.getY() + suunta.getY()));
            }
        }
    }

    /**
     * Lisätään käsiteltävä ruutu käytyihin ruutuihin.
     * @param lyhinMatka 
     */
    private void lisaaKaytyihin(Peliruutu lyhinMatka) {
        Peliruutu[] uusiKaydyt = new Peliruutu[kaydyt.length + 1];
        System.arraycopy(kaydyt, 0, uusiKaydyt, 0, kaydyt.length);
        uusiKaydyt[kaydyt.length] = lyhinMatka;
        kaydyt = uusiKaydyt;
    }

    /**
     * Pistetaan käymättömät taulukosta käsiteltäväksi valitty peliruutu, jolla oli pienin matka-arvio.
     */
    private void pollaaPienin() {
        Peliruutu[] uusiKaymattomat = new Peliruutu[kaymattomat.length - 1];
        for (int i = 0; i < uusiKaymattomat.length; i++) {
            uusiKaymattomat[i] = kaymattomat[i + 1];
        }
        kaymattomat = uusiKaymattomat;
    }

    /**
     * Käydään pelialusta läpi ja asetataan sopivaksi ruuduksi kaikki ne, jotka eivät ole seiniä.
     * @param alusta
     * @param sopivatSolmut
     */
    public void lisaaSopivatSolmut(Pelialusta alusta, Lista sopivatSolmut) {
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                if (alusta.getPeliruutu(x, y).getRuudunTyyppi() != 0) {
                    sopivatSolmut.lisaa(alusta.getPeliruutu(x, y));
                }
            }
        }
    }

    /**
     * Lasketaan etäisyysarvio maaliin annetulle ruudulle.
     * @param maali
     * @param ruutu
     * @return
     */
    public int etaisyys(Peliruutu maali, Peliruutu ruutu) {

        int arvioX = Math.abs(ruutu.getX() - maali.getX());
        int arvioY = Math.abs(ruutu.getY() - maali.getY());
        int etaisyysarvio = arvioX + arvioY;

        return etaisyysarvio;
    }

    /**
     * Tarkistetaan sisältääkö käydyt taulukko maaliruudun.
     * @param maali
     * @return palauttaa true, jos sisältää.
     */
    private boolean tarkistaSisaltaakoMaalin(Peliruutu maali) {
        for (Peliruutu kayty : kaydyt) {
            if (kayty.equals(maali)) {
                return true;
            }
        }
        return false;
    }

}
