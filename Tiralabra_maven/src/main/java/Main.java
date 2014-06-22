
import Huffman.HuffmanOhjaus;
import Huffman.HuffmanPurkaja;
import LZW.LZWPakkaaja;
import LZW.LZWPurkaja;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
/**
 * Metodi jolla varsinainen ohjelma k‰ynnistet‰‰n. T‰m‰ koodi ei ole refaktoroitu
 * laisinkaan, sill‰ en ajatellut sill‰ olevan juurikaan merkityst‰, sill‰ ohjelman
 * toiminnallisuushan oli se, mik‰ merkitsee.
 * @param args
 * @throws IOException
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
        
        System.out.println("Haluatko pakata vai purkaa tiedoston?\nValitse '1' (pakkaa) tai '2' (pura)");
        
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
}