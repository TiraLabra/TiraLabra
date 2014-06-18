package Tietorakenteet;

/**
 * Itsetoteutettu tietorakenne, jolla on hajautustaulun t�rkeimm�t operaatiot.
 * Luokalla on kaksi aliluokkaa, joista toinen kuvaa hajautettavaa oliota ja
 * toinen paikkaa hajautustaulussa.
 */

public class HajautusTaulu {
    private Hajautettava[][] taulu;
    
    /**
     * Luo uuden 256-sarakkeisen hajautustaulun.
     */
    
    public HajautusTaulu() {
        this.taulu = new Hajautettava[256][1];
    }
    
    /**
     * Kuvaa hajautettavaa oliota.
     * Oliolla on avain ja arvo sen arvoa voi muuttaa.
     * Avain m��ritt�� sen palauttaman "hashCoden".
     */
    
    protected class Hajautettava {
        private String avain;
        private String arvo;
        
        protected Hajautettava(String avain, String arvo) {
            this.avain = avain;
            this.arvo = arvo;
        }
        
        protected String getAvain() {
            return this.avain;
        }
        
        protected String getArvo() {
            return this.arvo;
        }
        
        protected void setArvo(String arvo) {
            this.arvo = arvo;
        }
        
        /**
         * Ottaa itseisarvon avaimen hashCodesta ja skaalaa sen pienemm�ksi kuin koko.
         * Ei toimi parhaiten optimoidulla tavalla.
         * @param koko
         * @return 
         */
        
        protected int hashCode(int koko) {
            return Math.abs(this.avain.hashCode()) % koko;
        }
    }
    
    protected class Paikka {
        private int j;
        private int i;
        
        public Paikka(int j, int i) {
            this.j = j;
            this.i = i;
        }
        
        protected int getJ() {
            return this.j;
        }
        
        protected int getI() {
            return this.i;
        }
    }
    
    /**
     * Palauttaa hajautustaulussa olevien alkioiden lukum��r�n.
     * @return 
     */
    
    public int getKoko() {
        int maara = 0;
        
        for (Hajautettava[] rivi : taulu) {
            for (Hajautettava hajautettava : rivi) {
                if (hajautettava != null) {
                    maara++;
                }
            }
        }
        return maara;
    }
    
    /**
     * Lis�� hajautustauluun avaimen ja sille arvon.
     * Jos avain on jo hajautustaulussa, korvaa sen arvon annetulla arvolla.
     * @param avain
     * @param arvo 
     */
    
    public void lisaa(String avain, String arvo) {
        Paikka paikka = etsi(avain);
        
        if (paikka == null) {
            Hajautettava lisattava = new Hajautettava(avain, arvo);
            lisaa(lisattava, hajauta(lisattava));
        }
        else {
            taulu[paikka.getJ()][paikka.getI()].setArvo(arvo);
        }
    }
    
    /**
     * Lis�� olion hajautustaulun sarakkeen j paikkaan, jossa on sille tyhj�
     * (null) paikka. Jos tyhj�� paikkaa ei ole, tuplataan ko. sarakkeen kokoa.
     * @param lisattava
     * @param j - sarake
     */
    
    protected void lisaa(Hajautettava lisattava, int j) {
        int i = 0;
        
        while (true) {
            if (i == taulu[j].length) {
                tuplaaKoko(j);
            }
            if (taulu[j][i] == null) {
                taulu[j][i] = lisattava;
                break;
            }
            i++;
        }
    }
    
    /**
     * Poistaa hajautustaulusta olion, jolla on sama avain kuin mink� metodi
     * saa parametrina. Jos oliota ei hajautustaulussa ole, ei tehd� mit��n.
     * @param avain 
     */
    
    public void poista(String avain) {
        if (avain == null) {
            return;
        }
        
        Hajautettava poistettava = new Hajautettava(avain, null);
        int j = hajauta(poistettava);
        int i = poistettavanIndeksi(poistettava, j);
        
        poista(i, j);
    }
    
    /**
     * Poistaa sarakkeesta j i:nnen alkion siirt�en sen j�lkeen olevat avaimet
     * yhdell� paikalla eteenp�in kunnes tulee eka null-paikka vastaan. Jos
     * null -paikkaa ei ole, se tulee per�lle.
     * 
     * Huom: metodi ei tee mit��n jos i == taulu[j].length ja t�m� tapaus tulee
     * vastaan kun poistettavaa avainta ei taulusssa ollut.
     * @param i
     * @param j 
     */
    
    protected void poista(int i, int j) {
        while (i < taulu[j].length) {
            
            if (i == taulu[j].length - 1) {
                taulu[j][i] = null;
            }
            
            if (taulu[j][i] == null) {
                return;
            }

            taulu[j][i] = taulu[j][i+1];
            i++;
        }
    }
    
    /**
     * Selvitt�� indeksin taulukossa taulu[j], mist� l�ytyy alkio, jolla
     * on sama avain kuin mik� on parametrina saatavalla oliolla.
     * 
     * Jos sellaista alkiota ei l�ydy, palautetaan "taulu[j].length".
     * @param poistettava
     * @param j
     * @return 
     */
    
