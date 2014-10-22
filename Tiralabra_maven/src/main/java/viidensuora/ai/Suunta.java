package viidensuora.ai;

/**
 * Erilaisia suuntia kuvaava enum. DeltaX ja DeltaY kertovat, mihin suuntaan
 * koordinaatistossa edetään.
 *
 * @author juha
 */
public enum Suunta {

    VAAKA(1, 0),
    PYSTY(0, 1),
    DIAG1(1, 1),
    DIAG2(1, -1),
    VASEN(-1, 0),
    OIKEA(1, 0),
    YLOS(0, -1),
    ALAS(0, 1),
    YLAVASEN(-1, -1),
    YLAOIKEA(1, -1),
    ALAVASEN(-1, 1),
    ALAOIKEA(1, 1);

    /**
     * Muutos x-akselilla.
     */
    public final int deltaX;

    /**
     * Muutos y-akselilla.
     */
    public final int deltaY;

    private Suunta(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
}
