package A;

import java.util.List;

/**
 * A Node in graph
 * @author Lutikka
 */
public interface INode {
        
    /**
     * Returns this node's neighbors
     * @return 
     */
    public List<IEdge> getNeighbors();
    
    /**
     * returns data associated with this node
     * @return 
     */
    public NodeData getNodeData();
    
    /**
     * Creates new NodeData associated with this node and returns it 
     */
    public NodeData createNodeData();
    
}
