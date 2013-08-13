package sovelluslogiikka;

import domain.Pino;
import java.util.Scanner;

/**
 * Laskin-luokka, joka vastaa ohjelman toiminnallisuuden toteutuksesta
 *
 * @author jukkapit
 */
public class Laskin {

    private Scanner lukija;
    private String kokonaisluvut;

    private enum OPERANDI {

        PLUS, MIINUS, KERTO, JAKO, SULKUAUKI
    };

    /**
     * Konstruktori
     */
    public Laskin() {
        lukija = new Scanner(System.in);
        kokonaisluvut = "0123456789";
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
     * Ratkaisee parametrina annetun laskutoimituksen, ja ilmoittaa ratkaisun
     *
     * @param syote Merkkijono
     */
    public void ratkaiseLaskutoimitus(String syote) {
        Pino operandit = new Pino(syote.length());
        Pino luvut = new Pino(syote.length());

        for (int i = 0; i < syote.length(); i++) {
            switch (syote.charAt(i)) {
                case '(':
                    operandit.push(OPERANDI.SULKUAUKI);
                    break;
                case '*':
                    operandit.push(OPERANDI.KERTO);
                    break;
                case '/':
                    operandit.push(OPERANDI.JAKO);
                    break;
                case '+':
                    while (!operandit.empty() && (operandit.peek() == OPERANDI.KERTO || operandit.peek() == OPERANDI.JAKO)) {
                        suoritaOperaatiot(luvut, operandit);
                    }
                    operandit.push(OPERANDI.PLUS);
                    break;
                case '-':
                    while (!operandit.empty() && (operandit.peek() == OPERANDI.KERTO || operandit.peek() == OPERANDI.JAKO)) {
                        suoritaOperaatiot(luvut, operandit);
                    }
                    operandit.push(OPERANDI.MIINUS);
                    break;
                case ')':
                    while (operandit.peek() != OPERANDI.SULKUAUKI) {
                        suoritaOperaatiot(luvut, operandit);
                    }
                    OPERANDI operandi = (OPERANDI) operandit.pop();
                    assert (operandi == OPERANDI.SULKUAUKI);
                    break;
                default:
                    int j = i;
                    while (i < syote.length() && kokonaisluvut.indexOf(syote.charAt(i)) >= 0) {
                        i++;
                    }
                    int numero = Integer.parseInt(syote.substring(j, i));
                    luvut.push(numero);
                    i--;
                    break;
            }
        }
        while (!operandit.empty()) {
            suoritaOperaatiot(luvut, operandit);
        }
        int ratkaisu = (Integer) luvut.pop();
        assert (luvut.empty());
        System.out.println("Ratkaisu: " + ratkaisu);
    }

    /**
     * Suorittaa parametreina annettuihin pinoihin kertyneet laskuoperaatiot
     *
     * @param luvut Kokonaislukupino
     *
     * @param operandit Luetellun tyypin pino
     */
    public void suoritaOperaatiot(Pino luvut, Pino operandit) {
        switch ((OPERANDI) operandit.pop()) {
            case PLUS:
                luvut.push((Integer) luvut.pop() + (Integer) luvut.pop());
                break;
            case KERTO:
                luvut.push((Integer) luvut.pop() * (Integer) luvut.pop());
                break;
            case MIINUS:
                int luku1 = (Integer) luvut.pop();
                int luku2 = (Integer) luvut.pop();
                luvut.push(luku2 - luku1);
                break;
        }
    }
}