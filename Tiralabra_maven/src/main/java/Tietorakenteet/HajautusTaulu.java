package Tietorakenteet;

/**
 * Hajautustaulu joka toimii kahdella taulukolla siten ett‰ ekan perusteella etsit‰‰n aina indeksi jonka kanssa pelataan.
 * Taulukot ovat aina samankokoisia ja projektissa riitt‰‰ koko "256".
 * Mahdollista taulukon koon tuplaamista ja uudelleenhajautusta varten on kuitenkin toiminnallisuus olemassa.
 */

public class HajautusTaulu {
    private String[] avaimet;
    private String[] arvot;
    private int avaimia;
    
    public HajautusTaulu() {
        this(257);
    }
    
    public HajautusTaulu(int avaimia) {
        this.avaimet = new String[avaimia];
        this.arvot = new String[avaimia];
        this.avaimia = 0;
    }
    
    public String[] getAvaimet() {
        return taulukko(avaimet);
    }

    public String[] getArvot() {
        return taulukko(arvot);
    }
    
    public boolean onTyhja() {
        return this.avaimia == 0;
    }
    
    public int getKoko() {
        return this.avaimia;
    }

    protected String[] taulukko(String[] verrattava) {
        String[] taulukko = new String[this.avaimia];
        
        int j = 0;
        for (int i = 0; i < verrattava.length; i++) {
            if (verrattava[i] != null) {
                taulukko[j] = verrattava[i];
                j++;
            }
        }
        return taulukko;
        
    }
    
    public void lisaa(String avain, String arvo) throws Exception {
        lisaa(avain, arvo, avaimet, arvot);
    }
    
    public void poista(String avain, String arvo) throws Exception {
        poista(avain, arvo, avaimet, arvot);
    }
    
    public boolean sisaltaaAvaimen(String avain) throws Exception {
        return getArvo(avain) != null;
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
        this.avaimia++;
    }
    
    protected int poista(String avain, String arvo, String[] avaimet, String[] arvot) throws Exception {
        int i = etsi(avain, avaimet);
        avaimet[i] = null;
        arvot[i] = null;
        this.avaimia--;
        
        return i;
    }
    
    protected int etsi(String avain, String[] avaimet) throws Exception {
        int muunnos = muunnaAvain(avain);
        
        int i = 0;
        while (true) {
            int paikka = paikka(muunnos, i);
            
            if (avaimet[paikka] != null) {
                if (avaimet[paikka].equals(avain)) {
                    return paikka;
                }
            }
            
            i++;
            
            if (i == avaimet.length) {
                throw new Exception("Avainta ei lˆytynyt.");
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
        this.avaimia -= avaimet.length;
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