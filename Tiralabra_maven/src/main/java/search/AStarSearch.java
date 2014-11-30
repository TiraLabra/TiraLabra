package search;

import map.Node;
import structures.ClosedSet;
import structures.OrderedStack;
import structures.MinHeap;

/**The A* search class does route searches on a 2d integer arrays. 
 * 
 */
public class AStarSearch {
   
   /**A 2d integer array for holding the map data. The integers tell how much
    * time/effort/etc it will take to move in to the map coordinate.
    * 
    */
   private int[][] map;
   
   public AStarSearch(int[][] map) {
       this.map = map;
   }
   
   
   /**The A* search loop.
    * 
    * @param startX route's start X value
    * @param startY route's start Y value
    * @param endX route's end X value
    * @param endY route's end Y value
    * 
    * @return The route in a string format
    */
   public String search(int startX, int startY, int endX, int endY) {
       if (startX < 0 || startX >= map[0].length || startY < 0 || startY >= map.length || 
           endX < 0 || endX >= map[0].length || endY < 0 || endY >= map.length) {
           return "Search value(s) out of map range. Max X: " + map[0].length + ", max Y: " + map.length + ".";
       }
       ClosedSet closedSet = new ClosedSet(map[0].length, map.length);
       MinHeap openSet = new MinHeap(map[0].length, map.length);
       Node start = new Node(startX, startY);
       openSet.add(start);
       
       start.setG(0);
       start.setF(start.getG() + heuristicEstimate(start.getX(), start.getY(), endX, endY));
       
       while (!openSet.isEmpty()) {
           Node current = openSet.pop();
           
           if (current.getX() == endX && current.getY() == endY) {
               return formPath(startX, startY, current);
           }
           
           closedSet.add(current);
           
           //neighbors
           handleNeighbor(new Node(current.getX() - 1, current.getY()), current, closedSet, openSet, endX, endY); //left
           handleNeighbor(new Node(current.getX() + 1, current.getY()), current, closedSet, openSet, endX, endY); //right
           handleNeighbor(new Node(current.getX(), current.getY() - 1), current, closedSet, openSet, endX, endY); //up
           handleNeighbor(new Node(current.getX(), current.getY() + 1), current, closedSet, openSet, endX, endY); //down
       }
       
       return "Search failed.";
   }
   
   /**Adds neighboring node to the openset and sets F and G values for it if it meets the requirements.
    * 
    * @param neighbor The neighboring node
    * @param current The node currently in search loop
    * @param closedSet closed set of the search
    * @param openSet open set of the search
    * @param endX search route's end X value
    * @param endY search route's end Y value
    */
   private void handleNeighbor(Node neighbor, Node current, ClosedSet closedSet, MinHeap openSet, int endX, int endY) {
       if (neighbor.getX() < 0 || neighbor.getX() >= map[0].length || neighbor.getY() < 0 || neighbor.getY() >= map.length) {
           return;
       }
       if (closedSet.contains(neighbor)) {
           return;
       }
       
       int tentativeGScore = current.getG() + map[neighbor.getY()][neighbor.getX()];
       
       boolean neighborInOpenSet = false;
       
       if (openSet.contains(neighbor.getX(), neighbor.getY())) {
           neighbor = openSet.get(neighbor.getX(), neighbor.getY());
           neighborInOpenSet = true;
       }
       
       if (!neighborInOpenSet || tentativeGScore < neighbor.getG()) {
           neighbor.setCameFrom(current);
           neighbor.setG(tentativeGScore);
           neighbor.setF(neighbor.getG() + heuristicEstimate(neighbor.getX(), neighbor.getY(), endX, endY));
           if (!neighborInOpenSet) {
               openSet.add(neighbor);
           }
       }
   }
   
   /**Creates a string of the route
    * 
    * @param startX
    * @param startY
    * @param end 
    */
   private String formPath(int startX, int startY, Node end) {
       String path = addToPath(end, "", true);
       Node current = end.getCameFrom();
       
       if (end.getX() != startX && end.getY() != startY) {
           while (true) {
               path = addToPath(current, path, false);
               current = current.getCameFrom();
               if (current.getX() == startX && current.getY() == startY) {
                   break;
               }
           }
           path = addToPath(current, path, false);
       }
       return path;
   }
   
   /**Adds a node to the path string
    * 
    * @param node node to be added to the path string
    * @param path path string already formed
    * @return path string with the added node
    */
   private String addToPath(Node node, String path, boolean endNode) {
       String tempPath = "(" + node.getX() + ", " + node.getY() + ": time: " + node.getG() + ")";
       if (!endNode) {
           tempPath = tempPath + " -> ";
       }
       tempPath = tempPath + path;
       return tempPath;
   }
   
   /**Calculates the heuristic estimate f() from the current position to the end of the route
    * 
    * @param currentX x value of the current position
    * @param currentY y value of the current position
    * @param goalX x value of the end position
    * @param goalY y value of the end position
    * @return The heuristic value
    */
   private int heuristicEstimate(int currentX, int currentY, int goalX, int goalY) {
       int result = Math.abs(goalX - currentX) + Math.abs(goalY - currentY);
       return result;
   }
   
   /**
    * Sets the map the searches are performed on.
    * @param map 
    */
   public void setMap(int[][] map) {
       this.map = map;
   }
   
   /**
    * Prints the map in a readable format
    */
   public String printMap() {
       String output = "";
       for (int y = 0; y < map.length; y++) {
           if (y == 0) {
               output += "  ";
               for (int xCoord = 0; xCoord < map[0].length; xCoord++) {
                   if (xCoord >= 10) {
                       output += xCoord;
                   }
                   else {
                       output += " " + xCoord;
                   }
               }
               output += " X\n";
           }
           if (y >= 10) {
               output += y;
           }
           else {
               output += " " + y;
           }
           for (int x = 0; x < map[0].length; x++) {
               if (map[y][x] >= 10) {
                   output += map[y][x];
               } else {
                  output += " " + map[y][x];
               }
           }
           output += "\n";
       }
       output += " Y";
       return output;
   }
}