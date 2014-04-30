package minimiKeko;

/**
 *
 * @param <D> 
 * @author Ilkimys
 */
public class Solmu<D> {
    
    private int key;
    private D data;

    /**
     *
     * @param key
     * @param data
     */
    public Solmu(int key, D data) {
        this.key = key;
        this.data = data;
    }

    public D getData() {
        return data;
    }

    public int getKey() {
        return key;
    }

    public void setData(D data) {
        this.data = data;
    }

    public void setKey(int key) {
        this.key = key;
    }
    
    
    
}
