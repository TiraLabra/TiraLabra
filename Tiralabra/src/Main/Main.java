package Main;

import OhjelmaLogiikka.Pakkaaja.Pakkaaja;
import OhjelmaLogiikka.Purkaja.Purkaja;

public class Main {

    public static void main(String[] args) {

        int blokinKoko = 1;

        Pakkaaja pakkaaja = new Pakkaaja(blokinKoko);
        Purkaja purkaaja = new Purkaja();


        System.out.println("Pakataan...");
    //    pakkaaja.pakkaa("Testitiedostot/kuva.BMP", "Testitiedostot/ulos.dat");
       // pakkaaja.pakkaa("Testitiedostot/Kalimba.mp3", "Testitiedostot/ulos.dat");
        pakkaaja.pakkaa("Testitiedostot/minimaalinen.txt", "Testitiedostot/ulos.dat");
     
        System.out.println("Puretaan...");
        purkaaja.pura("Testitiedostot/ulos.dat", "Testitiedostot/purettu.txt");


    }
}
