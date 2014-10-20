package labyrintti.tietorakenteet;

import labyrintti.logiikka.Maapala;

/**
 * Minimikeko-toteutus ohjelman käyttämälle avoimelle listalle, joka pitää
 * kirjaa alkioista, jotka tulee tarkastaa.
 *
 * @author Mikael Parvamo
 */
public class Keko {

    private int koko;
    private int maksimiKoko;
    private ListaAlkio[] avoinLista;
    private static int ALKU = 1;
    
    /**
     * Kontruktori, joka luo keon asettaen arvoon 0 dummyn.
     * 
     * @param maksimiKoko 
     */
    public Keko(int maksimiKoko) {
        this.maksimiKoko = maksimiKoko;
        this.koko = 0;
        this.avoinLista = new ListaAlkio[this.maksimiKoko + 1];
        Maapala dummy = new Maapala(Integer.MIN_VALUE, Integer.MIN_VALUE);
        dummy.setKokonaisArvo1(Integer.MIN_VALUE);
        avoinLista[0] = dummy;

    }
    /**
     * Saa parametrina tarkasteltavan alkion sijainnin ja laskee sen avulla alkion
     * vasemman lapsen sijainnin
     * 
     * @param sijainti
     * @return int 
     */
    private int vasLapsi(int sijainti) {
        return 2 * sijainti;
    }
    
    /**
     * Parametrina saadaan tarkasteltavan alkion sijainti, jonka perusteella
     * lasketaan oikean lapsen sijainti
     * 
     * @param sijainti
     * @return int 
     */
    private int oikLapsi(int sijainti) {
        return (2 * sijainti) + 1;
    }
    
    /**
     * parametrina saadun sijainnin perusteella lasketaan alkion vanhempi
     * ja palautetaan sen sijainti
     * 
     * @param sijainti
     * @return int
     */
    private int vanhempi(int sijainti) {
        return sijainti / 2;
    }
    
    /**
     * Parametrina saadun sijainnin perusteella lasketaan, onko kyseinen
     * alkio lehtenä keossa
     * 
     * @param sijainti
     * @return 
     */
    private boolean onLehti(int sijainti) {
        if (sijainti >= (koko / 2) && sijainti <= koko) {
            return true;
        }
        return false;
    }
    
    /**
     * Vaihtaa parametreina saaduissa sijainneissa olevien alkioiden paikkaa
     * keossa
     * 
     * @param eka
     * @param toka 
     */
    private void vaihdaPaikkaa(int eka, int toka) {
        ListaAlkio apuMuuttuja = avoinLista[eka];
        avoinLista[eka] = avoinLista[toka];
        avoinLista[toka] = apuMuuttuja;
    }
    
    /**
     * Lisää alkio kekoon oikealle paikalleen
     * @param alkio 
     */
    public void lisaaAlkio(ListaAlkio alkio) {
        this.koko++;
        avoinLista[koko] = alkio;

        int sijainti = koko;

        Maapala tarkAlk = (Maapala) avoinLista[sijainti];
        Maapala tarkVanh = (Maapala) avoinLista[vanhempi(sijainti)];

        while (tarkAlk.getKokonaisArvo() < tarkVanh.getKokonaisArvo()) {
            vaihdaPaikkaa(sijainti, vanhempi(sijainti));

            tarkAlk = (Maapala) avoinLista[vanhempi(sijainti)];
            sijainti = vanhempi(sijainti);
            tarkVanh = (Maapala) avoinLista[vanhempi(sijainti)];
        }
    }
    
    /**
     * Poistaa pienimmän alkion keosta ja kutsuu heapifyi metodia, joka
     * tarkistaa minimikeon ehtojen toteutuvuuden
     * 
     * @return 
     * poistettava maapala
     */
    public Maapala poistaAlkio() {
        Maapala poistettu = (Maapala) avoinLista[ALKU];
        avoinLista[ALKU] = avoinLista[koko];
        this.koko--;
        heapify(ALKU);
        avoinLista[koko + 1] = null;
        return poistettu;
    }
    
    /**
     * Tarkistaa saadusta sijainnista lähtien, että minimikeon ehdot toteutuvat.
     * Jos ehdot ovat rikki, vaihdetaan alkioiden paikkaa.
     * 
     * @param sijainti 
     */
    private void heapify(int sijainti) {
        if (!onLehti(sijainti)) {
            Maapala tarkAlkio = (Maapala) avoinLista[sijainti];
            Maapala vasLapsi = (Maapala) avoinLista[vasLapsi(sijainti)];
            Maapala oikLapsi = (Maapala) avoinLista[oikLapsi(sijainti)];

            if (oikLapsi(sijainti) <= this.koko) {
                if (vasLapsi.getKokonaisArvo() < oikLapsi.getKokonaisArvo()) {
                    if (tarkAlkio.getKokonaisArvo() > vasLapsi.getKokonaisArvo()) {
                        vaihdaPaikkaa(sijainti, vasLapsi(sijainti));
                    }
                } else {
                    if (tarkAlkio.getKokonaisArvo() > oikLapsi.getKokonaisArvo()) {
                        vaihdaPaikkaa(sijainti, oikLapsi(sijainti));
                    }
                }

            } else if (vasLapsi(sijainti) == this.koko && tarkAlkio.getKokonaisArvo() < vasLapsi.getKokonaisArvo()) {
                vaihdaPaikkaa(sijainti, vasLapsi(sijainti));
            }
        }
    }
    
    /**
     * Metodi kutsuu heapifyi metodia lähtien keon puolesta välistä.
     */
    public void minKeko() {
        for (int sijainti = (koko / 2); sijainti > 1; sijainti--) {
            heapify(sijainti);
        }
    }
    
    /**
     * Metodi palauttaa keon koon
     * @return this.koko
     */
    public int getKoko() {
        return this.koko;
    }
    
    /**
     * Metodi palauttaa kyseisessä sijainnissa sijaitsevan keon alkion.
     * 
     * @param sijainti
     * @return 
     */
    public ListaAlkio getAlkio(int sijainti){
        return avoinLista[sijainti];
    }
}
