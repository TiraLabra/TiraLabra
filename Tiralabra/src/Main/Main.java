package Main;

import OhjelmaLogiikka.Pakkaaja.Pakkaaja;
import OhjelmaLogiikka.Purkaaja.Purkaaja;

public class Main {

    // testailua
    public static void main(String[] args) {
        
        int blokinKoko = 8;
        
        Pakkaaja pakkaaja = new Pakkaaja(blokinKoko);
        Purkaaja purkaaja = new Purkaaja(blokinKoko);
      
        
        System.out.println("Pakataan...");
        pakkaaja.pakkaa("Testitiedostot/Kalimba.mp3", "Testitiedostot/ulos.dat");
        //pakkaaja.pakkaa("Testitiedostot/valtava.txt", "Testitiedostot/ulos.dat");
        System.out.println("Puretaan...");
        purkaaja.pura("Testitiedostot/ulos.dat", "Testitiedostot/purettu.txt");

    }
}
