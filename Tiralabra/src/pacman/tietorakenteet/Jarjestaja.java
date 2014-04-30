package pacman.tietorakenteet;

import pacman.alusta.Peliruutu;

/**
 * Järjestäjä luokka, järjestää taulukon, etäisyysarvioiden perusteella.
 * Järjestäminen tapahtuu lomitusjärjestämisen idealla.
 */
public class Jarjestaja {

    /**
     * Taulukko, johon tallennettuna kaikki solmut, jotka tulee järjestää.
     */
    private Peliruutu[] solmut;

    /**
     * Konstruktorissa alustetaan solmulista parametrinä olevaksi solmulistaksi.
     * @param solmulista
     */
    public Jarjestaja(Peliruutu[] solmulista) {
        solmut = solmulista;
    }

    /**
     * Metodi lomittaa palaset sopivaan järjestykseen.
     * @param vasenReuna
     * @param keskikohta
     * @param oikeaReuna
     */
    private void merge(int vasenReuna, int keskikohta, int oikeaReuna) {
        
        Peliruutu[] apu = new Peliruutu[solmut.length];
        
        kopioAlku(vasenReuna, oikeaReuna, apu);

        int i = vasenReuna;
        int j = keskikohta+1;
        int k = vasenReuna;

        while (i <= keskikohta && j <= oikeaReuna) {
            if(tarkistaKumpiSuurempi(apu, i, j)) {
                i = taydennaSolmu(k, apu, i);
            } else {
                j = taydennaSolmu(k, apu, j);
            }
            k++;
        }
        kopioLoppu(i, keskikohta, k, apu);        

    }

    private boolean tarkistaKumpiSuurempi(Peliruutu[] apu, int i, int j) {
        return apu[i].getEtaisyysAlkuun()+apu[i].getEtaisyysMaaliin() <= apu[j].getEtaisyysAlkuun()+apu[j].getEtaisyysMaaliin();
    }

    /**
     * Lisätään solmuihin seuraavana tuleva solmu, kun ollaan muodostamassa järjestystä.
     * @param k
     * @param apu
     * @param i
     * @return 
     */
    private int taydennaSolmu(int k, Peliruutu[] apu, int i) {
        solmut[k] = apu[i];
        i++;
        return i;
    }

    /**
     * Kopioidaan solmut taulukon alku aputaulukkoon.
     * @param vasenReuna
     * @param oikeaReuna
     * @param apu 
     */
    private void kopioAlku(int vasenReuna, int oikeaReuna, Peliruutu[] apu) {
        for (int i = vasenReuna; i <= oikeaReuna; i++) {
            apu[i] = solmut[i];
        }
    }

    /**
     * Kopio loppuosan aputaulusta solmut tauluun.
     * @param i
     * @param keskikohta
     * @param k
     * @param apu 
     */
    private void kopioLoppu(int i, int keskikohta, int k, Peliruutu[] apu) {
        while( i <= keskikohta) {
            solmut[k] = apu[i];
            k++;
            i++;
        }
    }

    /**
     * Metodi pilkkoo taulukon pieniksi osiksi ja kutsuu tämän jälkeen taulukon osat lomittavaa metodia.
     * @param vasenReuna
     * @param oikeaReuna
     */
    public void mergeSort(int vasenReuna, int oikeaReuna) {
        if (vasenReuna < oikeaReuna) {
            int keski = vasenReuna + (oikeaReuna - vasenReuna) / 2;
            mergeSort(vasenReuna, keski);
            mergeSort(keski + 1, oikeaReuna);
            merge(vasenReuna, keski, oikeaReuna);
        }
    }
    
    public void setLista(Peliruutu[] lista) {
        this.solmut = lista;
    }
}


