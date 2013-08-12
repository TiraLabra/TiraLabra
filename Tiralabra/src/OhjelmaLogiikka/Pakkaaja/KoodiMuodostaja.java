

package OhjelmaLogiikka.Pakkaaja;

import OhjelmaLogiikka.BittiUtility;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.ByteWrapper;
import Tietorakenteet.Koodi;
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


public class KoodiMuodostaja {
    private long sisaanTiedostonKoko;
    private final int BLOKIN_KOKO;
    private Kanonisoija kanonisoija;
    
    public OmaList<Pari<ByteWrapper, Koodi>> haeKooditJarjestettyna() {
        return kanonisoija.haeKooditJarjestettyna();
    }
    
    public KoodiMuodostaja(int blokinKoko) {
        BLOKIN_KOKO = blokinKoko;
    }
    
    public long haeTiedostonKoko() {
        return sisaanTiedostonKoko;
    }
    
    public OmaMap<ByteWrapper, Koodi> muodostaKoodit(String sisaan) throws FileNotFoundException, IOException {
        
        OmaMap<ByteWrapper, Integer> esiintymisTiheydet = laskeEsiintymisTiheydet(sisaan);
        OmaQueue<OmaTreeNode<Integer, ByteWrapper>> jono = muodostaPrioriteettiJono(esiintymisTiheydet);
        OmaTreeNode<Integer, ByteWrapper> puu = muodostaHuffmanPuu(jono);
        OmaList<Pari<ByteWrapper, Koodi>> koodiLista = kooditPuusta(puu);
        kanonisoija = new Kanonisoija(); 
        
        return kanonisoija.kanonisoi(koodiLista);
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


    private OmaList<Pari<ByteWrapper, Koodi>> kooditPuusta(OmaTreeNode<Integer, ByteWrapper> puu) {
        OmaList<Pari<ByteWrapper, Koodi>> koodit = new OmaArrayList<Pari<ByteWrapper, Koodi>>((int) Math.pow(2, 14));

        kayPuuLapiJaLuoKooditRekursiivisesti(puu, koodit, 0, 0);

        return koodit;
    }

    private void kayPuuLapiJaLuoKooditRekursiivisesti(OmaTreeNode<Integer, ByteWrapper> node, OmaList<Pari<ByteWrapper, Koodi>> koodit, long koodi, int paikka) {
        // puun pitäisi olla täydellinen
        assert (node != null);

        if (node.onLehti()) {
            Koodi k = new Koodi();
            k.koodi = koodi;
            k.pituus = paikka;
            Pari pari = new Pari();
            pari.ensimmainen = node.haeArvo();
            pari.toinen = k;
            
            koodit.add(pari);
            return;
        }

        kayPuuLapiJaLuoKooditRekursiivisesti(node.vasenLapsi(), koodit, koodi, paikka + 1);
        
        koodi = BittiUtility.tallennaBitinArvoPaikalle(koodi, 1, paikka);
        kayPuuLapiJaLuoKooditRekursiivisesti(node.oikeaLapsi(), koodit, koodi, paikka + 1);
    }

 

    private OmaMap<ByteWrapper, Integer> laskeEsiintymisTiheydet(String sisaan) throws IllegalArgumentException, IOException, FileNotFoundException {
        TiedostoLukija lukija = new TiedostoLukija(sisaan);
        lukija.avaaTiedosto();
        sisaanTiedostonKoko = lukija.koko();
        
        System.out.println("Pakattavan tiedoston koko " + (double) sisaanTiedostonKoko / 1024 / 1024 + " megatavua (" + (double) sisaanTiedostonKoko / 1024 + " kilotavua)");
        
        OmaMap<ByteWrapper, Integer> esiintymisTiheydet = new OmaHashMap<ByteWrapper, Integer>(8192);
        
        byte[] luetutTavut = new byte[BLOKIN_KOKO];
        int luettu;
        
        while ((luettu = lukija.lue(luetutTavut)) > 0) {
            kasitteleBlokki(luettu, luetutTavut, esiintymisTiheydet);
        }
        
        lukija.suljeTiedosto();
        return esiintymisTiheydet;
    }

    private void kasitteleBlokki(int luettu, byte[] luetutTavut, OmaMap<ByteWrapper, Integer> esiintymisTiheydet) {
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
}
