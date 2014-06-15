/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package cli;

import java.util.Scanner;
import labyrintti.Labyrintitin;
import labyrintti.Labyrintti2D;
import labyrintti.Prim;
import labyrintti.RecursiveBacktracker;
import polunetsinta.Astar;
import polunetsinta.Heuristiikka;
import polunetsinta.NollaHeuristiikka;
import polunetsinta.TaksimiehenEtaisyys;
import verkko.Solmu;

public class CLI {

    private Scanner s;

    public CLI() {
        s = new Scanner(System.in);
    }

    public void juokse() {

        while (true) {
            Labyrintitin labyrintitin;
            Labyrintti2D labyrintti;
            Heuristiikka h;
            Astar as;
            System.out.println("Polunetsintä ja labyrintin generointi!\n(Q lopettaa)");
            String nextLine = s.nextLine();
            if (nextLine.toUpperCase().startsWith("Q")) {
                break;
            }
            while (true) {

                System.out.print("Labyrintin korkeus: ");
                String korkeusInput = s.nextLine();
                int korkeus = 0;
                if (lueKoko(korkeus, korkeusInput)) {
                    continue;
                }

                System.out.print("Labyrintin leveys: ");
                String leveysInput = s.nextLine();
                int leveys = 0;
                if (lueKoko(leveys, leveysInput)) {
                    continue;
                }
                labyrintti = new Labyrintti2D(korkeus, leveys);
                System.out.print("Läbyrintitin: [R]ecursiveBacktracker, [P]rimin algoritmi: ");

                char eka = s.nextLine().toUpperCase().toCharArray()[0];
                Solmu[][] solmut = labyrintti.getSolmut();
                switch (eka) {
                    case 'R':
                        labyrintitin = new RecursiveBacktracker(solmut);
                        break;
                    case 'P':
                        labyrintitin = new Prim(solmut);
                        break;
                    default:
                        System.out.println("P tai R !");
                        continue;
                }
                labyrintti.setLabyrintitin(labyrintitin);
                long aika = System.currentTimeMillis();

                labyrintti.labyrintitaLabyrintti();
                solmut=labyrintti.getSolmut();
                long ero = System.currentTimeMillis() - aika;
                System.out.println("Labyrintitys suoritettu " + ero + "ms");
                System.out.print("A* vai Djikstra?");

                eka = s.nextLine().toUpperCase().toCharArray()[0];
                switch (eka) {
                    case 'A':
                        h = new TaksimiehenEtaisyys();
                        break;
                    case 'D':
                        h = new NollaHeuristiikka();
                        break;
                    default:
                        System.out.println("A tai D !");
                        continue;
                }
                as = new Astar(solmut[0][0], solmut[korkeus - 1][leveys - 1], h);
                aika = System.currentTimeMillis();
                as.suorita();
                ero = System.currentTimeMillis() - aika;
                System.out.println("Polunetsintä suoritettu " + ero + "ms");
                System.out.println(labyrintti);
            }

        }
        System.out.println("Heippa!");
    }

    protected boolean lueKoko(int koko, String korkeusInput) {
        try {
            koko = Integer.parseInt(korkeusInput);
        } catch (NumberFormatException e) {
            System.out.println("Kokonaisluku, kiitos!");
            return true;
        }
        if (koko < 2) {
            System.out.println("Liian pieni!");
            return true;
        }
        if (koko > 500) {
            System.out.println("Liian iso!");
            return true;
        }
        return false;
    }
}
