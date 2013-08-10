package OhjelmaLogiikka.Pakkaaja;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tietorakenteet.ByteWrapper;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;
import java.io.IOException;

public class HeaderMuodostaja {

    private final int OFFSET = 127;

    /**
     * Muodostaa headerin. Formaatin yksityiskohdat löytyy
     * tiedostoformaatti.txt-tiedostosta. Huom: kaikki yhden bitin pituuskentät
     * tallennetaan offsetin kanssa koska java käyttää vain etumerkillisiä
     * lukuja ja 255 -> 128 on liian suuri tiputus (eli -127 = 0, 128 = 255)
     *
     * @param koodit Blokki-koodiparit
     * @param viimeisessaTavussaMerkitseviaBitteja montako bittiä viimeisessä
     * tavussa on merkitseviä dataosiossa merkitsee mitään
     * @return tavut jotka kirjoitetaan tiedostoon.
     */
    public long muodostaHeader(String header, OmaMap<ByteWrapper, String> koodit, int viimeisessaTavussaMerkitseviaBitteja) {
        try {
            TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(header);
            kirjoittaja.avaaTiedosto();
           
            alustaHeader(viimeisessaTavussaMerkitseviaBitteja, kirjoittaja);

            OmaList<ByteWrapper> blokit = koodit.avaimet();

            for (int i = 0; i < blokit.size(); ++i) {
                muodostaBlokkiAvainPari(blokit.get(i), koodit.get(blokit.get(i)), kirjoittaja);
            }
            
            kirjoittaja.suljeTiedosto();
            return kirjoittaja.koko();
        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen headerin luomisessa " + ex.getMessage());
        }
        return 0;
    }

    /**
     * Tallentaa taulukkoon merkitsevien bittien määrän ja varaa 4 tavua tilaa
     * taulukkoon headerin kokoa varten.
     *
     * @param viimeisessaTavussaMerkitseviaBitteja montako bittiä viimeisessä
     * tavussa on merkitseviä
     * @param header header-tavut
     * @throws IllegalArgumentException jos viimeisessaTavussaMerkitseviaBitteja
     * on alle 1 tai suurempi kuin 8
     */
    private void alustaHeader(Integer viimeisessaTavussaMerkitseviaBitteja, TiedostoKirjoittaja kirjoittaja) throws IllegalArgumentException, IOException {
        if (viimeisessaTavussaMerkitseviaBitteja > 8 || viimeisessaTavussaMerkitseviaBitteja < 1) {
            throw new IllegalArgumentException("Tavussa oltava vähintään yksi ja korkeintaan 8 merkitsevää bittiä - annettu arvo: " + viimeisessaTavussaMerkitseviaBitteja);
        }

        byte [] puskuri = new byte[1];
        puskuri[0] = (byte) (viimeisessaTavussaMerkitseviaBitteja - OFFSET);
        kirjoittaja.kirjoita(puskuri);
    }

    /**
     * Muodostaa yhden blokki-koodi-parin headeriin
     *
     * @param blokki Koodattava blokki
     * @param koodi blokin koodi
     * @param header header-lista johonka tallennetaan
     */
    private void muodostaBlokkiAvainPari(ByteWrapper blokki, String koodi, TiedostoKirjoittaja kirjoittaja) throws IOException {
        if (blokki.size() > 255) {
            throw new IllegalArgumentException("Annetun blokin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + blokki.size());
        }
        byte [] puskuri = new byte[1];
        
        // tallennettavan bittiblokin pituus tavuissa
        puskuri[0] = (byte) (blokki.size() - OFFSET);
        kirjoittaja.kirjoita(puskuri);
        
        Pari<Integer, OmaList<Byte>> koodinTiedot = muunnaKoodiStringTavuiksi(koodi);

        int merkitseviaBitteja = koodinTiedot.ensimmainen;

        int pituus = koodinTiedot.toinen.size();

        
        if (pituus > 255) {
            throw new IllegalArgumentException("Koodin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + blokki.size());
        }

        puskuri[0] = (byte) (pituus - OFFSET);
        kirjoittaja.kirjoita(puskuri);

        // tallennetaan viimeisen tavun merkitsevien bittien määrä
        assert (merkitseviaBitteja >= 1 && merkitseviaBitteja <= 8);

        puskuri[0] = (byte) (merkitseviaBitteja - OFFSET);
        kirjoittaja.kirjoita(puskuri);

        // tallennetaan blokki
       
        kirjoittaja.kirjoita(blokki.byteTaulukko);

        // tallennetaan koodi
        puskuri = new byte[koodinTiedot.toinen.size()];
        for (int i = 0; i < koodinTiedot.toinen.size(); ++i) {
             puskuri[i] = koodinTiedot.toinen.get(i);
        }
        
        kirjoittaja.kirjoita(puskuri);
    }
    
    /**
     *
     * @param koodi Muunnettava koodi
     * @return <Integer, OmaList<Byte>>-pari. Integer kertoo viimeisessä tavussa
     * olevien merkitsevien bittien määrän, OmaList<Byte> sisältää tavut johon
     * on koodattu koodi bitteinä (jokainen 8 merkin String-blokki koodataan
     * yhteen byteen)
     */
    private Pari<Integer, OmaList<Byte>> muunnaKoodiStringTavuiksi(String koodi) {
        // muunnetaan tallennettava koodi String -> byteihin pakatut bitit
        OmaList<Byte> muunnettuKoodi = new OmaArrayList<Byte>();
        Pari<Integer, OmaList<Byte>> koodinTiedot = new Pari<Integer, OmaList<Byte>>();


        int tavunPaikka = 0;
        int tavu = 0;
        for (int i = 0; i < koodi.length(); ++i) {

            int arvo = 0;
            if (koodi.charAt(i) == '1') {
                arvo = 1;
            }

            tavu = tavu | (arvo << tavunPaikka);

            ++tavunPaikka;
            if (tavunPaikka == 8) {
                tavunPaikka = 0;

                // arvon on mahduttava yhteen tavuun - muutoin jotain on rikki shifteissä
                assert (tavu >= 0 && tavu <= 255);
                // koska tallennetaan bittikuvioita, ei ole väliä vaikka 11111111 ei mahdukkaan tavuun 
                muunnettuKoodi.add((byte) tavu);
                tavu = 0;
            }
        }

        if (tavunPaikka > 0) {
            muunnettuKoodi.add((byte) tavu);
        } else {
            tavunPaikka = 8; // kaikki tavut merkitseviä
        }
        koodinTiedot.toinen = muunnettuKoodi;
        koodinTiedot.ensimmainen = tavunPaikka;
        return koodinTiedot;
    }
}
