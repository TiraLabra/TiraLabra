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
                return 20;
        }
        return -1;
        
    }
    
}
