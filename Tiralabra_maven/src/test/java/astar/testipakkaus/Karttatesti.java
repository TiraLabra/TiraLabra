/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.testipakkaus;

import astar.astar.Kartta;
import astar.astar.Solmu;
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
        kartta.luoKartta();

    }

    @Test
    public void karttaNaapuriTestiKulmassa() {
        ArrayList<Solmu> napsut;

        napsut = kartta.naapurit(0, 0, null, 0);

        assertEquals(napsut.size(), 2);

    }

    @Test
    public void karttaNaapuriTestiKeskella() {
        ArrayList<Solmu> napsut;

        napsut = kartta.naapurit(5, 5, null, 0);

        assertEquals(napsut.size(), 4);

    }

    @Test
    public void karttaNaapuriKoordinaattiTesti() {
        ArrayList<Solmu> napsut;
        Solmu s = new Solmu(5, 5, null, 0);
        napsut = kartta.naapurit(s.getX(), s.getY(), s.getEdellinen(), 0);

        assertEquals(napsut.get(0).getEdellinen().getX(), s.getX());
        assertEquals(napsut.get(0).getEdellinen().getY(), s.getY());

    }

}
