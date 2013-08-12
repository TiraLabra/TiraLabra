package OhjelmaLogiikka.Pakkaaja;

import Tietorakenteet.ByteWrapper;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaMap;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Pakkaaja {

    private final int BLOKIN_KOKO;
 

    public Pakkaaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    public void pakkaa(String sisaan, String ulos) {
        try {
            long aika = System.nanoTime();
            System.out.println("Aloitetaan pakkaaminen");
            
            KoodiMuodostaja koodiMuodostaja = new KoodiMuodostaja(BLOKIN_KOKO);
            OmaMap<ByteWrapper, Koodi> koodit = koodiMuodostaja.muodostaKoodit(sisaan);
            long sisaanTiedostonKoko = koodiMuodostaja.haeTiedostonKoko();
                    
            Tiivistaja tiivistaja = new Tiivistaja(BLOKIN_KOKO);
            int bittejaKaytetty = tiivistaja.tiivista(sisaan, ulos, koodit);
            koodit = null; // koodeja ei tarvita enää, gc voi kerätä
            long ulosTiedostonKoko = tiivistaja.haeTiedostonKoko();
            
            ulosTiedostonKoko += (new HeaderMuodostaja()).muodostaHeader(ulos + ".header", koodiMuodostaja.haeKooditJarjestettyna(), bittejaKaytetty);
            tulostaStatsit(aika, sisaanTiedostonKoko, ulosTiedostonKoko);

            
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO-virhe: " + ex.getMessage());
        }
    }

    private void tulostaStatsit(long aika, long sisaanTiedostonKoko, long ulosTiedostonKoko) {
        aika = (System.nanoTime() - aika);
        System.out.println("Pakkaamiseen kului " + aika / 1000000 + " ms");
        System.out.println("Käsiteltiin " + ((double) sisaanTiedostonKoko / 1024 / 1024 / ((double) aika / 1000000000)) + " megatavua/sekunti");
        System.out.println("Pakatun tiedoston koko: " + (double) ulosTiedostonKoko / 1024 / 1024 + " megatavua " + "(" + (double) ulosTiedostonKoko / 1024 + "kilotavua)");
        System.out.println("Tiivistysprosentti: " + (1.0 - (double) ulosTiedostonKoko / (double) sisaanTiedostonKoko) * 100.0 + "%");
        System.out.print("\n\n");
    }
}
