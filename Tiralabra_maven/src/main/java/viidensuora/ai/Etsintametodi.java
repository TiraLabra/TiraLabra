package viidensuora.ai;

/**
 * Rajapinta Tekoälyn käyttämille Etsintämetodeille.
 *
 * @author juha
 */
public interface Etsintametodi {

    /**
     * Etsii parhaan ristin siirron.
     *
     * @param syvyys Syvyys, jolta pelipuusta etsitään.
     * @return Parhaan siirron Hakutulos
     */
    public Hakutulos etsiRistinSiirto(int syvyys);

    /**
     * Etsii parhaan nollan siirron.
     *
     * @param syvyys Syvyys, jolta pelipuusta etsitään.
     * @return Parhaan siirron Hakutulos
     */
    public Hakutulos etsiNollanSiirto(int syvyys);
}
