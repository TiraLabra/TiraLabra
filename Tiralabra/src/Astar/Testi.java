package Astar;

/**
 * Luokka sisaltaa pääohjelman, joka testaa reitinhakua annetussa
 * 'labyrintissa'. Tulosteena tehtävään kulutettu aika sekä annettu labyrintti
 * jossa löydetty polku merkittynä 'o' symbolilla.
 *
 * @author Ilkka Maristo
 */
public class Testi {

    public static void main(String args[]) {

        //Labyrintin tiedot
        int korkeus = 3;
        int leveys = 3;

        int lahtoX = 0;
        int lahtoY = 0;
        int maaliX = 2;
        int maaliY = 2;


        long aikaAlku;
        long aikaLoppu;

        /*
         * Itse labyrintti jossa:
         * '.' = Kulkukelpoinen maasto
         * '#' = Seinä, eli ei kulkukelpoinen
         * 'A' = Aloituskohta
         * 'M' = Maali
         */
        char[][] labyrintti = {{'A', '.', '.'}, {'.', '.', '.'}, {'.', '.', 'M'}};

        // Merkataan algoritminsuorituksen aloitusaika
        aikaAlku = System.currentTimeMillis();

        // Luodaan reitinhaku olio, jolla labyrinttia aletaan tulkitsemaan
        AtahtiReitinhaku hakuri = new AtahtiReitinhaku(labyrintti);
        hakuri.setLahto(lahtoY, lahtoX);
        hakuri.setMaali(maaliY, maaliX);

        // aTahtiAlgoritmi-metodi palauttaa labyrintin ratkaistuna, mikali reitti loytyy
        char[][] merkittyLabyrintti = hakuri.aTahtiAlgoritmi(korkeus, leveys);

        // Merkataan lopetusaika
        aikaLoppu = System.currentTimeMillis();

        //Tulostetaan labyrintti
        System.out.println("Hommat hoitui ajassa: " + (aikaLoppu - aikaAlku) + "ms");
        System.out.println("Syottamasi labyrintin lyhin reitti:");
        for (int i = 0; i < korkeus; i++) {
            System.out.println();
            for (int j = 0; j < leveys; j++) {
                System.out.print(merkittyLabyrintti[i][j]);
            }
        }


    }
}