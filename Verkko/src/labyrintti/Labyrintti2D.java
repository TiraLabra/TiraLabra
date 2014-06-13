/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package labyrintti;

import java.util.Arrays;
import java.util.Map;
import verkko.Solmu;

/**
 * Kaksiulotteinen labyrintti.
 *
 * @author Arvoitusmies
 */
public class Labyrintti2D {

    /**
     * Merkki käytävälle
     */
    private static final char CORRIDOR = ' ';
    /**
     * Merkki seinälle
     */
    private static final char WALL = '#';

    /**
     * 2D taulukko KoordinoituSolmu olioille jotka tässä labyrintissä on.
     */
    private Solmu[][] solmut;

    /**
     * Labyrintitin jota käytetään labyrintin solmujen välisten yhteyksien eli
     * labyrintin käytävien generointiin.
     */
    private Labyrintitin labyrintitin;

    /**
     * Initialisoi solmut kentän tyhjillä, naapurittomilla,
     * KoordinoiduillaSolmuilla.
     *
     * @param korkeus
     * @param leveys
     */
    public Labyrintti2D(int korkeus, int leveys) {
        this.solmut = new Solmu[korkeus][leveys];
        for (int k = 0; k < solmut.length; k++) {
            for (int l = 0; l < solmut[0].length; l++) {
                Double[] koord = {k * 1.0, l * 1.0};
                solmut[k][l] = new Solmu(koord);
            }
        }
    }

    /**
     * Asettaa käytettävän labyrintingeneroimisalgoritmin.
     *
     * @param labyrintitin
     */
    public void setLabyrintitin(Labyrintitin labyrintitin) {
        this.labyrintitin = labyrintitin;
    }

    /**
     * GetSolmut
     *
     * @return solmut
     */
    public Solmu[][] getSolmut() {
        return solmut;
    }

    /**
     * Labyrintittää labyrintin labyrintittimen avulla.
     */
    public void labyrintitaLabyrintti() {
        if (labyrintitin == null) {
            throw new IllegalStateException("Aseta labyrintitin ensin (setLabyrintitin)");
        }
        labyrintitin.labyrintita();
    }

    /**
     * True jos (x,y) kohdan solmulla on käytävä oikealle.
     *
     * @param x
     * @param y
     * @return
     */
    protected boolean onkoOikeaNaapuri(int x, int y) {
        if (y == solmut[0].length - 1) {
            return false;
        }
        Solmu solmu = solmut[x][y];
        Solmu oikeaSolmu = solmut[x][y + 1];
        return solmu.onkoNaapuri(oikeaSolmu);
    }

    /**
     * True jos (x,y) kohdan solmulla on käytävä alas.
     *
     * @param x
     * @param y
     * @return
     */
    protected boolean onkoAlaNaapuri(int x, int y) {
        if (x == solmut.length - 1) {
            return false;
        }
        Solmu solmu = solmut[x][y];
        Solmu alaSolmu = solmut[x + 1][y];
        return solmu.onkoNaapuri(alaSolmu);
    }

    @Override
    public String toString() {
        char[][] merkkiTauluEsitys = merkkitauluesitys();
        StringBuilder buffer = new StringBuilder();
        for (char[] merkkiTauluEsity : merkkiTauluEsitys) {
            buffer.append(charArrayToString(merkkiTauluEsity)).append("\n");
        }
        return buffer.toString();
    }

    /**
     * Merkkitauluesitys labyrintistä.
     * @return
     */
    protected char[][] merkkitauluesitys() {
        char[][] merkkiTauluEsitys = new char[solmut.length * 2 + 1][solmut[0].length * 2 + 1];
        merkkitauluReunat(merkkiTauluEsitys);

        for (int i = 1; i < merkkiTauluEsitys.length - 1; i++) {
            for (int j = 1; j < merkkiTauluEsitys[0].length - 1; j++) {
                if (j % 2 == 0 && i % 2 == 0) {
                    merkkiTauluEsitys[i][j] = WALL;
                } else if (j % 2 == 0 && i % 2 == 1) {
                    merkkitauluKaytavaVasemmalleJosOn(i, j, merkkiTauluEsitys);
                } else if (j % 2 == 1 && i % 2 == 0) {
                    merkkitauluKaytavaYlosJosOn(i, j, merkkiTauluEsitys);
                } else {
                    merkkiTauluEsitys[i][j] = CORRIDOR;
                }
            }
        }
        return merkkiTauluEsitys;
    }

