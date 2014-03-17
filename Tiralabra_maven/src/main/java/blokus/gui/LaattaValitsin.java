package blokus.gui;

import blokus.conf.GlobaalitMuuttujat;
import blokus.logiikka.PelaajanLaatat;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Luokassa muodostetaan näköiskuvat laattavalitsimen sen hetkisistä tiloista
 * 
 * @author Simo Auvinen
 */
public class LaattaValitsin {

    PelaajanLaatat laatat;

    /**
     * Asettaa vuorossa olevan laattojen tilanteen esille 
     * @param laatat
     */
    public LaattaValitsin(PelaajanLaatat laatat) {
        this.laatat = laatat;
    }

    /**
     * Muodostaa näköiskuvan isosta laattavalitsimesta
     * @return 
     */
    public BufferedImage muodostaValitsimestaKuva() {
        int[][] valitsin = laatat.getLaattaValitsin();
        BufferedImage kuva = new BufferedImage(GlobaalitMuuttujat.VALITSIMEN_RESOLUUTIO -8, GlobaalitMuuttujat.VALITSIMEN_RESOLUUTIO /2 +5, BufferedImage.TYPE_INT_RGB);
        int ruudunKoko = GlobaalitMuuttujat.VALITSIMEN_RESOLUUTIO / (valitsin[0].length);
        Graphics2D g = (Graphics2D) kuva.getGraphics();              
        for (int i = 0; i < valitsin[0].length; i++) {
            for (int j = 0; j < valitsin.length; j++) {
                if (valitsin[j][i] != GlobaalitMuuttujat.TYHJA) {
                    g.setColor(getVari(laatat.getPelaajanID()));
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * ruudunKoko, j * ruudunKoko, ruudunKoko, ruudunKoko);
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(i * ruudunKoko, j * ruudunKoko, ruudunKoko, ruudunKoko);
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, GlobaalitMuuttujat.VALITSIMEN_RESOLUUTIO -9, GlobaalitMuuttujat.VALITSIMEN_RESOLUUTIO /2 +4);
        return kuva;

    }

    /**
     *
     * @param pelaajanLaatat
     * @return
     */
    public BufferedImage muodostaPieniKuvaValitsimesta(PelaajanLaatat pelaajanLaatat) {
        int[][] valitsin = pelaajanLaatat.getLaattaValitsin();
        BufferedImage kuva = new BufferedImage(GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO - 15, GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO/2 -2, BufferedImage.TYPE_INT_RGB);
        int ruudunKoko = GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO / (valitsin[0].length);
        Graphics2D g = (Graphics2D) kuva.getGraphics();
        for (int i = 0; i < valitsin[0].length; i++) {
            for (int j = 0; j < valitsin.length; j++) {
                if (valitsin[j][i] != GlobaalitMuuttujat.TYHJA) {
                    g.setColor(getVari(pelaajanLaatat.getPelaajanID()));
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * ruudunKoko, j * ruudunKoko, ruudunKoko, ruudunKoko);
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(i * ruudunKoko, j * ruudunKoko, ruudunKoko, ruudunKoko);
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO -16, GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO -4);
        return kuva;

    }

    /**
     *
     * @param color
     * @return
     */
    public BufferedImage muodostaTyhjaKuva(Color color) {
        BufferedImage kuva = new BufferedImage(GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO -16, GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO /2 -4, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) kuva.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO -16, GlobaalitMuuttujat.PIENEN_VALITSIMEN_RESOLUUTIO /2 -4);
        return kuva;
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

    /**
     *
     * @param point
     * @return
     */
    public Point getSijainti(Point point) {
        return new Point(point.y / (GlobaalitMuuttujat.VALITSIMEN_RESOLUUTIO / 23), point.x / (GlobaalitMuuttujat.VALITSIMEN_RESOLUUTIO / 23));
    }

    /**
     *
     * @param laatat
     */
    public void setLaatat(PelaajanLaatat laatat) {
        this.laatat = laatat;
    }
}
