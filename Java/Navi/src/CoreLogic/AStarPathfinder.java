package CoreLogic;

// @author Leevi

import java.util.ArrayList;
import java.util.List;

public class AStarPathfinder {

    //================================================================================
    // Constructors
    //================================================================================
    
    public AStarPathfinder() {

    }

    //================================================================================
    // Route calculation
    //================================================================================
    
    public int[][] determineRoute(int xLim, int yLim, int[][] map, int startX, int startY, int goalX, int goalY) {
        
        List<String> open = new ArrayList<>();
        int currentX = startX;
        int currentY = startY;
        open.add(Integer.toString(currentX) + "," + Integer.toString(currentY));
        
        int heuristicDistance = Math.abs((startX - goalX)) + Math.abs((startY - goalY));

        // TODO: Implementation
        return new int[1][1];

    }

}
