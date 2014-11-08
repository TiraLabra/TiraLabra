package search;

import java.util.ArrayList;
import map.Node;

public class AStarSearch {
    
   private int[][] map;
   
   public AStarSearch(int[][] map) {
       this.map = map;
   }
   
   public void search(int startX, int startY, int endX, int endY) {
       ArrayList<Node> closedSet = new ArrayList<Node>();
       ArrayList<Node> openSet = new ArrayList<Node>();
       Node start = new Node(startX, startY);
       openSet.add(start);
       
       start.setG(0);
       start.setF(start.getG() + heuristicEstimate(start.getX(), start.getY(), endX, endY));
       
       while (!openSet.isEmpty()) {
           Node current = openSet.get(0);
           int bestF = current.getF();
           for (Node node : openSet) {
               int nodeF = node.getF();
               if (nodeF < bestF) {
                   bestF = nodeF;
                   current = node;
               }
           }
           
           System.out.println("Current Node: X: " + current.getX() + ", Y: " + current.getY());
           
           if (current.getX() == endX && current.getY() == endY) {
               showPath(startX, startY, current);
               return;
           }
           
           openSet.remove(current);
           closedSet.add(current);
           
           //neighbors
           handleNeighbor(new Node(current.getX() - 1, current.getY()), current, closedSet, openSet, endX, endY); //left
           handleNeighbor(new Node(current.getX() + 1, current.getY()), current, closedSet, openSet, endX, endY); //right
           handleNeighbor(new Node(current.getX(), current.getY() - 1), current, closedSet, openSet, endX, endY); //up
           handleNeighbor(new Node(current.getX(), current.getY() + 1), current, closedSet, openSet, endX, endY); //down
       }
       
       System.out.println("Epaonnistuin");
   }
   
   public void handleNeighbor(Node neighbor, Node current, ArrayList<Node> closedSet, ArrayList<Node> openSet, int endX, int endY) {
       if (neighbor.getX() < 0 || neighbor.getX() >= map.length || neighbor.getY() < 0 || neighbor.getY() >= map[0].length) {
           return;
       }
       for (Node node : closedSet) {
           if (node.getX() == neighbor.getX() && node.getY() == neighbor.getY()) {
               return;
           }
       }
       
       int tentativeGScore = current.getG() + map[neighbor.getX()][neighbor.getY()];
       
       boolean neighborInOpenSet = false;
       
       for (Node node : openSet) {
           if (node.getX() == neighbor.getX() && node.getY() == neighbor.getY()) {
               neighborInOpenSet = true;
               neighbor = node;
           }
       }
       if (!neighborInOpenSet || tentativeGScore < neighbor.getG()) {
           neighbor.setCameFrom(current);
           neighbor.setG(tentativeGScore);
           neighbor.setF(neighbor.getG() + heuristicEstimate(neighbor.getX(), neighbor.getY(), endX, endY));
           openSet.add(neighbor);
       }
   }
   
   public void showPath(int startX, int startY, Node end) {
       String path = "(" + end.getX() + ", " + end.getY() + ": time: " + end.getG() + ")";
       Node current = end.getCameFrom();
       
       while (true) {
           String tempPath = "(" + current.getX() + ", " + current.getY() + ": time: " + current.getG() + ") -> ";
           tempPath = tempPath + path;
           path = tempPath;
           current = current.getCameFrom();
           if (current.getX() == startX && current.getY() == startY) {
               break;
           }
       }
       
       String tempPath = "(" + current.getX() + ", " + current.getY() + ": time: " + current.getG() + ") -> ";
       tempPath = tempPath + path;
       path = tempPath;
       
       System.out.println(path);
   }
   
   public int heuristicEstimate(int currentX, int currentY, int goalX, int goalY) {
       int result = Math.abs(goalX - currentX) + Math.abs(goalY - currentY);
       return result;
   }
}