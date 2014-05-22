package Main;

import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class TiedostonPakkaaja {
    private HashMap<String, Integer> esiintymat;
    private HashMap<String, String> bittijonot;
    private String teksti;
    
    public TiedostonPakkaaja() {
        this.esiintymat = new HashMap<String, Integer>();
        this.teksti = "";
    }
    
    public void pakkaa(String polku) throws IOException {
        
        try {
            haeTeksti(polku);
        }
        
        catch (FileNotFoundException e) {
            System.out.print("Tiedoston luku ei onnistunut. Annoit sen polun väärin.\nOhjelma suljetaan.");
            return;
        }
        
        MinKeko keko = muodostaKeko();
        yhdistaKeonSolmut(keko);
        bittijonot = new HashMap<String, String>();
        
        muodostaMerkeilleBittiEsitykset(keko.getKeko()[0], "");
        
        luoUusiTiedosto(keko, polku);
    }
    
    private void haeTeksti(String polku) throws FileNotFoundException {
        Scanner lukija = new Scanner(new File(polku));
        
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            lisaaRivi(rivi);

            if (lukija.hasNextLine()) {
                lisaaMerkki("\n");
            }
        }
    }
    
    private void lisaaRivi(String rivi) {
        Scanner rivinLukija = new Scanner(rivi);
        
        while (rivinLukija.hasNext()) {
            String sana = rivinLukija.next();
                
            for (int i = 0; i < sana.length(); i++) {
                lisaaMerkki(sana.charAt(i) + "");
            }
            
            if (rivinLukija.hasNext()) {
                lisaaMerkki(" ");
            }
        }
    }
    
    private void lisaaMerkki(String merkki) {
        int maara = 1;
        
        if (esiintymat.containsKey(merkki)) {
            maara += esiintymat.get(merkki);
        }
        
        esiintymat.put(merkki, maara);
        teksti += merkki;
    }
    
    private MinKeko muodostaKeko() {
        MinKeko keko = new MinKeko(esiintymat.keySet().size());
        for (String avain : esiintymat.keySet()) {
            keko.lisaa(new Solmu(avain, esiintymat.get(avain)));
        }
        
        return keko;
    }
    
    private void yhdistaKeonSolmut(MinKeko keko) {
        while (keko.getKoko() > 1) {
            Solmu vasen = keko.poistaHuippuSolmu();
            Solmu oikea = keko.poistaHuippuSolmu();
            
            Solmu yhdistetty = new Solmu(vasen.getEsiintymat() + oikea.getEsiintymat());
            
            yhdistetty.setVasen(vasen);
            yhdistetty.setOikea(oikea);
            vasen.setVanh(yhdistetty);
            oikea.setVanh(yhdistetty);
            
            keko.lisaa(yhdistetty);
        }
    }

    private void muodostaMerkeilleBittiEsitykset(Solmu huippu, String bittijono) {
//        if (huippu == null) {
//            return;
//        }
        
        if (huippu.getAvain() != null) {
            bittijonot.put(huippu.getAvain(), bittijono);
            return;
        }
        
        muodostaMerkeilleBittiEsitykset(huippu.getVasen(), bittijono + "0");
        muodostaMerkeilleBittiEsitykset(huippu.getOikea(), bittijono + "1");
    }

//    private int kokonaisluvuksi(String bittijono) {
//        int luku = 0;
//        
//        for (int i = 0; i < bittijono.length(); i++) {
//            if (bittijono.charAt(i) == '1') {
//                luku += Math.pow(2, bittijono.length() - i - 1);    // 1001010
//            }
//        }
//        return luku;
//    }

    private void luoUusiTiedosto(MinKeko keko, String polku) throws IOException {
        File tiedosto = new File(polku + ".mihu");
        if (! tiedosto.exists()) {
            tiedosto.createNewFile();
        }
        else {
            System.out.print("Tiedostoa vastaava pakkaus on jo olemassa. Tiedostoa ei pakata uudestaan.");
            return;
        }
        
        tiedosto.setWritable(true);
        kirjoitaTiedostoon(keko, tiedosto);
    }
    
    private void kirjoitaTiedostoon(MinKeko keko, File tiedosto) throws IOException {
        FileWriter kirjoittaja = new FileWriter(tiedosto);
        
        for (int i = 0; i < teksti.length(); i++) {
            String merkki = teksti.charAt(i) + "";
            String binary = bittijonot.get(merkki);
            
            kirjoittaja.append(binary);
        }
        
        // Huffman puu (eli keko) täytyy jollain lailla kirjoittaa tekstitiedostoon mukaan siten että sitä voidaan käyttää purkuvaiheessa järkevästi.
        
        kirjoittaja.close();
    }
}
