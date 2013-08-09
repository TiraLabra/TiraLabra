package OhjelmaLogiikka.Pakkaaja;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.ByteWrapper;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.OmaMinimiPriorityQueue;
import Tietorakenteet.OmaQueue;
import Tietorakenteet.OmaTreeNode;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

public class Pakkaaja {

    private final int BLOKIN_KOKO;

    public Pakkaaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    public void pakkaa(String tiedosto, String ulosTiedosto) {
        try {

            OmaList<ByteWrapper> blokit = luoBlokit(tiedosto);


            OmaMap<ByteWrapper, String> koodit = muodostaKoodit(blokit);


            int viimeisessaTavussaMerkitseviaBitteja = tiivista(ulosTiedosto, blokit, koodit);
            blokit.clear();

            String header = ulosTiedosto + ".header";
            (new HeaderMuodostaja()).muodostaHeader(header, koodit, viimeisessaTavussaMerkitseviaBitteja);



        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO-virhe: " + ex.getMessage());
        }
    }

    private OmaList<ByteWrapper> luoBlokit(String tiedosto) throws FileNotFoundException, IOException {

        TiedostoLukija lukija = new TiedostoLukija(tiedosto);
        lukija.avaaTiedosto();

        OmaList<ByteWrapper> blokit = new OmaArrayList<ByteWrapper>();
        int muistikulutus = 8 + 12 + 4 + 4 + 1; // OmaList + array sisällä + koko + hashcode + onko muuttunut;


        byte[] luetutTavut = new byte[BLOKIN_KOKO];
        int luettu = 0;

        while ((luettu = lukija.lue(luetutTavut)) > 0) {
            ByteWrapper blokki = new ByteWrapper();

            blokki.byteTaulukko = new byte[luettu];

            for (int i = 0; i < luettu; ++i) {
                blokki.byteTaulukko[i] = luetutTavut[i];
            }

            // omalist + array + n byteä arrayssa
            muistikulutus += 8 + 12 + luettu;

            blokit.add(blokki);
        }

        System.out.println("Arvioitu blokkitietueen muistikulutus: " + muistikulutus / 1024 + "kt (" + muistikulutus / 1024 / 1024 + "mt)");

        lukija.suljeTiedosto();
        return blokit;
    }

    private OmaMap<ByteWrapper, String> muodostaKoodit(OmaList<ByteWrapper> blokit) {

        OmaMap<ByteWrapper, Integer> esiintymisTiheydet = laskeEsiintymisTiheys(blokit);

        OmaQueue<OmaTreeNode<Integer, ByteWrapper>> jono = muodostaPrioriteettiJono(esiintymisTiheydet);
        esiintymisTiheydet.clear(); // vapautetaan muisti

        OmaTreeNode<Integer, ByteWrapper> puu = muodostaHuffmanPuu(jono);

        return kooditPuusta(puu);

    }

    private OmaMap<ByteWrapper, Integer> laskeEsiintymisTiheys(OmaList<ByteWrapper> blokit) {

        OmaMap<ByteWrapper, Integer> esiintymisTiheydet = new OmaHashMap<ByteWrapper, Integer>(blokit.size() / 10);

        for (int i = 0; i < blokit.size(); ++i) {
            ByteWrapper avain = blokit.get(i);
            if (!esiintymisTiheydet.containsKey(avain)) {
                esiintymisTiheydet.put(avain, 1);
            } else {
                esiintymisTiheydet.put(avain, esiintymisTiheydet.get(avain) + 1);
            }
        }

        return esiintymisTiheydet;
    }

    private OmaQueue<OmaTreeNode<Integer, ByteWrapper>> muodostaPrioriteettiJono(OmaMap<ByteWrapper, Integer> esiintymisTiheydet) {
        OmaQueue<OmaTreeNode<Integer, ByteWrapper>> jono = new OmaMinimiPriorityQueue<OmaTreeNode<Integer, ByteWrapper>>(new Comparator<OmaTreeNode<Integer, ByteWrapper>>() {
            @Override
            public int compare(OmaTreeNode<Integer, ByteWrapper> o1, OmaTreeNode<Integer, ByteWrapper> o2) {
                return o1.haeAvain() - o2.haeAvain();
            }
        });

        OmaList<ByteWrapper> avaimet = esiintymisTiheydet.avaimet();

        for (int i = 0; i < avaimet.size(); ++i) {
            OmaTreeNode<Integer, ByteWrapper> node = new OmaTreeNode<Integer, ByteWrapper>(esiintymisTiheydet.get(avaimet.get(i)), avaimet.get(i));
            jono.push(node);
        }

        return jono;
    }

