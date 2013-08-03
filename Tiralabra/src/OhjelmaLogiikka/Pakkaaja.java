package OhjelmaLogiikka;

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
            TiedostoLukija lukija = new TiedostoLukija(tiedosto);
            TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(ulosTiedosto);    
            
            OmaList<Byte> luetutTavut = lukija.lueTiedosto();
            OmaList<Byte> pakattu = huffmanKoodaa(luetutTavut, ulosTiedosto + ".header");            
            kirjoittaja.kirjoitaTiedosto(pakattu);

        } catch (FileNotFoundException ex) {
            System.out.println("Tiedostoa ei löytynyn: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO-virhe: " + ex.getMessage());
        }

        // lue tiedostosta tavut
        // tiedot hash mappiin
        // laske tavujen esiintymistiheys
        // esiintymistiheyden pohjalta minimijono
        // muodosta puu minimijonosta
        // muodosta hajautustaulu puusta
        // korvaa tiedostoon tavut koodeilla
        // tallenna tiedostoon
    }

    private OmaList<Byte> huffmanKoodaa(OmaList<Byte> luetutTavut, String header) throws IOException {
        OmaList<OmaList<Byte>> blokit = luoBlokit(luetutTavut);

        // hash map: alkio tiedostosta - pari joka sisältää korvaavan alkion ja siinä olevien bittien määrän
        OmaMap<OmaList<Byte>, String> koodit = muodostaKoodit(blokit);

        // muodostetaan header
        OmaList<Byte> ulos;
        Pari<Integer, OmaList<Byte>> pakatut = tiivista(blokit, koodit);
        TiedostoKirjoittaja kirjoittaja = new TiedostoKirjoittaja(header);
        
        
        kirjoittaja.kirjoitaTiedosto(muodostaHeader(koodit, pakatut.ensimmainen));

        return pakatut.toinen;
    }

    private OmaList<OmaList<Byte>> luoBlokit(OmaList<Byte> luetutTavut) {
        OmaList<OmaList<Byte>> blokit = new OmaArrayList<OmaList<Byte>>();
        OmaList<Byte> blokki = new OmaArrayList<Byte>();

        for (int i = 0; i < luetutTavut.size(); ++i) {
            blokki.add(luetutTavut.get(i));

            if (blokki.size() % BLOKIN_KOKO == 0) {
                blokit.add(blokki);
                blokki = new OmaArrayList<Byte>();
            }
        }

        // jämäblokki vielä
        if (blokki.size() > 0) {
            blokit.add(blokki);

        }
        return blokit;
    }

    private OmaMap<OmaList<Byte>, String> muodostaKoodit(OmaList<OmaList<Byte>> blokit) {
        OmaMap<OmaList<Byte>, Integer> esiintymisTiheydet = laskeEsiintymisTiheys(blokit);
        OmaQueue<OmaTreeNode<Integer, OmaList<Byte>>> jono = muodostaPrioriteettiJono(esiintymisTiheydet);

        OmaTreeNode<Integer, OmaList<Byte>> puu = muodostaHuffmanPuu(jono);

        return kooditPuusta(puu);

    }

    private OmaMap<OmaList<Byte>, Integer> laskeEsiintymisTiheys(OmaList<OmaList<Byte>> blokit) {
        OmaMap<OmaList<Byte>, Integer> esiintymisTiheydet = new OmaHashMap<OmaList<Byte>, Integer>();

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

    OmaMap<OmaList<Byte>, String> kooditPuusta(OmaTreeNode<Integer, OmaList<Byte>> puu) {
        OmaMap<OmaList<Byte>, String> koodit = new OmaHashMap<OmaList<Byte>, String>();



        kayPuuLapiJaLuoKoodit(puu, koodit, "");

        return koodit;
    }

    private void kayPuuLapiJaLuoKoodit(OmaTreeNode<Integer, OmaList<Byte>> node, OmaMap<OmaList<Byte>, String> koodit, String koodi) {
        // puun pitäisi olla täydellinen
        assert (node != null);
        if (node.onLehti()) {
            koodit.put(node.haeArvo(), koodi);
            return;
        }

        kayPuuLapiJaLuoKoodit(node.vasenLapsi(), koodit, koodi + "0");
        kayPuuLapiJaLuoKoodit(node.oikeaLapsi(), koodit, koodi + "1");

    }

    private OmaList<Byte> muodostaHeader(OmaMap<OmaList<Byte>, String> koodit, Integer viimeisessaTavussaMerkitsevia) {
        String newLine = "\r\n";
        OmaList<OmaList<Byte>> avaimet = koodit.avaimet();

        //String headerinKoko = Integer.toString(avaimet.size()) + newLine;
        String viimeisessaTavussaMerkitseviaString = viimeisessaTavussaMerkitsevia.toString() + newLine;
        OmaList<String> avainArvot = new OmaArrayList<String>();

        for (int i = 0; i < avaimet.size(); ++i) {

            StringBuilder builder = new StringBuilder();
            OmaList<Byte> avain = avaimet.get(i);

            byte[] tavut = new byte[avain.size()];
            for (int j = 0; j < avain.size(); ++j) {
                tavut[j] = avain.get(j);
            }
            builder.append(new String(tavut));


            builder.append(" ");
            builder.append(koodit.get(avain));
            builder.append(newLine);
            avainArvot.add(builder.toString());
        }

        OmaList<Byte> tavuina = new OmaArrayList<Byte>();
        
       /* byte[] tavut = headerinKoko.getBytes();
        for (int i = 0; i < tavut.length; ++i) {
            tavuina.add(tavut[i]);
        }*/
        
        byte [] tavut = viimeisessaTavussaMerkitseviaString.getBytes();
        for (int i = 0; i < tavut.length; ++i) {
            tavuina.add(tavut[i]);
        }
        
        for (int i = 0; i < avainArvot.size(); ++i) {
            tavut = avainArvot.get(i).getBytes();
            for (int j = 0; j < tavut.length; ++j) {
                tavuina.add(tavut[j]);
            }
        }
        System.out.println("Header tavuissa: " + tavuina.size());
        return tavuina;
    }

    private Pari<Integer, OmaList<Byte>> tiivista(OmaList<OmaList<Byte>> blokit, OmaMap<OmaList<Byte>, String> koodit) {
        int bittejaKaytetty = 0;
        byte tavu = 0;

        OmaList<Byte> tiiviste = new OmaArrayList<Byte>();
        int saasto = 0;
        for (int i = 0; i < blokit.size(); ++i) {
            String pakkausKoodi = koodit.get(blokit.get(i));
            saasto += 8 - pakkausKoodi.length();
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
        }
        
        System.out.println((blokit.size() - tiiviste.size())/1024);
        System.out.println("Säästö kilotavuissa: " + saasto/(8*1024));

        Pari paluuArvo = new Pari<Integer, OmaList<Byte>>();
        paluuArvo.ensimmainen = bittejaKaytetty;
        paluuArvo.toinen = tiiviste;
        return paluuArvo;
    }
}
