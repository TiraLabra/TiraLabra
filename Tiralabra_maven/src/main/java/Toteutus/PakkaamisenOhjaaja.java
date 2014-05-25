package Toteutus;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Luokka on tällä hetkellä tällainen yleisluokka, joka huolehtii koko pakkausoperaation suorituksesta pyytäen
 * muita luokkia ja olioita tekemään asiat sen puolesta.
 * 
 * Ensin luetaan tiedosto, sitten muodostetaan Huffman puu, määritetään esiintyneille merkeille bittiesitykset ja
 * luodaan uusi tiedosto (pakkaus). Tiedoston pakkaaminen ei ole vielä toteutettu.
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
