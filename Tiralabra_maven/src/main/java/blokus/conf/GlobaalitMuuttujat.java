package blokus.conf;

import java.awt.Color;

/**
 * Sisältää kaikki globaalit muuttuja, jotka pysyvät samoina kokoajan.
 * @author Simo Auvinen
 */
public abstract class GlobaalitMuuttujat {

    public static final int LAUDAN_KOKO = 20;  // 20x20
    public static final int RUUDUKON_KOKO = 7; // 7x7
    //
    public static final int LAATTA = 3;
    public static final int KIELLETTY_ALUE = 2;
    public static final int KULMA = 1;
    public static final int TYHJA = 0;
    //
    public static final boolean ANTAUDU = true;
    public static final boolean OHITA_VUORO = true;
    //
    public static final int LAUDAN_RESOLUUTIO = 400;
    public static final int VALITSIMEN_RESOLUUTIO = 400;
    public static final int PIENEN_VALITSIMEN_RESOLUUTIO = 200;
    //
    public static final Color SININEN_VARI = new Color(0, 157, 162);
    public static final Color ORANSSI_VARI = new Color(250, 150, 0);
    public static final Color PUNAINEN_VARI = new Color(154, 5, 0);
    public static final Color VIHREA_VARI = Color.getHSBColor(0.35f, 0.8f, 0.40f);
    //
    public static final int SININEN = 1;
    public static final int ORANSSI = 2;
    public static final int PUNAINEN = 3;
    public static final int VIHREA = 4;
}
