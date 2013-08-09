package OhjelmaLogiikka.Pakkaaja;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaHashMap;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMap;
import Tietorakenteet.OmaMinimiPriorityQueue;
import Tietorakenteet.OmaQueue;
import Tietorakenteet.OmaTreeNode;
import Tietorakenteet.Pari;
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
            
            OmaList<OmaList<Byte>> blokit = luoBlokit(tiedosto);


            OmaMap<OmaList<Byte>, String> koodit = muodostaKoodit(blokit);


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

    private OmaList<OmaList<Byte>> luoBlokit(String tiedosto) throws FileNotFoundException, IOException {

        TiedostoLukija lukija = new TiedostoLukija(tiedosto);
        lukija.avaaTiedosto();

        OmaList<OmaList<Byte>> blokit = new OmaArrayList<OmaList<Byte>>();
        long koko = 0;

        byte[] luetutTavut = new byte[BLOKIN_KOKO];
        int luettu = 0;

        while ((luettu = lukija.lue(luetutTavut)) > 0) {
            OmaList<Byte> blokki = new OmaArrayList<Byte>(BLOKIN_KOKO);
            for (int i = 0; i < luettu; ++i) {
                blokki.add(luetutTavut[i]);
            }

            blokit.add(blokki);
        }

        lukija.suljeTiedosto();
        return blokit;
    }

    private OmaMap<OmaList<Byte>, String> muodostaKoodit(OmaList<OmaList<Byte>> blokit) {
        OmaMap<OmaList<Byte>, Integer> esiintymisTiheydet = laskeEsiintymisTiheys(blokit);
     
     
        int koko = 0;
        OmaList<OmaList<Byte>> avaimet = esiintymisTiheydet.avaimet();
        for (int i = 0; i < avaimet.size(); ++i) {
            koko += avaimet.get(i).capacity();
        }

   
        OmaQueue<OmaTreeNode<Integer, OmaList<Byte>>> jono = muodostaPrioriteettiJono(esiintymisTiheydet);
        esiintymisTiheydet.clear(); // vapautetaan muisti

        OmaTreeNode<Integer, OmaList<Byte>> puu = muodostaHuffmanPuu(jono);

        return kooditPuusta(puu);

    }

    private OmaMap<OmaList<Byte>, Integer> laskeEsiintymisTiheys(OmaList<OmaList<Byte>> blokit) {

        OmaMap<OmaList<Byte>, Integer> esiintymisTiheydet = new OmaHashMap<OmaList<Byte>, Integer>(blokit.size() / 10);

        for (int i = 0; i < blokit.size(); ++i) {
            OmaList<Byte> avain = blokit.get(i);
            if (!esiintymisTiheydet.containsKey(avain)) {
                esiintymisTiheydet.put(avain, 1);
            } else {
                esiintymisTiheydet.put(avain, esiintymisTiheydet.get(avain) + 1);
            }
        }

        return esiintymisTiheydet;
    }

    private OmaQueue<OmaTreeNode<Integer, OmaList<Byte>>> muodostaPrioriteettiJono(OmaMap<OmaList<Byte>, Integer> esiintymisTiheydet) {
        OmaQueue<OmaTreeNode<Integer, OmaList<Byte>>> jono = new OmaMinimiPriorityQueue<OmaTreeNode<Integer, OmaList<Byte>>>(new Comparator<OmaTreeNode<Integer, OmaList<Byte>>>() {
            @Override
            public int compare(OmaTreeNode<Integer, OmaList<Byte>> o1, OmaTreeNode<Integer, OmaList<Byte>> o2) {
                return o1.haeAvain() - o2.haeAvain();
            }
        });

        OmaList<OmaList<Byte>> avaimet = esiintymisTiheydet.avaimet();

        for (int i = 0; i < avaimet.size(); ++i) {
            OmaTreeNode<Integer, OmaList<Byte>> node = new OmaTreeNode<Integer, OmaList<Byte>>(esiintymisTiheydet.get(avaimet.get(i)), avaimet.get(i));
            jono.push(node);
        }

        return jono;
    }

    private OmaTreeNode<Integer, OmaList<Byte>> muodostaHuffmanPuu(OmaQueue<OmaTreeNode<Integer, OmaList<Byte>>> jono) {
        while (jono.size() > 1) {
            OmaTreeNode<Integer, OmaList<Byte>> node1 = jono.pop();
            OmaTreeNode<Integer, OmaList<Byte>> node2 = jono.pop();
            OmaTreeNode<Integer, OmaList<Byte>> uusiNode = new OmaTreeNode<Integer, OmaList<Byte>>(node1.haeAvain() + node2.haeAvain(), null, node1, node2);
            jono.push(uusiNode);
        }

        OmaTreeNode<Integer, OmaList<Byte>> puu = jono.pop();

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
    private OmaMap<OmaList<Byte>, String> kooditPuusta(OmaTreeNode<Integer, OmaList<Byte>> puu) {
        OmaMap<OmaList<Byte>, String> koodit = new OmaHashMap<OmaList<Byte>, String>();

        // kayPuuLapiJaLuoKooditIteratiivisesti(puu, koodit);

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
    private void kayPuuLapiJaLuoKooditRekursiivisesti(OmaTreeNode<Integer, OmaList<Byte>> node, OmaMap<OmaList<Byte>, String> koodit, String koodi) {
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
    private int tiivista(String ulosTiedosto, OmaList<OmaList<Byte>> blokit, OmaMap<OmaList<Byte>, String> koodit) throws FileNotFoundException, IOException {
        int bittejaKaytetty = 0;
        byte [] puskuri = new byte[1];
        
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
