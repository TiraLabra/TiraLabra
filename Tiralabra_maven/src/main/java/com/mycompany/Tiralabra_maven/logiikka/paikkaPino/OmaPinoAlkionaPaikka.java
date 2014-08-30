package com.mycompany.Tiralabra_maven.logiikka.paikkaPino;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 * Pinon oma toteutus linkitettynälistana. Pinon alkiot tyyppiä Paikka.
 */
public class OmaPinoAlkionaPaikka implements PinoAlkionaPaikka {

    private OmanPaikkaPinonAlkio top;

    public OmaPinoAlkionaPaikka() {
        this.top = null;
    }

    /**
     * Pinoon lisätään pinoalkio.
     *
     * @param paikka lisättävä pinoalkio
     */
    @Override
    public void stackPush(Paikka paikka) {
        OmanPaikkaPinonAlkio uusiAlkio = new OmanPaikkaPinonAlkio(paikka, this.top);
        this.top = uusiAlkio;
    }

    /**
     * Pinosta poistetaan päällimmäisenä oleva pinoalkio.
     *
     * @return pinosta poistettu päällimmäisenä ollut pinoalkio
     */
    @Override
    public Paikka stackPop() {
        OmanPaikkaPinonAlkio poistettavaAlkio = this.top;
        this.top = poistettavaAlkio.seuraava;
        return poistettavaAlkio.paikka;
    }

    /**
     * Tarkastaa onko pino tyhjä.
     *
     * @return palautetaan true, jos pino on tyhjä
     */
    @Override
    public boolean stackIsEmpty() {
        return (this.top == null);
    }
}
