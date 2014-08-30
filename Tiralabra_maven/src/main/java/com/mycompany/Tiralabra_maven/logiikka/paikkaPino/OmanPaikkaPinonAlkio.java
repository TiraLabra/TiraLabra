package com.mycompany.Tiralabra_maven.logiikka.paikkaPino;

import com.mycompany.Tiralabra_maven.logiikka.Paikka;

/**
 *
 * @author Hannu
 */
public class OmanPaikkaPinonAlkio {
    
    public Paikka paikka;
    
    public OmanPaikkaPinonAlkio seuraava;
    
    public OmanPaikkaPinonAlkio(Paikka paikka, OmanPaikkaPinonAlkio seuraava){
        this.paikka=paikka;
        this.seuraava=seuraava;
    }
    
    
    
}
