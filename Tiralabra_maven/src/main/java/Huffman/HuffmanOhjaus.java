package Huffman;

import Apuvalineet.Kirjoittaja;
import Apuvalineet.Lukija;
import java.io.IOException;

/**
 * Luokka on t‰llainen yleisluokka, joka huolehtii koko pakkausoperaation suorituksesta pyyt‰en
 * muita luokkia ja olioita tekem‰‰n asiat sen puolesta.
 * 
 * Ensin luetaan tiedosto, sitten muodostetaan Huffman puu, m‰‰ritet‰‰n esiintyneille merkeille bittiesitykset,
 * muodostetaan kirjoitettava teksti ja kirjoitetaan se.
 */

public class HuffmanOhjaus {
    
    public void suoritaPakkaaminen(String polku) throws IOException {
        Lukija lukija = new Lukija(true);
        lukija.lue(polku);

        HuffmanPuu puu = new HuffmanPuu();
        puu.muodostaHuffmanPuu(lukija.getEsiintymat());

        BittiEsitykset esitykset = new BittiEsitykset();
        esitykset.muodostaMerkeilleBittiEsitykset(puu.getKeko().getSolmut()[0], "");
        
        HuffmanPakkaaja pakkaaja = new HuffmanPakkaaja();
        String teksti = pakkaaja.muodostaKirjoitettavaTeksti(lukija.getTeksti(), esitykset);

        Kirjoittaja kirjoittaja = new Kirjoittaja(polku + ".huff");
        kirjoittaja.kirjoita(teksti);
    }
}