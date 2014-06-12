package Tietorakenteet;

/**
 * Hajautustaulu joka toimii kahdella taulukolla siten ett‰ "avainten" perusteella
 * etsit‰‰n aina indeksi, jonka kanssa pelataan.
 */

public class HajautusTaulu {
    private String[] avaimet;
    private String[] arvot;
    private int avaimia;
    
    /**
     * Luo 257-alkioisen hajautustaulun.
     */
    
    public HajautusTaulu() {
        this(100003);
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
    
    /**
     * Palauttaa lis‰ttyjen avainten (ja arvojen) lukum‰‰r‰n.
     * @return 
     */
    
    public int getKoko() {
        return this.avaimia;
    }

    /**
     * Palauttaa taulukon, joka sis‰lt‰‰ joko kaikki "oikeat" (= ei null)
     * avaimet tai arvot.
     * @param verrattava
     * @return 
     */
    
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
    
    public void poista(String avain) throws Exception {
        poista(avain, avaimet, arvot);
    }
    
    public boolean sisaltaaAvaimen(String avain) {
        return getArvo(avain) != null;
    }
    
    /**
     * Palauttaa avainta vastaavan arvon jos avain on hajautustaulussa.
     * @param avain
     * @return
     */
    
    public String getArvo(String avain) {
        try {
            int paikka = etsi(avain);
            return arvot[paikka];
        }
        catch (Exception e) {}
        return null;
    }
    

    /**
     * Etsii indeksin jossa avain on.
     * Heitt‰‰ poikkeuksen jos avainta ei lˆydy.
     * @param avain
     * @return
     * @throws Exception 
     */
    
    protected int etsi(String avain) throws Exception {
        return etsi(avain, avaimet);
    }
    
    protected void setAvaimet(String[] avaimet) {
        this.avaimet = avaimet;
    }
    
    protected void setArvot(String[] arvot) {
        this.arvot = arvot;
    }
    
    /**
     * Avaimen ja arvon lis‰ys toimii siten ett‰ ensin yritet‰‰n poistaa avain
     * ja arvo hajautustaulusta. Jos poisto onnistuu, lis‰t‰‰n taulukoiden
     * indeksiin, josta ne poistettiin, uudet arvot.
     * 
     * Jos poisto ei onnistu, etsit‰‰n avaimelle paikka hajautustaulussa ja
     * lis‰t‰‰n ko. paikkaan avain ja arvo eri taulukoihin.
     * 
     * Avaimien m‰‰r‰ kasvaa, jos taulukosta ei poistettu mit‰‰n.
     * @param avain
     * @param arvo
     * @param avaimet
     * @param arvot
     * @throws Exception 
     */
    
    protected void lisaa(String avain, String arvo, String[] avaimet, String[] arvot) throws Exception {
        int i;
        
        try {
            i = poista(avain, avaimet, arvot);
        }
        
        catch (Exception e) {
            i = hajauta(muunnaAvain(avain), avaimet);
        }
        
        avaimet[i] = avain;
        arvot[i] = arvo;        
        this.avaimia++;
    }
    
    /**
     * Etsii paikan josta avain lˆytyy. Jos ei lˆydy, heitt‰‰ poikkeuksen ja
     * mit‰‰n ei tapahdu.
     * 
     * Jos lˆytyy, poistaa avaimen ja arvon v‰hent‰en samalla avainten lkm.
     * T‰m‰n j‰lkeen palauttaa indeksin, josta poisto tapahtui.
     * @param avain
     * @param avaimet
     * @param arvot
     * @return
     * @throws Exception 
     */
    
    protected int poista(String avain, String[] avaimet, String[] arvot) throws Exception {
        int i = etsi(avain, avaimet);
        avaimet[i] = null;
        arvot[i] = null;
        this.avaimia--;
        
        return i;
    }
    
    /**
     * Etsii lˆytyykˆ avain taulukosta avaimet.
     * Jos lˆytyy, palauttaaa indeksin jossa se on. Jos ei lˆydy, heitt‰‰
     * poikkeuksen.
     * @param avain
     * @param avaimet
     * @return
     * @throws Exception 
     */
    
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
    
    /**
     * Etsii paikan, jonne avain voidaan taulukkoon avaimet lis‰t‰.
     * Jos taulukko on t‰ysi, uudelleenhajautetaan avaimet ja etsit‰‰n
     * paikka uudelleenhajautetusta taulukosta (jossa nyt siis mukana null-arvoja).
     * @param avain
     * @param avaimet
     * @return
     * @throws Exception 
     */
    
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
    
    /**
     * Luo uudet suuremmat hajautustaulukot ja lis‰‰ avaimet ja arvot n‰ihin.
     * @throws Exception 
     */
    
    protected void uudelleenHajautaAvaimet() throws Exception {
        String[] uudetAvaimet = new String[uusiAlkuLuku()];
        String[] uudetArvot = new String[uusiAlkuLuku()];
        
        alustaUudetTaulukot(uudetAvaimet, uudetArvot);

        setAvaimet(uudetAvaimet);
        setArvot(uudetArvot);
    }
    
    /**
     * Pit‰isi palauttaa seuraava alkuluku. Toiminnallisuus ei kunnossa.
     * @return 
     */
    protected int uusiAlkuLuku() {
        return 521;
    }
    
    /**
     * Lis‰‰ alkuper‰iset avaimet ja arvot uusiin taulukoihin. Lis‰yksen aikana
     * avaimien m‰‰r‰ kasvaa, joten tehd‰‰n v‰hennysoperaatio alustuksen j‰lkeen.
     * @param uudetAvaimet
     * @param uudetArvot
     * @throws Exception 
     */
    
    protected void alustaUudetTaulukot(String[] uudetAvaimet, String[] uudetArvot) throws Exception {

        for (int i = 0; i < avaimet.length; i++) {
            lisaa(avaimet[i], arvot[i], uudetAvaimet, uudetArvot);
        }
        this.avaimia -= avaimet.length;
    }
    
    protected int paikka(int avain, int i) {
        return paikka(avain, i, avaimet);
    }
    
    /**
     * "Hajautusfunktio" eli m‰‰ritt‰‰ paikan, minne avain
     * (muunnettuna int-muotoon) sijoitettaisiin hajautustauluun.
     * @param muunnos
     * @param i
     * @param taulukko
     * @return 
     */
    
    protected int paikka(int muunnos, int i, String[] taulukko) {
        int maara = taulukko.length;
        return ( (muunnos % maara) + i * (1 + muunnos % (maara - 2)) ) % maara;
    }
    
    /**
     * Muuntaa avaimen int -muotoon paikan hajautusfunktion k‰yttˆ‰ varten.
     * @param avain
     * @return
     * @throws Exception 
     */
    
    protected int muunnaAvain(String avain) throws Exception {
        if (avain.isEmpty()) {
            throw new Exception("Avain on tyhj‰ ja sit‰ ei hajauteta");
        }
        return muunna(avain);
    }
    
    /**
     * Muuntaminen tapahtuu tarkastellen max. kolmea ensimm‰ist‰ avaimen merkki‰
     * ja laskien int-arvon n‰iden perusteella.
     * @param avain
     * @return 
     */
    
    protected int muunna(String avain) {
        int muunnettu = 0;
        
        int min = Math.min(avain.length(), 3);
        for (int i = 0; i < min; i++) {
            muunnettu += avain.charAt(i);
        }
        
        return muunnettu;
    }
}