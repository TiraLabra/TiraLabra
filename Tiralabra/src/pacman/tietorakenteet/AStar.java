package pacman.tietorakenteet;

import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.hahmot.Suunta;

public class AStar {

    private Peliruutu[] reitti;

    public Peliruutu[] getReitti() {
        return this.reitti;
    }

    public void astar(Pelialusta alusta, Peliruutu lahto, Peliruutu maali) {
        Lista sopivatSolmut = new Lista();
        lisaaSopivatSolmut(alusta, sopivatSolmut);
        Peliruutu[] kaymattomat = muunnaSopivatListaKaymattomatTaulukoksi(sopivatSolmut);

        alustus(kaymattomat, maali);

        lahto.setEtaisyysAlkuun(0);
        Peliruutu[] kaydyt = new Peliruutu[0];
        liiku(kaydyt, maali, kaymattomat, alusta);

        Peliruutu solmu = maali;
        reitti = new Peliruutu[0];

        while (!solmu.equals(lahto)) {
            System.out.println("täällä");
            lisaaRuutuReittiin(solmu);
            solmu = solmu.getEdellinen();
        }
    }

    private Peliruutu[] muunnaSopivatListaKaymattomatTaulukoksi(Lista sopivatSolmut) {
        Peliruutu[] kaymattomat = new Peliruutu[sopivatSolmut.koko()];
        for (int i = 0; i < kaymattomat.length; i++) {
            Peliruutu ruutu = (Peliruutu) sopivatSolmut.getAlkio(i);
            kaymattomat[i] = ruutu;
        }
        return kaymattomat;
    }

    private void lisaaRuutuReittiin(Peliruutu solmu) {
        Peliruutu[] uusiReitti = new Peliruutu[reitti.length + 1];
        for (int i = 0; i < reitti.length; i++) {
            uusiReitti[i] = reitti[i];
        }
        uusiReitti[reitti.length] = solmu;
        reitti = uusiReitti;
    }

    private void liiku(Peliruutu[] kaydyt, Peliruutu maali, Peliruutu[] kaymattomat, Pelialusta alusta) {
        while (!tarkistaSisaltaakoMaalin(kaydyt, maali)) {
            Jarjestaja jar = new Jarjestaja(kaymattomat);
            jar.mergeSort(0, kaymattomat.length - 1);

            Peliruutu lyhinMatka = kaymattomat[0];
            System.out.println(lyhinMatka.getX());
            System.out.println(lyhinMatka.getY());
            kaymattomat = pollaaPienin(kaymattomat);
            kaydyt = lisaaKaytyihin(kaydyt, lyhinMatka);

            Lista naapurit = new Lista();
            selvitaNaapurit(alusta, lyhinMatka, naapurit);
            relax(naapurit, lyhinMatka);

        }
    }

    private void alustus(Peliruutu[] kaymattomat, Peliruutu maali) {
        for (int i = 0; i < kaymattomat.length; i++) {
            kaymattomat[i].setEtaisyysAlkuun(2000000000);
            kaymattomat[i].setEtaisyysMaaliin(etaisyys(maali, kaymattomat[i]));
            kaymattomat[i].setEdellinen(null);
        }
    }

    private void relax(Lista naapurit, Peliruutu lyhinMatka) {
        for (int i = 0; i < naapurit.koko(); i++) {
            Peliruutu naapuri = (Peliruutu) naapurit.getAlkio(i);
            if (naapuri.getEtaisyysAlkuun() > lyhinMatka.getEtaisyysAlkuun() + 1) {
                naapuri.setEtaisyysAlkuun(lyhinMatka.getEtaisyysAlkuun() + 1);
                naapuri.setEdellinen(lyhinMatka);
            }
        }
    }

    private void selvitaNaapurit(Pelialusta alusta, Peliruutu lyhinMatka, Lista naapurit) {
        for (Suunta suunta : Suunta.values()) {
            if (alusta.getPeliruutu(lyhinMatka.getX() + suunta.getX(), lyhinMatka.getY() + suunta.getY()).getRuudunTyyppi() != 0) {
                naapurit.lisaa(alusta.getPeliruutu(lyhinMatka.getX() + suunta.getX(), lyhinMatka.getY() + suunta.getY()));
            }
        }
    }

    private Peliruutu[] lisaaKaytyihin(Peliruutu[] kaydyt, Peliruutu lyhinMatka) {
        Peliruutu[] uusiKaydyt = new Peliruutu[kaydyt.length + 1];
        for (int i = 0; i < kaydyt.length; i++) {
            uusiKaydyt[i] = kaydyt[i];
        }
        uusiKaydyt[kaydyt.length] = lyhinMatka;
        kaydyt = uusiKaydyt;
        return kaydyt;
    }

    private Peliruutu[] pollaaPienin(Peliruutu[] kaymattomat) {
        Peliruutu[] uusiKaymattomat = new Peliruutu[kaymattomat.length - 1];
        for (int i = 0; i < uusiKaymattomat.length; i++) {
            uusiKaymattomat[i] = kaymattomat[i + 1];
        }
        kaymattomat = uusiKaymattomat;
        return kaymattomat;
    }

    private void lisaaSopivatSolmut(Pelialusta alusta, Lista sopivatSolmut) {
        for (int y = 0; y < alusta.getKorkeus(); y++) {
            for (int x = 0; x < alusta.getLeveys(); x++) {
                if (alusta.getPeliruutu(x, y).getRuudunTyyppi() != 0) {
                    sopivatSolmut.lisaa(alusta.getPeliruutu(x, y));
                }
            }
        }
    }

    public int etaisyys(Peliruutu maali, Peliruutu ruutu) {

        int arvioX = Math.abs(ruutu.getX() - maali.getX());
        int arvioY = Math.abs(ruutu.getY() - maali.getY());
        int etaisyysarvio = arvioX + arvioY;

        return etaisyysarvio;
    }

    public boolean tarkistaSisaltaakoMaalin(Peliruutu[] kaydyt, Peliruutu maali) {
        for (int i = 0; i < kaydyt.length; i++) {
            if (kaydyt[i].equals(maali)) {
                return true;
            }
        }
        return false;
    }

}
