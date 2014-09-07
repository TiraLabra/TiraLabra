package com.mycompany.tiralabra_maven.AI;

import com.mycompany.tiralabra_maven.peli.Peli;
import com.mycompany.tiralabra_maven.peli.PeliOhjain;
import com.mycompany.tiralabra_maven.peli.Siirto;

/**
 * Luokka toimii yksinkertaisena tekoälynä, joka palauttaa aina mahdollisten siirtojen listalta ensimmäisen siirron
 * @author noora
 */
public class EkaAI extends AI {
    
    public EkaAI(Peli peli, PeliOhjain peliohjain, boolean siirraAutomaagisesti, int viive){
        super(peli, peliohjain, siirraAutomaagisesti, viive);
    }
    

    /**
     * Metodi palauttaa tekoälyn seuraavan siirron, joka tässä tapauksessa on aina mahdollisten siirtojen listan ensimmäinen siirto.
     * Tarvittaessa ennen siirtoa odotetaan käyttäjän määrittelemä aika.
     * @return Palauttaa seuraavan siirron
     */
    @Override
    public Siirto seuraavaSiirto(Siirto[] sallitutSiirrot) {
        odota();
        return sallitutSiirrot[0];
    }
    
}
