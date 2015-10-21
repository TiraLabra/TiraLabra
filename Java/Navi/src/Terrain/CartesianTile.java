package Terrain;

/**
 *
 * @author Leevi
 */
public enum CartesianTile {

    HIGHWAY,     
    ROAD,
    TRAFFIC,  
    DIRTROAD,
    VOID;
    
    /**
     * Returns movement cost of self.
     *
     * @return movement cost if node conforms to any type, -1 if not.
     */
    public int movementCost() {
        
        switch (this) {
            case HIGHWAY:
                return 1;
            case ROAD:
                return 2;
            case TRAFFIC:
                return 3;
            case DIRTROAD:
                return 4;
            case VOID:
                return 99;
        }
        return -1;
        
    }
    
    /**
     * Returns movement cost from node's string representation.
     *
     * @param node
     * @return movement cost if node conforms to any type, -1 if not.
     */
    public static int getMovementCostFromNode(String node) {
        switch (node) {
            case "=":
                return 1;
            case "-":
                return 3;
            case "/":
                return 6;
            case "*":
                return 7;
            case "0":
                return 99;
        }
        return -1;
    }
    
    /**
     * Returns movement cost from enum type.
     *
     * @param node
     * @return movement cost if node has any type, -1 if not.
     */
    public static int getMovementCostFromNodeName(CartesianTile node) {
        switch (node) {
            case HIGHWAY:
                return 1;
            case ROAD:
                return 3;
            case TRAFFIC:
                return 6;
            case DIRTROAD:
                return 7;
            case VOID:
                return 99;
        }
        return -1;
    }
    
    /**
     * Returns enum type from movement cost.
     *
     * @param cost
     * @return enum type if cost is valid, null if cost is invalid.
     */
    public static CartesianTile getTypeFromMovementCost(int cost) {
        switch (cost) {
            case 1:
                return HIGHWAY;
            case 3:
                return ROAD;
            case 6:
                return TRAFFIC;
            case 7:
                return DIRTROAD;
            case 99:
                return VOID;
        }
        return null;
    }
    
}
