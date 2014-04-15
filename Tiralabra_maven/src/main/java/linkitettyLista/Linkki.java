package linkitettyLista;


/**
 * Listan solmu
 * @param <T> Datan tyyppi
 * @author Ilkimys
 */
public class Linkki<T> {
    
    private Linkki<T> seuraava;
    private Linkki<T> edellinen;
    private T data;

    /**
     * Luodaan uusi solmu ja lisätään siihen haluttu data
     * @param data
     */
    public Linkki(T data) {
        this.data = data;
    }
    
    
    /**
     * Palauttaa solmusta seuraavan solmun
     * @return
     */
    public Linkki<T> getSeuraava() {
        return seuraava;
    }

    /**
     *
     * @return
     */
    public Linkki<T> getEdellinen() {
        return edellinen;
    }
    

    /**
     * Palauttaa solmun datan
     * @return
     */
    public T getData() {
        return data;
    }
    
    
    
    /**
     * Asettaa solmulle seuraavan solmun
     * @param l
     */
    public void setSeuraava(Linkki<T> l) {
        seuraava = l;
    }

    /**
     *
     * @param edellinen
     */
    public void setEdellinen(Linkki<T> edellinen) {
        this.edellinen = edellinen;
    }
    
    


    
    
    
    
    
}
