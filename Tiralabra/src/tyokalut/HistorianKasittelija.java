package tyokalut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import osat.Laatikko;
import osat.Lava;

/**
 * Toteutetaan AVL-puun muodossa historia kaikista aiemmin lasketuista tuotteista,
 * jotka aina tallennetaan tekstitiedostoon ja ohjelman käynnistyessä luetaan takaisin
 * AsetteluHistoria-olioksi.
 * 
 * @author albis
 */
public class HistorianKasittelija {
    
    public HistorianKasittelija() {
    }
    
    /**
     * Lisää solmun annettuun AVL-puuhun
     * 
     * @param juuri Sen AVL-puun juurisolmu, johon halutaan lisätä.
     * @param laatikko Kokotiedot sisältävä laatikko-olio, joka lisätään.
     * @param asettelu Asettelutapa, joka lisätään uuteen solmuun.
     * @param lava Lavan mittatiedot, jotka lisätään uuteen solmuun.
     * @return Palauttaa lisätyn solmun.
     */
    private AVLsolmu lisaa(AVLsolmu juuri, Laatikko laatikko, KasvavaLista asettelu, Lava lava) {
        AVLsolmu uusi = new AVLsolmu(laatikko, asettelu, lava);
        
        if (juuri == null) {
            juuri = uusi;
            return uusi;
        }
        
        AVLsolmu solmu = juuri;
        AVLsolmu vanhempi = null;
        while (solmu != null) {
            vanhempi = solmu;
            
            if (uusi.getKey() < solmu.getKey()) {
                solmu = solmu.getVasen();
            } else {
                solmu = solmu.getOikea();
            }
        }
        
        uusi.setVanhempi(vanhempi);
            
        if (uusi.getKey() < vanhempi.getKey()) {
            vanhempi.setVasen(uusi);
        } else {
            vanhempi.setOikea(uusi);
        }
        
        return uusi;
    }
    
    /**
     * Käyttää lisaa-metodia ja tämän jälkeen huolehtii puun tasapainossa pysymisestä.
     * 
     * @param juuri Sen AVL-puun juurisolmu, johon lisätään.
     * @param laatikko Lisättävä, mittatiedot sisältävä laatikko-olio.
     * @param asettelu Lisättävät asettelutiedot sisältävä lista.
     * @param lava Lavan mittatiedot sisältävä olio.
     */
    public void AVLlisays(AVLsolmu juuri, Laatikko laatikko, KasvavaLista asettelu, Lava lava) {
        AVLsolmu uusi = lisaa(juuri, laatikko, asettelu, lava);
        AVLsolmu solmu = uusi.getVanhempi();
        
        while (solmu != null) {
            AVLsolmu vanhempi = solmu.getVanhempi();
            AVLsolmu alipuu;
            
            if (haeKorkeus(solmu.getVasen()) - haeKorkeus(solmu.getOikea()) > 1) {
                
                if (haeKorkeus(solmu.getVasen().getVasen()) > haeKorkeus(solmu.getVasen().getOikea())) {
                    alipuu = kiertoOikealle(solmu);
                } else {
                    alipuu = vasenOikeaKierto(solmu);
                }
                
                if (vanhempi == null) {
                    juuri = alipuu;
                } else if (vanhempi.getVasen().getKey() == solmu.getKey()) {
                    vanhempi.setVasen(alipuu);
                } else {
                    vanhempi.setOikea(alipuu);
                }
                
                if (vanhempi != null) {
                    vanhempi.setKorkeus(Math.max(haeKorkeus(vanhempi.getVasen()),
                            haeKorkeus(vanhempi.getOikea())) + 1);
                }
                return;
            }
            if (haeKorkeus(solmu.getVasen()) - haeKorkeus(solmu.getOikea()) < - 1) {
                if (haeKorkeus(solmu.getOikea().getOikea()) > haeKorkeus(solmu.getOikea().getVasen())) {
                    alipuu = kiertoVasemmalle(solmu);
                } else {
                    alipuu = oikeaVasenKierto(solmu);
                }
                
                if (vanhempi == null) {
                    juuri = alipuu;
                } else if (vanhempi.getVasen().getKey() == solmu.getKey()) {
                    vanhempi.setVasen(alipuu);
                } else {
                    vanhempi.setOikea(alipuu);
                }
                
                if (vanhempi != null) {
                    vanhempi.setKorkeus(Math.max(haeKorkeus(vanhempi.getVasen()),
                            haeKorkeus(vanhempi.getOikea())) + 1);
                }
                return;
            }
            
            solmu.setKorkeus(Math.max(haeKorkeus(solmu.getVasen()),
                            haeKorkeus(solmu.getOikea())) + 1);
            solmu = solmu.getVanhempi();
        }
    }
    
    /**
     * Etsii annetusta AVL-puusta onko siellä annetun EAN-koodin tuotetta.
     * 
     * @param solmu Puun juurisolmu, josta etsitään.
     * @param EAN Halutun tuotteen EAN-koodi.
     * @return Palauttaa halutun tuotteen tiedot, jos löytyy, null jos ei.
     */
    public AVLsolmu etsi(AVLsolmu solmu, long EAN) {
        if (solmu == null || solmu.getKey() == EAN) {
            return solmu;
        }
        
        if (EAN < solmu.getKey()) {
            return etsi(solmu.getVasen(), EAN);
        } else {
            return etsi(solmu.getOikea(), EAN);
        }
    }
    
