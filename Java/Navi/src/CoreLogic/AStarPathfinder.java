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
    //================================================================================'
    
    public AStarPathfinder(CartesianMap map) {

        open = new ArrayList<>();
        closed = new ArrayList<>();
        this.map = map;

    }

    //================================================================================
    // Route calculation
    //================================================================================
    
    public int[][] determineRoute(int startX, int startY, int goalX, int goalY) {
        
        if (startX == goalX && startY == goalY) {
            return null; // No distance to travel!
        } 
        else if (map.getMap()[goalX][goalY] == CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID)) {
            return null; // No valid goal!
        }

        CartesianTile type = CartesianTile.getTypeFromMovementCost(map.getMap()[startX][startY]);
        Node current = new Node(startX, startY, type);
        open.add(current);

        while (true) {
            
            if (current.x == goalX && current.y == goalY) {
                break; // Finished.
            }

            int index = 1;
            
            for (Node node : map.getAdjacentNodes(current.x, current.y)) { // Check node's neighbours.

                if (closed.contains(node) || node.type == CartesianTile.VOID) {
                    index++;
                    if (index > map.getAdjacentNodes(current.x, current.y).size()) { // Dead end.
                        current = new Node(startX, startY, type);
                        open.clear();
                        open.add(current);
                        break;
                    }
                    continue; // Node was checked already or is inaccessible.
                }
                
                if (index == 1 || open.isEmpty()) {
                    open.add(node);
                } 
                else {
                    int heuristicDistance = Math.abs((node.x - goalX)) + Math.abs((node.y - goalY)); // Approximate the distance.
                    int movementCost = map.getSingleTile(node.x, node.y);
                    int previousHeuristicDistance = Math.abs(current.x - goalX) + Math.abs(current.y - goalY);
                    int previousMovementCost = map.getSingleTile(current.x, current.y);
                    if (previousHeuristicDistance > heuristicDistance) {
                        open.remove(open.size() - 1);
                        open.add(node);
                    }
                }

                index++;

            }
            
            closed.add(current);
            current = open.get(open.size() - 1);

        }
        
        int[][] returnMap = new int[Navi.xLim][Navi.yLim];
        
        for (int y = 0; y < Navi.yLim; y++) {
            for (int x = 0; x < Navi.xLim; x++) {
                returnMap[x][y] = map.getSingleTile(x, y);
                if (x == startX && y == startY) {
                    returnMap[x][y] = 100;
                    continue;
                }
                else if (x == goalX && y == goalY) {
                    returnMap[x][y] = 300;
                    continue;
                }
                else {
                    returnMap[x][y] = map.getSingleTile(x, y);
                }
                for (Node node : open) {
                    if (node.x == x && node.y == y) {
                        returnMap[x][y] = 200;
                        break;
                    }
                }
            }
        }

        return returnMap;

    }

}
