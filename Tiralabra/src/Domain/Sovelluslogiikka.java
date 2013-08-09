/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import UI.Kartanpiirtaja;

/**
 *
 * @author Emleri
 */
public class Sovelluslogiikka {
    private Hirvio[] hirviot;
    private Luola luola;
    private Kartanpiirtaja piirtaja;
    private Polunetsija polunetsija;
    

    public Sovelluslogiikka() {
        this.hirviot = new Hirvio[1];
        this.hirviot[0] = new Hirvio(new Koordinaatit(0, 0));
        this.luola = new Luola(5,5);
        this.piirtaja = new Kartanpiirtaja(luola);
        this.luola.lisaaObjekti(hirviot[0]);
    }
    
    public void eteneAskel() {
        //Laske polut hirviöille
        for(int i = 0; i < hirviot.length; i++) {
            hirviot[i].setPolku(this.polunetsija.etsi(hirviot[i].getKoordinaatit(), luola.getKohde(), luola));
        }
        
        //Siirrä hirviöitä polkua pitkin
        
        piirtaja.piirra();
    }
}
