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
        this(257);
    }
    
    public HajautusTaulu(int avaimia) {
        this.avaimet = new int[avaimia];
        this.arvot = new String[avaimia];
        alustaAvaimet(avaimet);
    }
    
    private void alustaAvaimet(int[] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = Integer.MIN_VALUE;
        }
    }
    
    public void lisaa(String avain, String arvo) throws Exception {
        lisaa(muunnaAvain(avain), arvo, avaimet, arvot);
    }
    
    public void poista(String avain, String arvo) throws Exception {
        poista(muunnaAvain(avain), arvo, avaimet, arvot);
    }
    
    public String getArvo(int paikka) {
        return arvot[paikka];
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
    
    public int etsi(String avain) throws Exception {
        return etsi(muunnaAvain(avain));
    }
    
    public int etsi(int avain) throws Exception {
        int i = 0;
        while (true) {
            int paikka = paikka(avain, i);
            
            if (avaimet[paikka] == avain) {
                return paikka;
            }
            i++;
            
            if (i == avaimet.length) {
                throw new Exception("Avainta ei lˆytynyt.");
            }
        }
    }
    
    protected int hajauta(int avain, int[] taulukko) {
        int i = 0;
        while (true) {
            int paikka = paikka(avain, i, taulukko);
            
            if (taulukko[paikka] == Integer.MIN_VALUE) {
                return paikka;
            }
            i++;
            
            if (i == taulukko.length) {
                uudelleenHajautaAvaimet();
                i = 0;
            }
        }
    }
    
    protected void uudelleenHajautaAvaimet() {
        int[] uudetAvaimet = new int[uusiAlkuLuku()];
        String[] uudetArvot = new String[uusiAlkuLuku()];
        
        alustaUudetTaulukot(uudetAvaimet, uudetArvot);

        this.avaimet = uudetAvaimet;
        this.arvot = uudetArvot;
    }
    
    protected int uusiAlkuLuku() {
        return 521;
    }
    
    protected void alustaUudetTaulukot(int[] uudetAvaimet, String[] uudetArvot) {
        alustaAvaimet(uudetAvaimet);
        
        for (int i = 0; i < avaimet.length; i++) {
            if (avaimet[i] != Integer.MIN_VALUE) {
                lisaa(avaimet[i], arvot[i], uudetAvaimet, uudetArvot);
            }
        }
    }
    
    protected int paikka(int avain, int i) {
        return paikka(avain, i, avaimet);
    }
    
    protected int paikka(int avain, int i, int[] taulukko) {
        int maara = taulukko.length;
        return ( (avain % maara) + i * (1 + avain % (maara - 2)) ) % maara;
    }
    
    protected int muunnaAvain(String avain) throws Exception {
        if (avain.isEmpty()) {
            throw new Exception("Avain on tyhj‰ ja sit‰ ei hajauteta");
        }
        return muunna(avain);
    }
    
    protected int muunna(String avain) {
        int muunnettu = 0;
        
        int min = Math.min(avain.length(), 3);
        for (int i = 0; i < min; i++) {
            muunnettu += avain.charAt(i);
        }
        
        return muunnettu;
    }
}
