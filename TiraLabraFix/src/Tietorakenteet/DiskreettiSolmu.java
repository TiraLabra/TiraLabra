/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 *
 * Konkreettinen luokka, joka toteuttaa keon alkion ehdot.
 */
public class DiskreettiSolmu implements Iteroitava, Abstraktisolmu {

    private Kordinaatti kordinaatti;
    private int sijainti;
    private Verkko verkko;
    private SolmuMuisti muisti;
    private boolean kulku;

    /**
     *
     * Asettaa Solmulle x ja y kordinaatin
     *
     * @param x x kordinaatti
     * @param y y kordinaatti
     */
    public DiskreettiSolmu(Kordinaatti kordinaatti) {
        this.kordinaatti = kordinaatti;
        this.muisti = new SolmuMuisti();
    }

    public DiskreettiSolmu(double x, double y) {
        this.kordinaatti = new Kordinaatti(x, y);
        this.muisti = new SolmuMuisti();
        this.muisti.Varita(0);
        this.kulku = true;
    }

    /**
     *
     * Palauttaa x kordinaatin
     *
     * @return double X kordinaatti
     */
    public double palautaX() {
        return this.kordinaatti.palautaX();
    }

    /**
     *
     * Palauttaa y kordinaatin
     *
     * @return double y kordinaatti
     */
    public double palautaY() {
        return this.kordinaatti.palautaY();
    }

    /**
     *
     * Palauttaa alkion arvon
     *
     * @return double alkionarvo
     */
    /**
     *
     * Palauttaa sijainnin keossa
     *
     * @return sijainti keossa
     */
    @Override
    public int sijaintiKeossa() {
        return this.sijainti;
    }

    /**
     *
     * Asettaa alkiolle sijainnin keossa
     */
    @Override
    public void asetaSijainti(int i) {
        this.sijainti = i;
    }

    /**
     *
     * Asettaa alkiolle arvon
     *
     * @param double asetettava arvo
     */
    @Override
    public void asetaArvo(double d) {
        this.muisti.asetaFScore(d);
    }

    /**
     *
     * Asettaa verkon
     *
     * @param Verkko asetettava verkko
     */
    @Override
    public void asteaVerkko(Verkko verkko) {
        this.verkko = verkko;
    }

    /**
     *
     * Palauttaa verkkon johon tämä alkio kuuluu
     *
     * @return Verkko
     *
     */
    @Override
    public Verkko palautaVerkko() {
        return this.verkko;
    }

    /**
     *
     * Palauttaa tämän solmun lisärakenteen solmumuistin
     *
     * @return Solmumuisti
     *
     */

    @Override
    public SolmuMuisti palautaSolmuMuisti() {
        return this.muisti;
    }

    public void asetaKulku(boolean k) {
        this.kulku = k;
    }

    public boolean palautaKulku() {
        return this.kulku;
    }

    public Kordinaatti palautaKordinaatit() {
        return this.kordinaatti;
    }

    @Override
    public int vertausoperaatio(Iteroitava toinen) {
        DiskreettiSolmu d = (DiskreettiSolmu) toinen;
        if (this.palautaSolmuMuisti().palautaFScore() - d.palautaSolmuMuisti().palautaFScore() > 0) {
            return 1;
        }
        if (this.palautaSolmuMuisti().palautaFScore() == d.palautaSolmuMuisti().palautaFScore()) {
            return 0;
        } else {
            return -1;
        }
    }

}
