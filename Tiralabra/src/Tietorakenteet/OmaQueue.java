
package Tietorakenteet;

/**
 * Oma rajapinta jonoille josta on karsittu omalta kannalta turhaa asiaa
 */
public interface OmaQueue<T> {
    
    /**
     * Metodi joka palauttaa jonon koon.
     * @return Jonon koko
     */
    public int size();
    /**
     * Metodi joka kertoo onko jono tyhjä
     * @return Boolean joka kertoo onko jono tyhjä
     */
    public boolean isEmpty();

    /**
     * Metodi joka lisää jonoon annetun alkion
     * @param e Lisättävä alkio
     * @return Palauttaa aina true
     */
    public boolean push(T e);
    
    /**
     * Metodi joka palauttaa ja poistaa jonon päällimmäisen alkion.
     * @return Päällimmäisenä ollut alkio
     */
    
    public T pop();

}
