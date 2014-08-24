/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algoritmit;

import Tietorakenteet.Kordinaatti;
import Tietorakenteet.Monikulmio;

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
     *
     * @param piste1 Ensimmäisen janan päätepiste
     * @param piste2 Ensimmäisen janan toinen päätepiste
     * @param piste3 Toisen janan päätepiste
     * @param piste4 Toisen janan toinen päätepiste
     * @return boolean Palauttaa leikkaako janat jotka muodostuvat kyseista pistejoukosta
     */
    public boolean leikkaako(Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4) {

        Kordinaatti testi = suoraLeikkaus(piste1, piste2, piste3, piste4);
        if (testi == null) {
            return false;
        }
        double ratkaisux = testi.palautaX();
        double ratkaisuy = testi.palautaY();
        
        if ((testi == piste1) || (testi == piste2) || (testi == piste3) || (testi == piste4)) {
            return false;

        }

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
    /**
 *
 * Konkreettinen luokka, joka toteuttaa keon alkion ehdot.
 */

    /**
     * Löytää kahden pisteen määräämien suorien leikkauspisteen (Jos sellainen on olemassa)
    * @param piste1 Ensimmäisen janan päätepiste
     * @param piste2 Ensimmäisen janan toinen päätepiste
     * @param piste3 Toisen janan päätepiste
     * @param piste4 Toisen janan toinen päätepiste
     */
    public Kordinaatti suoraLeikkaus(Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4) {
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
            return null;

        } else {
            s = 1 / determinantti * ((u0x - u1x) * v0y - v0x * (u0y - u1y));
            t = 1 / determinantti * -1 * ((-1) * v1y * (u0x - u1x) + (u0y - u1y) * v1x);
            double ratkaisux = u0x + v0x * t;
            double ratkaisuy = u0y + v0y * t;
            Kordinaatti palautus = new Kordinaatti(ratkaisux, ratkaisuy);
            return palautus;
        }

    }
    
     /**
     * Löytää suoran ja janan leikkauspisteen
    * @param piste1 Ensimmäisen janan päätepiste
     * @param piste2 Ensimmäisen janan toinen päätepiste
     * @param piste3 Toisen janan päätepiste
     * @param piste4 Toisen janan toinen päätepiste
     * @return Kordinaatti leikkauspiste, jos sellaista ei ole palauttaa null
     */
    
    public Kordinaatti suoranjaJananleikkaus(Kordinaatti piste1, Kordinaatti piste2, Kordinaatti piste3, Kordinaatti piste4)
    {
    Kordinaatti testi = suoraLeikkaus(piste1, piste2, piste3, piste4);
        if (testi == null) {
            return null;
        }
        double ratkaisux = testi.palautaX();
        double ratkaisuy = testi.palautaY();
        
        double MaxX1 = Math.max(piste1.palautaX(), piste2.palautaX());
        double MinX1 = Math.min(piste1.palautaX(), piste2.palautaX());
        double MaxY1 = Math.max(piste1.palautaY(), piste2.palautaY());
        double MinY1 = Math.min(piste1.palautaY(), piste2.palautaY());
         if ((ratkaisux <= MaxX1) && (ratkaisux >= MinX1) && (ratkaisuy <= MaxY1) && (ratkaisuy >= MinY1) ) {
            return testi;
        }
        return null;

    }

    public boolean nakeeko(Kordinaatti k, Kordinaatti k2, Monikulmio a) {
        Kordinaatti[][] janat = a.PalautaJanat();
        
        return true;
    }

}
