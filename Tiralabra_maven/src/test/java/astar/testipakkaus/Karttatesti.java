/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.testipakkaus;

import astar.tietorakenteet.Lista;
import astar.verkko.Kartta;
import astar.verkko.Solmu;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sasumaki
 */
public class Karttatesti {

    Kartta kartta;

    public Karttatesti() {
        this.kartta = new Kartta(10, 10);
        

    }

    @Test
    public void karttaNaapuriTestiKulmassa() {
        Lista<Solmu> napsut;

        napsut = kartta.naapurit(0, 0, null, 0);

        assertEquals(napsut.size(), 2);

    }

    @Test
    public void karttaNaapuriTestiKeskella() {
        Lista<Solmu> napsut;

        napsut = kartta.naapurit(5, 5, null, 0);

        assertEquals(napsut.size(), 4);

    }

    @Test
    public void karttaNaapuriKoordinaattiTesti() {
        Lista<Solmu> napsut;
        Solmu s = new Solmu(5, 5, null, 0);
        napsut = kartta.naapurit(s.getX(), s.getY(), s.getEdellinen(), 0);

        assertEquals(napsut.get(0).getX(), 5);
        assertEquals(napsut.get(0).getY(), 4);

    }
    

}
