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
            System.out.println("Polunetsinta ja labyrintin generointi!\n(Q lopettaa)");
            String nextLine = s.nextLine();
            if (nextLine.toUpperCase().startsWith("Q")) {
                break;
            }
            while (true) {

                System.out.print("Labyrintin korkeus: ");
                String korkeusInput = s.nextLine();
                int korkeus = lueKoko(korkeusInput);

                System.out.print("Labyrintin leveys: ");
                String leveysInput = s.nextLine();
                int leveys = lueKoko(leveysInput);

                labyrintti = new Labyrintti2D(korkeus, leveys);
                System.out.print("Labyrintitin: [R]ecursiveBacktracker, [P]rimin algoritmi: ");

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
                solmut = labyrintti.getSolmut();
                long ero = System.currentTimeMillis() - aika;
                System.out.println("Labyrintitys suoritettu " + ero + "ms");
                System.out.print("A* vai Djikstra? ");

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
                final Solmu maali = solmut[korkeus - 1][leveys - 1];
                as = new Astar(solmut[0][0], maali, h);
                aika = System.currentTimeMillis();
                as.suorita();
                ero = System.currentTimeMillis() - aika;
                System.out.println("Polunetsintä suoritettu " + ero + "ms");
                System.out.println(labyrintti.printtaaReittiLabyrintissa(as.getReitti(), maali));
                break;
            }

        }
        System.out.println("Heippa!");
    }

    protected int lueKoko(String korkeusInput) {
        int koko;
        while (true) {
            try {
                koko = Integer.parseInt(korkeusInput);
            } catch (NumberFormatException e) {
                System.out.println("Kokonaisluku, kiitos!");
                korkeusInput = s.nextLine();
                continue;
            }

            if (koko < 2) {
                System.out.println("Liian pieni!");
                korkeusInput = s.nextLine();
                continue;
            }
            if (koko > 500) {
                System.out.println("Liian iso!");
                korkeusInput = s.nextLine();
                continue;
            }
            break;
        }
        return koko;
    }
}
