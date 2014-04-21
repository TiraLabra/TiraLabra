package pacman;

import javax.swing.SwingUtilities;
import pacman.alusta.Peliruutu;
import pacman.gui.Kayttoliittyma;
import pacman.peli.Pacman;
import pacman.tietorakenteet.Jarjestaja;

public class Main {

    public static void main(String[] args) throws Exception {


//        long aikaAlussa = System.currentTimeMillis();
//        long aikaLopussa = System.currentTimeMillis();
//        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms."); 

        
        Pacman peli = new Pacman();

        Kayttoliittyma kayttis = new Kayttoliittyma(peli);

        SwingUtilities.invokeLater(kayttis);

        while (kayttis.getPaivitettava() == null) {
            try {
                Thread.sleep(0, 5);
            } catch (InterruptedException ex) {
                kayttis.virheilmoitus("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        peli.setPaivitettava(kayttis.getPaivitettava());
        peli.start();

    }
}
