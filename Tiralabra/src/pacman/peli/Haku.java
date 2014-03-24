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

    private static int parasSumma;
    private static Peliruutu[] parasReitti;
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
        Peliruutu ylos = alusta.getPeliruutu(ruutu.getX() + Suunta.YLOS.getX(), ruutu.getY() + Suunta.YLOS.getY());
        if (ylos.getRuudunTyyppi() != 0) {
            solmulista.add(ylos);
        }
        Peliruutu alas = alusta.getPeliruutu(ruutu.getX() + Suunta.ALAS.getX(), ruutu.getY() + Suunta.ALAS.getY());
        if (alas.getRuudunTyyppi() != 0) {
            solmulista.add(alas);
        }
        Peliruutu vasen = alusta.getPeliruutu(ruutu.getX() + Suunta.VASEN.getX(), ruutu.getY() + Suunta.VASEN.getY());
        if (vasen.getRuudunTyyppi() != 0) {
            solmulista.add(vasen);
        }
        Peliruutu oikea = alusta.getPeliruutu(ruutu.getX() + Suunta.OIKEA.getX(), ruutu.getY() + Suunta.OIKEA.getY());
        if (oikea.getRuudunTyyppi() != 0) {
            solmulista.add(oikea);
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
     * Metodi selvittää rekursiivisesti parhaimman reitin lähtöruudusta maaliin
     * ja .
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

        if (ollaankoMaalissa(nykyinen, maali, summa, reitti, indeksi)) {
            return;
        }

        if (nykyinen == null) {
            return;
        } else if (this.ekaKerta == false) {
            summa += 1;
            if (nykyinen.getEtaisyysAlkuun() < summa) {
                return;
            }
            solmulista = new PriorityQueue<Peliruutu>();
            etsiNaapurit(alusta, nykyinen, solmulista);
            nykyinen.setEtaisyysAlkuun(summa);
        } else {
            this.ekaKerta = false;
        }

        if (summa >= parasSumma) {
            return;
        }

        reitti[indeksi] = nykyinen;
        indeksi++;

        nykyinen = solmulista.poll();

        Peliruutu[] reittiKopio = new Peliruutu[reitti.length + 1];
        System.arraycopy(reitti, 0, reittiKopio, 0, reitti.length);
        
        ArrayList<Peliruutu> kaydytKopio = kaydyt;

        liiku(nykyinen, alusta, summa, maali, reittiKopio, indeksi, solmulista, kaydytKopio);
        if (!solmulista.isEmpty()) {
            nykyinen = solmulista.poll();
            liiku(nykyinen, alusta, summa, maali, reittiKopio, indeksi, solmulista, kaydytKopio);
            if (!solmulista.isEmpty()) {
                nykyinen = solmulista.poll();
                liiku(nykyinen, alusta, summa, maali, reittiKopio, indeksi, solmulista, kaydytKopio);
                if (!solmulista.isEmpty()) {
                    nykyinen = solmulista.poll();
                    liiku(nykyinen, alusta, summa, maali, reittiKopio, indeksi, solmulista, kaydytKopio);
                }
            }
        }

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
