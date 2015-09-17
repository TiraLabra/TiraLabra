package Terrain;

import java.util.Random;

// @author Leevi
public class CartesianMap {

    int xLim;
    int yLim;
    int[][] map;

    public CartesianMap(int x, int y) {

        this.xLim = x;
        this.yLim = y;
        this.map = new int[x][y];

    }

    public void generateTerrain(boolean hasRoughTerrain) {

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                Random rand = new Random();
                int tileValue = rand.nextInt(3 + 1);
                map[x][y] = tileValue;
            }
        }

    }

    public int getSingleTile(int xPos, int yPos) {

        return map[xPos][yPos];

    }

    public void displayMap() {

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                if (getSingleTile(x, y) == 0) { // Forest
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

}
