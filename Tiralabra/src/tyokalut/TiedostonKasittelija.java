package tyokalut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import osat.Laatikko;
import osat.Nelikulmio;

/**
 * Asettelutietojen tekstitiedostoon tallentamisesta, sekä sieltä avaamisesta huolehtiva
 * luokka.
 *
 * @author albis
 */
public class TiedostonKasittelija {
    /**
     * Tallennettavan AVL-puun käsittelijä-olio.
     */
    AVLkasittelija historia;
    
    public TiedostonKasittelija(AVLkasittelija historia) {
        this.historia = historia;
    }
    
    /**
     * Avaa tekstitiedoston, johon asettelutiedot on tallennettu ja kerää sen tiedot
     * muistiin AVL-puuhun.
     */
    public void avaa() {
        try {
            Scanner lukija = new Scanner(new File("historia.txt"));
            
            if (lukija.hasNextLine()) {
                lukija.nextLine();
            }
            while (lukija.hasNextLine()) {
                haeTietoPuuhun(lukija);
            }
        } catch (IOException e) {
        }
    }
    
    /**
     * Hakee tekstitiedostosta seuraavalla rivillä olevan asettelutiedon ja lisää
     * sen AVL-puuhun, josta sitä voidaan tarkastella.
     * @param lukija 
     */
    private void haeTietoPuuhun(Scanner lukija) {
        String rivi = lukija.nextLine();
        String[] osat = rivi.split(":");
                
                
        Laatikko laatikko = new Laatikko(Integer.parseInt(osat[0]), Integer.parseInt(osat[1]),
        Integer.parseInt(osat[2]), Long.parseLong(osat[3]));
                
        Nelikulmio lava = new Nelikulmio(Integer.parseInt(osat[5]), Integer.parseInt(osat[6]),
        Integer.parseInt(osat[7]));
                
        int[][] asettelu = new int[lava.getPituus()][lava.getLeveys()];
                
       String asetteluMerkkijono[] = osat[4].split(",");
       int monesko = 0;
                
       for (int i = 0; i < lava.getPituus(); i++) {
            for (int j = 0; j < lava.getLeveys(); j++) {
                asettelu[i][j] = Integer.parseInt(asetteluMerkkijono[monesko]);
                monesko++;
            }
        }
                
        historia.AVLlisays(laatikko, asettelu, lava);
    }
    
    public void tallenna() {
        try {
            FileWriter kirjoittaja = new FileWriter("historia.txt");
            
            tallennaSolmu(historia.getJuuri(), kirjoittaja);
            
            kirjoittaja.close();
        } catch (IOException e) {
            System.out.println("Virhe kirjoittajassa!");
        }
    }
    
    /**
     * Tallentaa annetun solmun asettelutiedot tekstitiedostoon ja tekee saman koko puulle,
     * käyden rekursiivisesti jokaisen solmun läpi.
     * 
     * @param solmu Tallennettavat tiedot sisältävä solmu.
     * @param kirjoittaja FileWriter-olio, jolla tiedostoon tallennetaan.
     * @throws IOException 
     */
    private void tallennaSolmu(AVLsolmu solmu, FileWriter kirjoittaja) throws IOException {
        if (solmu == null) {
            return;
        }
        
        tallennaSolmu(solmu.getVasen(), kirjoittaja);
        
        Laatikko laatikko = solmu.getLaatikko();
        Nelikulmio lava = solmu.getLava();
        
        kirjoittaja.write("\n" + laatikko.getLeveys() + ":" + laatikko.getPituus() + ":" + laatikko.getKorkeus() + ":" +
                laatikko.getEAN() + ":" + getAsettelu(solmu) + ":" + lava.getLeveys() + ":" +
                lava.getPituus() + ":" + lava.getKorkeus());
        
        tallennaSolmu(solmu.getOikea(), kirjoittaja);
    }
    
    /**
     * Asettelutavan merkkijonomuotoon muuntamisesta huolehtiva metodi.
     * 
     * @param solmu Muuntamisen kohteena oleva solmu.
     * @return Palauttaa asettelun merkkijonoesityksen.
     */
    private String getAsettelu(AVLsolmu solmu) {
        String asettelu = "";
        
        for (int i = 0; i < solmu.getAsettelu().length; i++) {
            for (int j = 0; j < solmu.getAsettelu()[0].length; j++) {
                asettelu += solmu.getAsettelu()[i][j] + ",";
            }
        }
        
        return asettelu;
    }
}
