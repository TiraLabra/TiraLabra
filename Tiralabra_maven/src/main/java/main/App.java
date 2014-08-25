package main;

import com.mycompany.tiralabra_maven.Packer;

final class App {

    /**
     * Käyttö:
     *
     * 1. Luo testi tekstitiedosto.
     *
     * 2. Kommentoi compress metodi käyttöön.
     *
     * 3. Aja ohjelma.
     *
     * 4. Uusi tiedosto, jonka nimi on sama kuin alkuperäinen + ".pkx", ilmestyy
     * kansioon.
     *
     * 5. Uudelleen nimeä alkuperäinen tiedosto joksikin muuksi.
     *
     * 6. Kommentoi compress metodi pois, poista kommentit deCompress metodista
     *
     * 7. Aja ohjelma
     *
     * 8. Teksti tiedosto on palautunut kansioon alkuperäisessä muodossa.
     *
     * Huom: kannattaa testata tekstitiedostoa, joka on riittävän suuri.
     * Kehityksessä on käytetty noin 90 kt kokoista, jolloin pakattu version on
     * noin 50 kt
     */
    public static void main(String[] args) {
        final Packer packer = new Packer(args);
        packer.run();
    }
}
