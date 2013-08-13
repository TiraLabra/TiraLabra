package sovelluslogiikka;

import domain.Pino;
import java.util.Scanner;

/**
 * Laskin-luokka, joka vastaa ohjelman toiminnallisuuden toteutuksesta
 *
 * @author jukkapit
 */
public class Laskin {

    Scanner lukija = new Scanner(System.in);

    /**
     * Konstruktori
     */
    public Laskin() {
    }

    /**
     * Asettaa laskimen valmiustilaan
     */
    public void kaynnista() {
        System.out.println("Tervetuloa käyttämään laskinta!\n"
                + "Voit sulkea laskimen kirjoittamalla milloin tahansa \"QUIT\" syöteriville.\n");
        while (true) {
            System.out.print("Syötä laskutoimitus (sallitut merkit ovat: 0-9, (, ), +, -, * ja /): ");
            String syote = lukija.nextLine().trim();
            if (syote.equals("QUIT")) {
                break;
            } else if (tarkastaSyote(syote)) {
                ratkaiseLaskutoimitus(syote);
            }
        }
    }

    /**
     * Tarkastaa, onko annettu parametri kelvollinen laskutoimitukseksi, ja
     * ilmoittaa, jos ei ole
     *
     * @param syote Merkkijono
     *
     * @return Totuusarvo (tosi tai epätosi)
     */
    public boolean tarkastaSyote(String syote) {
        return true;
    }

    /**
     * Ratkaisee laskutoimituksen, ja ilmoittaa ratkaisun
     *
     * @param syote Merkkijono
     */
    public void ratkaiseLaskutoimitus(String syote) {
        Pino operandit = new Pino(syote.length());
        Pino luvut = new Pino(syote.length());
        
        for (int i = 0; i < syote.length(); i++) {
            switch(syote.charAt(i)){
                case '+':
            }
            
        }
        
        System.out.println("Ratkaisu: ");
    }
}