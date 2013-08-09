package kolmiopeli;

import java.awt.Color;
import kolmiopeli.UI.Kayttoliittyma;
import kolmiopeli.domain.Kolmio;

/**
 * @author eemihauk
 */
public class KolmioPeli {

    public static void main(String[] args) {
//        Ruudukko ruudukko = new Ruudukko(8, 15);
//        AlkutilanteenLuoja tayttaja = new AlkutilanteenLuoja(ruudukko);
//        tayttaja.taytaRuudukko();
//        ruudukko.setPeliruudukko(testausPelilauta);
//        Siirrot s = new Siirrot();
//        s.setPeliruudukko(ruudukko);
//        
//        PistesiirtojenEtsija etsija = new PistesiirtojenEtsija(ruudukko);
//        s.setEtsija(etsija);

        

        Kayttoliittyma k = new Kayttoliittyma();
        k.run();
        
//        PeliFrame k = new PeliFrame(8, 15);
//        k.run();



//        System.out.println("**KOORDINAATIT OVAT MUOTOA (rivi, sarake), LAHTIEN (0,0)**");
//        System.out.println(ruudukko);
//        System.out.println("KolmioPareja = " + etsija.getKolmiopareja());
//        System.out.println("Pistesiirtoja = " + etsija.etsiKaikkiPistesiirrot());
//        System.out.println("");

//        System.out.println("Onko kolmio (0,1) ylospain? " + ruudukko.getRuudukko()[0][1].osoittaakoKolmioYlospain());
//        System.out.println("Onko kolmio (1,1) ylospain? " + ruudukko.getRuudukko()[1][1].osoittaakoKolmioYlospain());
//
//        System.out.println("");
//
//        System.out.println("Siirretaan (1,2) vasemmalle. Onnistuiko? " + s.siirraKolmioVasemmalle(1, 2));
//        System.out.println(ruudukko);
//        System.out.println("");
//
//        System.out.println("Siirretaan (2,3) oikealle. Onnistuiko? " + s.siirraKolmioOikealle(2, 3));
//        System.out.println(ruudukko);
//        System.out.println("");
//
//        System.out.println("Siirretaan (2,3) ylos. Onnistuiko? " + s.siirraKolmioYlos(2, 3));
//        System.out.println(ruudukko);
//        System.out.println("");




    }
    static Kolmio[][] testausPelilauta = {{new Kolmio(Color.BLUE, 0, 0), new Kolmio(Color.BLUE, 0, 1), new Kolmio(Color.YELLOW, 0, 2), new Kolmio(Color.RED, 0, 3), new Kolmio(Color.RED, 0, 4)},
        {new Kolmio(Color.YELLOW, 1, 0), new Kolmio(Color.BLUE, 1, 1), new Kolmio(Color.BLUE, 1, 2), new Kolmio(Color.YELLOW, 1, 3), new Kolmio(Color.YELLOW, 1, 4)},
        {new Kolmio(Color.BLUE, 2, 0), new Kolmio(Color.YELLOW, 2, 1), new Kolmio(Color.RED, 2, 2), new Kolmio(Color.BLUE, 2, 3), new Kolmio(Color.GREEN, 2, 4)},
        {new Kolmio(Color.RED, 3, 0), new Kolmio(Color.YELLOW, 3, 1), new Kolmio(Color.GREEN, 3, 2), new Kolmio(Color.BLUE, 3, 3), new Kolmio(Color.BLUE, 3, 4)}};
}
