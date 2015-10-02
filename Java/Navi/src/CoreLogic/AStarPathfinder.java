package CoreLogic;

// @author Leevi
import DataStructures.Node;
import DataStructures.NodeStack;
import Main.Navi;
import Terrain.CartesianMap;
import Terrain.CartesianTile;
import java.util.ArrayList;
import java.util.List;

public class AStarPathfinder {

    //================================================================================
    // Parameters
    //================================================================================
    
    List<Node> open;
    List<Node> closed; // List of nodes that have not been searched, sorted by their (heuristic) distance to the goal.
    CartesianMap map;

    //================================================================================
    // Constructors
    //================================================================================
    public AStarPathfinder(CartesianMap map) {

        open = new ArrayList<>();
        closed = new ArrayList<>();
        this.map = map;

    }

    //================================================================================
    // Route calculation
    //================================================================================
    
    public Integer[][] determineRoute(int startX, int startY, int goalX, int goalY) {

        Integer[][] gridMap = map.getMap();

        if (startX == goalX && startY == goalY) {
            return null; // No distance to travel!
        } else if (gridMap[goalX][goalY] == CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID)) {
            return null; // No valid goal!
        }

        CartesianTile type = CartesianTile.getTypeFromMovementCost(gridMap[startX][startY]);
        Node current = new Node(startX, startY, type);

        while (true) {

            // closed.add(current);

            if (current.x == goalX && current.y == goalY) {

                open.add(current);
                break; // Finished.

            }

            int index = 0;

            for (Node node : map.getAdjacentNodes(current.x, current.y)) { // Check node's neighbours.

                if (closed.contains(node) || node.type == CartesianTile.VOID) {
                    continue; // Node was checked already or is inaccessible.
                }

                int heuristicDistance = Math.abs((node.x - goalX)) + Math.abs((node.y - goalY)); // Approximate the distance.

                if (index == 0) {
                    open.add(node);
                } 
                else {
                    int previousHeuristicDistance = Math.abs((open.get(open.size() - 1).x - goalX)) + Math.abs((open.get(open.size() - 1).y - goalY));
                    if (previousHeuristicDistance < heuristicDistance) {
                        open.remove(open.size() - 1);
                        open.add(node);
                    }
                }

                index++;

            }
            
            current = open.get(open.size() - 1);

        }
        
        Integer[][] returnMap = new Integer[Navi.xLim][Navi.yLim];
        
        for (int y = 0; y < Navi.yLim; y++) {
            for (int x = 0; x < Navi.xLim; x++) {
                returnMap[x][y] = map.getSingleTile(x, y);
            }
        }

        return returnMap;

    }

}
