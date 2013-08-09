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
            String header = ulosTiedosto + ".header";
            OmaList<OmaList<Byte>> blokit = luoBlokit(tiedosto);


            OmaMap<OmaList<Byte>, String> koodit = muodostaKoodit(blokit);

            // muodostetaan header
            Pari<Integer, OmaList<Byte>> pakatut = tiivista(blokit, koodit);
            blokit.clear(); // vapautetaan muisti

            TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(header);

            kirjoittaja.kirjoitaTiedosto((new HeaderMuodostaja()).muodostaHeader(koodit, pakatut.ensimmainen));

            kirjoittaja = new TiedostoKirjoittaja(ulosTiedosto);
            kirjoittaja.kirjoitaTiedosto(pakatut.toinen);

        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyn: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO-virhe: " + ex.getMessage());
        }
    }

    private OmaList<OmaList<Byte>> luoBlokit(String tiedosto) throws FileNotFoundException, IOException {

        TiedostoLukija lukija = new TiedostoLukija(tiedosto);
        lukija.avaaTiedosto();

        OmaList<OmaList<Byte>> blokit = new OmaArrayList<OmaList<Byte>>();
        long koko = 0;

        byte [] luetutTavut = new byte[BLOKIN_KOKO];
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
        System.out.println("Esiintymistiheydet.size(): " + esiintymisTiheydet.size());
        int koko = 0;
        OmaList<OmaList<Byte>> avaimet = esiintymisTiheydet.avaimet();
        for (int i = 0; i < avaimet.size(); ++i) {
            koko += avaimet.get(i).capacity();
        }

        System.out.println("Esiintymistiheyksien avaimien muistikulutus~ (kt) " + koko / 1024);

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
    /*
     private void kayPuuLapiJaLuoKooditIteratiivisesti(OmaTreeNode<Integer, OmaList<Byte>> puu, OmaMap<OmaList<Byte>, String> koodit) {
     Deque<Pari
     <
     OmaTreeNode<Integer, OmaList<Byte>>, 
     String
     >
     > 
     stack = new ArrayDeque<Pari<OmaTreeNode<Integer, OmaList<Byte>>, String>>();
       
     Pari<OmaTreeNode<Integer, OmaList<Byte>>, String> node = new Pari<OmaTreeNode<Integer, OmaList<Byte>>, String>();
     node.ensimmainen = puu;
     node.toinen = "";
     stack.add(node);
     int koko = 0;
        
     while (!stack.isEmpty()) {
     node = stack.pop();
     ++koko;
     if (node.ensimmainen.onLehti() == false) {
     Pari<OmaTreeNode<Integer, OmaList<Byte>>, String> temp = new Pari<OmaTreeNode<Integer, OmaList<Byte>>, String>();
     temp.ensimmainen = node.ensimmainen.oikeaLapsi();
     temp.toinen = node.toinen + "1";
     stack.add(temp);
     temp = new Pari<OmaTreeNode<Integer, OmaList<Byte>>, String>();
                
     temp.ensimmainen = node.ensimmainen.vasenLapsi();
     temp.toinen = node.toinen + "0";
                
     stack.add(temp);
     }
     else {
     koodit.put(node.ensimmainen.haeArvo(), node.toinen);
     }
     }
        
     System.out.println("Puun koko: " + koko);
        
        
     }*/

    /**
     * Rekursiometodi kooditPuusta-metodille. käy rekursiivisesti puun läpi ja
     * muodostaa blokille vastaavan koodin Aina vasemman haaran ottaessa koodiin
     * lisätään 0, oikeaan haaraan lisätään yksi. Kun törmätään lehteen,
     * tallennetaan koodi karttaan
     *
     * @param node
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
     * @param blokit Tiedostosta luetut n tavua pitkät blokit
     * @param koodit map joka sisältää blokki - koodiparit
     * @return Pari joka sisältää Integerin joka kertoo montako bittiä
     * viimeisestä tavusta on käytössä, ja tavulistan joka sisältää tiivistetyn
     * datan
     */
    private Pari<Integer, OmaList<Byte>> tiivista(OmaList<OmaList<Byte>> blokit, OmaMap<OmaList<Byte>, String> koodit) {
        int bittejaKaytetty = 0;
        byte tavu = 0;

        OmaList<Byte> tiiviste = new OmaArrayList<Byte>();

        for (int i = 0; i < blokit.size(); ++i) {
            String pakkausKoodi = koodit.get(blokit.get(i));


            assert (pakkausKoodi != null);

            for (int j = 0; j < pakkausKoodi.length(); ++j) {

                byte arvo = 1;

                if (pakkausKoodi.charAt(j) == '0') {
                    arvo = 0;
                }

                tavu = (byte) (tavu | (arvo << bittejaKaytetty));

                ++bittejaKaytetty;
                if (bittejaKaytetty == 8) {
                    tiiviste.add(tavu);
                    tavu = 0;
                    bittejaKaytetty = 0;
                }
            }
        }

        if (bittejaKaytetty != 0) {
            tiiviste.add(tavu);
        } else {
            bittejaKaytetty = 8; // viimeisessä tavussa on kaikki tavut merkitseviä
        }

        Pari paluuArvo = new Pari<Integer, OmaList<Byte>>();
        paluuArvo.ensimmainen = bittejaKaytetty;
        paluuArvo.toinen = tiiviste;
        return paluuArvo;
    }
}
