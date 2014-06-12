
import Tietorakenteet.HajautusTaulu;
import Toteutus.Huffman.Pakkaaminen.PakkaamisenOhjaaja;
import Toteutus.Huffman.Purkaminen.TiedostonPurkaja;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws IOException, Exception {
        if (true) {
            hajautusAjat();
            return;
        }
        
        
        System.out.println("Anna k�sitelt�v�n tiedoston nimi joka sij. kansiossa /Tiralabra_maven");
        System.out.print("Nimi: ");
        
        Scanner lukija = new Scanner(System.in);
        String polku = lukija.nextLine();
        
        System.out.println("Haluatko pakata vai purkaa tiedoston?\npakkaa(1)\npura(2)");
        
        while (true) {
            try {
                int vastaus = Integer.parseInt(lukija.nextLine());
                if (vastaus == 1 || vastaus == 2) {
                    
                    long ennen = System.currentTimeMillis();
                    
                    if (vastaus == 1) {
                        pakkaa(polku);
                    }
                    else {
                        pura(polku);
                    }
                    
                    long jalkeen = System.currentTimeMillis();
                    System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
                    
                    break;
                } 
            }
            catch (NumberFormatException e) {}
            
            System.out.println("Sinun tulee vastata joko '1' (pakkaa) tai '2' (pura).");
        }
    }
    
    public static void hajautusAjat() throws Exception {
        HashMap<String, String> hashmap = new HashMap<>();
        HajautusTaulu hajautus = new HajautusTaulu();
        
        String[] map = new String[100000];
        String[] taulu = new String[100000];
        
        long ennen = System.currentTimeMillis();
        
        for (int i = 0; i < 100000; i++) {
            String bittijono = arvoBittijono();
            map[i] = bittijono;
            hashmap.put(bittijono, "");
        }
        
        long jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        ennen = System.currentTimeMillis();
        
        for (int i = 0; i < 100000; i++) {
            String bittijono = arvoBittijono();
            taulu[i] = bittijono;
            hajautus.lisaa(bittijono, "");
        }
        
        jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        
        
        
        ennen = System.currentTimeMillis();
        
        for (int i = 0; i < 100000; i++) {
            hashmap.remove(map[i]);
        }
        
        jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        ennen = System.currentTimeMillis();
        
        for (int i = 0; i < 100000; i++) {
            try {
                hajautus.poista(taulu[i]);
            }
            catch (Exception e) {}
        }
        
        jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        
        

        
        
        
        
        
    }
    
    
    private static String arvoBittijono() {
        String sana = "";
        Random random = new Random();
        
        for (int i = 0; i < 8; i++) {
            if (random.nextInt(3) == 0) {
                sana += "0";
            }
            else {
                sana += "1";
            }
        }
        return sana;
    }
    
    
    private static void pakkaa(String polku) throws IOException, Exception {
        
        
        new PakkaamisenOhjaaja().suoritaPakkaaminen(polku);
    }
    
    private static void pura(String polku) throws IOException, Exception {
        new TiedostonPurkaja().pura(polku);
    }   
}



// 00010111001100
// b c  g f a  b

// Huffman puun tekstiesitys suoraan "BittiEsityksistä".

//     a = 011
//     b = 00
//     c = 010
//     f = 10
//     g = 11

// Jonoa ei tarvitse toteuttaa laisinkaan. StringBuilderia saa käyttää.
// Purkaminen tapahtuu siten että luetaan Huffman puu (= Bittiesitykset) läpi, viedään ne hajautustauluun (bittijono --> char) ja
// tämän jälkeen puretaan tiedosto lukemalla bitti bitiltä. Jos ei löydy vastinetta haj. taulusta, luetaan lisää.

// Jos Huffman puun tallettaa tiedoston alkuun loppuun, ei tarvitse muodostaa pointteria laisinkaan.
// Puu kannattaa tallettaa siten että jokaisen merkin perään lisätään tavuja 00 ja 01 (vastaamaan 0 ja 1 bittejä siitä, millainen esitys merkillä on). Ei vie liikaa tilaa :)
// Puu päättyy siten että sen lopussa on esim. FF FF koska jokaista merkkiä vastaava seur. merkki pitäisi olla 00 tai 01.