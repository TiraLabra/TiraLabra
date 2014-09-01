package com.mycompany.Tiralabra_maven.logiikka.paikkaPino;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;
import java.util.Stack;

/**
 * Pino toteutettuna Javan valmiilla tietorakenteella Stack. Pinon
 * alkiot tyyppiä Paikka.
 */
public class StackPinoAlkionaPaikka implements PinoAlkionaPaikka {

    private Stack<Paikka> pino;

    public StackPinoAlkionaPaikka() {
        this.pino = new Stack<Paikka>();
    }

    /**
     * Pinoon lisätään pinoalkio.
     *
     * @param paikka lisättävä pinoalkio
     */
    @Override
    public void stackPush(Paikka paikka) {
        this.pino.push(paikka);
    }

    /**
     * Pinosta poistetaan päällimmäisenä oleva pinoalkio.
     *
     * @return pinosta poistettu päällimmäisenä ollut pinoalkio
     */
    @Override
    public Paikka stackPop() {
        return this.pino.pop();
    }

    /**
     * Tarkastaa onko pino tyhjä.
     *
     * @return palautetaan true, jos pino on tyhjä
     */
    @Override
    public boolean stackIsEmpty() {
        return this.pino.isEmpty();
    }
}
