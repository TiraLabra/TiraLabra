package pacman.peli;

import java.util.ArrayList;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Suunta;

/**
 * Hakuluokan on tarkoitus selvittää mihin peliruutuun haamujen on kannattavinta
 * liikahtaa saavuttaakseen tavoitteensa.
 *
 * @author Hanna
 */
public class Haku {

    private int parasSumma;
    private Peliruutu[] parasReitti;
    private boolean ekaKerta;

    /**
     * Konstruktori, jossa alustetaan totuusarvo, josta selviää onko kyseessä
     * hakukierroksen ensimmäinen kerta, jolloin tulee toimia hieman erilailla.
     */
    public Haku() {
        this.ekaKerta = true;

    }
    
    public int getParasSumma() {
        return this.parasSumma;
    }
    
    public Peliruutu[] getParasReitti() {
        return this.parasReitti;
    }
    

    /**
     * aStar on hakualgoritmi, joka palauttaa peliruudun johon haamun kannattaa
     * liikahtaa saavuttaakseen tavoitteensa. Parametrinaan aStar saa
     * lähtöruudun, maaliruudun ja alustan, jolla peli toimii. Algoritmin on
     * tarkoitus selvittää paras reitti lähtöruudun ja maaliruudun välille.
     *
     * @param lahto
     * @param maali
     * @param alusta
     * @return Peliruutu, johon haamun kuuluu liikahtaa.
     */
    public Peliruutu aStar(Peliruutu lahto, Peliruutu maali, Pelialusta alusta) {
        alustus(alusta, maali);
        parasReitti = new Peliruutu[1];
        parasSumma = Integer.MAX_VALUE;

        lahto.setEtaisyysAlkuun(0);

        Peliruutu[] solmulista = etsiNaapurit(alusta, lahto);

        Jarjestaja jarjesta = new Jarjestaja(solmulista);
        jarjesta.mergeSort(0, solmulista.length - 1);

        Peliruutu[] reitti = new Peliruutu[1];

        liiku(lahto, alusta, 1, maali, reitti, 0, solmulista, jarjesta);

        this.ekaKerta = true;
        return parasReitti[1];
    }

    /**
     * Metodi etsii tietyn peliruudun kaikki sopiva naapurit, eli ne jotka eivät
     * ole seiniä ja ovat suoraan oikealla, vasemmalla, ylhäällä tai alhaalla.
     *
     * @param alusta
     * @param ruutu
     * @param solmulista
     * @return Taulukko, josta selviää mahdolliset naapurit.
     */
    private Peliruutu[] etsiNaapurit(Pelialusta alusta, Peliruutu ruutu) {
        ArrayList<Peliruutu> naapurit = new ArrayList<Peliruutu>();

        for (Suunta suunta : Suunta.values()) {
            haeNaapuriAnnetustaSuunnasta(alusta, ruutu, naapurit, suunta);
        }

        Peliruutu[] solmulista = naapuriListaTaulukoksi(naapurit);

        return solmulista;
    }
    
    /**
     * Metodi muuttaa listan, johon on tallennettu sopivat naapurit, taulukoksi.
     * @param naapurit
     * @return Palauttaa taulukon, jossa sopivat naapurit.
     */
    private Peliruutu[] naapuriListaTaulukoksi(ArrayList<Peliruutu> naapurit) {
        Peliruutu[] uusiLista = new Peliruutu[naapurit.size()];
        for (int i = 0; i < naapurit.size(); i++) {
            uusiLista[i] = naapurit.get(i);
        }
        return uusiLista;
    }

    /**
     * Katsoo onko annetussa suunnassa oleva ruutu sellainen mihin voi liikkua.
     *
     * @param alusta
     * @param ruutu
     * @param solmulista
     * @param suunta
     */
    private void haeNaapuriAnnetustaSuunnasta(Pelialusta alusta, Peliruutu ruutu, ArrayList<Peliruutu> naapurit, Suunta suunta) {
        Peliruutu haettava = alusta.getPeliruutu(ruutu.getX() + suunta.getX(), ruutu.getY() + suunta.getY());
        if (haettava.getRuudunTyyppi() != 0) {
            naapurit.add(haettava);
        }
    }

