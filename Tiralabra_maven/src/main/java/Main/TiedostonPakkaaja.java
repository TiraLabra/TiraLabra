package Main;

import Tietorakenteet.MinKeko;
import Tietorakenteet.Solmu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TiedostonPakkaaja {
    private HashMap<String, Integer> hash;
    private String teksti;
    
    public TiedostonPakkaaja() {
        this.hash = new HashMap<String, Integer>();
        this.teksti = "";
    }
    
    public void pakkaa(String polku) {
        
        try {
            haeTeksti(polku);
        }
        
        catch (FileNotFoundException e) {
            System.out.print("Tiedoston luku ei onnistunut. Annoit sen polun väärin.\nOhjelma suljetaan.");
            return;
        }
        
        MinKeko keko = muodostaKeko();
        yhdistaKeonSolmut(keko);
    }
    
    private void haeTeksti(String polku) throws FileNotFoundException {
        Scanner lukija = new Scanner(new File(polku));
        
        while (lukija.hasNextLine()) {
            String rivi = lukija.nextLine();
            lisaaRivi(rivi);

            if (lukija.hasNextLine()) {
                lisaaMerkki("\n");
            }
        }
    }
    
    private void lisaaRivi(String rivi) {
        Scanner rivinLukija = new Scanner(rivi);
        
        while (rivinLukija.hasNext()) {
            String sana = rivinLukija.next();
                
            for (int i = 0; i < sana.length(); i++) {
                lisaaMerkki(sana.charAt(i) + "");
            }
            
            if (rivinLukija.hasNext()) {
                lisaaMerkki(" ");
            }
        }
    }
    
    private void lisaaMerkki(String merkki) {
        int maara = 1;
        
        if (hash.containsKey(merkki)) {
            maara += hash.get(merkki);
        }
        
        hash.put(merkki, maara);
        teksti += merkki;
    }
    
    private MinKeko muodostaKeko() {
        MinKeko keko = new MinKeko(hash.keySet().size());
        for (String avain : hash.keySet()) {
            keko.lisaa(new Solmu(avain, hash.get(avain)));
        }
        
        return keko;
    }
    
    private void yhdistaKeonSolmut(MinKeko keko) {
        while (keko.getKoko() > 1) {
            Solmu oikea = keko.poistaHuippuSolmu();
            Solmu vasen = keko.poistaHuippuSolmu();
            
            Solmu yhdistetty = new Solmu(oikea.getEsiintymat() + vasen.getEsiintymat());
            
            yhdistetty.setVasen(vasen);
            yhdistetty.setOikea(oikea);
            vasen.setVanh(yhdistetty);
            oikea.setVanh(yhdistetty);
            
            keko.lisaa(yhdistetty);
        }
    }
}
