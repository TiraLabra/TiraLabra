package labyrintti;

import labyrintti.gui.Menu;
import labyrintti.logiikka.LyhinReitti;
import labyrintti.logiikka.Maapala;
import labyrintti.logiikka.Maapalarekisteri;

/**
 * @author Mikael Parvamo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Menu menu = new Menu();

        //menu.run();
        int k = 0;

        while (k < 10) {

            Maapalarekisteri maapalarekisteri = new Maapalarekisteri(1000, 0, 0, 999, 999);
            maapalarekisteri.luoMaapalat();
            maapalarekisteri.alustaMaapalat();
            LyhinReitti lyhinreitti = new LyhinReitti(maapalarekisteri);
            Maapala[][] labyrintti = maapalarekisteri.getLabyrintti();
            labyrintti[998][999].asetaSeinaksi();
            labyrintti[998][998].asetaSeinaksi();
            labyrintti[999][998].asetaSeinaksi();



            long aikaAlussa = System.currentTimeMillis();
            lyhinreitti.etsiLyhinReitti();
            long aikaLopussa = System.currentTimeMillis();
            System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");

            k++;


        }
    }
}