    /**
     * 
     * @param i
     * @param j
     * @param merkkiTauluEsitys
     */
    protected void merkkitauluKaytavaVasemmalleJosOn(int i, int j, char[][] merkkiTauluEsitys) {
        if (onkoOikeaNaapuri(i >> 1, (j >> 1) - 1)) {
            merkkiTauluEsitys[i][j] = CORRIDOR;
        } else {
            merkkiTauluEsitys[i][j] = WALL;
        }
    }

    /**
     *
     * @param i
     * @param j
     * @param merkkiTauluEsitys
     */
    protected void merkkitauluKaytavaYlosJosOn(int i, int j, char[][] merkkiTauluEsitys) {
        if (onkoAlaNaapuri((i >> 1) - 1, j >> 1)) {
            merkkiTauluEsitys[i][j] = CORRIDOR;
        } else {
            merkkiTauluEsitys[i][j] = WALL;
        }
    }

    /**
     * Merkkitauluesitykseen reunat=WALL.
     *
     * @param merkkiTauluEsitys
     */
    protected void merkkitauluReunat(char[][] merkkiTauluEsitys) {
        char[] yläJaAla = new char[merkkiTauluEsitys[0].length];
        Arrays.fill(yläJaAla, WALL);
        merkkiTauluEsitys[0] = yläJaAla.clone();
        merkkiTauluEsitys[merkkiTauluEsitys.length - 1] = yläJaAla.clone();

        for (char[] merkkiTauluEsity : merkkiTauluEsitys) {
            merkkiTauluEsity[0] = WALL;
            merkkiTauluEsity[merkkiTauluEsitys[0].length - 1] = WALL;
        }
    }

    /**
     * Tällänen apumetodi tuohon tulostukseen. Tökkää char taulukon alkiot
     * pötköön stringiksi.
     *
     * @param ca
     * @return
     */
    protected String charArrayToString(char[] ca) {
        StringBuilder builder = new StringBuilder(ca.length);
        for (char c : ca) {
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * Printtaa reitin labyrintissa käyttäen pistettä ( . ) merkkinä, mistä
     * annetun reitin reitti (joka on saatu polunetsintäalgoritmilta kuten
     * Astar) menee solmut -kentän "alakulmaan" jos ajatellaan x indeksin
     * kasvavan oikealle ja y indeksin alas mentäessä. Polunetsintä algoritmille
     * on tietysti pitänyt antaa tämän labyrintin solmut ja sama maalisolmu
     *
     * @param reitti
     * @param maali
     * @return
     */
    public String printtaaReittiLabyrintissa(Map<Solmu, Solmu> reitti, Solmu maali) {
        char[][] merkkitauluesitys = merkkitauluesitys();
        Solmu s = reitti.get(maali);
        if (s == null) {
            throw new IllegalArgumentException("Annettua maalisolmua ei löytynyt reitistä");
        }
        while (true) {
            if (s == null) {
                break;
            }
            int x = (int) Math.round(s.koordinaatti(0)) * 2 + 1;
            int y = (int) Math.round(s.koordinaatti(1)) * 2 + 1;
            merkkitauluesitys[x][y] = 'O';
            s = reitti.get(s);
        }
        StringBuilder build = new StringBuilder(merkkitauluesitys[0].length * merkkitauluesitys.length);
        for (char[] merkkitauluesity : merkkitauluesitys) {
            build.append(charArrayToString(merkkitauluesity)).append("\n");
        }
        return build.toString();
    }

}
