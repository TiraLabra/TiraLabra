/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 *
 * @author Serafim
 */
public class SolmuMuisti {

    private int vari;
    private Abstraktisolmu edellinen;
    private double fscore;
    private double gscore;

    public SolmuMuisti() {

    }

    public void Varita(int i) {
        this.vari = i;
    }

    public int palautaVari() {
        return this.vari;
    }

    public void asetaEdellinen(Abstraktisolmu solmu) {
        this.edellinen = solmu;
    }

    public Abstraktisolmu palautaEdellinen() {
        return this.edellinen;
    }

    public void asetaFScore(double fscore) {
        this.fscore = fscore;
    }

    public double palautaFScore() {
        return this.fscore;
    }

    public void asetaGScore(double gscore) {
        this.gscore = gscore;
    }

    public double palautaGScore() {
        return this.gscore;
    }

}
