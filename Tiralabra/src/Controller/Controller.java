package Controller;


import Model.LaskinModel;
import View.Kayttoliittyma;


/**
 * Controllerin tehtävänä on hoitaa ja validoida liikenne käyttöliittymän ja itse laskimen välillä
 * 
 */
public class Controller {
   private LaskinModel laskin;
   private Kayttoliittyma kayttoliittyma;

    public Controller() {
    laskin = new LaskinModel();
    kayttoliittyma = new Kayttoliittyma(this);
    
    }

    
   
   public Controller(LaskinModel laskin, Kayttoliittyma kayttoliittyma) {
        this.laskin = laskin;
        this.kayttoliittyma = kayttoliittyma;
       
    }
    
   /**
    * Ohjelma käynnistetään controllerin käskyllä run().
    */
   
    public void run() {
        kayttoliittyma.run();
    }

    /**
     * Käyttöliittymä antaa parametrit controllerille tämän metodin kautta kun halutaan
     * tehdä laskutoimituksia. UNDER CONSTRUCTION.
     * 
     * @param matriisiA Käyttöliittymän matriisi A
     * @param matriisiB Käyttöliittymän matriisi B
     */
    public void laske(String matriisiA, String matriisiB) {
        
        String[] matriisiArivit = matriisiA.split("\\r?\\n");
        String[] matriisiBrivit = matriisiB.split("\\r?\\n");
        


    }
   
    /**
     * Luo liukulukutaulukon pilkuilla erotetusta string jonosta, mikä käyttöliittymältä saadaan.
     * UNDER CONSTRUCTION.
     * 
     * @param matriisiPilkuillaEroteltuna Käyttöliittymältä saatu String taulukko matriisin alkioista.
     * @return liukulukutaulukko String taulukosta.
     */
    public double[][] luoMatriisi(String[] matriisiPilkuillaEroteltuna) {
        String[] ekaRivi = matriisiPilkuillaEroteltuna[0].split(",");                
        double[][] matriisi = new double[matriisiPilkuillaEroteltuna.length][ekaRivi.length];
        for (int i = 0; i < matriisi.length; i++) {
            String[] rivi = matriisiPilkuillaEroteltuna[i].split(",");
            for (int j = 0; j < matriisi[0].length; j++) {
                matriisi[i][j] = Double.parseDouble(rivi[j]);
            }
        }
        
        
        
        return matriisi;
    }
    

}
