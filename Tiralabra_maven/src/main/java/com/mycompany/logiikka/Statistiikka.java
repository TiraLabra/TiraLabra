package com.mycompany.logiikka;

import com.mycompany.domain.Kasi;

/**
 * Luokka pitää yllä pelin statistiikkaa:
 * <ul>
 * <li> Pelatut kierrokset
 * <li> Pelaajan voittojen määrä
 * <li> Tasapelien määrä
 * <li> Pelaajan vähiten pelaama käsi
 * <li> Vähiten pelatun käden osuus kaikista pelatuista käsistä
 * </ul>
 * 
 */
public class Statistiikka {

    private int kierrokset;
    private int pelaajanVoitot;
    private int tasapelit;
    private int[] kadet;
    private int moodi;

    /**
     * Konstruktori asettaa luokkamuuttujat
     * <p>
     * Moodit
     * <ul>
     * <li> 1 = Perinteinen Kivi-Paperi-Sakset
     * <li> 2 = Laajennettu Kivi-Paperi-Sakset-Lisko-Spock
     * </ul>
     * 
     * @param moodi Moodi 
     */
    public Statistiikka(int moodi) {
        this.moodi = moodi;
        this.kierrokset = 0;
        this.pelaajanVoitot = 0;
        this.tasapelit = 0;
        this.kadet = new int[5];
        alustaKadet();
    }

    /**
     * Palauttaa tasapelien määrän
     * 
     * @return tasapelien määrä 
     */
    public int getTasapelit() {
        return this.tasapelit;
    }
    
    /**
     * Kasvattaa tasapelien laskuria yhdellä
     * 
     * @see #getTasapelit() 
     */
    public void asetaTasapeli() {
        this.tasapelit++;
    }
    
    /**
     * Sisäinen metodi. Alustaa int[5] listan nollilla. Mikäli vallitseva
     * moodi on 2, asetetaan int[3] ja int[4] Integer.MAX_VALUE
     */
    private void alustaKadet() {
        for (int i = 0; i < 5; i++) {
            this.kadet[i] = 0;
        }
        if (this.moodi == 1) {
            this.kadet[3] = Integer.MAX_VALUE;
            this.kadet[4] = Integer.MAX_VALUE;
        }
    }

    /**
     * Kasvattaa kierrosten määrää yhdellä ja päivittää pelaajan käden
     * int[5]-tyyppiseen muuttujaan.
     * 
     * @see #paivitakadet(com.mycompany.domain.Kasi) 
     * 
     * @param pelaaja Pelaajan käsi
     */
    public void paivitaKierros(Kasi pelaaja) {
        this.kierrokset++;
        paivitakadet(pelaaja);
    }
    
    /**
     * Kasvattaa pelaajan voittojen määrää yhdellä
     */
    public void lisaaPelaajanVoitto() {
        this.pelaajanVoitot++;
    }

    /**
     * Sisäinen metodi kasvattaa int[5] listaa kättä vastaavalla indeksillä
     * 
     * @param kasi Käsi jonka mukaan listaa kasvatetaan 
     */
    private void paivitakadet(Kasi kasi) {
        if (kasi.getNimi().equals("KIVI")) {
            this.kadet[0]++;
        } else if (kasi.getNimi().equals("PAPERI")) {
            this.kadet[1]++;
        } else if (kasi.getNimi().equals("SAKSET")) {
            this.kadet[2]++;
        } else if (kasi.getNimi().equals("LISKO")) {
            this.kadet[3]++;
        } else {
            this.kadet[4]++;
        }
    }

    /**
     * Palauttaa pelaajan vähiten pelaaman käden
     * 
     * @return Vähiten pelattu käsi 
     */
    public Kasi pelaajanVahitenPelattuKasi() {
        int pienin = Integer.MAX_VALUE;
        int kasiIndeksi = -1;
        int moodiIndeksi = -1;

        if (this.moodi == 1) {
            moodiIndeksi = 3;
        } else {
            moodiIndeksi = 5;
        }

        for (int i = 0; i < moodiIndeksi; i++) {
            if (this.kadet[i] < pienin) {
                kasiIndeksi = i;
                pienin = this.kadet[i];
            }
        }

        if (kasiIndeksi == 0) {
            return new Kasi("KIVI");
        } else if (kasiIndeksi == 1) {
            return new Kasi("PAPERI");
        } else if (kasiIndeksi == 2) {
            return new Kasi("SAKSET");
        } else if (kasiIndeksi == 3) {
            return new Kasi("LISKO");
        } else {
            return new Kasi("SPOCK");
        }
    }

    /**
     * Palauttaa pelaajan vähiten pelaaman käden <b>prosenttiosuuden</b>
     * kaikista käsistä.
     * <p>
     * Esim. jos pelaaja on pelannut 10 pelin aikana kerran saksi-kättä,
     *  metodi palauttaa "10", ei 0.1!
     * 
     * @return prosenttiosuus
     */
    public long vahitenPelattuKasiProsentit() {
        int maara = -1;
        Kasi vahiten = pelaajanVahitenPelattuKasi();
        if (vahiten.getNimi().equals("KIVI")) {
            maara = this.kadet[0];
        } else if (vahiten.getNimi().equals("PAPERI")) {
            maara = this.kadet[1];
        } else if (vahiten.getNimi().equals("SAKSET")) {
            maara = this.kadet[2];
        } else if (vahiten.getNimi().equals("LISKO")) {
            maara = this.kadet[3];
        } else {
            maara = this.kadet[4];
        }
        double pros = maara / this.kierrokset;
        return (long) Math.floor(pros + 0.5d);
    }
    
    /**
     * Palauttaa pelattujen kierrosten kokonaismäärän
     * 
     * @return Kierrosten määrä 
     */
    public int getKierrokset() {
        return this.kierrokset;
    }
    
    /**
     * Tulostaa statistiikan kuluneesta pelistä.
     * <p>
     * Tarkoitettu lähinnä CLI:n käyttöön
     * 
     * @return Statistiikka
     */
    @Override
    public String toString() {
        return "Kierroksia : " + this.kierrokset +
                ", pelaajan voittoja: " + this.pelaajanVoitot +
                ", tasapelejä: " + this.tasapelit + "";
    }
}
