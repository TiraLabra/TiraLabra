
import Huffman.HuffmanOhjaus;
import Huffman.HuffmanPurkaja;
import LZW.LZWPakkaaja;
import LZW.LZWPurkaja;
import Tietorakenteet.HajautusTaulu;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

// Taulukoista, jotka sisältävät suorituskykytestejä,
// saa kivan näköiset graafiset versiot Google Docsin "Spreadsheetiä" käyttäen.

public class Main {
    
    public static void main(String[] args) throws IOException {

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
    
    private static void pakkaa(String polku) throws IOException {
        new LZWPakkaaja().suoritaPakkaaminen(polku);
        
    }
    
    private static void pura(String polku) throws IOException {
        new LZWPurkaja().pura(polku);
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