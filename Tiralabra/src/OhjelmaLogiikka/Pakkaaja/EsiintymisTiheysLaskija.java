package OhjelmaLogiikka.Pakkaaja;

import Tiedostokasittely.ITiedostoLukija;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.OmaMinimiPriorityQueue;
import Tietorakenteet.OmaQueue;
import Tietorakenteet.OmaTreeNode;
import Tietorakenteet.TiedostoBlokki;
import java.io.IOException;
import java.util.Comparator;

public class EsiintymisTiheysLaskija {

    /**
     * Laskee annetusta tiedostosta blokkien esiintymistiheydet
     *
     * @param lukija ITiedostoLukija-rajapinnan toeuttava objekti. Tästä luetaan
     * blokit
     *
     * @param blokinKoko Pakkauksen blokkikoko
     *
     * @return palauttaa Taulun joka sisältää blokit ja niiden
     * esiintymistiheydet
     * @throws IOException Jos lukeminen epäonnistuu
     */
    public OmaMap<TiedostoBlokki, Integer> laskeEsiintymisTiheydet(ITiedostoLukija lukija, int blokinKoko) throws IOException {
        lukija.avaaTiedosto();

        OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet = new OmaHashMap<TiedostoBlokki, Integer>(8192);

        byte[] luetutTavut = new byte[blokinKoko];
        int luettu;

        while ((luettu = lukija.lue(luetutTavut)) > 0) {
            kasitteleBlokki(luettu, luetutTavut, esiintymisTiheydet);
        }

        return esiintymisTiheydet;
    }

    /**
     * Käsittelee yhden blokin
     *
     * @param luettu montako tavua luettu tiedostosta
     * @param luetutTavut luetut tavut
     * @param esiintymisTiheydet Taulu blokki-esiintymistiheyksistä
     */
    private void kasitteleBlokki(int luettu, byte[] luetutTavut, OmaMap<TiedostoBlokki, Integer> esiintymisTiheydet) {
        TiedostoBlokki blokki = new TiedostoBlokki();

        blokki.byteTaulukko = new byte[luettu];

        for (int i = 0; i < luettu; ++i) {
            blokki.byteTaulukko[i] = luetutTavut[i];
        }

        Integer arvo = esiintymisTiheydet.get(blokki);
        if (arvo == null) {
            esiintymisTiheydet.put(blokki, 1);
        } else {
            esiintymisTiheydet.put(blokki, arvo + 1);
        }
    }
}
