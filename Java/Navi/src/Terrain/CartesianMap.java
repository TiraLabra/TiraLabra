package Terrain;

import java.util.Random;
import DataStructures.Node;
import Main.Navi;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leevi
 */
public class CartesianMap {

    //================================================================================
    // Parameters
    //================================================================================
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    private final int xLim;
    private final int yLim;
    private int[][] map;

    //================================================================================
    // Constructors
    //================================================================================
    
    /**
     *
     */
    public CartesianMap() {

        this.xLim = Navi.xLim;
        this.yLim = Navi.yLim;
        this.map = new int[Navi.xLim][Navi.yLim];

    }

    //================================================================================
    // Map generation
    //================================================================================
    
    /**
     * Generates a random grid map, with a certain level of obstacles.
     * 
     * @param hasRoughTerrain, determines if the terrain has more obstacles or not.
     */
    public void generateTerrain(boolean hasRoughTerrain) {

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                Random rand = new Random();
                int tileValue;
                if (!hasRoughTerrain) {
                    tileValue = rand.nextInt(38 + 1);
                } else {
                    tileValue = rand.nextInt(18 + 1);
                }

                if (tileValue < 3) {
                    tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID);
                } else if (tileValue < 7) {
                    tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.DIRTROAD);
                } else if (tileValue < 10) {
                    tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.TRAFFIC);
                } else if (tileValue < 18) {
                    tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.ROAD);
                } else {
                    tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.HIGHWAY);
                }
                map[x][y] = tileValue;
            }
        }

    }

    /**
     * Generates a semi-random grid map with fixed obstacles.
     */
    public void generateTerrainPreset1() {

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                Random rand = new Random();
                int tileValue;
                tileValue = rand.nextInt(30 + 1);

                if (x > 2 && x < xLim - 5 && y == 5) {
                    tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID);
                } else if (y > 5 && y < yLim - 8 && x == 3) {
                    tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID);
                } else if (y > 5 && y < yLim - 8 && x == 15) {
                    tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID);
                } else {
                    if (tileValue < 3) {
                        tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID);
                    } else if (tileValue < 7) {
                        tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.DIRTROAD);
                    } else if (tileValue < 10) {
                        tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.TRAFFIC);
                    } else if (tileValue < 18) {
                        tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.ROAD);
                    } else {
                        tileValue = CartesianTile.getMovementCostFromNodeName(CartesianTile.HIGHWAY);
                    }
                }
                map[x][y] = tileValue;
            }
        }

    }

    //================================================================================
    // Getters
    //================================================================================
    
    /**
     *
     * @param xPos
     * @param yPos
     * @return int xy, convertible to a node.
     * @see CartesianTile
     */
    public int getSingleTile(int xPos, int yPos) {

        return map[xPos][yPos];

    }

    /**
     *
     * @return 2d array, contains integers.
     */
    public int[][] getMap() {

        return map;

    }
    
    /**
     * Determines the neighbours of a node.
     *
     * @param xPos
     * @param yPos
     * @return list of nodes adjacent to a given node.
     */
    public List<Node> getAdjacentNodes(int xPos, int yPos) {
        
        List<Node> neighbours = new ArrayList<>();
        
        if (xPos > 0) {
            Node node = new Node(xPos - 1, yPos, CartesianTile.getTypeFromMovementCost(getSingleTile(xPos - 1, yPos)));
            neighbours.add(node);
        }
        if (yPos > 0) {
            Node node = new Node(xPos, yPos - 1, CartesianTile.getTypeFromMovementCost(getSingleTile(xPos, yPos - 1)));
            neighbours.add(node);
        }
        if (xPos < xLim - 1) {
            Node node = new Node(xPos + 1, yPos, CartesianTile.getTypeFromMovementCost(getSingleTile(xPos + 1, yPos)));
            neighbours.add(node);
        }
        if (yPos < yLim - 1) {
            Node node = new Node(xPos, yPos + 1, CartesianTile.getTypeFromMovementCost(getSingleTile(xPos, yPos + 1)));
            neighbours.add(node);
        }
        
        return neighbours;
        
    }
    
    //================================================================================
    // Setters
    //================================================================================
    
    /**
     * Replace map with new one.
     * 
     * @param newMap 
     */
    
    public void setMap(int[][] newMap) {
        
        this.map = newMap;
        
    }

    //================================================================================
    // Map drawing
    //================================================================================
    
    /**
     * Render the map to a textual representation.
     */
    public void displayMap() {

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                if (getSingleTile(x, y) == 100) {
                    System.out.print(ANSI_YELLOW + " S " + ANSI_RESET);
                }
                else if (getSingleTile(x, y) == 200) {
                    System.out.print(ANSI_RED + " X " + ANSI_RESET);
                }
                if (getSingleTile(x, y) == 300) {
                    System.out.print(ANSI_YELLOW + " G " + ANSI_RESET);
                }
                else if (getSingleTile(x, y) == CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID)) {
                    System.out.print(ANSI_PURPLE + " 0 " + ANSI_RESET);
                } else if (getSingleTile(x, y) == CartesianTile.getMovementCostFromNodeName(CartesianTile.DIRTROAD)) {
                    System.out.print(" * ");
                } else if (getSingleTile(x, y) == CartesianTile.getMovementCostFromNodeName(CartesianTile.TRAFFIC)) {
                    System.out.print(" / ");
                } else if (getSingleTile(x, y) == CartesianTile.getMovementCostFromNodeName(CartesianTile.ROAD)) {
                    System.out.print(" - ");
                } else if (getSingleTile(x, y) == CartesianTile.getMovementCostFromNodeName(CartesianTile.HIGHWAY)) {
                    System.out.print(" = ");
                }
            }
            System.out.println("");
        }

    }

}
