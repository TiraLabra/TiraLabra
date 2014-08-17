package Main;

import Tietorakenteet.Abstraktisolmu;
import Tietorakenteet.DiskreettiSolmu;
import Tietorakenteet.DiskreettiVerkko;
import Tietorakenteet.Kordinaatti;
import java.util.ArrayList;
import java.util.HashMap;



/*
 * 
 * Toimii tällä hetkellä lähinnä debuggaus metodina
 * 
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        
      NaapuriDebugagus();
      //  testaaHashMap();

    }

    public static void testaaHashMap() {
        
        HashMap<Kordinaatti, Integer> mappi = new HashMap<Kordinaatti, Integer>();
        Kordinaatti a = new Kordinaatti(1, 1);
        Kordinaatti b = new Kordinaatti(1, 2);
        Kordinaatti c = new Kordinaatti(1, 1);
        mappi.put(a, 12);
        
        System.out.println(mappi.get(c));
        mappi.put(c, 42);
        System.out.println(mappi.get(a));
    }
    
    public static void NaapuriDebugagus()
    {
     HashMap<Kordinaatti, DiskreettiSolmu> kartta = new HashMap<Kordinaatti, DiskreettiSolmu>();
        DiskreettiSolmu[] solmuvektori = new DiskreettiSolmu[9];
        solmuvektori[0] = new DiskreettiSolmu(0, 0);
        solmuvektori[1] = new DiskreettiSolmu(1, 1);
        solmuvektori[2] = new DiskreettiSolmu(-1, -1);
        solmuvektori[3] = new DiskreettiSolmu(1, -1);
        solmuvektori[4] = new DiskreettiSolmu(-1, 1);
        solmuvektori[5] = new DiskreettiSolmu(1, 0);
        solmuvektori[6] = new DiskreettiSolmu(0, 1);
        solmuvektori[7] = new DiskreettiSolmu(-1, 0);
        solmuvektori[8] = new DiskreettiSolmu(0, -1);
        for (int i = 0; i < 9; i++) {
            Kordinaatti k = solmuvektori[i].palautaKordinaatit();
            kartta.put(k, solmuvektori[i]);
        }
        DiskreettiVerkko verkkor = new DiskreettiVerkko(1);
        verkkor.asetaKartta(kartta);
        ArrayList<Abstraktisolmu> lista = verkkor.Naapurit(solmuvektori[0]);
        int i = lista.size();
    }

}
