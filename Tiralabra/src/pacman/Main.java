package pacman;

import javax.swing.SwingUtilities;
import pacman.alusta.Peliruutu;
import pacman.gui.Kayttoliittyma;
import pacman.peli.Jarjestaja;
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
        
        
//        Peliruutu[] solmut = new Peliruutu[4];
//        solmut[0] = new Peliruutu(1, 1);
//        solmut[0].setEtaisyysAlkuun(3);
//        solmut[0].setEtaisyysMaaliin(7);
//        solmut[1] = new Peliruutu(2, 2);
//        solmut[1].setEtaisyysAlkuun(1);
//        solmut[1].setEtaisyysMaaliin(2);
//        solmut[2] = new Peliruutu(3, 3);
//        solmut[2].setEtaisyysAlkuun(10);
//        solmut[2].setEtaisyysMaaliin(2);
//        solmut[3] = new Peliruutu(4, 4);
//        solmut[3].setEtaisyysAlkuun(3);
//        solmut[3].setEtaisyysMaaliin(3);
//        for (Peliruutu peliruutu : solmut) {
//            System.out.println(peliruutu.toString());
//        }
//        
//        Jarjestaja koe = new Jarjestaja(solmut);
//        koe.mergeSort(0, solmut.length-1);
//        
//        for (Peliruutu peliruutu : solmut) {
//            System.out.println(peliruutu.toString());
//        }

    }
}
