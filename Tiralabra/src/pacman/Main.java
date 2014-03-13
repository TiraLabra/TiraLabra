package pacman;

import javax.swing.SwingUtilities;
import pacman.gui.Kayttoliittyma;
import pacman.peli.Pacman;

public class Main {

    public static void main(String[] args) throws Exception {  
       
        Pacman peli = new Pacman();
        Kayttoliittyma kayttis = new Kayttoliittyma(peli);
        
        SwingUtilities.invokeLater(kayttis);

        while (kayttis.getPaivitettava() == null) {
            try {
                Thread.sleep(0,5);
            } catch (InterruptedException ex) {
                kayttis.virheilmoitus("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        peli.setPaivitettava(kayttis.getPaivitettava());
        peli.start();

    }
}