    protected int poistettavanIndeksi(Hajautettava poistettava, int j) {
        int i = 0;
        while (i < taulu[j].length) {
            
            if (taulu[j][i] == null) {
                i = taulu[j].length;
                break;
            }
            
            if (taulu[j][i].getAvain().equals(poistettava.getAvain())) {
                break;
            }
            i++;
        }
        return i;
    }
    
    /**
     * Kun sarake j on t�ynn�, sen koko tuplataan luomalla uusi taulukko, joka
     * on kaksi kertaa suurempi ja siirt�en taulun sarakkeesta j arvot t�h�n.
     * @param j 
     */
    
    protected void tuplaaKoko(int j) {
        Hajautettava[] uusi = new Hajautettava[2 * taulu[j].length];
        
        for (int i = 0; i < taulu[j].length; i++) {
            uusi[i] = taulu[j][i];
        }
        taulu[j] = uusi;
    }
    
    public boolean sisaltaaAvaimen(String avain) {
        return etsi(avain) != null;
    }
    
    public boolean onTyhja() {
        return getKoko() == 0;
    }
    
    /**
     * Palauttaa avainta koskevan arvon, jos avain on hajautustaulussa.
     * Muuten null.
     * @param avain
     * @return 
     */
    
    public String getArvo(String avain) {
        Paikka paikka = etsi(avain);
        if (paikka != null) {
            return taulu[paikka.getJ()][paikka.getI()].getArvo();
        }
        return null;
    }
    
    public String[] getAvaimet() {
        return getTaulukko(true);
    }
    
    public String[] getArvot() {
        return getTaulukko(false);
    }
    
    /**
     * Palauttaa taulukon joka on parametrista riippuen joko taulun
     * avainten tai arvojen joukko.
     * Jos "true", palauttaa avaimet. Muuten arvot.
     * @param avaimet
     * @return 
     */
    
    protected String[] getTaulukko(boolean avaimet) {
        String[] taulukko = new String[getKoko()];
        lisaaTaulukkoon(taulukko, avaimet);
        return taulukko;
    }
    
    /**
     * getTaulukko() metodin alametodi joka lis�� taulukkoon avaimet tai
     * arvot boolean-arvosta riippuen.
     * @param taulukko
     * @param avaimet 
     */
    
    protected void lisaaTaulukkoon(String[] taulukko, boolean avaimet) {
        int i = 0;
        
        for (Hajautettava[] rivi : taulu) {
            for (Hajautettava hajautettava : rivi) {
                
                if (hajautettava != null) {
                    lisaaHajautettava(taulukko, hajautettava, avaimet, i);
                    i++;
                    
                    if (i >= taulukko.length) {
                        return;
                    }
                }
            }
        }   
    }
    
    /**
     * Lis�� hajautettavan avaimen tai arvon taulukkoon riippuen boolean -arvosta.
     * @param taulukko
     * @param hajautettava
     * @param avaimet
     * @param i 
     */
    
    protected void lisaaHajautettava(String[] taulukko, Hajautettava hajautettava, boolean avaimet, int i) {
        if (avaimet) {
            taulukko[i] = hajautettava.getAvain();
        }
        else {
            taulukko[i] = hajautettava.getArvo();
        }
    }
    
    /**
     * Etsii hajautustaulun paikan, josta l�ytyy olio, jolla on sama avain
     * kuin parametrina annettu.
     * Palauttaa null jos ko. oliota ei l�ydy.
     * @param avain
     * @return 
     */
    
    protected Paikka etsi(String avain) {
        Hajautettava etsittava = new Hajautettava(avain, null);
        int j = etsittava.hashCode(taulu.length);
        
        return etsi(avain, j);
    }
    
    /**
     * Kelaa sarakkeen j arvoja l�pi kunnes vastaan tulee ensimm�inen null-arvo
     * tai kunnes l�ydet��n olio, jolla on sama avain kuin parametrina annettu.
     * T�ll�in palautetaan sit� vastaava paikka.
     * @param avain
     * @param j
     * @return 
     */
    
    protected Paikka etsi(String avain, int j) {
        for (int i = 0; i < taulu[j].length; i++) {
            if (taulu[j][i] == null) {
                break;
            }
            
            if (taulu[j][i].getAvain().equals(avain)) {
                return new Paikka(j,i);
            }
        }
        return null;
    }
    
    /**
     * Palauttaa kokonaisluvun v�lilt� [0-taulu.length], mink�
     * hajautusfunktio parametrina saatavan olion avaimelle antaa.
     * @param hajautettava
     * @return 
     */
    
    protected int hajauta(Hajautettava hajautettava) {
        return hajautettava.hashCode(taulu.length);
    }
    
    /**
     * Metodi, jolla p��sen k�siksi alaluokkaan "Hajautettava" testiluokasta.
     * @param avain
     * @param arvo
     * @return 
     */
    
    protected Hajautettava hajautettava(String avain, String arvo) {
        return new Hajautettava(avain, arvo);
    }
    
    /**
     * Testimetodi samoin kuin edellinenkin.
     * @param j
     * @return 
     */
    
    protected Hajautettava[] getRivi(int j) {
        return taulu[j];
    }
}
