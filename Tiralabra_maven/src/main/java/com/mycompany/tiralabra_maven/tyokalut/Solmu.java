package com.mycompany.tiralabra_maven.tyokalut;

/**
 *
 * @author Markus
 */
public class Solmu {

    int[] data;
    Solmu seuraava;

    public Solmu(int[] data) {
        this.data = data;
    }

    public int[] getData() {
        return data;
    }

    public Solmu getSeuraava() {
        return seuraava;
    }

    public void setSeuraava(Solmu seuraava) {
        this.seuraava = seuraava;
    }

}

