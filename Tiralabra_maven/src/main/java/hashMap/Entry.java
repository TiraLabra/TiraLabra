package hashMap;

/**
 * 
 * @param <A> Avaimen tyyppi
 * @param <B> Datan tyyppi
 * @author Ilkimys
 */
public class Entry<A, B> {
    private final A avain;
    private Entry<A,B> seuraava;
    private B data;

    /**
     * Asettaa Entrylle avaimen ja siihen liittyvän datan
     * @param avain
     * @param data
     */
    public Entry(A avain, B data) {
        this.avain = avain;
        this.data = data;
    }
    
    
    public A getAvain() {
        return avain;
    }

    /**
     * Palauttaa datan
     * @return
     */
    public B getData() {
        return data;
    }

    /**
     * ASettaa ylivuotolistaan seuraavan
     * @param seuraava Entry<A,B>
     */
    public void setSeuraava(Entry<A, B> seuraava) {
        this.seuraava = seuraava;
    }

    /**
     * Asettaa uuden arvon datalle
     * @param data
     */
    public void setData(B data) {
        this.data = data;
    }
    
    
    
    /**
     * Palauttaa ylivuotolistasta seuraavan
     * @return 
     */
    public Entry<A,B> haeSeuraava() {
        return seuraava;
    }
    
    
    
}
