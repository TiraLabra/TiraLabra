package Main;

import Tiedostokasittely.TiedostoKirjoittaja;
import Tiedostokasittely.TiedostoLukija;
import Tietorakenteet.OmaArrayList;
import Tietorakenteet.OmaList;
import Tietorakenteet.OmaMinimiPriorityQueue;
import Tietorakenteet.Pari;
import java.util.Comparator;
import java.util.HashMap;

public class Main {

    // testailua
    public static void main(String[] args) {
        TiedostoLukija l = new TiedostoLukija("Testitiedostot/pieni.txt");
        try {
            OmaList<Byte> luettuTieto = l.lueTiedosto();
           /* 
            HashMap<OmaList<Byte>, Integer> esiintymisTiheys = new HashMap<OmaList<Byte>, Integer>();
            
            for (int i = 0; i < luettuTieto.size(); ++i) {
                if (!esiintymisTiheys.containsKey(luettuTieto.get(i))) {
                    esiintymisTiheys.put(luettuTieto.get(i), 1);
                } else {
                    esiintymisTiheys.put(luettuTieto.get(i), esiintymisTiheys.get(luettuTieto.get(i)) + 1);
                }
            }
            
            OmaMinimiPriorityQueue<Pari<OmaList<Byte>, Integer>> pJono;
            pJono = new OmaMinimiPriorityQueue(new Comparator<Pari<OmaList<Byte>, Integer>>() {
                @Override
                public int compare(Pari<OmaList<Byte>, Integer> o1, Pari<OmaList<Byte>, Integer> o2) {
                    return o1.toinen - o2.toinen;
                }
            });            
            
            
            for (OmaList<Byte> avain : esiintymisTiheys.keySet()) {
                System.out.print("Avain: ");
                for (int j = 0; j < avain.size(); ++j) {
                    System.out.print(avain.get(j));
                }
                
                System.out.println("  esiintymistiheys: " + esiintymisTiheys.get(avain));
                
                Pari<OmaList<Byte>, Integer> pari = new Pari<OmaList<Byte>, Integer>();
                pari.ensimmainen = avain;
                pari.toinen = esiintymisTiheys.get(avain);
                
                pJono.push(pari);
            }
            
            System.out.println("\n\n\n\nJärjestettynä: ");
            
            while (!pJono.isEmpty()) {
                 Pari<OmaList<Byte>, Integer> pari = pJono.pop();
  
                 byte temp = pari.ensimmainen.get(0);
                 System.out.print((char)temp);
                 
                  System.out.println("  esiintymistiheys: " + pari.toinen);      
            }
            
            */
            
            
            
            
            TiedostoKirjoittaja k = new TiedostoKirjoittaja("Testitiedostot/testioutput.txt");
            k.kirjoitaTiedosto(luettuTieto);
            
            
        } catch (Exception e) {
            System.out.println("Jotain meni pieleen: " + e.getMessage());
        }
    }
}
