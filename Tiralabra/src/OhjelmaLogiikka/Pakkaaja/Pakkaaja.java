package OhjelmaLogiikka.Pakkaaja;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.ByteWrapper;
import Tietorakenteet.Koodi;
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
    private long sisaanTiedostonKoko;
    private long ulosTiedostonKoko;

    public Pakkaaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }

    public void pakkaa(String sisaan, String ulos) {
        try {
            long aika = System.nanoTime();
            System.out.println("Aloitetaan pakkaaminen");

            OmaMap<ByteWrapper, Koodi> koodit = luoKoodit(sisaan);
            int bittejaKaytetty = tiivista(sisaan, ulos, koodit);

            ulosTiedostonKoko += (new HeaderMuodostaja()).muodostaHeader(ulos + ".header", koodit, bittejaKaytetty);

            aika = (System.nanoTime() - aika);

            System.out.println("Pakkaamiseen kului " + aika / 1000000 + " ms");
            System.out.println("Käsiteltiin " + ((double) sisaanTiedostonKoko / 1024 / 1024 / ((double) aika / 1000000000)) + " megatavua/sekunti");
            System.out.println("Pakatun tiedoston koko: " + (double) ulosTiedostonKoko / 1024 / 1024 + " megatavua " + "(" + (double) ulosTiedostonKoko / 1024 + "kilotavua)");
            System.out.println("Tiivistysprosentti: " + (1.0 - (double) ulosTiedostonKoko / (double) sisaanTiedostonKoko) * 100.0 + "%");

            System.out.print("\n\n");
        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyt: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO-virhe: " + ex.getMessage());
        }
    }

    private OmaMap<ByteWrapper, Koodi> luoKoodit(String sisaan) throws FileNotFoundException, IOException {

        TiedostoLukija lukija = new TiedostoLukija(sisaan);
        sisaanTiedostonKoko = lukija.koko();
        System.out.println("Pakattavan tiedoston koko " + (double) sisaanTiedostonKoko / 1024 / 1024 + " megatavua (" + (double) sisaanTiedostonKoko / 1024 + " kilotavua)");

        lukija.avaaTiedosto();
        OmaMap<ByteWrapper, Integer> esiintymisTiheydet = new OmaHashMap<ByteWrapper, Integer>(8192);

        byte[] luetutTavut = new byte[BLOKIN_KOKO];
        int luettu = 0;

        while ((luettu = lukija.lue(luetutTavut)) > 0) {
            ByteWrapper blokki = new ByteWrapper();

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

        OmaQueue<OmaTreeNode<Integer, ByteWrapper>> jono = muodostaPrioriteettiJono(esiintymisTiheydet);
        esiintymisTiheydet.clear(); // vapautetaan muisti

        OmaTreeNode<Integer, ByteWrapper> puu = muodostaHuffmanPuu(jono);

        lukija.suljeTiedosto();
        return kooditPuusta(puu);
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
    private OmaMap<ByteWrapper, Koodi> kooditPuusta(OmaTreeNode<Integer, ByteWrapper> puu) {
        OmaMap<ByteWrapper, Koodi> koodit = new OmaHashMap<ByteWrapper, Koodi>((int) Math.pow(2, 14));

        kayPuuLapiJaLuoKooditRekursiivisesti(puu, koodit, 0, 0, "");

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
    private void kayPuuLapiJaLuoKooditRekursiivisesti(OmaTreeNode<Integer, ByteWrapper> node, OmaMap<ByteWrapper, Koodi> koodit, long koodi, int paikka, String foo) {
        // puun pitäisi olla täydellinen
        assert (node != null);

        if (node.onLehti()) {
            Koodi k = new Koodi();
            k.koodi = koodi;
            k.pituus = paikka;
            koodit.put(node.haeArvo(), k);
            return;
        }


        kayPuuLapiJaLuoKooditRekursiivisesti(node.vasenLapsi(), koodit, koodi, paikka + 1, foo + "0");

        koodi = koodi | (1 << paikka);
        kayPuuLapiJaLuoKooditRekursiivisesti(node.oikeaLapsi(), koodit, koodi, paikka + 1, foo + "1");

    }

    private int tiivista(String sisaan, String ulos, OmaMap<ByteWrapper, Koodi> koodit) throws FileNotFoundException, IOException {

        TiedostoLukija lukija = new TiedostoLukija(sisaan);
        lukija.avaaTiedosto();
        byte[] luetutTavut = new byte[BLOKIN_KOKO];
        int luettu;
        TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(ulos);
        kirjoittaja.avaaTiedosto();
        int bittejaKaytetty = 0;
        byte[] puskuri = new byte[1];

        while ((luettu = lukija.lue(luetutTavut)) > 0) {
            ByteWrapper blokki = new ByteWrapper();

            blokki.byteTaulukko = new byte[luettu];

            for (int i = 0; i < luettu; ++i) {
                blokki.byteTaulukko[i] = luetutTavut[i];
            }

            Koodi pakkausKoodi = koodit.get(blokki);
         
           
            for (int j = 0; j < pakkausKoodi.pituus; ++j) {
                int arvo = (int) (pakkausKoodi.koodi & (1 << j));
                arvo = arvo >> j;

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
        ulosTiedostonKoko = kirjoittaja.koko();
        lukija.suljeTiedosto();
        return bittejaKaytetty;
    }
}
