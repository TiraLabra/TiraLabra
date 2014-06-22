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
        Kirjoittaja kirjoittaja = new Kirjoittaja(polku + ".huff");
        kirjoittaja.kirjoita(kirjoitettavaTeksti(polku));
    }
    
    protected String kirjoitettavaTeksti(String polku) throws IOException {
        Lukija lukija = new Lukija(true);
        lukija.lue(polku);

        BittiEsitykset esitykset = muodostaBittiEsitykset(lukija);
        return new HuffmanPakkaaja().muodostaKirjoitettavaTeksti(lukija.getTeksti(), esitykset);
    }
    
    protected BittiEsitykset muodostaBittiEsitykset(Lukija lukija) {
        BittiEsitykset esitykset = new BittiEsitykset();
        
        HuffmanPuu puu = new HuffmanPuu();
        puu.muodostaHuffmanPuu(lukija.getEsiintymat());
        esitykset.muodostaMerkeilleBittiEsitykset(puu.getKeko().getSolmut()[0], "");
        
        return esitykset;
    }
}