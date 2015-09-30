package Terrain;

import java.util.Random;
import CoreLogic.AStarPathfinder;

/**
 *
 * @author Leevi
 */
public class CartesianMap {

    //================================================================================
    // Parameters
    //================================================================================
    
    private final int xLim;
    private final int yLim;
    private int[][] map;

    //================================================================================
    // Constructors
    //================================================================================
    
    /**
     *
     * @param x, limit of width.
     * @param y, limit of height.
     */
    public CartesianMap(int x, int y) {

        this.xLim = x;
        this.yLim = y;
        this.map = new int[x][y];

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

    //================================================================================
    // Map drawing
    //================================================================================
    
    /**
     * Render the map to a textual representation.
     */
    public void displayMap() {

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                if (getSingleTile(x, y) == CartesianTile.getMovementCostFromNodeName(CartesianTile.VOID)) {
                    System.out.print(" 0 ");
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

    /**
     *
     * @param route
     */
    public void displayMapWithRoute(int[][] route) {

        // TODO: Implementation
        
    }

}
