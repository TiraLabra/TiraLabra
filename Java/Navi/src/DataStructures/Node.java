package DataStructures;

// @author Leevi

import Terrain.CartesianTile;

public class Node {

    public int x;
    public int y;
    public CartesianTile type;
    
    public Node(int x, int y, CartesianTile type) {
        
        this.x = x;
        this.y = y;
        this.type = type;
        
    }
    
    public int getMovementCost() {
        
        return CartesianTile.getMovementCostFromNodeName(type);
        
    }

}
