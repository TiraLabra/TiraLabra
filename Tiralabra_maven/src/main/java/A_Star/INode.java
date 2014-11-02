package A_Star;

/**
 * A position in map
 * @author Lutikka
 */
public interface INode {
    
    /**
     * Returns the position of this node
     * @return 
     */
    public IPosition getPos();
    
    /**
     * Returns this nodes neighbors
     * @return 
     */
    public INode[] getNeighbors();
}
