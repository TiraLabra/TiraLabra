package Main;

import OhjelmaLogiikka.Pakkaaja.Pakkaaja;
import OhjelmaLogiikka.Purkaaja.Purkaaja;

public class Main {

    public static void main(String[] args) {

        int blokinKoko = 2;

        Pakkaaja pakkaaja = new Pakkaaja(blokinKoko);
        Purkaaja purkaaja = new Purkaaja();


        System.out.println("Pakataan...");
    //    pakkaaja.pakkaa("Testitiedostot/kuva.BMP", "Testitiedostot/ulos.dat");
       // pakkaaja.pakkaa("Testitiedostot/Kalimba.mp3", "Testitiedostot/ulos.dat");
        pakkaaja.pakkaa("Testitiedostot/loremipsum.txt", "Testitiedostot/ulos.dat");
     
        System.out.println("Puretaan...");
        purkaaja.pura("Testitiedostot/ulos.dat", "Testitiedostot/purettu.txt");


    }
}