    /**
     * AVL-puun solmuja oikealle kiertävä metodi, jolla huolehditaan sen tasapainosta.
     * 
     * @param solmu Solmu, jonka suhteen kierto tehdään.
     * @return Palauttaa uuden solmujen vanhemman.
     */
    private AVLsolmu kiertoOikealle(AVLsolmu solmu) {
        AVLsolmu vasen = solmu.getVasen();
        vasen.setVanhempi(solmu.getVanhempi());
        
        solmu.setVanhempi(vasen);
        solmu.setVasen(vasen.getOikea());
        
        vasen.setOikea(solmu);
        
        if (solmu.getVasen() != null) {
            solmu.getVasen().setVanhempi(solmu);
        }
        
        solmu.setKorkeus(Math.max(haeKorkeus(solmu.getVasen()), haeKorkeus(solmu.getOikea())) + 1);
        vasen.setKorkeus(Math.max(haeKorkeus(vasen.getVasen()), haeKorkeus(vasen.getOikea())) + 1);
        
        return vasen;
    }
    
    /**
     * AVL-puun solmuja vasemmalle kiertävä metodi, jolla huolehditaan sen tasapainosta.
     * 
     * @param solmu Solmu, jonka suhteen kierto tehdään.
     * @return Palauttaa uuden solmujen vanhemman.
     */
    private AVLsolmu kiertoVasemmalle(AVLsolmu solmu) {
        AVLsolmu oikea = solmu.getOikea();
        oikea.setVanhempi(solmu.getVanhempi());
        
        solmu.setVanhempi(oikea);
        solmu.setOikea(oikea.getVasen());
        
        oikea.setVasen(solmu);
        
        if (solmu.getOikea() != null) {
            solmu.getOikea().setVanhempi(solmu);
        }
        
        solmu.setKorkeus(Math.max(haeKorkeus(solmu.getVasen()), haeKorkeus(solmu.getOikea())) + 1);
        oikea.setKorkeus(Math.max(haeKorkeus(oikea.getVasen()), haeKorkeus(oikea.getOikea())) + 1);
        
        return oikea;
    }
    
    /**
     * AVL-puun solmuja ensin oikealle ja sitten vasemmalle kiertävä metodi, jolla huolehditaan sen tasapainosta.
     * 
     * @param solmu Solmu, jonka suhteen kierto tehdään.
     * @return Palauttaa uuden solmujen vanhemman.
     */
    private AVLsolmu oikeaVasenKierto(AVLsolmu solmu) {
        AVLsolmu oikea = solmu.getOikea();
        solmu.setOikea(kiertoOikealle(oikea));
        return kiertoVasemmalle(solmu);
    }
    
    /**
     * AVL-puun solmuja ensin vasemmalle ja sitten oikealle kiertävä metodi, jolla huolehditaan sen tasapainosta.
     * 
     * @param solmu Solmu, jonka suhteen kierto tehdään.
     * @return Palauttaa uuden solmujen vanhemman.
     */
    private AVLsolmu vasenOikeaKierto(AVLsolmu solmu) {
        AVLsolmu vasen = solmu.getVasen();
        solmu.setVasen(kiertoVasemmalle(vasen));
        return kiertoOikealle(solmu);
    }
    
    /**
     * Kertoo annetun solmun etäisyyden lehtisolmuista.
     * 
     * @param solmu Solmu, jonka korkeus halutaan tietää.
     * @return Palauttaa korkeustiedon kokonaislukuna.
     */
    private int haeKorkeus(AVLsolmu solmu) {
        if (solmu == null) {
            return -1;
        }
        return solmu.getKorkeus();
    }
    
    /**
     * Metodi, joka avaa vanhat asettelutavat sisältävän tiedoston ja lukee sen tiedot.
     */
    public AVLsolmu avaa() {
        File historiaTiedosto = new File("historia.txt");
        AVLsolmu juuri = null;
        
        try {
            Scanner lukija = new Scanner(historiaTiedosto);
            
            while (lukija.hasNextLine()) {
                String rivi = lukija.nextLine();
                
                String[] osat = rivi.split("=");
                String[] mitat = osat[0].split(":");
                
                String[] laatikonMitat = mitat[0].split("-");
                String[] lavanMitat = mitat[1].split("-");
                
                Laatikko laatikko = new Laatikko(Integer.parseInt(laatikonMitat[0]),
                        Integer.parseInt(laatikonMitat[1]), 
                        Integer.parseInt(laatikonMitat[2]), Long.parseLong(laatikonMitat[3]));
                Lava lava = new Lava(Integer.parseInt(lavanMitat[0]), Integer.parseInt(lavanMitat[1]),
                        Integer.parseInt(lavanMitat[2]));
                
                KasvavaLista asettelu = new KasvavaLista();
                
                String[] asennot = osat[1].split(",");
                
                for (int i = 0; i < asennot.length; i++) {
                    asettelu.lisaa(osat[i]);
                }
                
                AVLlisays(juuri, laatikko, asettelu, lava);
            }
            
            lukija.close();
        } catch (FileNotFoundException e) {
            System.out.println("Virhe tiedostoa avattaessa!" + e.getMessage());
        }
        
        return juuri;
    }
    
    /**
     * Metodi, joka tallentaa kaikki puun sisältämät asettelutavat tekstitiedostoon
     * ohjelma suljettaessa.
     */
    public void tallenna(AVLsolmu juuri) {
        try {
            FileWriter kirjoittaja = new FileWriter("historia.txt");
            
            juuri.tallennaSolmu(kirjoittaja);
            
            kirjoittaja.close();
        } catch (IOException e) {
            System.out.println("Virhe tallennettaessa!" + e.getMessage());
        }
    }
}
