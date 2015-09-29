package Terrain;

// @author Leevi
public enum CartesianTile {

    HIGHWAY,     
    ROAD,
    TRAFFIC,  
    DIRTROAD,
    VOID;
    
    public int movementCost() {
        
        switch (this) {
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
    
}
