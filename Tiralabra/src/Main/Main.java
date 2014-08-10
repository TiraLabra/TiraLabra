package Main;

import Tietorakenteet.Keko.Iteroitava;
import Tietorakenteet.Keko.Keko;
import Tietorakenteet.Solmu;

/*
 * 
 * Toimii tällä hetkellä lähinnä debuggaus metodina
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Keko keko = new Keko();
        Solmu[] solmut = new Solmu[200];
        keko.asetaTaulukko(solmut, 99);

        for (int j = 98; j >= 0; j--) {
            Solmu lisattava = new Solmu(j, j);
            lisattava.asetaArvo(j);
            Iteroitava iter = lisattava;
            keko.Lisaa(iter);

        }

        Solmu[] solmud = (Solmu[]) keko.palautaTaulukko();

    }

}
