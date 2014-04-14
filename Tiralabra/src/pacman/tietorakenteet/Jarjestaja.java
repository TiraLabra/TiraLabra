package pacman.tietorakenteet;

import pacman.alusta.Peliruutu;

/**
 * Järjestäjä luokka, järjestää taulukon, etäisyysarvioiden perusteella.
 * Järjestäminen tapahtuu lomitusjärjestämisen idealla.
 * @author Hanna
 */
public class Jarjestaja {

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
            if(apu[i].getEtaisyysAlkuun()+apu[i].getEtaisyysMaaliin() <= apu[j].getEtaisyysAlkuun()+apu[j].getEtaisyysMaaliin()) {
                solmut[k] = apu[i];
                i++;
            } else {
                solmut[k] = apu[j];
                j++;
            }
            k++;
        }
        kopioLoppu(i, keskikohta, k, apu);        

    }

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


