/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.logiikka;

import java.util.ArrayList;

/**
 * Tämä luokka edustaa itse labyrintin lyhimmän reitin etsimiseen käytettävää mekanismia.
 * Luokalla on maapalarekisteri käytössään sekä kaksi listaa, openList ja closedList.
 * käsittelemättömän ja reitin välittömässä läheisyydessä olevat nodet (maapalat) ovat openListissä. 
 * Kun maapala on käsitelty, siirtyy se closedListiin, josta tiedetään, että sitä ei tarvitse enää käsitellä uudestaa.
 * 
 * @author Mikael Parvamo
 */
public class LyhinReitti {

    private Maapalarekisteri maapalaRekisteri;
    private int alkuX;
    private int alkuY;
    private int loppuX;
    private int loppuY;
    private Maapala[][] labyrintti;
    private ArrayList<Maapala> openList;
    private ArrayList<Maapala> closedList;

    public LyhinReitti(Maapalarekisteri maapalaRekisteri) {
        this.maapalaRekisteri = maapalaRekisteri;
        this.alkuX = maapalaRekisteri.getAlkuX();
        this.alkuY = maapalaRekisteri.getAlkuY();
        this.loppuX = maapalaRekisteri.getLoppuX();
        this.loppuY = maapalaRekisteri.getLoppuY();
        this.labyrintti = maapalaRekisteri.getLabyrintti();
    }
    
     /**
     * Tämä metodi palauttaa haetun maapalan parhaan naapurin. Eli viereisen maapalan,
     * jolla on pienin heuristinen arvo. Tämän avulla saadaan selville, mikä olisi reitin
     * kannalta paras mahdollinen siirto. Metodi tarkistaa vertikaaliset sekä horisontaaliset naapurit.
     * 
     * @param Maapala maapala, tarkasteltava maapala.
     * @return parasNaapuri, eli maapala jolla on pienin heuristinen arvo kaikista haetun maapalan naapureista.
     */

    public Maapala getParasNaapuri(Maapala maapala) {
        int pieninHValue = 2000;
        Maapala parasNaapuri = maapala;
        int x = parasNaapuri.getX();
        int y = parasNaapuri.getY();

        if(!this.labyrintti[x][y-1].onkoSeina()){
            pieninHValue = this.labyrintti[x][y-1].getHValue();
            parasNaapuri = this.labyrintti[x][y-1];
        }
        
        if(this.labyrintti[x+1][y].getHValue() < pieninHValue && !this.labyrintti[x+1][y].onkoSeina()){
            pieninHValue = this.labyrintti[x+1][y].getHValue();
            parasNaapuri = this.labyrintti[x+1][y];
        }
        if(this.labyrintti[x][y+1].getHValue() < pieninHValue && !this.labyrintti[x][y+1].onkoSeina()){
            pieninHValue = this.labyrintti[x][y+1].getHValue();
            parasNaapuri = this.labyrintti[x][y+1];
        }
        if(this.labyrintti[x-1][y].getHValue() < pieninHValue && !this.labyrintti[x-1][y].onkoSeina()){
            pieninHValue = this.labyrintti[x-1][y].getHValue();
            parasNaapuri = this.labyrintti[x-1][y];
        }
        
        return parasNaapuri;
    }
}
