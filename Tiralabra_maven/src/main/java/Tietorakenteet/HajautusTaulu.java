package Tietorakenteet;

/**
 * Hajautustaulu joka toimii kahdella taulukolla siten ett‰ ekan perusteella etsit‰‰n aina indeksi jonka kanssa pelataan.
 * Taulukot ovat aina samankokoisia ja projektissa riitt‰‰ koko "256".
 * Mahdollista taulukon koon tuplaamista ja uudelleenhajautusta varten on kuitenkin toiminnallisuus olemassa.
 */

public class HajautusTaulu {
    private int[] avaimet;
    private String[] arvot;
    
    public HajautusTaulu() {
        this.avaimet = new int[256];
        this.arvot = new String[256];
        alustaAvaimet(avaimet);
    }
    
    private void alustaAvaimet(int[] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = Integer.MIN_VALUE;
        }
    }
    
    public void lisaa(String avain, String arvo) {
        lisaa(Integer.parseInt(avain), arvo, avaimet, arvot);
    }
    
    public void poista(String avain, String arvo) throws Exception {
        poista(Integer.parseInt(avain), arvo, avaimet, arvot);
    }
    
    public int hajauta(int avain) {
        return hajauta(avain, avaimet);
    }
    
    protected void lisaa(int avain, String arvo, int[] avaimet, String[] arvot) {
        int i;
        
        try {
            i = etsi(avain);
        }
        
        catch (Exception e) {
            i = hajauta(avain, avaimet);
            avaimet[i] = avain;
        }
        
        arvot[i] = arvo;
    }
    
    protected void poista(int avain, String arvo, int[] avaimet, String[] arvot) throws Exception {
        int i = etsi(avain);
        avaimet[i] = Integer.MIN_VALUE;
        arvot[i] = null;
    }

    public int etsi(int avain) throws Exception {
        int i = 0;
        while (true) {
            int paikka = paikka(avain, i);
            
            if (avaimet[paikka] == avain) {
                return paikka;
            }
            i++;
            
            if (i == avaimet.length - 1) {
                throw new Exception("Avainta ei lˆytynyt.");
            }
        }
    }
    
    protected int hajauta(int avain, int[] taulukko) {
        int i = 0;
        while (true) {
            int paikka = paikka(avain, i);
            
            if (taulukko[paikka] == Integer.MIN_VALUE) {
                return paikka;
            }
            i++;
            
            if (i == taulukko.length - 1) {
                uudelleenHajautaAvaimet();
            }
        }
    }
    
    protected void uudelleenHajautaAvaimet() {
        int[] uudetAvaimet = new int[avaimet.length * 2];
        String[] uudetArvot = new String[arvot.length * 2];
        
        alustaUudetTaulukot(uudetAvaimet, uudetArvot);

        this.avaimet = uudetAvaimet;
        this.arvot = uudetArvot;
    }
    
    protected void alustaUudetTaulukot(int[] uudetAvaimet, String[] uudetArvot) {
        alustaAvaimet(uudetAvaimet);
        
        for (int avain : avaimet) {
            if (avain != Integer.MIN_VALUE) {
                int i = hajauta(avain, uudetAvaimet);
                lisaa(avaimet[i], arvot[i], uudetAvaimet, uudetArvot);
            }
        }
    }
    
    protected int paikka(int avain, int i) {
        int maara = avaimet.length;
        return (avain % maara) + i * (1 + avain % (maara - 2));
    }
}
