package Main;

import OhjelmaLogiikka.Pakkaaja;
import OhjelmaLogiikka.Purkaaja;

public class Main {

    // testailua
    public static void main(String[] args) {
        int blokinKoko = 1;
        Pakkaaja pakkaaja = new Pakkaaja(blokinKoko);
        Purkaaja purkaaja = new Purkaaja(blokinKoko); 
        pakkaaja.pakkaa("Testitiedostot/suuri.xml", "Testitiedostot/ulos.txt");
        purkaaja.pura("Testitiedostot/ulos.txt", "Testitiedostot/purettu.txt");
        
    }
}
