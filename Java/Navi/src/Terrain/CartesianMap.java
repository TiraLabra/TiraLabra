package Terrain;

// @author Leevi
public class CartesianMap {

    int x;
    int y;
    int [ ] [ ] map;

    public CartesianMap(int x, int y) {

        this.x = x;
        this.y = y;
        this.map = new int  [x] [y];

    }

    public void generateTerrain(boolean hasRoughTerrain) {

        // TODO: Implementation
    }

    public int getSingleTile(int xPos, int yPos) {

        // TODO: Implementation
        return -1;

    }

    public void displayMap() {

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(" * ");
            }
            System.out.println("");
        }

    }

}
