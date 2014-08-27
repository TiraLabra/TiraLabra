package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 * Luokka sisältää metodeja pelilaudan tilanteen käsittelyyn
 *
 * @author noora
 */
public class Pelilauta {

    private Nappula[][] ruudukko;

    /**
     * Konstruktorissa luodaan uusi tyhjä pelilauta, joka on kooltaan 8x8
     */
    public Pelilauta() {
        this.ruudukko = new Nappula[8][8];
    }

    /**
     * Metodi asettaa nappulat laudalle siten kun ne siellä ovat pelin alussa
     */
    public void asetaNappulatAlkuasetelmaan() {
        this.ruudukko = new Nappula[8][8];

        ruudukko[0][1] = Nappula.MUSTA;
        ruudukko[0][3] = Nappula.MUSTA;
        ruudukko[0][5] = Nappula.MUSTA;
        ruudukko[0][7] = Nappula.MUSTA;
        ruudukko[1][0] = Nappula.MUSTA;
        ruudukko[1][2] = Nappula.MUSTA;
        ruudukko[1][4] = Nappula.MUSTA;
        ruudukko[1][6] = Nappula.MUSTA;
        ruudukko[2][1] = Nappula.MUSTA;
        ruudukko[2][3] = Nappula.MUSTA;
        ruudukko[2][5] = Nappula.MUSTA;
        ruudukko[2][7] = Nappula.MUSTA;
        ruudukko[5][0] = Nappula.VALKOINEN;
        ruudukko[5][2] = Nappula.VALKOINEN;
        ruudukko[5][4] = Nappula.VALKOINEN;
        ruudukko[5][6] = Nappula.VALKOINEN;
        ruudukko[6][1] = Nappula.VALKOINEN;
        ruudukko[6][3] = Nappula.VALKOINEN;
        ruudukko[6][5] = Nappula.VALKOINEN;
        ruudukko[6][7] = Nappula.VALKOINEN;
        ruudukko[7][0] = Nappula.VALKOINEN;
        ruudukko[7][2] = Nappula.VALKOINEN;
        ruudukko[7][4] = Nappula.VALKOINEN;
        ruudukko[7][6] = Nappula.VALKOINEN;

    }

    public Nappula getNappula(int x, int y) {
        return ruudukko[x][y];
    }

    public Nappula[][] getRuudukko() {
        return ruudukko;
    }

