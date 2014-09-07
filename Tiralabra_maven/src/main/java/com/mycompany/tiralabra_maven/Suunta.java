package com.mycompany.tiralabra_maven;

/**
 * Suuntaa kuvaava enum. Suunta voi olla YLOS, ALAS, OIKEA, VASEN, YLAOIKEA,
 * YLAVASEN, ALAOIKEA tai ALAVASEN.
 *
 * @author mikko
 */
public enum Suunta {

    YLOS, ALAS, OIKEA, VASEN, YLAOIKEA, YLAVASEN, ALAOIKEA, ALAVASEN;

    private static final Suunta[] kohtisuoratSuunnat;

    static {
        kohtisuoratSuunnat = new Suunta[]{Suunta.YLOS, Suunta.ALAS, Suunta.OIKEA, Suunta.VASEN};
    }

    /**
     * Palauttaa kohtisuorat suunnat. Siis, YLOS, ALAS, OIKEA ja VASEN.
     * @return kohtisuorat
     */
    public static Suunta[] kohtisuoratSuunnat() {
        return kohtisuoratSuunnat;
    }

}
