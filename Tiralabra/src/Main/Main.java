package Main;

import Nopeustestit.ArrayListVertailu;
import Nopeustestit.HashMapVertailu;
import Nopeustestit.QueueVertailu;
import OhjelmaLogiikka.Pakkaaja.Pakkaaja;
import OhjelmaLogiikka.Purkaja.Purkaja;
import Poikkeukset.PurkuException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static void nopeusVertailu() {
        ArrayListVertailu vertailu = new ArrayListVertailu();
        vertailu.vertaile();

        HashMapVertailu hVertailu = new HashMapVertailu();
        hVertailu.vertailePut();
        hVertailu.vertaileGet();

        QueueVertailu qVertailu = new QueueVertailu();
        qVertailu.vertailePush();
        qVertailu.vertailePop();

    }

    public static void main(String[] args) {
          pakkausJaPurkuKysyParametrit();
    }

    /**
     * Metodi joka kysyy blokkikoon ja tiedostonimen
     */
    private static void pakkausJaPurkuKysyParametrit() {
        int blokinKoko;
        String nimi;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Anna tiedosto joka pakataan ja puretaan: ");
        nimi = scanner.nextLine();
        System.out.println("Anna blokkikoko (1 - 255)");


        try {
            blokinKoko = Integer.parseInt(scanner.next());
        } catch (NumberFormatException ex) {
            System.out.println("Virhe muunnettaessa lukua - annoithan numeron?: " + ex.getMessage());
            return;
        }


        if (blokinKoko < 1 || blokinKoko > 255) {
            System.out.println("Virheellinen blokkikoko: " + blokinKoko);
            return;
        }

        operointi(blokinKoko, nimi);
    }

    private static void operointi(int blokinKoko, String nimi) {
        Pakkaaja pakkaaja = new Pakkaaja(blokinKoko);
        Purkaja purkaja = new Purkaja();

        try {
            System.out.println("Pakataan...");
            pakkaaja.pakkaa(nimi, "Testitiedostot/ulos.dat");

            System.out.println("Puretaan...");
            purkaja.pura("Testitiedostot/ulos.dat", "Testitiedostot/purettu.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("Annettua tiedostoa ei löytynyt - tarkista tiedostonimi");
        } catch (IOException ex) {
            System.out.println("IO-virhe: " + ex.getMessage());
        } catch (PurkuException ex) {
            System.out.println("Virhe purettaessa tiedostoa" + ex.getMessage());
        }

    }
}