    /**
     * Metodi tekee pelilaudasta kopion eli luo uuden pelilaudan ja sille ruudukon ja kopioi nappuloiden paikat uuteen ruudukkoon
     * @return Palauttaa pelilaudasta tehdyn kopion
     */
    public Pelilauta teeKopio() {
        Pelilauta palautus = new Pelilauta();
        palautus.ruudukko = new Nappula[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                palautus.ruudukko[i][j] = this.ruudukko[i][j];
            }
        }
        return palautus;
    }

    /**
     * Metodi palauttaa listan mahdollisista siirroista
     *
     * @param valkoisenVuoroSiirtaa kertoo kumman pelaajan vuoro on siirtää eli
     * kumman pelaajan mahdollisista siirroista on kyse
     * @return palauttaa listan mahdollisista siirroista tai null, jos niitä ei
     * ole
     */
    public Siirto[] getSallitutSiirrot(boolean valkoisenVuoroSiirtaa) {
        ArrayList<Siirto> mahdollisetSiirrot = new ArrayList<>();
        lisaaMahdollisetHypytListaan(valkoisenVuoroSiirtaa, mahdollisetSiirrot);
        if (mahdollisetSiirrot.isEmpty()) {
            lisaaMahdollisetSiirrotListaan(valkoisenVuoroSiirtaa, mahdollisetSiirrot);
        }
        if (!mahdollisetSiirrot.isEmpty()) {
            Siirto[] siirrot = new Siirto[mahdollisetSiirrot.size()];
            for (int i = 0; i < mahdollisetSiirrot.size(); i++) {
                siirrot[i] = mahdollisetSiirrot.get(i);
            }
            return siirrot;
        }
        return null;
    }

    /**
     * Metodi lisää listaan sellaiset siirrot, joissa nappula siirtyy viereiseen
     * ruutuun eikä hyppää
     *
     * @param valkoisenVuoroSiirtaa sisältää tiedon siitä, kumman pelaajan vuoro
     * on siirtää
     * @param mahdollisetSiirrot lista mahdollisista siirroista
     */
    private void lisaaMahdollisetSiirrotListaan(boolean valkoisenVuoroSiirtaa, ArrayList<Siirto> mahdollisetSiirrot) {
        for (int rivi = 0; rivi < 8; rivi++) {
            for (int sarake = 0; sarake < 8; sarake++) {
                if (ruudukko[rivi][sarake] == null) {
                    continue;
                }
                if (voikoNappulaLiikkua(valkoisenVuoroSiirtaa, rivi, sarake, rivi + 1, sarake + 1)) {
                    mahdollisetSiirrot.add(new Siirto(rivi, sarake, rivi + 1, sarake + 1));
                }
                if (voikoNappulaLiikkua(valkoisenVuoroSiirtaa, rivi, sarake, rivi - 1, sarake + 1)) {
                    mahdollisetSiirrot.add(new Siirto(rivi, sarake, rivi - 1, sarake + 1));
                }
                if (voikoNappulaLiikkua(valkoisenVuoroSiirtaa, rivi, sarake, rivi + 1, sarake - 1)) {
                    mahdollisetSiirrot.add(new Siirto(rivi, sarake, rivi + 1, sarake - 1));
                }
                if (voikoNappulaLiikkua(valkoisenVuoroSiirtaa, rivi, sarake, rivi - 1, sarake - 1)) {
                    mahdollisetSiirrot.add(new Siirto(rivi, sarake, rivi - 1, sarake - 1));
                }
            }
        }
    }

    /**
     * Lisää listaan mahdolliset hypyt eli sellaiset siirrot, joissa nappula
     * hyppää vastustajan nappulan yli
     *
     * @param valkoisenVuoroSiirtaa Kertoo kumman pelaajan vuorosta on kyse
     * @param mahdollisetSiirrot Lista mahdollisista siirroista
     */
    private void lisaaMahdollisetHypytListaan(boolean valkoisenVuoroSiirtaa, ArrayList<Siirto> mahdollisetSiirrot) {
        for (int rivi = 0; rivi < 8; rivi++) {
            for (int sarake = 0; sarake < 8; sarake++) {
                if (ruudukko[rivi][sarake] == null) {
                    continue;
                }
                if (voikoNappulaHypata(valkoisenVuoroSiirtaa, rivi, sarake, rivi + 1, sarake + 1, rivi + 2, sarake + 2)) {
                    mahdollisetSiirrot.add(new Siirto(rivi, sarake, rivi + 2, sarake + 2));
                }
                if (voikoNappulaHypata(valkoisenVuoroSiirtaa, rivi, sarake, rivi - 1, sarake + 1, rivi - 2, sarake + 2)) {
                    mahdollisetSiirrot.add(new Siirto(rivi, sarake, rivi - 2, sarake + 2));
                }
                if (voikoNappulaHypata(valkoisenVuoroSiirtaa, rivi, sarake, rivi + 1, sarake - 1, rivi + 2, sarake - 2)) {
                    mahdollisetSiirrot.add(new Siirto(rivi, sarake, rivi + 2, sarake - 2));
                }
                if (voikoNappulaHypata(valkoisenVuoroSiirtaa, rivi, sarake, rivi - 1, sarake - 1, rivi - 2, sarake - 2)) {
                    mahdollisetSiirrot.add(new Siirto(rivi, sarake, rivi - 2, sarake - 2));
                }
            }
        }
    }

    /**
     * Metodi toteuttaa annetun siirron eli siirtää nappulan siirron mukaisesti
     * ruudusta toiseen
     *
     * @param siirto Tieto siitä, mitä siirtoa ollaan tekemässä
     */
    public void teeSiirto(Siirto siirto) {
        vaihdaAnnettujenRuutujenNappulat(siirto.getAlkuRivi(), siirto.getAlkuSarake(), siirto.getLoppuRivi(), siirto.getLoppuSarake(), siirto.onkoSiirtoHyppy());
    }

    /**
     * Metodi palauttaa listan sallituista hypyistä. Sitä tarvitaan kun ollaan
     * jo tehty yksi hyppy. Tämän jälkeen mahdollisia siirtoja ovat enää hypyt
     * samalla nappulalla, jota on jo siirretty
     *
     * @param valkoisenVuoroSiirtaa Kertoo kumman pelaajan vuoro on menossa eli
     * kumman pelaajan siirroista on kyse
     * @param rivi kertoo millä rivillä olevan nappulan mahdollisista hypyistä
     * ollaan kiinnostuttu
     * @param sarake keroo missä sarakkeessa olevan nappulan mahdollisista
     * hypyistä ollaan kiinnostuttu
     * @return palauttaa listan vielä mahdollisista hypyistä tai null, jos niitä
     * ei ole
     */
    public Siirto[] getSallitutHypyt(boolean valkoisenVuoroSiirtaa, int rivi, int sarake) {
        ArrayList<Siirto> mahdollisetSiirrot = new ArrayList<>();
        lisaaMahdollisetHypytListaan(valkoisenVuoroSiirtaa, mahdollisetSiirrot);
        if (!mahdollisetSiirrot.isEmpty()) {
            Siirto[] siirrot = new Siirto[mahdollisetSiirrot.size()];
            for (int i = 0; i < mahdollisetSiirrot.size(); i++) {
                siirrot[i] = mahdollisetSiirrot.get(i);
                return siirrot;
            }
        }
        return null;
    }

    /**
     * Metodi toteuttaa nappuloiden siirron pelilaudan tasolla. Nappula
     * poistetaan ruudusta, jossa se oli ja sijoitetaan ruutuun, johon se
     * liikkui. Jos siirto oli hyppy, ylihypätty nappula poistetaan
     *
     * @param alkuRivi Kertoo miltä riviltä lähdettiin liikkeelle
     * @param alkuSarake Kertoo, mistä sarakkeesta lähdettiin liikkeelle
     * @param loppuRivi Kertoo, mille riville liikuttiin
     * @param loppuSarake Kertoo, mihin sarakkeeseen liikuttiin
     * @param onkoSiirtoHyppy Kertoo, oliko siirto hyppy
     */
    private void vaihdaAnnettujenRuutujenNappulat(int alkuRivi, int alkuSarake, int loppuRivi, int loppuSarake, boolean onkoSiirtoHyppy) {
        if (ruudukko[alkuRivi][alkuSarake] == null) {
            throw new IllegalStateException("Yritettiin siirtää nappulaa jota ei ole");
        }
        
        this.ruudukko[loppuRivi][loppuSarake] = ruudukko[alkuRivi][alkuSarake];
        this.ruudukko[alkuRivi][alkuSarake] = null;

        if (onkoSiirtoHyppy) {
            int poistettavanRivi = (alkuRivi + loppuRivi) / 2;
            int poistettavanSarake = (alkuSarake + loppuSarake) / 2;
            this.ruudukko[poistettavanRivi][poistettavanSarake] = null;
        }

        if (loppuRivi == 0 && this.ruudukko[loppuRivi][loppuSarake].equals(Nappula.VALKOINEN)) {
            this.ruudukko[loppuRivi][loppuSarake] = Nappula.KRUUNATTU_VALKOINEN;
        }
        if (loppuRivi == 7 && this.ruudukko[loppuRivi][loppuSarake].equals(Nappula.MUSTA)) {
            this.ruudukko[loppuRivi][loppuSarake] = Nappula.KRUUNATTU_MUSTA;
        }
    }

    /**
     * Metodin avulla tutkitaan, voiko annetussa ruudussa sijaitseva nappula
     * tehdä hyppyä
     *
     * @param valkoisenVuoroSiirtaa kertoo, kumman pelaajan vuoro on menossa
     * @param r1 Kertoo aloitusrivin, jota tutkitaan
     * @param s1 Kertoo aloitussarakkeen jota tutkitaan
     * @param r2 Kertoo rivin, jonka yli ollaan hyppäämässä
     * @param s2 Kertoo sarakkeen, jonka yli ollaan hyppäämässä
     * @param r3 Kertoo rivin jolle yritetään hypätä
     * @param s3 Kertoo sarakkeen, jolle yritetään hypätä
     * @return Palauttaa tiedon siitä, oli annettu hyppy mahdollinen
     */
    public boolean voikoNappulaHypata(boolean valkoisenVuoroSiirtaa, int r1, int s1, int r2, int s2, int r3, int s3) {
        if (r3 < 0 || r3 >= 8 || s3 < 0 || s3 >= 8) {
            return false;
        }

        if (ruudukko[r1][s1] == null) {
            return false;
        }
        if (ruudukko[r2][s2] == null) {
            return false;
        }

        if (ruudukko[r3][s3] != null) {
            return false;
        }
        if (valkoisenVuoroSiirtaa) {
            if (ruudukko[r1][s1].equals(Nappula.KRUUNATTU_MUSTA) || ruudukko[r1][s1].equals(Nappula.MUSTA)) {
                return false;
            }
            if (ruudukko[r1][s1].equals(Nappula.VALKOINEN) && r2 >= r1) {
                return false;
            }
            return ruudukko[r2][s2].equals(Nappula.MUSTA) || ruudukko[r2][s2].equals(Nappula.KRUUNATTU_MUSTA);
        } else if (!valkoisenVuoroSiirtaa) {
            if (ruudukko[r1][s1].equals(Nappula.KRUUNATTU_VALKOINEN) || ruudukko[r1][s1].equals(Nappula.VALKOINEN)) {
                return false;
            }
            if (ruudukko[r1][s1].equals(Nappula.MUSTA) && r2 <= r1) {
                return false;
            }
            return ruudukko[r2][s2].equals(Nappula.VALKOINEN) || ruudukko[r2][s2].equals(Nappula.KRUUNATTU_VALKOINEN);
        }
        return false;
    }

    /**
     * Metodin avulla tutkitaan, voiko annetussa ruudussa sijaitseva nappula
     * liikkua annettuun ruutuun
     *
     * @param valkoisenVuoroSiirtaa Kertoo, kumman pelaajan vuoro on siirtää
     * @param r1 Kertoo aloitusrivin
     * @param s1 Kertoo aloitussarakkeen
     * @param r2 Keroo rivin, jolle ollaan siirtymässä
     * @param s2 Kertoo sarakkeen, jolle ollaan siirtymässä
     * @return
     */
    public boolean voikoNappulaLiikkua(boolean valkoisenVuoroSiirtaa, int r1, int s1, int r2, int s2) {
        if (r2 < 0 || r2 >= 8 || s2 < 0 || s2 >= 8) {
            return false;
        }
        if (ruudukko[r1][s1] == null) {
            return false;
        }
        if (ruudukko[r2][s2] != null) {
            return false;
        }
        if (valkoisenVuoroSiirtaa) {
            if (ruudukko[r1][s1].equals(Nappula.KRUUNATTU_MUSTA) || ruudukko[r1][s1].equals(Nappula.MUSTA)) {
                return false;
            }
            if (ruudukko[r1][s1].equals(Nappula.VALKOINEN) && r2 >= r1) {
                return false;
            }
        } else if (!valkoisenVuoroSiirtaa) {
            if (ruudukko[r1][s1].equals(Nappula.KRUUNATTU_VALKOINEN) || ruudukko[r1][s1].equals(Nappula.VALKOINEN)) {
                return false;
            }
            if (ruudukko[r1][s1].equals(Nappula.MUSTA) && r2 <= r1) {
                return false;
            }
        }
        return true;
    }
}
