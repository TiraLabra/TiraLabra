
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author maef
 *
 * Muutetaan labyrintin sisältävä kuvatiedosto javalle luettavaan muotoon ja
 * määrätään etäisyydet solmujen (kuvan koordinaattien) välille.
 */
public class Labyrintti {

    private BufferedImage labyrintti;
    private int lattia;
    private int seina;
    Solmu[][] verkko;

    public Labyrintti(String laby) {
        try {
            labyrintti = ImageIO.read(new File("src/" + laby + ".bmp"));
        } catch (Exception e) {
            System.out.println("Kuvaa ei löytynyt");
        }
        asetaLattiaJaSeina();
        luoSolmuverkko();
    }

    /*
     * Luo kuvasta taulukkoesityksen.
     */
    private void luoSolmuverkko() {
        verkko = new Solmu[getHeight()][getWidth()];

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Solmu s = new Solmu(j, i, verkko);
               s.setOnkoSeina(seina(j, i));
                verkko[i][j] = s;
            }
        }
    }

    public BufferedImage haeLaby() {
        return labyrintti;
    }

    private void asetaLattiaJaSeina() {
        lattia = -1; //Valkoinen
        seina = -16777216; //Musta
    }

    private boolean seina(int i, int j) {
        return labyrintti.getRGB(i, j) == seina;


    }

    /**
     *
     * @param x Metodilla määritetään pisteen etäisyysarvo, eli kuinka pitkä
     * matka on kuljettava, että siihen voi päästä.
     */
    public int etaisyys(Solmu x) {
        try {
            if (labyrintti.getRGB(x.getX(), x.getY()) == lattia) {
                return 1;
            } else {
                return 1000000;
            }
        } catch (Exception e) {
            System.out.println("Exception: ");
            System.out.println("  solmu.getx: " + x.getX());
            System.out.println("  solmu.gety: " + x.getY());
            System.out.println("  " + e.getMessage());
            return 9999999;
        }
    }

    public int getLattia() {
        return lattia;
    }

    public int getSeina() {
        return seina;
    }

    public int getWidth() {
        return labyrintti.getWidth();
    }

    public int getHeight() {
        return labyrintti.getHeight();
    }

    public Solmu[][] getVerkko() {
        return verkko;
    }
}
