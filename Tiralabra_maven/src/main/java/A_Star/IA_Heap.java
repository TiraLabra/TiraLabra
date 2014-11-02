package A_Star;

/**
 *
 * @author Lutikka
 */
public interface IA_Heap {

    public void insert(Object o);

    public Object peakMin();

    public Object poolMin();
    
    public void remove(Object o);
}
