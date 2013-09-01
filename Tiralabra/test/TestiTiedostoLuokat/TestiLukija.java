package TestiTiedostoLuokat;

import Tiedostokasittely.ITiedostoLukija;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * Testiluokka joka teeskentelee olevansa tiedostolukija. Toteuttaa ITiedostoLukija-rajapinnan.
 * 
 */
public class TestiLukija implements ITiedostoLukija {

    public byte[] tavut;
    private int paikka = 0;

    @Override
    public void avaaTiedosto() throws FileNotFoundException {
        paikka = 0;
    }

    @Override
    public long koko() {
        return tavut.length;
    }

    @Override
    public int lue(byte[] puskuri) throws IOException {
        int luettu = 0;

        if (paikka >= tavut.length) {
            return -1;
        }

        for (; paikka < tavut.length && luettu < puskuri.length; ++paikka) {
            puskuri[luettu] = tavut[paikka];
            ++luettu;
        }

        return luettu;
    }

    @Override
    public void suljeTiedosto() throws IOException {
        paikka = 0;
    }
}