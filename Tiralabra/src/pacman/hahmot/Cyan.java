package pacman.hahmot;

import java.util.Random;
import pacman.alusta.Pelialusta;
import pacman.alusta.Peliruutu;
import pacman.tietorakenteet.Lista;

/**
 * Cyan on yksi haamuista, joka pyrkii ennakoimaan manin liikkumista.
 * @author Hanna
 */
public class Cyan extends Haamu {
    
    /**
     * Konstruktori, joka peritään yläluokalta haamu.
     * @param x
     * @param y
     * @param suunta
     * @param nimi
     * @param alusta 
     */
    public Cyan(int x, int y, Suunta suunta, String nimi, Pelialusta alusta) {
        super(x, y, suunta, nimi, alusta);
    }

    /**
     * Metodi tarkistaa onko maali sopiva ruutu, haamulle Cyan. Haamulla on
     * kohteena kolme ruutua Manin edellä oleva ruutu. Jos ruutu ei ole sopiva
     * maaliksi etsitään uusi ruutu. Jos ei löydy mitään sopivaa ruutua, maali
     * on Man itse.
     *
     * @param man
     * @return Palauttaa sopivan maali ruudun.
     */
    public Peliruutu selvitaMaaliCyan(Man man) {

        int testiX = laskeXCyan(man);
        int testiY = laskeYCyan(man);

        if (tarkistaOnkoHuonoRuutu(testiX, testiY)) {
            return alusta.getPeliruutu(man.getX(), man.getY());
        }

        Peliruutu maali = alusta.getPeliruutu(testiX, testiY);

        if (tarkistaOnkoMaaliSamaKuinHaamunSijainti(maali)) {
            return selvitaMaaliSuunnista(maali, man);
        }

        return tarkistaOnkoSeina(maali, man);

    }

    /**
     * Tarkistetaan, onko tämän hetkinen maali sama kuin haamun omat
     * koordinaatit
     *
     * @param maali
     * @param man
     * @return
     */
    private boolean tarkistaOnkoMaaliSamaKuinHaamunSijainti(Peliruutu maali) {
        if (maali.getX() == this.x && maali.getY() == this.y) {
            return true;
        }
        return false;
    }

    /**
     * Lasketaan x-koordinaatti, siten, että se tulee sijaitsemaan kolme ruutua
     * manin edellä.
     *
     * @param man
     * @return
     */
    private int laskeXCyan(Man man) {
        int testiX = man.getX() + man.getSuunta().getX() * 3;
        return testiX;
    }

    /**
     * Lasketaan y-koordinaatti, siten, että se tulee sijaitsemaan kolme ruutua
     * manin edellä.
     *
     * @param man
     * @return
     */
    private int laskeYCyan(Man man) {
        int testiY = man.getY() + man.getSuunta().getY() * 3;
        return testiY;
    }

    /**
     * Metodi tarkistaa onko maalin tyyppi seinä. Jos maali on seinä, metodi
     * etsii uuden sopivan ruudun ja tämän jälkeen tarkistaa vielä, että etsitty
     * ruutu ei ole sellainen missä olisi haamu. Jos ruudussa on haamu, täytyy
     * etsiä vielä uusi ruutu. Jos alkuperäinen maali ei ole seinä, petodi
     * palauttaa alkuperäisen maalin.
     *
     * @param maali
     * @param man
     * @return palauttaa sopivan maali ruudun.
     */
    private Peliruutu tarkistaOnkoSeina(Peliruutu maali, Man man) {
        if (maali.getRuudunTyyppi() == 0) {
            Peliruutu uusiMaali = selvitaMaaliSuunnista(maali, man);
            return tarkistaEttaMaaliEiOleHaamunOmaRuutu(uusiMaali, man);
        } else {
            return maali;
        }
    }

    /**
     * Tarkistetaan, että uusi maaliruutu ei ole haamun oma ruutu. Jos ruutu on haamun oma ruutu täytyy etsiä uusi maaliruutu.
     * @param uusiMaali
     * @param man
     * @return 
     */
    private Peliruutu tarkistaEttaMaaliEiOleHaamunOmaRuutu(Peliruutu uusiMaali, Man man) {
        if (uusiMaali.getX() == this.x && uusiMaali.getY() == this.y) {
            return selvitaMaaliSuunnista(uusiMaali, man);
        } else {
            return uusiMaali;
        }
    }

    /**
     * Metodi selvittää uuden maaliruudun, vanhan maaliruudun ympärillä olevista
     * ruuduista.
     *
     * @param maali
     * @param man
     * @return palauttaa uuden maaliruudun
     */
    private Peliruutu selvitaMaaliSuunnista(Peliruutu maali, Man man) {

        selvitaMahdollisetSuunnat2(maali);
        if (!mahdollisetSuunnat.onkoTyhja()) {
            Suunta snta = arvoSuunta();
            Peliruutu uusiMaali = alusta.getPeliruutu(maali.getX() + snta.getX(), maali.getY() + snta.getY());
            if (onkoAlustanSisalla(maali.getX(), maali.getY())) {
                return alusta.getPeliruutu(uusiMaali.getX(), uusiMaali.getY());
            }
        }
        return alusta.getPeliruutu(man.getX(), man.getY());
    }

    /**
     * Metodi selvittää missä suunnassa uusi maali voisi olla, jos alkuperäinen
     * ruutu ei sovi maaliruuduksi.
     *
     * @param maali
     */
    private void selvitaMahdollisetSuunnat2(Peliruutu maali) {
        mahdollisetSuunnat = new Lista();

        for (Suunta s : Suunta.values()) {
            Peliruutu tarkasteltava = alusta.getPeliruutu(maali.getX() + s.getX(), maali.getY() + s.getY());

            if (tarkasteltava.getRuudunTyyppi() == 1) {
                mahdollisetSuunnat.lisaa(s);
            }
        }
    }
    
    
    /**
     * Metodi arvoo listan komponenteista yhden.
     *
     * @return Palauttaa listasta arvotun suunnan.
     */
    private Suunta arvoSuunta() {
        Random arpoja = new Random();
        int arpaluku = arpoja.nextInt(mahdollisetSuunnat.koko());
        Suunta snta = (Suunta) mahdollisetSuunnat.getAlkio(arpaluku);
        return snta;
    }
}
