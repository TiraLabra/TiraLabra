package Toteutus.Huffman.Pakkaaminen;

import Apuvalineet.Kirjoittaja;
import Apuvalineet.Lukija;
import Apuvalineet.TekstinLukija;
import Toteutus.Huffman.BittiEsitykset;
import Toteutus.Huffman.HuffmanPuu;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka on t‰llainen yleisluokka, joka huolehtii koko pakkausoperaation suorituksesta pyyt‰en
 * muita luokkia ja olioita tekem‰‰n asiat sen puolesta.
 * 
 * Ensin luetaan tiedosto, sitten muodostetaan Huffman puu, m‰‰ritet‰‰n esiintyneille merkeille bittiesitykset ja
 * luodaan uusi tiedosto (pakkaus).
 */

public class PakkaamisenOhjaaja {
    
    public void suoritaPakkaaminen(String polku) throws FileNotFoundException, IOException, Exception {
        TekstinLukija lukija = new TekstinLukija();
        lukija.lueTiedosto(polku);

        HuffmanPuu puu = new HuffmanPuu();
        puu.muodostaHuffmanPuu(lukija.getEsiintymat());

        BittiEsitykset esitykset = new BittiEsitykset();
        esitykset.muodostaMerkeilleBittiEsitykset(puu.getKeko().getSolmut()[0], "");
        
        TiedostonPakkaaja pakkaaja = new TiedostonPakkaaja();
        String teksti = pakkaaja.muodostaKirjoitettavaTeksti(lukija.getTeksti(), puu, esitykset);

        Kirjoittaja kirjoittaja = new Kirjoittaja(polku + ".hemi");
        kirjoittaja.kirjoita(teksti);
    }
}