    private OmaTreeNode<Integer, ByteWrapper> muodostaHuffmanPuu(OmaQueue<OmaTreeNode<Integer, ByteWrapper>> jono) {
        while (jono.size() > 1) {
            OmaTreeNode<Integer, ByteWrapper> node1 = jono.pop();
            OmaTreeNode<Integer, ByteWrapper> node2 = jono.pop();
            OmaTreeNode<Integer, ByteWrapper> uusiNode = new OmaTreeNode<Integer, ByteWrapper>(node1.haeAvain() + node2.haeAvain(), null, node1, node2);
            jono.push(uusiNode);
        }

        OmaTreeNode<Integer, ByteWrapper> puu = jono.pop();

        assert (jono.isEmpty());
        return puu;
    }

    /**
     * Rakentaa blokki - koodiparit puusta rekursiivisesti
     *
     * @param puu Huffman-puun juuri
     * @return Map, avaimena blokki, arvona binäärikoodi Stringinä
     * ("0101001001")
     */
    private OmaMap<ByteWrapper, String> kooditPuusta(OmaTreeNode<Integer, ByteWrapper> puu) {
        OmaMap<ByteWrapper, String> koodit = new OmaHashMap<ByteWrapper, String>();



        kayPuuLapiJaLuoKooditRekursiivisesti(puu, koodit, "");

        return koodit;
    }

    /**
     * Rekursiometodi kooditPuusta-metodille. käy rekursiivisesti puun läpi ja
     * muodostaa blokille vastaavan koodin Aina vasemman haaran ottaessa koodiin
     * lisätään 0, oikeaan haaraan lisätään yksi. Kun törmätään lehteen,
     * tallennetaan koodi karttaan
     *
     * @param node node jota käsitellään juuri nyt
     * @param koodit kartta johon huffman-koodi tallennetaan. Avaimena blokki,
     * arvona koodi
     * @param koodi Tähänasti kasattu binäärikoodi
     */
    private void kayPuuLapiJaLuoKooditRekursiivisesti(OmaTreeNode<Integer, ByteWrapper> node, OmaMap<ByteWrapper, String> koodit, String koodi) {
        // puun pitäisi olla täydellinen
        assert (node != null);

        if (node.onLehti()) {
            koodit.put(node.haeArvo(), koodi);
            return;
        }

        kayPuuLapiJaLuoKooditRekursiivisesti(node.vasenLapsi(), koodit, koodi + "0");
        kayPuuLapiJaLuoKooditRekursiivisesti(node.oikeaLapsi(), koodit, koodi + "1");

    }

    /**
     * Tiivistää tiedoston; korvaa annetut blokit annetuilla koodeilla
     *
     * @param ulosTiedosto tiedoston nimi johonka kirjoitetaan tiedot
     * @param blokit Tiedostosta luetut n tavua pitkät blokit
     * @param koodit map joka sisältää blokki - koodiparit
     * @return Viimeisen tavun merkitsevien bittien määrä
     */
    private int tiivista(String ulosTiedosto, OmaList<ByteWrapper> blokit, OmaMap<ByteWrapper, String> koodit) throws FileNotFoundException, IOException {
        int bittejaKaytetty = 0;
        byte[] puskuri = new byte[1];

        TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(ulosTiedosto);
        kirjoittaja.avaaTiedosto();

        for (int i = 0; i < blokit.size(); ++i) {
            String pakkausKoodi = koodit.get(blokit.get(i));
            assert (pakkausKoodi != null);

            for (int j = 0; j < pakkausKoodi.length(); ++j) {
                byte arvo = 1;
                if (pakkausKoodi.charAt(j) == '0') {
                    arvo = 0;
                }

                puskuri[0] = (byte) (puskuri[0] | (arvo << bittejaKaytetty));

                ++bittejaKaytetty;
                if (bittejaKaytetty == 8) {

                    kirjoittaja.kirjoita(puskuri);
                    puskuri[0] = 0;
                    bittejaKaytetty = 0;
                }
            }
        }

        if (bittejaKaytetty != 0) {
            kirjoittaja.kirjoita(puskuri);
        } else {
            bittejaKaytetty = 8; // viimeisessä tavussa on kaikki tavut merkitseviä 
        }

        kirjoittaja.suljeTiedosto();
        return bittejaKaytetty;

    }
}
