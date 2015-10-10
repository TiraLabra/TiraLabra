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
    
    NodeStack possibleRoute;
    List<Node> closed; // List of nodes that have not been searched, sorted by their (heuristic) distance to the goal.
    CartesianMap map;

    //================================================================================
    // Constructors
    //================================================================================'
    
    public AStarPathfinder(CartesianMap map) {

        possibleRoute = new NodeStack(999);
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
        possibleRoute.push(current); // Push start node to stack;

        while (true) {
            
            if (current.x == goalX && current.y == goalY) {
                break; // Finished.
            }

            int index = 1;
            
            loop:
            for (Node node : map.getAdjacentNodes(current.x, current.y)) { // Check node's neighbours.
                
                for (Node compare : closed) {
                    if (compare.x == node.x && compare.y == node.y) {
                        continue loop;
                    }
                }
                if (node.type == CartesianTile.VOID) {
                    continue;
                }
                else if (index == 1) {
                    closed.add(node);
                    possibleRoute.push(node); // Push first neighbour to stack.
                } 
                else { // Check if new node is better than current.
                    int heuristicDistance = Math.abs((node.x - goalX)) + Math.abs((node.y - goalY));
                    int previousHeuristicDistance = Math.abs(current.x - goalX) + Math.abs(current.y - goalY);
                    int movementCost = map.getSingleTile(node.x, node.y);
                    int previousMovementCost = map.getSingleTile(current.x, current.y);
                    if (previousHeuristicDistance > heuristicDistance) {
                        closed.remove(closed.size() - 1);
                        closed.add(node);
                        possibleRoute.pop();
                        possibleRoute.push(node);
                    }
                }
                
                current = possibleRoute.peek();

                index++;

            }
            
            if (index == 1) { // Dead end.
                closed.add(possibleRoute.pop());
            }
            
            current = possibleRoute.peek();

        }
        
        int[][] returnMap = new int[Navi.xLim][Navi.yLim];
        
        while (!possibleRoute.isEmpty()) {
            Node node = possibleRoute.pop();
            returnMap[node.x][node.y] = 200;
        }
        
        for (int y = 0; y < Navi.yLim; y++) {
            for (int x = 0; x < Navi.xLim; x++) {
                if (x == startX && y == startY) {
                    returnMap[x][y] = 100;
                }
                else if (x == goalX && y == goalY) {
                    returnMap[x][y] = 300;
                }
                else if (returnMap[x][y] != 200) {
                    returnMap[x][y] = map.getSingleTile(x, y);
                }
            }
        }

        return returnMap;

    }

}
