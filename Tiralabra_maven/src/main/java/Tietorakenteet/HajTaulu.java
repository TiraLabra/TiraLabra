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
        int j = hajauta(lisattava);
        
        for (int i = 0; i < this.taulu[j].length; i++) {
            if (taulu[j][i] == null) {
                taulu[j][i] = lisattava;
            }
            else if (i == taulu[j].length - 1) {
                tuplaaKoko(j);
                taulu[j][i] = lisattava;
            }
        }
    }
    
    public void poista(String avain) {
        if (avain == null) {
            return;
        }
        
        Hajautettava poistettava = new Hajautettava(avain, null);
        int j = hajauta(poistettava);
        
        int i = 0;
        while (taulu[j][i] != poistettava && i < taulu[j].length) {
            i++;
        }
        
        while (i < taulu[j].length - 1) {
            taulu[j][i] = taulu[j][i+1];
            
            if (i == taulu[j].length - 1) {
                taulu[j][i] = null;
            }
        }
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
        lisaaAvaimet(taulukko, lisaaAvain);
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
    
    protected void lisaaAvaimet(String[] taulukko, boolean lisaaAvain) {
        int i = 0;
        while (i < taulukko.length) {
            
            for (Hajautettava[] rivi : taulu) {
                for (Hajautettava hajautettava : rivi) {
                    if (hajautettava != null) {
                        
                        if (lisaaAvain) {
                            taulukko[i] = hajautettava.getAvain();
                        }
                        else {
                            taulukko[i] = hajautettava.getArvo();
                        }
                        i++;
                    }
                }
            }
        }
    }
    
    protected Paikka etsi(String avain) {
        Hajautettava etsittava = new Hajautettava(avain, null);
        int j = etsittava.hashCode(getKoko());
        
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
}
