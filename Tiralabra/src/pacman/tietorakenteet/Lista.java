package pacman.tietorakenteet;

import pacman.alusta.Peliruutu;

public class Lista {

    private Object[] ruudut;
    private int koko;

    public Lista() {
        ruudut = new Peliruutu[0];
        koko = 0;
    }

    public void lisaa(Object alkio) {
        koko++;
        Object[] taulukko = new Object[koko];

        for (int i = 0; i < ruudut.length; i++) {
            taulukko[i] = ruudut[i];
        }
        taulukko[taulukko.length - 1] = alkio;

        ruudut = taulukko;

    }

    public int koko() {
        return ruudut.length;
    }

    public boolean sisaltaa(Object alkio) {
        for (int i = 0; i < ruudut.length; i++) {
            if (ruudut[i].equals(alkio)) {
                return true;
            }
        }
        return false;
    }
    
    public void tulostaLista() {
        for (int i = 0; i < ruudut.length; i++) {
            System.out.println(ruudut[i]);
        }
    }
    
    public Object getAlkio(int indeksi) {
        return ruudut[indeksi];
    }
    
    public boolean onkoTyhja() {
        if(ruudut.length == 0) {
            return true;
        }
        return false;
    }
}
