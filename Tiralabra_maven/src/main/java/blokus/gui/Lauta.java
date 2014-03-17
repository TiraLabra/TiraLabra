package blokus.gui;

import blokus.conf.GlobaalitMuuttujat;
import blokus.logiikka.Laatta;
import blokus.logiikka.PeliLauta;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Visualisoi pelilaudan ja visualisoi pelilaudan yllä olevan "ilmatilan"
 * @author Simo Auvinen
 */
public class Lauta {

    private PeliLauta lauta;
    private int resoluutio;
    private int[][] varjoLauta;
    private int[][] kulmat;
    

    /**
     * Liittää logiikan peliladan käyttöliittymään
     * @param peliLauta
     * @param resoluutio
     */
    public Lauta(PeliLauta lauta, int resoluutio) {
        this.lauta = lauta;
        this.resoluutio = resoluutio;
        varjoLauta = new int[GlobaalitMuuttujat.LAUDAN_KOKO][GlobaalitMuuttujat.LAUDAN_KOKO];
        kulmat = new int[GlobaalitMuuttujat.LAUDAN_KOKO][GlobaalitMuuttujat.LAUDAN_KOKO];
        lauta.alusta(varjoLauta);
        lauta.alusta(kulmat);
        alustaKulmat();

    }

    /**
     *
     * @param laatta
     * @param y
     * @param x
     */
    public void paivitaVarjoLautaa(Laatta laatta, int y, int x) {
        lauta.alusta(varjoLauta);
        for (int i = 0; i < GlobaalitMuuttujat.RUUDUKON_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.RUUDUKON_KOKO; j++) {
                if (lauta.onkoLaudalla(y, x, i, j) && laatta.getTaulukonArvo(i, j) == GlobaalitMuuttujat.LAATTA) {
                    varjoLauta[y + i - 3][x + j - 3] = laatta.getPelaajanID();
                }
            }
        }
    }

    /**
     *
     */
    public void alustaVarjoLauta() {
        lauta.alusta(varjoLauta);
    }

    /**
     *
     * @return
     */
    public BufferedImage muodostaLaudastaKuva() {
        BufferedImage kuva = new BufferedImage(resoluutio + 1, resoluutio + 1, BufferedImage.TYPE_INT_RGB);
        int ruudunKoko = resoluutio / (GlobaalitMuuttujat.LAUDAN_KOKO);
        Graphics2D g = (Graphics2D) kuva.getGraphics();
        for (int i = 0; i < GlobaalitMuuttujat.LAUDAN_KOKO; i++) {
            for (int j = 0; j < GlobaalitMuuttujat.LAUDAN_KOKO; j++) {
                g.setColor(getVari(lauta.getRuudunArvo(j, i)));
                if (varjoLauta[j][i] != 0 && lauta.getRuudunArvo(j, i) != 0) {
                    g.setColor(Color.BLACK);
                } else if (varjoLauta[j][i] != 0) {
                    g.setColor(getVari(varjoLauta[j][i]));
                }
                g.fillRect(i * ruudunKoko, j * ruudunKoko, ruudunKoko, ruudunKoko);
                if (kulmat[j][i] != 0 && lauta.getRuudunArvo(j, i) == 0) {
                    g.setColor(getVari(kulmat[j][i]));
                    g.fillRect(i * ruudunKoko+ruudunKoko/2-ruudunKoko/4, j * ruudunKoko+ruudunKoko/2-ruudunKoko/4, ruudunKoko/2, ruudunKoko/2);
                }
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(i * ruudunKoko , j * ruudunKoko, ruudunKoko, ruudunKoko);
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, resoluutio, resoluutio);
        return kuva;

    }

    /**
     *
     * @param pikseli
     * @return
     */
    public Point getSijainti(Point pikseli) {
        return new Point(pikseli.y / (resoluutio / GlobaalitMuuttujat.LAUDAN_KOKO), pikseli.x / (resoluutio / GlobaalitMuuttujat.LAUDAN_KOKO));
    }

    private static Color getVari(int vari) {
        switch (vari) {
            case GlobaalitMuuttujat.SININEN:
                return GlobaalitMuuttujat.SININEN_VARI;
            case GlobaalitMuuttujat.ORANSSI:
                return GlobaalitMuuttujat.ORANSSI_VARI;
            case GlobaalitMuuttujat.PUNAINEN:
                return GlobaalitMuuttujat.PUNAINEN_VARI;
            case GlobaalitMuuttujat.VIHREA:
                return GlobaalitMuuttujat.VIHREA_VARI;
            default:
                return Color.WHITE;
        }
    }
    

    private void alustaKulmat() {
        kulmat[0][0] = 1;
        kulmat[0][19] = 2;
        kulmat[19][19] = 3;
        kulmat[19][0] = 4;
        
    }
}
