package Main;
import Terrain.CartesianMap;

// @author Leevi
public class Navi {

    public static void main(String[] args) {
        
        CartesianMap map = new CartesianMap(28, 20);
        map.generateTerrain(false);
        map.displayMap();
        
    }
}
