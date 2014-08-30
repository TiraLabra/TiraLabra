package com.mycompany.Tiralabra_maven.logiikka.paikkaPino;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 *
 * @author Hannu
 */
public class OmaPinoAlkionaPaikka implements PinoAlkionaPaikka {

    private OmanPaikkaPinonAlkio top;

    public OmaPinoAlkionaPaikka() {
        this.top = null;
    }

    @Override
    public void stackPush(Paikka paikka) {
        OmanPaikkaPinonAlkio uusiAlkio = new OmanPaikkaPinonAlkio(paikka, this.top);
        this.top = uusiAlkio;
    }

    @Override
    public Paikka stackPop() {
        OmanPaikkaPinonAlkio poistettavaAlkio = this.top;
        this.top = poistettavaAlkio.seuraava;
        return poistettavaAlkio.paikka;
    }

    @Override
    public boolean stackIsEmpty() {
        return (this.top == null);
    }
}
