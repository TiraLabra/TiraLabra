package lista;

/**
 *
 * @author Ilkimys
 */
public class Linkki {
    private Linkki seuraava;
    private Object data;

    public Linkki(Object data) {
        this.data = data;
    }
    
    
    public Linkki getSeuraava() {
        return seuraava;
    }
    
    public void setSeuraava(Linkki l) {
        seuraava = l;
    }
    
    
    
    
    
}
