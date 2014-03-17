package blokus.logiikka;

import blokus.conf.GlobaalitMuuttujat;
import java.util.HashMap;
import java.util.Map;

/**
 * Pelilaudalle sijoitetaan laatat. Pelilauta esittää laattojen omistajat.
 * Pelilauta päivittää myös pelaajien tarkastus laudat oikeanlaisiksi.
 * 
 * @author Simo Auvinen
 */
public class PeliLauta {

    private int[][] lauta;
    private HashMap<Integer, TarkastusLauta> tarkastusLaudat;
    private String tuoreinVirheTeksti;

    /**
     * Luo vakio kokoisen pelilaudan ja alustaa sen.
     */
    public PeliLauta() {
        this.tuoreinVirheTeksti = "";
        lauta = new int[GlobaalitMuuttujat.LAUDAN_KOKO][GlobaalitMuuttujat.LAUDAN_KOKO];
        alusta(lauta);
        tarkastusLaudat = new HashMap<Integer, TarkastusLauta>();

    }

    /**
     * Metodi kysyy tarkistuslaudalta voiko laatan lisätä kyseiseen kohtaa,
     * jos voi niin aloittaa laatan lisäämisen. Jos taas ei, niin asettaa 
     * virhetekstin, joka kuvaa miksi lisääminen tähän kohtaan ei onnistu.
     * 
     * @param pelaajaId
     * @param laatta  Lisättävä laatta
     * @param y
     * @param x
     * @return Palauttaa true, jos laatan lisääminen onnistui, muuten false
     */
    public boolean lisaaLaattaLaudalle(int pelaajaId, Laatta laatta, int y, int x) {
        TarkastusLauta kasiteltava = tarkastusLaudat.get(pelaajaId);
        String onnistuuko = kasiteltava.tarkistaVoikoLisata(laatta, y, x);
        if (onnistuuko.isEmpty()) {
            lisaaLaatta(kasiteltava, laatta, y, x);
            setTuoreinVirheTeksti("");
            return true;
        } else {
            setTuoreinVirheTeksti(onnistuuko);
            return false;
        }

    }
    
        /**
     * Metodi päivittää jo hyväksytyn asettamisen pelilaudalle ja kaikille
     * tarkastuslaudoille.
     * 
     * @param kasiteltava  Tarkastus lauta jolle laatta lisätään
     * @param laatta  Lisättävä laatta
     * @param y
     * @param x
     */

    private void lisaaLaatta(TarkastusLauta kasiteltava, Laatta laatta, int y, int x) {
        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {

                if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA) {
                    lisaaLaattaTarkastusLautoihin(y + i - 3, x + j - 3);
                    muutaRuudunOmistaja(laatta.getPelaajanID(), y + i - 3, x + j - 3);
                } else if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.KULMA && onkoLaudalla(y, x, i, j)) {
                    kasiteltava.lisaaLaatta(1, y + i - 3, x + j - 3);
                } else if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.KIELLETTY_ALUE && onkoLaudalla(y, x, i, j)) {
                    kasiteltava.lisaaLaatta(2, y + i - 3, x + j - 3);
                }
            }
        }
    }

    /**
     *
     * @param y
     * @param x
     * @param i
     * @param j
     * @return palauttaa true, jos koordinaatti sijaitsee pelilaudalla
     */
    public boolean onkoLaudalla(int y, int x, int i, int j) {
        return y + i - 3 >= 0 && y + i - 3 < GlobaalitMuuttujat.LAUDAN_KOKO && x + j - 3 >= 0 && x + j - 3 < GlobaalitMuuttujat.LAUDAN_KOKO;
    }

    /**
     *
     * @param pelaajaId
     * @param y
     * @param x Muuttaa ruudun omistajuuden halutun pelaajan haltuun
     */
    public void muutaRuudunOmistaja(int pelaajaId, int y, int x) {
        this.lauta[y][x] = pelaajaId;
    }

  
    public void lisaaTarkastusLauta(int id, TarkastusLauta l) {
        tarkastusLaudat.put(id, l);
    }

    private void lisaaLaattaTarkastusLautoihin(int y, int x) {
        for (Map.Entry<Integer, TarkastusLauta> entry : tarkastusLaudat.entrySet()) {
            TarkastusLauta tarkastusLauta = entry.getValue();
            tarkastusLauta.lisaaLaatta(GlobaalitMuuttujat.LAATTA, y, x);

        }
    }

    /**
     *
     * @param alustettava Alustaa laudat kokonsa perustuen
     */
    public void alusta(int[][] alustettava) {
        for (int i = 0; i < alustettava.length; i++) {
            for (int j = 0; j < alustettava.length; j++) {
                alustettava[i][j] = 0;
            }
        }
    }

    public HashMap<Integer, TarkastusLauta> getTarkastusLaudat() {
        return tarkastusLaudat;
    }

    public int[][] getLauta() {
        return lauta;
    }

    public int getRuudunArvo(int y, int x) {
        return lauta[y][x];
    }

    public String getVirheTeksti() {
        return tuoreinVirheTeksti;
    }

    public void setTuoreinVirheTeksti(String tuoreinVirheTeksti) {
        this.tuoreinVirheTeksti = tuoreinVirheTeksti;
    }
}
