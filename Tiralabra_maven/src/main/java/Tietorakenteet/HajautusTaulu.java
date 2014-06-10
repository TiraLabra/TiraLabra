package Tietorakenteet;

/**
 * Hajautustaulu joka toimii kahdella taulukolla siten että ekan perusteella etsitään aina indeksi jonka kanssa pelataan.
 * Taulukot ovat aina samankokoisia ja projektissa riittää koko "256".
 * Mahdollista taulukon koon tuplaamista ja uudelleenhajautusta varten on kuitenkin toiminnallisuus olemassa.
 */

public class HajautusTaulu {
    private String[] avaimet;
    private String[] arvot;
    
    public HajautusTaulu() {
        this(257);
    }
    
    public HajautusTaulu(int avaimia) {
        this.avaimet = new String[avaimia];
        this.arvot = new String[avaimia];
    }
    
    public String[] getAvaimet() {
        return this.avaimet;
    }
    
    public void lisaa(String avain, String arvo) throws Exception {
        lisaa(avain, arvo, avaimet, arvot);
    }
    
    public void poista(String avain, String arvo) throws Exception {
        poista(avain, arvo, avaimet, arvot);
    }
    
    public String getArvo(String avain) throws Exception {
        try {
            int paikka = etsi(avain);
            return arvot[paikka];
        }
        catch (Exception e) {}
        return null;
    }
    

    protected int etsi(String avain) throws Exception {
        return etsi(avain, avaimet);
    }
    
    protected void setAvaimet(String[] avaimet) {
        this.avaimet = avaimet;
    }
    
    protected void setArvot(String[] arvot) {
        this.arvot = arvot;
    }
    
    protected void lisaa(String avain, String arvo, String[] avaimet, String[] arvot) throws Exception {
        int i;
        
        try {
            i = poista(avain, arvo, avaimet, arvot);
        }
        
        catch (Exception e) {
            i = hajauta(muunnaAvain(avain), avaimet);
        }
        
        avaimet[i] = avain;
        arvot[i] = arvo;        
        
    }
    
    protected int poista(String avain, String arvo, String[] avaimet, String[] arvot) throws Exception {
        int i = etsi(avain, avaimet);
        avaimet[i] = null;
        arvot[i] = null;
        
        return i;
    }
    
    protected int etsi(String avain, String[] avaimet) throws Exception {
        int muunnos = muunnaAvain(avain);
        
        int i = 0;
        while (true) {
            int paikka = paikka(muunnos, i);
            
            if (avaimet[paikka].equals(avain)) {
                return paikka;
            }
            
            i++;
            
            if (i == avaimet.length) {
                throw new Exception("Avainta ei löytynyt.");
            }
        }
    }
    
    protected int hajauta(int avain, String[] avaimet) throws Exception {
        int i = 0;
        while (true) {
            int paikka = paikka(avain, i, avaimet);
            
            if (avaimet[paikka] == null) {
                return paikka;
            }
            i++;
            
            if (i == avaimet.length) {
                uudelleenHajautaAvaimet();
                avaimet = this.avaimet;
                i = 0;
            }
        }
    }
    
    protected void uudelleenHajautaAvaimet() throws Exception {
        String[] uudetAvaimet = new String[uusiAlkuLuku()];
        String[] uudetArvot = new String[uusiAlkuLuku()];
        
        alustaUudetTaulukot(uudetAvaimet, uudetArvot);

        setAvaimet(uudetAvaimet);
        setArvot(uudetArvot);
    }
    
    protected int uusiAlkuLuku() {
        return 521;
    }
    
    protected void alustaUudetTaulukot(String[] uudetAvaimet, String[] uudetArvot) throws Exception {

        for (int i = 0; i < avaimet.length; i++) {
            lisaa(avaimet[i], arvot[i], uudetAvaimet, uudetArvot);
        }
    }
    
    protected int paikka(int avain, int i) {
        return paikka(avain, i, avaimet);
    }
    
    protected int paikka(int muunnos, int i, String[] taulukko) {
        int maara = taulukko.length;
        return ( (muunnos % maara) + i * (1 + muunnos % (maara - 2)) ) % maara;
    }
    
    protected int muunnaAvain(String avain) throws Exception {
        if (avain.isEmpty()) {
            throw new Exception("Avain on tyhjä ja sitä ei hajauteta");
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