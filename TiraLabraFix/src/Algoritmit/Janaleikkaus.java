/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Kordinaatti;

/**
 *
 *
 *
 */
public class Janaleikkaus {

    /**
     *
     * Palauttaa true vain jos janoilla on vain yksi leikkauspiste, muuten
     * false.
     *
     * @param piste1
     * @param piste2
     * @param piste3
     * @param piste4
     */
    public boolean leikkaako(Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4) {
        double u0x = piste1.palautaX();
        double u0y = piste1.palautaY();
        double v0x = piste1.palautaX() - piste2.palautaX();
        double v0y = piste1.palautaY() - piste2.palautaY();
        double t = 0;

        double u1x = piste3.palautaX();
        double u1y = piste3.palautaY();
        double v1x = piste3.palautaX() - piste4.palautaX();
        double v1y = piste3.palautaY() - piste4.palautaY();
        double s = 0;

        double determinantti = v1x * v0y - v0x * v1y;
        if (determinantti == 0) {
            return false;

        } else {
            s = 1 / determinantti * ((u0x - u1x) * v0y - v0x * (u0y - u1y));
            t = 1 / determinantti * -1 * ((-1) * v1y * (u0x - u1x) + (u0y - u1y) * v1x);
            double ratkaisux = u0x + v0x * t;
            double ratkaisuy = u0y + v0y * t;
            double MaxX1 = Math.max(piste1.palautaX(), piste2.palautaX());
            double MinX1 = Math.min(piste1.palautaX(), piste2.palautaX());
            double MaxY1 = Math.max(piste1.palautaY(), piste2.palautaY());
            double MinY1 = Math.min(piste1.palautaY(), piste2.palautaY());
            double MaxX2 = Math.max(piste3.palautaX(), piste4.palautaX());
            double MinX2 = Math.min(piste3.palautaX(), piste4.palautaX());
            double MaxY2 = Math.max(piste3.palautaY(), piste4.palautaY());
            double MinY2 = Math.min(piste3.palautaY(), piste4.palautaY());
            if ((ratkaisux <= MaxX1) && (ratkaisux <= MaxX2) && (ratkaisux >= MinX1) && (ratkaisux >= MinX2) && (ratkaisuy <= MaxY1) && (ratkaisuy <= MaxY2) && (ratkaisuy >= MinY1) && (ratkaisuy >= MinY2)) {
                return true;
            }
            return false;

        }
    }
}
