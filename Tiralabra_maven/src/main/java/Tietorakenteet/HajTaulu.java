package Tietorakenteet;

public class HajTaulu {
    private Hajautettava[][] taulu;
    
    public HajTaulu() {
        this.taulu = new Hajautettava[256][1];
    }
    
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
    
    public int getKoko() {
        return taulu.length;
    }
    
    public void lisaa(String avain, String arvo) {
        Paikka paikka = etsi(avain);
        
        if (paikka == null) {
            lisaa(new Hajautettava(avain, arvo));
        }
        else {
            taulu[paikka.getJ()][paikka.getI()].setArvo(arvo);
        }
    }
    
    protected void lisaa(Hajautettava lisattava) {
        lisaa(lisattava, hajauta(lisattava));
    }
    
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
    
    public void poista(String avain) {
        if (avain == null) {
            return;
        }
        
        Hajautettava poistettava = new Hajautettava(avain, null);
        int j = hajauta(poistettava);
        int i = poistettavanIndeksi(poistettava, j);
        
        poista(i, j);
    }
    
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
        return avaintenMaara() == 0;
    }
    
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
    
    protected String[] getTaulukko(boolean lisaaAvain) {
        String[] taulukko = new String[avaintenMaara()];
        lisaaTaulukkoon(taulukko, lisaaAvain);
        return taulukko;
    }
    
    protected int avaintenMaara() {
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
    
    protected void lisaaTaulukkoon(String[] taulukko, boolean avaimet) {
        int i = 0;
        
        for (Hajautettava[] rivi : taulu) {
            for (Hajautettava hajautettava : rivi) {
                if (lisaaJosEiNull(taulukko, hajautettava, avaimet, i)) {
                    i++;
                }

                if (i >= taulukko.length) {
                    return;
                }
            }
        }   
    }
    
    protected boolean lisaaJosEiNull(String[] taulukko, Hajautettava hajautettava, boolean avaimet, int i) {
        if (hajautettava == null) {
            return false;
        }
        
        if (avaimet) {
            taulukko[i] = hajautettava.getAvain();
        }
        else {
            taulukko[i] = hajautettava.getArvo();
        }
        
        return true;
    }
    
    protected Paikka etsi(String avain) {
        Hajautettava etsittava = new Hajautettava(avain, null);
        int j = etsittava.hashCode(getKoko());
        
        return etsi(etsittava, avain, j);
    }
    
    protected Paikka etsi(Hajautettava etsittava, String avain, int j) {
        for (int i = 0; i < taulu[j].length; i++) {
            if (taulu[j][i] != null && taulu[j][i].getAvain().equals(avain)) {
                return new Paikka(j,i);
            }
        }
        return null;
    }
    
    protected int hajauta(Hajautettava hajautettava) {
        return hajautettava.hashCode(getKoko());
    }
    
    protected Hajautettava hajautettava(String avain, String arvo) {
        return new Hajautettava(avain, arvo);
    }
    
    protected Hajautettava[] getRivi(int j) {
        return taulu[j];
    }
}
