/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet.Jono;


/**
 *
 * @author Serafim
 */
public class Jonoiteroitava {

    private Jonoiteroitava edellinen;
    private Jonoiteroitava seuraava;
    private boolean jonossa;
    private Object objekti;

    public Jonoiteroitava(Object objektir) {
        this.objekti = objektir;
    }

    public boolean onkoJonossa() {
        return jonossa;
    }

    public void asetaJonossa(boolean k) {
        this.jonossa = k;
    }

    public Jonoiteroitava palautaSeuraava() {
        return this.seuraava;
    }

    public Jonoiteroitava palauataEdellinen() {
        return this.edellinen;
    }

    public void asetaSeuraava(Jonoiteroitava seuraava) {
        this.seuraava = seuraava;
    }

    public void asetaEdellinen(Jonoiteroitava edellinen) {
        this.edellinen = edellinen;
    }

    public void clear() {
        this.edellinen = null;
        this.seuraava = null;
        this.jonossa = false;
        
    }
    public void asetaObjekti(Object objektir)
    {
    this.objekti = objektir;
    }
    public Object palautaObjekti()
    {
    return this.objekti;
    }

}
