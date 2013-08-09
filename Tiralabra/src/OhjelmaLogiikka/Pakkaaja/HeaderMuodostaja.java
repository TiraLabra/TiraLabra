package OhjelmaLogiikka.Pakkaaja;

import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.Pari;

public class HeaderMuodostaja {

    private final Integer OFFSET = 127;

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
    public OmaList<Byte> muodostaHeader(OmaMap<OmaList<Byte>, String> koodit, Integer viimeisessaTavussaMerkitseviaBitteja) {

        OmaList<Byte> header = new OmaArrayList<Byte>();
        alustaHeader(viimeisessaTavussaMerkitseviaBitteja, header);

        OmaList<OmaList<Byte>> blokit = koodit.avaimet();

        for (int i = 0; i < blokit.size(); ++i) {
            muodostaBlokkiAvainPari(blokit.get(i), koodit.get(blokit.get(i)), header);
        }
        tallennaHeaderinKoko(header);


        return header;
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
    private void alustaHeader(Integer viimeisessaTavussaMerkitseviaBitteja, OmaList<Byte> header) throws IllegalArgumentException {
        if (viimeisessaTavussaMerkitseviaBitteja > 8 || viimeisessaTavussaMerkitseviaBitteja < 1) {
            throw new IllegalArgumentException("Tavussa oltava vähintään yksi ja korkeintaan 8 merkitsevää bittiä - annettu arvo: " + viimeisessaTavussaMerkitseviaBitteja);
        }


        Integer temp = viimeisessaTavussaMerkitseviaBitteja - OFFSET;
        header.add(temp.byteValue());

        for (int i = 0; i < 4; ++i) {
            header.add((byte) 0);
        }
    }

    /**
     * Muodostaa yhden blokki-koodi-parin headeriin
     *
     * @param blokki Koodattava blokki
     * @param koodi blokin koodi
     * @param header header-lista johonka tallennetaan
     */
    private void muodostaBlokkiAvainPari(OmaList<Byte> blokki, String koodi, OmaList<Byte> header) {
        if (blokki.size() > 255) {
            throw new IllegalArgumentException("Annetun blokin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + blokki.size());
        }

        // tallennettavan bittiblokin pituus tavuissa
        Integer temp = blokki.size() - OFFSET;
        header.add(temp.byteValue());
        Pari<Integer, OmaList<Byte>> koodinTiedot = muunnaKoodiStringTavuiksi(koodi);

        int merkitseviaBitteja = koodinTiedot.ensimmainen;
        
        int pituus = koodinTiedot.toinen.size();
        
        // tallennetaan bittiblokin pituus
        if (pituus > 255) {
            throw new IllegalArgumentException("Koodin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + blokki.size());
        }

        temp = pituus - OFFSET;
        header.add(temp.byteValue());

        // tallennetaan viimeisen tavun merkitsevien bittien määrä
        assert (merkitseviaBitteja >= 1 && merkitseviaBitteja <= 8);

        temp = merkitseviaBitteja - OFFSET;
        header.add(temp.byteValue());

        // tallennetaan blokki
        for (int i = 0; i < blokki.size(); ++i) {
            header.add(blokki.get(i));
        }

        // tallennetaan koodi
        for (int i = 0; i < koodinTiedot.toinen.size(); ++i) {
            header.add(koodinTiedot.toinen.get(i));
        }

    }

    /**
     * Tallentaa header-tavulistaan headerin pituuden; integer tavuihin 1 - 5
     *
     * @param header headerin tavut
     */
    private void tallennaHeaderinKoko(OmaList<Byte> header) {
        int headerinKoko = header.size() - 5; // 5 ensimmäistä tavua aina olemassa, tätä tietoa ei tarvita;

        assert (headerinKoko > 0);
        // en jaksa implementoida ByteBuffer-luokkaa; teen Integer -> 4xbyte-konversion bittishifteillä
        for (int i = 0; i < 4; ++i) {
            int tavu = 0; // käytetään ensimmäiset 8 tavua vain
            for (int j = 0; j < 8; ++j) {
                int paikka = i * 8 + j;
                tavu = tavu | ((headerinKoko & (1 << paikka)) >> paikka);
            }
            
            assert (tavu <= 255 && tavu >= 0);
            header.set(i + 1, (byte) tavu);
        }
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
            muunnettuKoodi.add((byte)tavu);
        } else {
            tavunPaikka = 8; // kaikki tavut merkitseviä
        }
        koodinTiedot.toinen = muunnettuKoodi;
        koodinTiedot.ensimmainen = tavunPaikka;
        return koodinTiedot;
    }
}
