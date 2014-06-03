package Toteutus.Huffman.Pakkaaminen;

import Toteutus.Huffman.BittiEsitykset;
import Toteutus.Huffman.HuffmanPuu;
import Toteutus.TekstinLukija;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka on tällä hetkellä tällainen yleisluokka, joka huolehtii koko pakkausoperaation suorituksesta pyytäen
 * muita luokkia ja olioita tekemään asiat sen puolesta.
 * 
 * Ensin luetaan tiedosto, sitten muodostetaan Huffman puu, määritetään esiintyneille merkeille bittiesitykset ja
 * luodaan uusi tiedosto (pakkaus).
 */

public class PakkaamisenOhjaaja {
    
    public void suoritaPakkaaminen(String polku) throws FileNotFoundException, IOException {
        TekstinLukija lukija = new TekstinLukija();
        lukija.lueTiedosto(polku);
        
        HuffmanPuu puu = new HuffmanPuu();
        puu.muodostaHuffmanPuu(lukija.getEsiintymat());
        
        BittiEsitykset esitykset = new BittiEsitykset();
        esitykset.muodostaMerkeilleBittiEsitykset(puu.getKeko().getSolmut()[0], "");
        
        TiedostonPakkaaja pakkaaja = new TiedostonPakkaaja();
        pakkaaja.pakkaaTiedosto(lukija, puu, esitykset, polku);
    }
}
