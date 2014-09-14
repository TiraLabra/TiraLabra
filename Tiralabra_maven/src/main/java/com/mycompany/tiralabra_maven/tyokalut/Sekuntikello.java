package com.mycompany.tiralabra_maven.tyokalut;

/**
 * Ajanottajana toimiva luokka. Tarkistusten määrä minimissä jottei niihin
 * kuluva aika vaikuta tuloksiin.
 *
 * @author Markus
 */
public class Sekuntikello {

    private long alkuaika;

    public Sekuntikello() {
        alkuaika = 0L;
    }

    /**
     * Ottaa aloitusajan talteen.
     */
    public void aloita() {
        alkuaika = System.nanoTime();
    }

    /**
     * Palauttaa aloita ja lopeta metodien kutsujen välisen ajan
     *
     * @return aloita metodin kutsusta kulunut aika. Palauttaa tuntemattomasta
     * pisteestä kuluneen ajan mikäli aloita metodia ei ole kutsuttu kertaakaan.
     */
    public long lopeta() {
        return System.nanoTime() - alkuaika;
    }
}
