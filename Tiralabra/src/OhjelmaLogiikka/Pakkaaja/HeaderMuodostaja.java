package OhjelmaLogiikka.Pakkaaja;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tietorakenteet.ByteWrapper;
import Tietorakenteet.Koodi;
import Tietorakenteet.OmaList;
import Tietorakenteet.Pari;
import java.io.IOException;

public class HeaderMuodostaja {

    private final int OFFSET = 128;
    private int blokinPituus;

    public long muodostaHeader(String header, OmaList<Pari<ByteWrapper, Koodi>> koodit, int viimeisessaTavussaMerkitseviaBitteja, int blokinPituus) {
        try {
            TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(header);
            kirjoittaja.avaaTiedosto();
            this.blokinPituus = blokinPituus;
            alustaHeader(viimeisessaTavussaMerkitseviaBitteja, kirjoittaja);
            
            for (int i = 0; i < koodit.size(); ++i) {
                tallennaBlokinTiedot(koodit.get(i).ensimmainen, koodit.get(i).toinen, kirjoittaja);

            }

            kirjoittaja.suljeTiedosto();
            return kirjoittaja.koko();
        } catch (Exception ex) {
            System.out.println("Jotain meni pieleen headerin luomisessa " + ex.getMessage());
        }
        return 0;
    }

    private void alustaHeader(Integer viimeisessaTavussaMerkitseviaBitteja, TiedostoKirjoittaja kirjoittaja) throws IllegalArgumentException, IOException {
        if (viimeisessaTavussaMerkitseviaBitteja > 8 || viimeisessaTavussaMerkitseviaBitteja < 1) {
            throw new IllegalArgumentException("Tavussa oltava vähintään yksi ja korkeintaan 8 merkitsevää bittiä - annettu arvo: " + viimeisessaTavussaMerkitseviaBitteja);
        }

        byte[] puskuri = new byte[1];
        puskuri[0] = (byte) (viimeisessaTavussaMerkitseviaBitteja - OFFSET);
        kirjoittaja.kirjoita(puskuri);

        puskuri[0] = (byte) (blokinPituus - OFFSET);
        kirjoittaja.kirjoita(puskuri);
    }

    private void tallennaBlokinTiedot(ByteWrapper blokki, Koodi koodi, TiedostoKirjoittaja kirjoittaja) throws IllegalArgumentException, IOException {


        if (blokki.size() > 255) {
            throw new IllegalArgumentException("Annetun blokin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + blokki.size());
        }
        byte[] puskuri = new byte[1];
        // jos bittiblokin pituus eroaa blokkipituudesta, tallennetaan
        // merkitään tämä 0:llä että purkuvaiheessa tiedetään että on poikkeava blokki

        if (blokki.size() != blokinPituus) {
            puskuri[0] = (byte) (0 - OFFSET);
            kirjoittaja.kirjoita(puskuri);

            // tallennettavan bittiblokin pituus tavuissa
            puskuri[0] = (byte) (blokki.size() - OFFSET);
            kirjoittaja.kirjoita(puskuri);
        }

        if (koodi.pituus > 64) {
            throw new IllegalArgumentException("Koodin pituus on suurempi kuin header-formaattiin on varattu tilaa: Pituus: " + koodi.pituus);
        }

        puskuri[0] = (byte) (koodi.pituus - OFFSET);
       
        kirjoittaja.kirjoita(puskuri); // tallennetaan merkitsevien bittien määrä  - max 64 bittiä koska bittien on mahduttava long-muuttujaan
        kirjoittaja.kirjoita(blokki.byteTaulukko);

    }
}
