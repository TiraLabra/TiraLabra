package sovelluslogiikka;

import domain.Pino;
import java.util.Scanner;

/**
 * Laskin-luokka, joka vastaa ohjelman toiminnallisuuden toteutuksesta
 *
 * @author csgit
 */
public class Laskin {

    private Scanner lukija;
    private String kokonaisluvut;
    private String sallitutMerkit;

    private enum OPERANDI {

        PLUS, MIINUS, KERTO, JAKO, ALKUSULKU
    };

    /**
     * Konstruktori
     */
    public Laskin() {
        lukija = new Scanner(System.in);
        kokonaisluvut = "0123456789";
        sallitutMerkit = "0123456789()+-*/";
    }

    /**
     * Asettaa laskimen valmiustilaan
     */
    public void kaynnista() {
        System.out.println("Tervetuloa käyttämään kokonaislukujen laskinta!\n"
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
                System.out.println("Tarkista laskutoimitus ja syötä se korjattuna uudelleen.\n");
            }
        }
    }

    /**
     * Tarkastaa, onko annettu parametri kelvollinen laskutoimitukseksi, ja
     * palauttaa vastauksen metodin arvona
     *
     * @param syote Merkkijono
     *
     * @return Totuusarvo (tosi tai epätosi)
     */
    public boolean tarkastaSyote(String syote) {
        for (int i = 0; i < syote.length(); i++) {
            if (sallitutMerkit.indexOf(syote.charAt(i)) == -1) {
                System.out.print("Laskutoimitus sisältää ei-sallittuja merkkejä tai välilyöntejä. ");
                return false;
            }
        }
        if (!sulutTasapainossa(syote)) {
            System.out.print("Laskutoimituksen sulut ovat epätasapainossa. ");
            return false;
        } else if (syote.isEmpty()) {
            System.out.print("Laskutoimitus ei sisällä yhtään merkkiä. ");
            return false;
        } else if (syote.contains("()") || syote.contains(")(")) {
            System.out.print("Laskutoimitus sisältää peräkkäin alku- ja loppusulun, joiden välissä ei ole yhtäkään merkkiä tai peräkkäin loppu- ja alkusulun, joiden välissä ei ole yhtäkään merkkiä. ");
            return false;
        } else if (syote.charAt(0) == '+' || syote.charAt(0) == '*' || syote.charAt(0) == '/' || syote.charAt(0) == ')') {
            System.out.print("Laskutoimitus sisältää laskuoperaatiomerkin tai loppusulun ennen ensimmäistäkään lukua. ");
            return false;
        } else if (syote.charAt(syote.length() - 1) == '+' || syote.charAt(syote.length() - 1) == '-' || syote.charAt(syote.length() - 1) == '*'
                || syote.charAt(syote.length() - 1) == '/' || syote.charAt(syote.length() - 1) == '(') {
            System.out.print("Laskutoimitus sisältää laskuoperaatiomerkin tai alkusulun viimeisen luvun jälkeen. ");
            return false;
        } else if (syote.charAt(0) == '-' || syote.contains("--") || syote.contains("+-")
                || syote.contains("*-") || syote.contains("/-") || syote.contains("(-")) {
            System.out.print("Laskutoimitus sisältää yhden tai useamman laskuoperaation negatiivisilla kokonaisluvuilla, mikä ei ole sallittua, sillä laskin pystyy käsittelemään laskutoimituksen syöttövaiheessa ainoastaan positiivisia kokonaislukuja. ");
            return false;
        } else if (syote.contains("++") || syote.contains("+*") || syote.contains("+/")
                || syote.contains("-+") || syote.contains("-*") || syote.contains("-/")
                || syote.contains("**") || syote.contains("*+") || syote.contains("*/")
                || syote.contains("//") || syote.contains("/+") || syote.contains("/*")) {
            System.out.print("Laskutoimitus sisältää  peräkkäin kahden tai useamman laskuoperaatiomerkin, joiden välissä ei ole yhtäkään lukua. ");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Tarkastaa, onko parametrina annetun laskutoimituksen sulut tasapainossa,
     * ja palauttaa vastauksen metodin arvona
     *
     * @param syote Merkkijono
     *
     * @return Totuusarvo (tosi tai epätosi)
     */
    public boolean sulutTasapainossa(String syote) {
        Pino sulut = new Pino(syote.length());
        for (int i = 0; i < syote.length(); i++) {
            char sulku1 = syote.charAt(i);
            if (sulku1 == '(') {
                sulut.push(sulku1);
            } else if (sulku1 == ')') {
                if (sulut.empty()) {
                    return false;
                }
                char sulku2 = (Character) sulut.pop();
                if (sulku1 == ')' && sulku2 != '(') {
                    return false;
                }
            }
        }
        if (!sulut.empty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Ratkaisee parametrina annetun laskutoimituksen, ja palauttaa ratkaisun
     * metodin arvona
     *
     * @param syote Merkkijono
     */
    public int ratkaiseLaskutoimitus(String syote) {
        Pino operandit = new Pino(syote.length());
        Pino luvut = new Pino(syote.length());

        for (int i = 0; i < syote.length(); i++) {
            switch (syote.charAt(i)) {
                case '(':
                    operandit.push(OPERANDI.ALKUSULKU);
                    break;
                case '*':
                    while (!operandit.empty() && (operandit.peek() == OPERANDI.KERTO || operandit.peek() == OPERANDI.JAKO)) {
                        suoritaOperaatiot(luvut, operandit);
                    }
                    operandit.push(OPERANDI.KERTO);
                    break;
                case '/':
                    while (!operandit.empty() && (operandit.peek() == OPERANDI.KERTO || operandit.peek() == OPERANDI.JAKO)) {
                        suoritaOperaatiot(luvut, operandit);
                    }
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
                    while (operandit.peek() != OPERANDI.ALKUSULKU) {
                        suoritaOperaatiot(luvut, operandit);
                    }
                    OPERANDI operandi = (OPERANDI) operandit.pop();
                    assert (operandi == OPERANDI.ALKUSULKU);
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
                    System.out.println("Laskutoimitus sisältää nollalla jakamista, mikä voi pahimmillaan johtaa universumin luhistumiseen, joten käytetään jakajana kokonaislukua yksi.");
                    luvut.push(jaettava / vakiojakaja);
                } else {
                    luvut.push(jaettava / jakaja);
                }
                break;
        }
    }
}