/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.tietorakenteet;

import com.mycompany.tiralabra_maven.automaatit.Tila;

/**
 *
 * @author John Lång <jllang@cs.helsinki.fi>
 */
public final class Tilasiirtymakartta extends Hajautuskartta<Tila> {
    
    private Tilasiirtymajono[] ylivuotolistat;
//    private int alkioita = 0;
    
    public Tilasiirtymakartta(int HAJAUTUSTAULUN_PITUS) {
        tarkastaHajautustaulunPituus(HAJAUTUSTAULUN_PITUS);
        ylivuotolistat  = alustaYlivuotolistat(HAJAUTUSTAULUN_PITUS);
        this.alkioita   = 0;
    }

    @Override
    protected Tilasiirtymajono[] alustaYlivuotolistat(int HAJAUTUSTAULUN_PITUS) {
        Tilasiirtymajono[] paluuarvo
                = new Tilasiirtymajono[HAJAUTUSTAULUN_PITUS];
        for (int i = 0; i < HAJAUTUSTAULUN_PITUS; i++) {
            paluuarvo[i] = new Tilasiirtymajono();
        }
        return paluuarvo;
    }

    @Override
    public void lisaa(char AVAIN, Tila ARVO) {
        ylivuotolistat[hajauta(AVAIN)].lisaa(AVAIN, ARVO);
        alkioita++;
    }

    /**
     * Kuten luokan <b>Hajautuskartta</b> samanniminen metodi. Tämä metodi on
     * tarpeeton automaatin toiminnan kannalta.
     * 
     * @param AVAIN
     * @return
     * @deprecated
     * @see Hajautuskartta#haeEnsimmainen(char)
     */
    @Override
    @Deprecated
    public Tila haeEnsimmainen(char AVAIN) {
        return ylivuotolistat[hajauta(AVAIN)].hae(AVAIN);
    }    
    
    
    /**
     * Palauttaa jonon kaikista avaimeen liitetyistä arvoista. Metodi on 
     * tarpeen luokan <b>Tila</b> tilasiirtymässä jossa samalla merkillä voidaan
     * siirtyä useampaan eri tilaan. Esimerkki tällaisesta tilanteesta on
     * säännöllinen lauseke "a| ab".
     * 
     * @return <b>Jono</b>, joka sisältää kaikki avaimeen liitetyt arvot.
     */
    public Jono<Tila> haeKaikki(final char AVAIN) {
        // Tämä operaatio voi olla ehkä vähän hidas...
        Tilasiirtymajono avainArvoJono = ylivuotolistat[hajauta(AVAIN)];
        
        return avainArvoJono.haeKaikki(AVAIN);
    }
    
    @Override
    public String toString() {
        StringBuilder mjr = new StringBuilder();
        mjr.append('{');
        for (Tilasiirtymajono tilasiirtymajono : ylivuotolistat) {
            mjr.append(tilasiirtymajono.toString());
            mjr.append(',');
        }
        mjr.delete(mjr.length() - 1, mjr.length());
        mjr.append('}');
        return mjr.toString();
    }
    
    public String sisennettyMerkkijono(final String SISENNYS) {
        if (alkioita == 0) {
            return "\u2205";
        }
        
        StringBuilder mjr = new StringBuilder();
        mjr.append('{');
        if (alkioita > 1) {
            mjr.append('\n');
            mjr.append(SISENNYS);
        }
        String mj;
        int epatyhjia = 0;
        for (Tilasiirtymajono avainArvoJono : ylivuotolistat) {
            mj = avainArvoJono.sisennettyMerkkijono(SISENNYS);
            // Poistetaan "Ö":t että saadaan lyhyempi tuloste:
            if (!mj.equals("\u2205")) {
                epatyhjia++;
                mjr.append(mj);
                mjr.append(',');
                mjr.append('\n');
                mjr.append(SISENNYS);
            }
        }
        if (epatyhjia <= 1) {
            mjr.delete(mjr.length() - (SISENNYS.length() + 2), mjr.length());
        } else {
            mjr.delete(mjr.length() - 4, mjr.length());
        }
        mjr.append('}');
        return mjr.toString();
    }
    
    /**
     * Uudelleenhajauttaa hajautustaulun. <b>Hajautuskartta</b> ei suorita
     * uudelleenhajautusta automaattisesti vaan käyttäjän vastuulla on päättää
     * milloin (jos ollenkaan) uudelleenhajautus toteutetaan.
     * 
     * @see Hajautuskartta#tayttosuhde
     */
    public void uudelleenhajauta(final int HAJAUTUSTAULUN_PITUS) {
        tarkastaHajautustaulunPituus(HAJAUTUSTAULUN_PITUS);
        Tilasiirtymajono[] vanhatYlivuotolistat = ylivuotolistat;
        ylivuotolistat = alustaYlivuotolistat(HAJAUTUSTAULUN_PITUS);
        int i = 0;
        Character avain;
        Tila arvo;
        Jono<Character> avainjono;
        Jono<Tila> arvojono;
        for (Tilasiirtymajono avainArvoJono : vanhatYlivuotolistat) {
            avainjono   = avainArvoJono.avainjono();
            arvojono    = avainArvoJono.arvojono();
            while (!avainjono.onTyhja()) {
                avain   = avainjono.poista();
                arvo    = arvojono.poista();
                lisaa(avain, arvo);
            }
            i++;
        }
    }
    
    /**
     * Palauttaa hajautuskartan täyttösuhteen. Täyttösuhde on ylivuotoketjuissa
     * olevien tietoalkioiden lukumäärä jaettuna ylivuotoketjujen määrällä.
     * Täyttösuhdetta ei tallenneta muuttujaan vaan lasketaan erikseen tätä
     * metodia kutsuttaessa.
     * 
     * @return Hajautuskartan täyttösuhde.
     */
    public float tayttosuhde() {
        return (float) alkioita / ylivuotolistat.length;
    }
    
}
