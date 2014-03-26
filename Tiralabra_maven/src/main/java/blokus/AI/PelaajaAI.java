package blokus.AI;

import blokus.conf.GlobaalitMuuttujat;
import blokus.logiikka.Laatta;
import blokus.logiikka.Pelaaja;
import blokus.logiikka.PeliLauta;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Tekoälyn pohja
 *
 * @author Ilkimys
 */
public class PelaajaAI {

    Pelaaja omaTilanne;
    ArrayList<Sijainti> laudanMahdolliset;
    Siirto parasSiirto;

    /**
     * Liitetään pelaaja tekoälyyn.
     *
     * @param aThis
     */
    public PelaajaAI(Pelaaja aThis) {
        omaTilanne = aThis;

    }

    /**
     * Aloittaa AI tekemän vuoron prosessoinnin ja asettaa valitun laatan
     * laudalle
     *
     * @param peliLauta
     */
    public void aloitaVuoro(PeliLauta peliLauta) {
        parasSiirto = null;
        listaaMahdollisetAsettamisPaikat();
        kayLaatatLapi();
        if (parasSiirto != null) {
            parasSiirto.getLaatta().kaannaTiettyynAsentoon(parasSiirto.getAsento(), parasSiirto.getYmpari());
            peliLauta.lisaaLaattaLaudalle(omaTilanne.getPelaajantID(), parasSiirto.getLaatta(), parasSiirto.getKohdeI(), parasSiirto.getKohdeJ());
            omaTilanne.getLaatat().getLaattaById(parasSiirto.getLaatta().getId());
            omaTilanne.getLaatat().poistaLaattaValitsemesta(parasSiirto.getLaatta().getId());
        } else {
            omaTilanne.getLaatat().setJaljellaLaatatTyhjaksi();
        }


    }

    /*
     * Asettaa listaan kaikki sinä hetkenä mahdolliset asettamispaikat
     */
    private void listaaMahdollisetAsettamisPaikat() {
        int[][] l = omaTilanne.getTarkastusLauta().getLauta();
        laudanMahdolliset = new ArrayList<Sijainti>();
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l.length; j++) {
                if (l[i][j] == 1) {
                    Sijainti x = new Sijainti(i, j);
                    laudanMahdolliset.add(x);
                }
            }
        }
        

    }

    /**
     * Tarkistaa voiko laatan lisätä nykyisessä asennossa tiettyyn kohtaan
     * @param laatta
     * @param y
     * @param x
     * @param tLauta
     * @return
     */
    private boolean voikoLisata(Laatta laatta, int y, int x, int[][] tLauta) {
        boolean laattaSalKohtaan = false;
        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {
                if (!onkoLaudalla(y, x, i, j) && laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA) {
                    return false;
                } else if (onkoLaudalla(y, x, i, j)) {
                    if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA && tLauta[y + i - 3][x + j - 3] == GlobaalitMuuttujat.KIELLETTY_ALUE) {
                        return false;
                    } else if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA && tLauta[y + i - 3][x + j - 3] == GlobaalitMuuttujat.LAATTA) {
                        return false;
                    }
                    if (laatta.getMuoto()[i][j] == GlobaalitMuuttujat.LAATTA && tLauta[y + i - 3][x + j - 3] == GlobaalitMuuttujat.KULMA) {
                        laattaSalKohtaan = true;
                    }
                }
            }
        }
        return laattaSalKohtaan;
    }

    /**
     * Tarkistaa että annettu koordinaatti on laudalla
     * @param y
     * @param x
     * @param i
     * @param j
     * @return
     */
    private boolean onkoLaudalla(int y, int x, int i, int j) {
        return y + i - 3 >= 0 && y + i - 3 < GlobaalitMuuttujat.LAUDAN_KOKO && x + j - 3 >= 0 && x + j - 3 < GlobaalitMuuttujat.LAUDAN_KOKO;
    }

//    /**
//     *
//     * @param l
//     */
//    public void kuvaalauta(int[][] l) {
//        for (int i = 0; i < l.length; i++) {
//            for (int j = 0; j < l.length; j++) {
//                System.out.print(l[i][j]);
//
//            }
//            System.out.println("");
//        }
//        System.out.println("");
//    }
    
    
    
    /*
     * Laskee jokaisen mahdollisen siirron "hyvyyden" 
     * ja pitää kirjaa sillä hetkellä parhaimmasta.
     */

    private void haeParasSiirto(Laatta la, Sijainti s, int[][] l) {
        for (int i = 0; i < la.getMuoto().length; i++) {
            for (int j = 0; j < la.getMuoto().length; j++) {
                if (la.getMuoto()[i][j] == 3) {
                    if (voikoLisata(la, s.getI() - (i - 3), s.getJ() - (j - 3), l)) {

                        Siirto si = new Siirto(s.getI() - (i - 3), s.getJ() - (j - 3), la);
                        if (parasSiirto == null || parasSiirto.getHyvyys() < si.getHyvyys()) {
                            parasSiirto = si;
                        }
                    }
                }
            }
        }
    }
    
    
    /*
     * Käy kaikki laatat ja laatan mahdolliset variaatiot
     */

    private void kayLaatatLapi() {
        int[][] l = omaTilanne.getTarkastusLauta().getLauta();
        HashMap<Integer, Laatta> jaljella = omaTilanne.getLaatat().getJaljellaLaatat();
        for (Sijainti s : laudanMahdolliset) {
            for (Laatta la : jaljella.values()) {
                la.palautaAlkuperainenAsento();
                haeParasSiirto(la, s, l);
                la.kaannaOikeaan();
                haeParasSiirto(la, s, l);
                la.kaannaOikeaan();
                haeParasSiirto(la, s, l);
                la.kaannaOikeaan();
                haeParasSiirto(la, s, l);
                la.kaannaOikeaan();
                la.kaannaYmpari();
                haeParasSiirto(la, s, l);
                la.kaannaOikeaan();
                haeParasSiirto(la, s, l);
                la.kaannaOikeaan();
                haeParasSiirto(la, s, l);
                la.kaannaOikeaan();
                haeParasSiirto(la, s, l);
            }

        }
    }
}
