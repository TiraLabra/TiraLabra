package com.mycompany.Tiralabra_maven.logiikka.paikkaPino;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 * Luokan OmaPinoAlkionaPaikka toteutuksessa käytetty pinoalkio.
 */
public class OmanPaikkaPinonAlkio {

    public Paikka paikka;
    public OmanPaikkaPinonAlkio seuraava;

    /**
     * Luokan OmanPaikkaPinonAlkio konstruktori.
     *
     * @param paikka Paikka-olio, johon luotava OmanPaikkaPinonAlkio-olio
     * viittaa.
     *
     * @param seuraava OmanPaikkaPinonAlkio-olio, joka on luotavaa
     * OmanPaikkaPinonAlkio-oliota pinossa seuraava (alempi)
     * OmanPaikkaPinonAlkio-olio.
     *
     */
    public OmanPaikkaPinonAlkio(Paikka paikka, OmanPaikkaPinonAlkio seuraava) {
        this.paikka = paikka;
        this.seuraava = seuraava;
    }
}
