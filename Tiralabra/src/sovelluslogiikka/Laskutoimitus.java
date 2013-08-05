package sovelluslogiikka;

import domain.Pino;

/**
 *
 * @author jukkapit
 */
public class Laskutoimitus {
    private String kayttajanSyote;
    private Pino pino;

    public Laskutoimitus(String syote) {
        kayttajanSyote = syote.trim();
    }
    /**
     * 
     */
    public void kaynnistaLaskin(){
        System.out.println("-----LASKIN-----");
        while(true){
            System.out.println("Syötä laskutoimitus (sallitut merkit ovat: 0-9, (, ), +, -, * ja /):");            
        }
    }
    /**
     * 
     * @param syote 
     */
    public void syotteenValidointi(String syote){
        
    }
    /**
     * 
     * @param syote
     * @return 
     */
    public int tuotaRatkaisu(String syote){
        
        return 0;
    }
    
}
