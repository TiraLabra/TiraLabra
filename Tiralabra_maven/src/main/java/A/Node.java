package A;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the INode interface with added methods
 * addEdge(), getPosition().
 * 
 * This Class represents a node in a graph.
 * @author Lutikka
 */
public class Node implements INode {
    
    /**
     * edges
     */
    private List<IEdge> edges;
    /**
     * data associated with this node.
     * used by a star
     */
    private NodeData data;
    /**
     * position used by heuristic
     */
    private int[] position;

    public Node(List<IEdge> edges) {
        this.edges = edges;
        this.data=null;
    }
    
    public Node() {
        this.data=null;
        edges = new LinkedList<IEdge>();
    }
    /**
     * returns the position of this node.
     * used by heuristic
     * @return 
     */
    public int[] getPosition(){
        return position;
    }
    /**
     * sets the position for this node.
     * position is used by heuristic. 
     * @param position 
     */
    public void setPosition(int[] position){
        this.position = position;
    }
    /**
     * adds given edge to this node's edges
     * used for easily adding edges to this node
     * @param edge 
     */
    public void addEdge(IEdge edge){
        edges.add(edge);
    }

    public List<IEdge> getNeighbors() {
        return edges;
    }

    public NodeData createNodeData(){
        data = new NodeData();
        return data;
    }
    public NodeData getNodeData() {
        return data;
    }
    
}
