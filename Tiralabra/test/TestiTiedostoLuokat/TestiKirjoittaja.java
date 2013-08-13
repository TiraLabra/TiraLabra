package TestiTiedostoLuokat;

import Tiedostokasittely.ITiedostoKirjoittaja;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Testiluokka joka teeskentelee olevansa tiedostokirjoittaja. Toteuttaa
 * ITiedostoKirjoittaja-rajapinnan. Tiedostot varastoidaan mutta niitä ei
 * kirjoiteta mihinkään.
 *
 */
public class TestiKirjoittaja implements ITiedostoKirjoittaja {

    private byte[] tavut;
    private int paikka;

    public byte[] haeTavut() {
        byte [] paluu = new byte[paikka];
        for (int i = 0; i < paikka; ++i) {
            paluu[i] = tavut[i];
        }
        return paluu;
    }

    @Override
    public void avaaTiedosto() throws FileNotFoundException {
        paikka = 0;
        tavut = new byte[32];
    }

    @Override
    public void kirjoita(byte[] kirjoitusPuskuri) throws IOException {
        
        if (kirjoitusPuskuri.length + paikka > tavut.length) {
            kasvata();
        }
        
        for (int i = 0; i < kirjoitusPuskuri.length; ++i) {
            tavut[paikka] = kirjoitusPuskuri[i];
            ++paikka;
        }
    }

    @Override
    public long koko() {
        return paikka;
    }

    @Override
    public void suljeTiedosto() throws IOException {
    }

    private void kasvata() {
        byte[] uusi = new byte[tavut.length * 2];
        for (int i = 0; i < tavut.length; ++i) {
            uusi[i] = tavut[i];
        }

        tavut = uusi;
    }
}
