package Main;
import Terrain.CartesianMap;
import UI.TestUserInterface;

// @author Leevi
public class Navi {

    public static void main(String[] args) {
        
        TestUserInterface ui = new TestUserInterface();
        ui.startUI();
        
        CartesianMap map = new CartesianMap(28, 20);
        map.generateTerrain(false);
        map.displayMap();
        
        System.out.println("");
        
        map.generateTerrain(true);
        map.displayMap();
        
    }
}
