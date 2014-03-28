package pacman.peli;

import pacman.alusta.Peliruutu;

public class Jarjestaja {

    private Peliruutu[] solmut;
    private Peliruutu[] apu;

    public Jarjestaja(Peliruutu[] solmulista) {
        solmut = solmulista;
        apu = new Peliruutu[solmulista.length];
    }

    public void merge(int vasenReuna, int keskikohta, int oikeaReuna) {
        
        Peliruutu[] apu = new Peliruutu[solmut.length];

        for (int i = vasenReuna; i <= oikeaReuna; i++) {
            apu[i] = solmut[i];
        }

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
        
        while( i <= keskikohta) {
            solmut[k] = apu[i];
            k++;
            i++;
        }

    }

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
    
    public Peliruutu[] getLista() {
        return this.solmut;
    }
}


