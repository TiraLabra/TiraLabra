package ai;

import viidensuora.ViidenSuora;

public class Tekoaly {

    private final ViidenSuora peli;

    public Tekoaly(ViidenSuora peli) {
        this.peli = peli;
    }

    public int minmax(int syvyys, boolean maksimoi) {
        if (syvyys == 0) {
            return 0; //todo
        }

        if (maksimoi) {
            int parasArvo = Integer.MIN_VALUE;
            for (int i = 0; i < peli.getKorkeus(); i++) {
                for (int j = 0; j < peli.getLeveys(); j++) {
                    if (peli.ruutuTyhja(i, j)) {
                        peli.asetaMerkki(i, j, peli.RISTI);
                        int arvo = minmax(syvyys - 1, false);
                        parasArvo = Math.max(arvo, parasArvo);
                        peli.asetaMerkki(i, j, peli.TYHJA);
                    }
                }
            }
            return parasArvo;
        } else {
            int parasArvo = Integer.MAX_VALUE;
            for (int i = 0; i < peli.getKorkeus(); i++) {
                for (int j = 0; j < peli.getLeveys(); j++) {
                    if (peli.ruutuTyhja(i, j)) {
                        peli.asetaMerkki(i, j, peli.NOLLA);
                        int arvo = minmax(syvyys - 1, true);
                        parasArvo = Math.min(arvo, parasArvo);
                        peli.asetaMerkki(i, j, peli.TYHJA);
                    }
                }
            }
            return parasArvo;
        }
    }

    public int alphaBetaKarsinta(int syvyys, boolean maksimoi, int alpha, int beta) {
        return 0;
    }

}
