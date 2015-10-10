package DataStructures;

import Terrain.CartesianTile;

/**
 *
 * @author Leevi
 */
public class Node {

    public int x;
    public int y;
    public CartesianTile type;
    
    /**
     * Node represents a single point in a 2d map.
     *
     * @param x
     * @param y
     * @param type
     */
    public Node(int x, int y, CartesianTile type) {
        
        this.x = x;
        this.y = y;
        this.type = type;
        
    }
    
    /**
     *
     * @return int, representing the cost of movement.
     */
    public int getMovementCost() {
        
        return CartesianTile.getMovementCostFromNodeName(type);
        
    }

}
