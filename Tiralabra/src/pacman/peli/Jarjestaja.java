package pacman.peli;

import pacman.alusta.Peliruutu;

public class Jarjestaja {

    private Peliruutu[] solmut;

    public Jarjestaja(Peliruutu[] solmulista) {
        solmut = solmulista;
    }

    public void merge(int vasenReuna, int keskikohta, int oikeaReuna) {
        int apu1 = keskikohta - vasenReuna;
        int apu2 = oikeaReuna - keskikohta;
        Peliruutu[] apuTaulu1 = new Peliruutu[apu1 + 1];
        Peliruutu[] apuTaulu2 = new Peliruutu[apu2 + 1];

        for (int i = 0; i < apu1; i++) {
            apuTaulu1[i] = solmut[vasenReuna + i];
        }
        Peliruutu ruutu1 = new Peliruutu(300, 300);
        ruutu1.setEtaisyysAlkuun(Integer.MAX_VALUE);
        apuTaulu1[apu1] = ruutu1;

        for (int j = 0; j < apu2; j++) {
            apuTaulu2[j] = solmut[keskikohta + j];
        }
        Peliruutu ruutu = new Peliruutu(300, 300);
        ruutu.setEtaisyysAlkuun(Integer.MAX_VALUE);
        apuTaulu2[apu2] = ruutu;

        int i = 0;
        int j = 0;

        for (int k = vasenReuna; k < oikeaReuna; k++) {
            if (apuTaulu1[i].getEtaisyysAlkuun() + apuTaulu1[i].getEtaisyysMaaliin() <= apuTaulu2[j].getEtaisyysAlkuun() + apuTaulu2[j].getEtaisyysMaaliin()) {
                solmut[k] = apuTaulu1[i];
                i++;
            } else {
                solmut[k] = apuTaulu2[j];
                j++;
            }
        }

    }

    public void mergeSort(int vasenReuna, int oikeaReuna) {
        if (vasenReuna < oikeaReuna) {
            int keski = (vasenReuna + oikeaReuna) / 2;
            mergeSort(vasenReuna, keski);
            mergeSort(keski + 1, oikeaReuna);
            merge(vasenReuna, keski, oikeaReuna);
        }
    }

}
