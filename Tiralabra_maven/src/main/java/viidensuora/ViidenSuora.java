package viidensuora;

public class ViidenSuora {

    public static final char TYHJA = '.';
    public static final char RISTI = 'X';
    public static final char NOLLA = 'O';

    private final char[][] pelilauta;
    private final int leveys;
    private final int korkeus;

    private boolean ristinVuoro;

    public ViidenSuora(int korkeus, int leveys) {
        this.pelilauta = new char[korkeus][leveys];
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.ristinVuoro = true;

        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                this.pelilauta[i][j] = TYHJA;
            }
        }
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void asetaSeuraavaMerkki(int rivi, int sarake) {
        if (ruutuTyhja(rivi, sarake)) {
            pelilauta[rivi][sarake] = ristinVuoro ? RISTI : NOLLA;
            ristinVuoro = !ristinVuoro;
        }
    }

    public void asetaMerkki(int rivi, int sarake, char merkki) {
        if (koordinaattiLaudalla(rivi, sarake)) {
            pelilauta[rivi][sarake] = merkki;
        }
    }

    public boolean ruutuTyhja(int rivi, int sarake) {
        return koordinaattiLaudalla(rivi, sarake)
                && pelilauta[rivi][sarake] == TYHJA;
    }

    private boolean koordinaattiLaudalla(int rivi, int sarake) {
        return rivi >= 0 && rivi < korkeus && sarake >= 0 && sarake < leveys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                sb.append(pelilauta[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
