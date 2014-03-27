package pacman.peli;

import java.util.ArrayList;
import java.util.PriorityQueue;
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

    /**
     * aStar on hakualgoritmi, joka palauttaa peliruudun johon haamun kannattaa
     * liikahtaa saavuttaakseen tavoitteensa. Parametrinaan aStar saa
     * lähtöruudun, maaliruudun ja alustan jolla peli toimii. Algoritmin on
     * tarkoitus selvittää paras reitti lähtöruudun ja maaliruudun välille.
     *
     * @param lahto
     * @param maali
     * @param alusta
     * @return
     */
    public Peliruutu aStar(Peliruutu lahto, Peliruutu maali, Pelialusta alusta) {
        alustus(alusta, maali);
        parasSumma = Integer.MAX_VALUE;

        lahto.setEtaisyysAlkuun(0);

        ArrayList<Peliruutu> kaydyt = new ArrayList<Peliruutu>();

        PriorityQueue<Peliruutu> solmulista = new PriorityQueue<Peliruutu>();

        etsiNaapurit(alusta, lahto, solmulista);
        Peliruutu[] reitti = new Peliruutu[1];

        liiku(lahto, alusta, 1, maali, reitti, 0, solmulista, kaydyt);
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
     */
    private void etsiNaapurit(Pelialusta alusta, Peliruutu ruutu, PriorityQueue<Peliruutu> solmulista) {
        haeNaapuriAnnetustaSuunnasta(alusta, ruutu, solmulista, Suunta.YLOS);
        haeNaapuriAnnetustaSuunnasta(alusta, ruutu, solmulista, Suunta.ALAS);
        haeNaapuriAnnetustaSuunnasta(alusta, ruutu, solmulista, Suunta.VASEN);
        haeNaapuriAnnetustaSuunnasta(alusta, ruutu, solmulista, Suunta.OIKEA);
    }
    
    /**
     * Katsoo onko annetussa suunnassa oleva ruutu sellainen mihin voi liikkua.
     * 
     * @param alusta
     * @param ruutu
     * @param solmulista
     * @param suunta 
     */

    private void haeNaapuriAnnetustaSuunnasta(Pelialusta alusta, Peliruutu ruutu, PriorityQueue<Peliruutu> solmulista, Suunta suunta) {
        Peliruutu haettava = alusta.getPeliruutu(ruutu.getX() + suunta.getX(), ruutu.getY() + suunta.getY());
        if (haettava.getRuudunTyyppi() != 0) {
            solmulista.add(haettava);
        }
    }

    /**
     * Metodi alustaa pelialustan siten, että asettaan jokaiselle ruudulle
     * etäisyysarvion maaliruutuun.
     *
     * @param alusta
     * @param maali
     */
    public void alustus(Pelialusta alusta, Peliruutu maali) {
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                if (alusta.getPeliruutu(x, y).getRuudunTyyppi() != 0) {
                    alusta.getPeliruutu(x, y).setEtaisyysAlkuun(Integer.MAX_VALUE);
                    alusta.getPeliruutu(x, y).setEtaisyysMaaliin(etaisyys(maali, alusta.getPeliruutu(x, y)));
                }
            }
        }
    }

    /**
     *
     * Metodi selvittää rekursiivisesti parhaimman reitin lähtöruudusta maaliin.
     *
     * @param nykyinen
     * @param alusta
     * @param summa
     * @param maali
     * @param reitti
     * @param indeksi
     * @param solmulista
     * @param kaydyt
     */
    public void liiku(Peliruutu nykyinen, Pelialusta alusta, int summa, Peliruutu maali, Peliruutu[] reitti, int indeksi, PriorityQueue<Peliruutu> solmulista, ArrayList<Peliruutu> kaydyt) {

        if (nykyinen == null) {
            return;
        } else if (ollaankoMaalissa(nykyinen, maali, summa, reitti, indeksi)) {
            return;
        } else if (this.ekaKerta == true) {
            this.ekaKerta = false;
        } else {
            summa += 1;
            if (nykyinen.getEtaisyysAlkuun() < summa || summa >= parasSumma) {
                return;
            }
            solmulista = new PriorityQueue<Peliruutu>();
            etsiNaapurit(alusta, nykyinen, solmulista);
            nykyinen.setEtaisyysAlkuun(summa);
        }

        reitti[indeksi] = nykyinen;
        indeksi++;
        Peliruutu[] reittiKopio = reitistaKopioReitti(reitti);

        ArrayList<Peliruutu> kaydytKopio = kaydyt;

        while (!solmulista.isEmpty()) {
            nykyinen = solmulista.poll();
            liiku(nykyinen, alusta, summa, maali, reittiKopio, indeksi, solmulista, kaydytKopio);
        }

    }
    
    /**
     * Metodi tekee reitistä kopion.
     * @param reitti
     * @return 
     */

    private Peliruutu[] reitistaKopioReitti(Peliruutu[] reitti) {
        Peliruutu[] reittiKopio = new Peliruutu[reitti.length + 1];
        System.arraycopy(reitti, 0, reittiKopio, 0, reitti.length);
        return reittiKopio;
    }

    /**
     *
     * Tarkistaan ollaanko jo päästy maaliruutuun, jos ollaan palautetaan true.
     * Jos ei vielä olla maalissa palautetaan false.
     *
     * @param nykyinen
     * @param maali
     * @param summa
     * @param reitti
     * @param indeksi
     * @return
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
     * @return
     */
    public int etaisyys(Peliruutu maali, Peliruutu ruutu) {

        int arvioX = Math.abs(ruutu.getX() - maali.getX());
        int arvioY = Math.abs(ruutu.getY() - maali.getY());
        int etaisyysarvio = arvioX + arvioY;

        return etaisyysarvio;
    }

}
