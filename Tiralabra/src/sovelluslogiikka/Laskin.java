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
    private String eiSallitutMerkitAlussa;
    private String eiSallitutMerkitLopussa;

    private enum OPERANDI {

        PLUS, MIINUS, KERTO, JAKO, SULKUAUKI
    };

    /**
     * Konstruktori
     */
    public Laskin() {
        lukija = new Scanner(System.in);
        kokonaisluvut = "0123456789";
        eiSallitutMerkitAlussa = "+-*/)";
        eiSallitutMerkitLopussa = "+-*/(";
    }

    /**
     * Asettaa laskimen valmiustilaan
     */
    public void kaynnista() {
        System.out.println("Tervetuloa käyttämään kokonaislukulaskinta!\n"
                + "Laskimen kaikki ratkaisut ovat tarkkuudeltaan kokonaislukuja. "
                + "Voit sulkea laskimen kirjoittamalla milloin tahansa \"QUIT\" syöteriville.\n");
        while (true) {
            System.out.print("Syötä laskutoimitus (sallitut merkit ovat: 0-9, (, ), +, -, * ja /): ");
            String syote = lukija.nextLine().trim();
            if (syote.equals("QUIT")) {
                break;
            } else if (tarkastaSyote(syote)) {
                System.out.println("Ratkaisu: " + ratkaiseLaskutoimitus(syote) + "\n");
            } else {
                System.out.println("Laskutoimitus on virheellinen. Tarkista syötetty laskutoimitus.\n");
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
        if (syote.isEmpty()) {
            return false;
        } else if (eiSallitutMerkitAlussa.indexOf(syote.charAt(0)) == 0 || eiSallitutMerkitLopussa.indexOf(syote.charAt(syote.length() - 1)) == syote.length() - 1) {
            return false;
        } else if (syote.contains("/0") || syote.contains(" ")) {
            return false;
        } else if (syote.contains("++") || syote.contains("--") || syote.contains("**") || syote.contains("//")) {
            return false;
        } else if (syote.contains("+-") || syote.contains("+*") || syote.contains("+/")) {
            return false;
        } else if (syote.contains("-+") || syote.contains("-*") || syote.contains("-/")) {
            return false;
        } else if (syote.contains("*+") || syote.contains("*-") || syote.contains("*/")) {
            return false;
        } else if (syote.contains("/+") || syote.contains("/-") || syote.contains("/*")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Ratkaisee parametrina annetun laskutoimituksen, ja ilmoittaa ratkaisun
     *
     * @param syote Merkkijono
     */
    public int ratkaiseLaskutoimitus(String syote) {
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
        return ratkaisu;
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
            case MIINUS:
                int vahentaja = (Integer) luvut.pop();
                int vahennettava = (Integer) luvut.pop();
                luvut.push(vahennettava - vahentaja);
                break;
            case KERTO:
                luvut.push((Integer) luvut.pop() * (Integer) luvut.pop());
                break;
            case JAKO:
                int jakaja = (Integer) luvut.pop();
                int jaettava = (Integer) luvut.pop();
                int vakiojakaja = 1;
                if (jakaja == 0) {
                    System.out.println("Virhe, laskutoimituksessa yritetään jakaa nollalla, mikä ei ole sallittua, joten käytetään jakajana vakiota yksi.");
                    luvut.push(jaettava / vakiojakaja);
                } else {
                    luvut.push(jaettava / jakaja);

                }
        }
    }
}