
import Huffman.HuffmanOhjaus;
import Huffman.HuffmanPurkaja;
import LZW.LZWPakkaaja;
import LZW.LZWPurkaja;
import Tietorakenteet.HajautusTaulu;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Taulukoista, jotka sis√§lt√§v√§t suorituskykytestej√§,
// saa kivan n√§k√∂iset graafiset versiot Google Docsin "Spreadsheeti√§" k√§ytt√§en.

public class Main {
    
/**
 * Metodi jolla varsinainen ohjelma k‰ynnistet‰‰n. T‰m‰ koodi ei ole refaktoroitu
 * laisinkaan, sill‰ en ajatellut sill‰ olevan juurikaan merkityst‰, sill‰ ohjelman
 * toiminnallisuushan oli se, mik‰ merkitsee.
 */
    
    public static void main(String[] args) throws IOException {
        Scanner lukija = new Scanner(System.in);
        boolean huffman = true;
        System.out.println("Valitse k‰ytett‰v‰ pakkausalgoritmi: 'h' (Huffman), 'l' (LZW).");
        
        loop:
        while (true) {
            String vastaus = lukija.nextLine().toLowerCase();
            
            switch (vastaus) {
                case "h":
                    break loop;
                case "l":
                    huffman = false;
                    break loop;
            }
            System.out.println("Valitse joko 'h' (Huffman) tai 'l' (LZW).");
        }
        
        System.out.println("Anna k‰sitelt‰v‰n tiedoston nimi joka sijaitsee samassa kansiossa kuin ajettava ohjelma.");
        System.out.print("Nimi: ");
        
        String polku = lukija.nextLine();
        
        System.out.println("Haluatko pakata vai purkaa tiedoston?\npakkaa(1)\npura(2)");
        
        while (true) {
            try {
                int vastaus = Integer.parseInt(lukija.nextLine());
                
                if (vastaus == 1) {
                    pakkaa(huffman, polku);
                    break;
                }
                else if (vastaus == 2) {
                    pura(huffman, polku);
                    break;
                }
            } 
            
            catch (NumberFormatException e) {}
            
            System.out.println("Sinun tulee valita joko '1' (pakkaa) tai '2' (pura).");
        }
    }
    
    private static void pakkaa(boolean huffman, String polku) throws IOException {
        if (huffman) {
            new HuffmanOhjaus().suoritaPakkaaminen(polku);
        } else {
            new LZWPakkaaja().suoritaPakkaaminen(polku);
        }
        
    }
    
    private static void pura(boolean huffman, String polku) throws IOException {
        if (huffman) {
            new HuffmanPurkaja().pura(polku);
        } else {
            new LZWPurkaja().pura(polku);
        }
    }   


    
    public static void hajautusAjat() {
        HashMap<String, String> hashmap = new HashMap<>();
        HajautusTaulu hajautus = new HajautusTaulu();
        
        int maara = 100000;
        
        String[] map = new String[maara];
        String[] taulu = new String[maara];
        
        long ennen = System.currentTimeMillis();
        
        for (int i = 0; i < maara; i++) {
            String bittijono = arvoMerkkiJono();
            map[i] = bittijono;
            hashmap.put(bittijono, "");
        }
        
        long jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        ennen = System.currentTimeMillis();
        
        for (int i = 0; i < maara; i++) {
            String bittijono = arvoMerkkiJono();
            taulu[i] = bittijono;
            hajautus.lisaa(bittijono, "");
        }
        
        jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        
        ennen = System.currentTimeMillis();
        
        for (int i = 0; i < maara; i++) {
            hashmap.containsKey(map[i]);
        }
        
        jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        ennen = System.currentTimeMillis();
        
        for (int i = 0; i < maara; i++) {
            hajautus.sisaltaaAvaimen(taulu[i]);
        }
        
        jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        
        
        ennen = System.currentTimeMillis();
        
        for (int i = 0; i < maara; i++) {
            hashmap.remove(map[i]);
        }
        
        jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
        
        
        ennen = System.currentTimeMillis();
        
        for (int i = 0; i < maara; i++) {
            hajautus.poista(taulu[i]);
        }
        
        jalkeen = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa " + (jalkeen - ennen) + "ms");
    }
    
    
    private static String arvoMerkkiJono() {
        String sana = "";
        Random random = new Random();
        
        int pituus = random.nextInt(20);
        
        for (int i = 0; i < pituus; i++) {
            sana += (char) random.nextInt(256);
        }
        return sana;
    }
    
}