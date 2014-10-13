package viidensuora.ai;

import viidensuora.logiikka.Ristinolla;

/**
 * Kuvaa pelilaudan aktiivisia pelialueita, jotta Etsintämetodit voivat karsia
 * turhia alueita hakupuusta.
 *
 * @author juha
 */
public class AktiivinenAlue {

    /**
     * Pelitilanne.
     */
    private final Ristinolla ristinolla;

    /**
     * Aktiiviset ruudut ovat arvoltaan suurempia kuin 0.
     */
    private int[][] aktiivinenAlue;

    public AktiivinenAlue(Ristinolla ristinolla) {
        this.ristinolla = ristinolla;
    }

    /**
     * Alustaa aktiiviset alueet tämänhetkisen pelitilanteen perusteella.
     *
     * @param aktiivistenEtaisyys Etäisyys jonka sisällä tyhjät ruudut katsotaan
     * aktiivisiksi
     */
    public final void alusta(int aktiivistenEtaisyys) {
        aktiivinenAlue = new int[ristinolla.korkeus][ristinolla.leveys];

        // Jos pelilauta on tyhjä, aseta laudan keskimmäinen ruutu aktiiviseksi
        if (ristinolla.laudallaMerkkeja() == 0) {
            paivita(ristinolla.leveys / 2, ristinolla.korkeus / 2, 0, 1);
            return;
        }

        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                if (!ristinolla.ruutuOnTyhja(x, y)) {
                    paivita(x, y, aktiivistenEtaisyys, 1);
                }
            }
        }
    }

    /**
     * Tarkistaa onko laudan ruutu tyhjä ja aktiivinen.
     *
     * @param x Ruudun x-koordinaaatti.
     * @param y Ruudun y-koordinaatti.
     * @return TRUE jos ruutu on tyhjä ja aktiivinen, FALSE jos ruudussa on jo
     * merkki tai ruutu on tyhjä muttei aktiivinen.
     */
    public boolean onAktiivinenTyhja(int x, int y) {
        return aktiivinenAlue[y][x] > 0 && ristinolla.ruutuOnTyhja(x, y);
    }

    /**
     * Päivittää annetun koordinaatin lähellä olevat ruudut aktiivisiksi. Vain
     * samalla vaaka-, pysty- ja diagonaalirivillä olevat ruudut asetetaan
     * aktiiviseksi. "Ratsun siirto" on tuskin missään tilanteessa hyödyllinen.
     *
     * @param x Keskimmäisen ruudun x-koordinaatti
     * @param y Keskimmäisen ruudun y-koordinaatti
     * @param etaisyys Etäisyys jonka sisällä olevat ruudut asetetaan
     * aktiiviseksi
     * @param muutos +1 lisää aktiiviseksi, -1 poistaa aktiivisista
     */
    public void paivita(int x, int y, int etaisyys, int muutos) {
        for (int dy = -etaisyys; dy <= etaisyys; dy++) {
            for (int dx = -etaisyys; dx <= etaisyys; dx++) {
                int paivX = x + dx;
                int paivY = y + dy;
                if (ristinolla.onLaudalla(paivX, paivY)
                        && (dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy))) {
                    aktiivinenAlue[paivY][paivX] += muutos;
                }
            }
        }
    }

    /**
     *
     * @return Merkkijonoesitys Aktiivisesta alueesta
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < ristinolla.korkeus; y++) {
            for (int x = 0; x < ristinolla.leveys; x++) {
                sb.append(aktiivinenAlue[y][x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
