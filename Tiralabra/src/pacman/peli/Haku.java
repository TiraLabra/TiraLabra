package pacman.peli;

import java.util.ArrayList;
import java.util.PriorityQueue;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Suunta;

public class Haku {

    private static int parasSumma;
    private static Peliruutu[] parasReitti;
    private boolean ekaKerta;


    public Haku() {
        this.ekaKerta = true;
    }

    public Peliruutu aStar(Peliruutu lahto, Peliruutu maali, Pelialusta alusta) {
        alustus(alusta, maali);
        parasSumma = Integer.MAX_VALUE;

        lahto.setEtaisyysAlkuun(0);

        ArrayList<Peliruutu> kaydyt = new ArrayList<Peliruutu>();

        PriorityQueue<Peliruutu> solmulista = new PriorityQueue<Peliruutu>();

        etsiNaapurit(alusta, lahto, solmulista);
        Peliruutu[] reitti = new Peliruutu[1];

        liiku(lahto, alusta, 1, maali, reitti, 0, solmulista, kaydyt);

        return parasReitti[1];
    }

    private void etsiNaapurit(Pelialusta alusta, Peliruutu lahto, PriorityQueue<Peliruutu> solmulista) {
        Peliruutu ylos = alusta.getPeliruutu(lahto.getX() + Suunta.YLOS.getX(), lahto.getY() + Suunta.YLOS.getY());
        if (ylos.getRuudunTyyppi() != 0) {
            solmulista.add(ylos);
        }
        Peliruutu alas = alusta.getPeliruutu(lahto.getX() + Suunta.ALAS.getX(), lahto.getY() + Suunta.ALAS.getY());
        if (alas.getRuudunTyyppi() != 0) {
            solmulista.add(alas);
        }
        Peliruutu vasen = alusta.getPeliruutu(lahto.getX() + Suunta.VASEN.getX(), lahto.getY() + Suunta.VASEN.getY());
        if (vasen.getRuudunTyyppi() != 0) {
            solmulista.add(vasen);
        }
        Peliruutu oikea = alusta.getPeliruutu(lahto.getX() + Suunta.OIKEA.getX(), lahto.getY() + Suunta.OIKEA.getY());
        if (oikea.getRuudunTyyppi() != 0) {
            solmulista.add(oikea);
        }
    }

    private void alustus(Pelialusta alusta, Peliruutu maali) {
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                alusta.getPeliruutu(x, y).setEtaisyysAlkuun(Integer.MAX_VALUE);
                alusta.getPeliruutu(x, y).setEtaisyysMaaliin(etaisyys(maali, alusta.getPeliruutu(x, y)));
//                kaymattomat.add(alusta.getPeliruutu(x, y));                
            }
        }
    }

    private void liiku(Peliruutu nykyinen, Pelialusta alusta, int summa, Peliruutu maali, Peliruutu[] reitti, int indeksi, PriorityQueue<Peliruutu> solmulista, ArrayList<Peliruutu> kaydyt) {

        if (nykyinen == null) {
            return;
        }

        if (kaydyt.contains(nykyinen)) {
            return;
        }
        if (this.ekaKerta == false) {
            solmulista = new PriorityQueue<Peliruutu>();
            etsiNaapurit(alusta, nykyinen, solmulista);
            kaydyt.add(nykyinen);
        } else {
            summa += 1;
            kaydyt.add(nykyinen);
            this.ekaKerta = false;
        }

        if (summa >= parasSumma) {
            return;
        }

        if (nykyinen.equals(maali)) {
            if (summa <= parasSumma) {
                parasSumma = summa;
                reitti[indeksi] = nykyinen;
                parasReitti = new Peliruutu[reitti.length];
                for (int i = 0; i < reitti.length; i++) {
                    parasReitti[i] = reitti[i];
                }
            }
            return;
        }

        reitti[indeksi] = nykyinen;
        indeksi++;

        nykyinen = solmulista.poll();

        Peliruutu[] reittiKopio = new Peliruutu[reitti.length + 1];
        for (int i = 0; i < reitti.length; i++) {
            reittiKopio[i] = reitti[i];
        }

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

    public int etaisyys(Peliruutu maali, Peliruutu ruutu) {
        int etaisyysarvio = (ruutu.getX() - maali.getX()) + (ruutu.getY() - maali.getY());
        etaisyysarvio = Math.abs(etaisyysarvio);

        return etaisyysarvio;
    }

}
