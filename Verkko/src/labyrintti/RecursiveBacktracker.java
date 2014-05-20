/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package labyrintti;

import java.util.ArrayList;
import java.util.Random;
import verkko.KoordinoituSolmu;

/**
 *
 * @author Arvoitusmies
 */
public class RecursiveBacktracker {

    private KoordinoituSolmu[][] asdf;
    private Boolean[][] kayty;
    private ArrayList<KoordinoituSolmu> kasiteltavat;
    private Random r;

    public RecursiveBacktracker(KoordinoituSolmu[][] asdf) {
        this.asdf = asdf;
        kayty = new Boolean[asdf.length][asdf[0].length];
        for (int i = 0; i < asdf.length; i++) {
            for (int j = 0; j < asdf[0].length; j++) {
                kayty[i][j] = false;
            }
        }
        r = new Random();
    }

    public KoordinoituSolmu[][] labyrintitaLabyrintti() {
        recur(0, 0);
        return asdf;
    }

    private void recur(int x, int y) {
        kayty[x][y] = true;
        KoordinoituSolmu[] naapurit = naapurit(x, y);
        naapurit = poistaNullit(naapurit);
        if (naapurit.length > 0) {
            //sekoita naapurit
            sekoita(naapurit);
            for (int i = 0; i < naapurit.length; i++) {
                KoordinoituSolmu naapuri = naapurit[i];
                int ksx = (int) Math.round(naapuri.koordinaatti(0));
                int ksy = (int) Math.round(naapuri.koordinaatti(1));
                if (!kayty[ksx][ksy]) {
                    asdf[x][y].lisaaNaapuri(naapuri, 1);
                    naapuri.lisaaNaapuri(asdf[x][y], 1);
                    recur(ksx, ksy);
                }
            }
            //foreach jossei ole käyty niin
            //  1.tee yhteys 2. kutsu recur
        }
    }

    private void sekoita(KoordinoituSolmu[] ks) {
        for (int i = 0; i < ks.length - 1; i++) {
            int swapattavaIndeksi = r.nextInt(ks.length - 1) + 1;
            if (i != swapattavaIndeksi) {
                KoordinoituSolmu tmp = ks[i];
                ks[i] = ks[swapattavaIndeksi];
                ks[swapattavaIndeksi] = tmp;
            }
        }
    }

    private KoordinoituSolmu[] poistaNullit(KoordinoituSolmu[] ks) {
        int nulleja = 0;
        for (int i = 0; i < ks.length; i++) {
            if (ks[i] == null) {
                nulleja++;
            }
        }
        KoordinoituSolmu[] uus = new KoordinoituSolmu[ks.length - nulleja];
        int i = 0;
        for (KoordinoituSolmu koordinoituSolmu : ks) {
            if (koordinoituSolmu != null) {
                uus[i] = koordinoituSolmu;
                i++;
            }
        }
        return uus;
    }

    private KoordinoituSolmu[] naapurit(int x, int y) {
        KoordinoituSolmu[] paluu = new KoordinoituSolmu[4];
        if (x > 0) {
            paluu[0] = asdf[x - 1][y];
        }
        if (y > 0) {
            paluu[1] = asdf[x][y - 1];
        }
        if (x < asdf.length - 1) {
            paluu[2] = asdf[x + 1][y];
        }
        if (y < asdf[0].length - 1) {
            paluu[3] = asdf[x][y + 1];
        }
        return paluu;
    }

}
