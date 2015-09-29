package Terrain;

import java.util.Random;
import CoreLogic.Pathfinder;

// @author Leevi
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

    public CartesianMap(int x, int y) {

        this.xLim = x;
        this.yLim = y;
        this.map = new int[x][y];

    }
    
    //================================================================================
    // Map generation
    //================================================================================

    public void generateTerrain(boolean hasRoughTerrain) {

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                Random rand = new Random();
                int tileValue;
                if (!hasRoughTerrain) {
                    tileValue = rand.nextInt(38 + 1);
                }
                else {
                    tileValue = rand.nextInt(18 + 1);
                }
                
                if (tileValue < 3) {
                    tileValue = -1; // Void
                }
                else if (tileValue < 7) {
                    tileValue = 0; // Dirtroad
                }
                else if (tileValue < 10) {
                    tileValue = 1; // Traffic
                }
                else if (tileValue < 18) {
                    tileValue = 2; // Road
                }
                else {
                    tileValue = 3; // Highway
                }
                map[x][y] = tileValue;
            }
        }

    }
    
    public void generateTerrainPreset1() {
        
        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                Random rand = new Random();
                int tileValue;
                tileValue = rand.nextInt(30 + 1);
                
                if (x > 2 && x < xLim - 5 && y == 5) {
                    tileValue = -1;
                }
                else if (y > 5 && y < yLim - 8 && x == 3) {
                    tileValue = -1;
                }
                else if (y > 5 && y < yLim - 8 && x == 15) {
                    tileValue = -1;
                }
                else {
                    if (tileValue < 3) {
                        tileValue = -1; // Void
                    }
                    else if (tileValue < 7) {
                        tileValue = 0; // Dirtroad
                    }
                    else if (tileValue < 10) {
                        tileValue = 1; // Traffic
                    }
                    else if (tileValue < 18) {
                        tileValue = 2; // Road
                    }
                    else {
                        tileValue = 3; // Highway
                    }
                }
                map[x][y] = tileValue;
            }
        }
        
    }
    
    //================================================================================
    // Getters
    //================================================================================

    public int getSingleTile(int xPos, int yPos) {

        return map[xPos][yPos];

    }
    
    public int[][] getMap() {
        
        return map;
        
    }
    
    //================================================================================
    // Map drawing
    //================================================================================

    public void displayMap() {

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                if (getSingleTile(x, y) < 0) {
                    System.out.print(" 0 "); // Void
                }
                else if (getSingleTile(x, y) == 0) { // Dirtroad
                    System.out.print(" * ");
                }
                else if (getSingleTile(x, y) == 1) { // Traffic
                    System.out.print(" / ");
                }
                else if (getSingleTile(x, y) == 2) { // Road
                    System.out.print(" - ");
                }
                else if (getSingleTile(x, y) == 3) { // Highway
                    System.out.print(" = ");
                }
            }
            System.out.println("");
        }

    }
    
    public void displayMapWithRoute(int [][] route) {
        
        // TODO: Implementation
        
    }

}