    /**
     * Metodi alustaa pelialustan siten, että asettaan jokaiselle ruudulle
     * etäisyysarvion maaliruutuun ja alkuruutuun suuren luvun.
     *
     * @param alusta
     * @param maali
     */
    public void alustus(Pelialusta alusta, Peliruutu maali) {
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                if (alusta.getPeliruutu(x, y).getRuudunTyyppi() != 0) {
                    alusta.getPeliruutu(x, y).setEtaisyysAlkuun(2000000000);
                    alusta.getPeliruutu(x, y).setEtaisyysMaaliin(etaisyys(maali, alusta.getPeliruutu(x, y)));
                }
            }
        }
    }

    /**
     * Metodi selvittää rekursiivisesti parhaimman reitin lähtöruudusta maaliin.
     *
     * @param nykyinen
     * @param alusta
     * @param summa
     * @param maali
     * @param reitti
     * @param indeksi
     * @param solmulista
     * @param jar
     */
    public void liiku(Peliruutu nykyinen, Pelialusta alusta, int summa, Peliruutu maali, Peliruutu[] reitti, int indeksi, Peliruutu[] solmulista, Jarjestaja jar) {

        if (nykyinen == null || ollaankoMaalissa(nykyinen, maali, summa, reitti, indeksi)) {
            return;
        }
        if (this.ekaKerta == true) {
            this.ekaKerta = false;
        } else {
            summa++;
            if (nykyinen.getEtaisyysAlkuun() < summa || summa >= parasSumma) {
                return;
            }

            solmulista = etsiNaapurit(alusta, nykyinen);

            jar.setLista(solmulista);
            jar.mergeSort(0, solmulista.length - 1);
            nykyinen.setEtaisyysAlkuun(summa);
        }

        reitti[indeksi] = nykyinen;
        indeksi++;
        Peliruutu[] reittiKopio = reitistaKopioReitti(reitti);

        Peliruutu[] uusiLista = new Peliruutu[1];
        nykyinen = solmulista[0];

        liikutaNiinKauanKunOnNaapureita(uusiLista, solmulista, nykyinen, alusta, summa, maali, reittiKopio, indeksi, jar);

    }
    /**
     * Metodi kutsuu rekursiivisesti liiku-metodia, niin kauan kun tietyllä ruudulla on naapureita.
     * @param uusiLista
     * @param solmulista
     * @param nykyinen
     * @param alusta
     * @param summa
     * @param maali
     * @param reittiKopio
     * @param indeksi
     * @param jar 
     */
    private void liikutaNiinKauanKunOnNaapureita(Peliruutu[] uusiLista, Peliruutu[] solmulista, Peliruutu nykyinen, Pelialusta alusta, int summa, Peliruutu maali, Peliruutu[] reittiKopio, int indeksi, Jarjestaja jar) {
        while (uusiLista.length != 0) {
            uusiLista = paivitaListanPituus(solmulista, uusiLista);

            liiku(nykyinen, alusta, summa, maali, reittiKopio, indeksi, uusiLista, jar);

            if (uusiLista.length > 0) {
                nykyinen = uusiLista[0];
                solmulista = uusiLista;
            }
        }
    }

    /**
     * Metodi päivittää taulukon pituuden oikeaksi, kun sieltä otetaan yksi ruutu nykyiseksi.
     * @param solmulista
     * @param uusiLista
     * @return Palauttaa listan, jonka kokoa on vähennetty yhdellä ja kaikkia ruutuja on siirretty yhdellä indeksillä eteenpäin.
     */
    private Peliruutu[] paivitaListanPituus(Peliruutu[] solmulista, Peliruutu[] uusiLista) {
        if (solmulista.length > 1) {
            uusiLista = new Peliruutu[solmulista.length - 1];

            for (int i = 0; i < uusiLista.length; i++) {
                uusiLista[i] = solmulista[i + 1];
            }
        } else {
            uusiLista = new Peliruutu[0];
        }
        return uusiLista;
    }

    /**
     * Metodi tekee reitistä kopion.
     *
     * @param reitti
     * @return Palauttaa taulukon, joka on kopio reitistä.
     */
    private Peliruutu[] reitistaKopioReitti(Peliruutu[] reitti) {
        Peliruutu[] reittiKopio = new Peliruutu[reitti.length + 1];
        System.arraycopy(reitti, 0, reittiKopio, 0, reitti.length);
        return reittiKopio;
    }

    /**
     * Tarkistaan ollaanko jo päästy maaliruutuun, jos ollaan palautetaan true.
     * Jos ei vielä olla maalissa palautetaan false.
     *
     * @param nykyinen
     * @param maali
     * @param summa
     * @param reitti
     * @param indeksi
     * @return Palauttaa totuusarvon, sen perusteella ollaanko maalissa vai ei.
     */
    private boolean ollaankoMaalissa(Peliruutu nykyinen, Peliruutu maali, int summa, Peliruutu[] reitti, int indeksi) {
        if (nykyinen.equals(maali)) {
            if (summa <= parasSumma) {
                parasSumma = summa;
                reitti[indeksi] = nykyinen;
                parasReitti = new Peliruutu[reitti.length];
                System.arraycopy(reitti, 0, parasReitti, 0, reitti.length);
            }
            return true;
        }
        return false;
    }

    /**
     * Metodi laskee etäisyysarvion maalin ja kyseisen ruudun välille.
     *
     * @param maali
     * @param ruutu
     * @return Palauttaa int arvon joka on etäisyysarvio.
     */
    public int etaisyys(Peliruutu maali, Peliruutu ruutu) {

        int arvioX = Math.abs(ruutu.getX() - maali.getX());
        int arvioY = Math.abs(ruutu.getY() - maali.getY());
        int etaisyysarvio = arvioX + arvioY;

        return etaisyysarvio;
    }

}
