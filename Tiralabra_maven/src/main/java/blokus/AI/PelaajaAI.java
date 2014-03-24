package blokus.AI;

import blokus.conf.GlobaalitMuuttujat;
import blokus.logiikka.Laatta;
import blokus.logiikka.Pelaaja;
import blokus.logiikka.PeliLauta;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ilkimys
 */
public class PelaajaAI {

    Pelaaja omaTilanne;
    ArrayList<Sijainti> laudanMahdolliset;
    Siirto parasSiirto;

    public PelaajaAI(Pelaaja aThis) {
        omaTilanne = aThis;

    }

    public void aloitaVuoro(PeliLauta peliLauta) {
        parasSiirto = null;
        listaaMahdollisetAsettamisPaikat();
        if (parasSiirto != null) {
            peliLauta.lisaaLaattaLaudalle(omaTilanne.getPelaajantID(), parasSiirto.getLaatta(), parasSiirto.getKohdeI(), parasSiirto.getKohdeJ());
            omaTilanne.getLaatat().getLaattaById(parasSiirto.getLaatta().getId());
            omaTilanne.getLaatat().poistaLaattaValitsemesta(parasSiirto.getLaatta().getId());
        } else {
            omaTilanne.getLaatat().setJaljellaLaatatTyhjaksi();
        }


    }

    private void listaaMahdollisetAsettamisPaikat() {
        int[][] l = omaTilanne.getTarkastusLauta().gettLauta();
        kuvaalauta(l);
        laudanMahdolliset = new ArrayList<Sijainti>();
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l.length; j++) {
                if (l[i][j] == 1) {
                    Sijainti x = new Sijainti(i, j);
                    laudanMahdolliset.add(x);
                }
            }
        }
        HashMap<Integer, Laatta> jaljella = omaTilanne.getLaatat().getJaljellaLaatat();
        for (Sijainti s : laudanMahdolliset) {
            for (Laatta la : jaljella.values()) {
                int[][] muoto = la.getMuoto();
                for (int i = 0; i < muoto.length; i++) {
                    for (int j = 0; j < muoto.length; j++) {
                        if (muoto[i][j] == 3) {
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

        }

    }

    public boolean voikoLisata(Laatta laatta, int y, int x, int[][] tLauta) {
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

    public boolean onkoLaudalla(int y, int x, int i, int j) {
        return y + i - 3 >= 0 && y + i - 3 < GlobaalitMuuttujat.LAUDAN_KOKO && x + j - 3 >= 0 && x + j - 3 < GlobaalitMuuttujat.LAUDAN_KOKO;
    }
    
    public void kuvaalauta(int[][] l) {
        for (int i = 0; i < l.length; i++) {
            for (int j = 0; j < l.length; j++) {
                System.out.print(l[i][j]);
                
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
}
