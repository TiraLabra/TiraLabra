package pacman;

import javax.swing.SwingUtilities;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.gui.Kayttoliittyma;
import pacman.peli.Pacman;
import pacman.tietorakenteet.AStar;

public class Main {

    public static void main(String[] args) throws Exception {

        Pacman peli = new Pacman();
        
        Pelialusta alusta = peli.getAlusta();
        AStar haku = new AStar();
        haku.astar(alusta, alusta.getPeliruutu(9, 7), alusta.getPeliruutu(9, 11));
        Peliruutu[] reitti = haku.getReitti();
        for (int i = reitti.length-1; i >= 0; i--) {
            System.out.println(reitti[i]);
        }

//        Kayttoliittyma kayttis = new Kayttoliittyma(peli);
//
//        SwingUtilities.invokeLater(kayttis);
//
//        while (kayttis.getPaivitettava() == null) {
//            try {
//                Thread.sleep(0, 5);
//            } catch (InterruptedException ex) {
//                kayttis.virheilmoitus("Piirtoalustaa ei ole vielä luotu.");
//            }
//        }
//
//        peli.setPaivitettava(kayttis.getPaivitettava());
//        peli.start();

    }
}
