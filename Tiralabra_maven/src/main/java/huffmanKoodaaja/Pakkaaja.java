package huffmanKoodaaja;

import huffmanKoodaaja.kasittely.Algoritmi;
import huffmanKoodaaja.kasittely.Tiedosto;

public class Pakkaaja {

    public static void main(String[] args) {
        if (args.length > 0) {
            String sijainti = args[0];
            Tiedosto tiedosto = null;
            if (args.length > 1) {
                String tallennus = args[1];
                tiedosto = new Tiedosto(sijainti, tallennus);
            } else {
                tiedosto = new Tiedosto(sijainti);
            }
            Algoritmi algoritmi = new Algoritmi();
            algoritmi.setTiedosto(tiedosto);
            algoritmi.kasittele();
        }
    }
    
}
