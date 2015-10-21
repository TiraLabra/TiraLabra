package UI;

import Main.Navi;
import Terrain.CartesianMap;
import CoreLogic.AStarPathfinder;
import java.util.Scanner;

/**
 *
 * @author Leevi
 */
public class TestUserInterface {

    /**
     * Starts UI
     */
    public void startUI() {

        System.out.println("***Navi 1.0***\n");
        listMenuItems();

    }

    /**
     * Reads user input and starts simulation.
     */
    void listMenuItems() {
        
        int terrain = 0;
        int startX = 0;
        int startY = 0;
        int goalX = 0;
        int goalY = 0;

        System.out.println("Select terrain");
        System.out.println("  Flat, type '1'");
        System.out.println("  Rough, type '2'");
        System.out.println("  Preset 1, type '3'");
        System.out.print("> ");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            terrain = scanner.nextInt();
            if (terrain == 1) {
                break;
            } else if (terrain == 2) {
                break;
            } else if (terrain == 3) {
                break;
            }
            System.out.print("> ");
        }

        System.out.println("Set startX, type [0-" + (Navi.xLim - 1) + "]");
        System.out.print("> ");

        while (true) {
            startX = scanner.nextInt();
            if (startX > -1 && startX < Navi.xLim) {
                break;
            }
            System.out.print("> ");
        }

        System.out.println("Set startY, type [0-" + (Navi.yLim - 1) + "]");
        System.out.print("> ");

        while (true) {
            startY = scanner.nextInt();
            if (startY > -1 && startY < Navi.yLim) {
                break;
            }
            System.out.print("> ");
        }

        System.out.println("Set goalX, type [0-" + (Navi.xLim - 1) + "]");
        System.out.print("> ");

        while (true) {
            goalX = scanner.nextInt();
            if (goalX > -1 && goalX < Navi.xLim) {
                break;
            }
            System.out.print("> ");
        }

        System.out.println("Set goalY, type [0-" + (Navi.yLim - 1) + "]");
        System.out.print("> ");

        while (true) {
            goalY = scanner.nextInt();
            if (goalY > -1 && goalY < Navi.yLim) {
                break;
            }
            System.out.print("> ");
        }

        run(terrain, startX, startY, goalX, goalY);

    }

    /**
     * Calls map generation and A* route calculation.
     * 
     * @param terrainType
     * @param startX
     * @param startY
     * @param goalX
     * @param goalY 
     * @see CartesianMap
     * @see AStarPathfinder
     */
    void run(int terrainType, int startX, int startY, int goalX, int goalY) {

        CartesianMap map = new CartesianMap();

        if (terrainType == 1) {
            map.generateTerrain(false);
        } else if (terrainType == 2) {
            map.generateTerrain(true);
        } else if (terrainType == 3) {
            map.generateTerrainPreset1();
        }

        System.out.println("\nMap:\n");
        map.displayMap();

        System.out.print("\nType any key to start.\n> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("");

        AStarPathfinder finder = new AStarPathfinder(map);
        int[][] route = finder.determineRoute(startX, startY, goalX, goalY);
        if (route != null) {
            System.out.println("\nRoute:\n");
            map.setMap(route);
            map.displayMap();

            System.out.println("\nLegend:\n");
            System.out.println("  '0' = Void");
            System.out.println("  '*' = Dirtroad");
            System.out.println("  '/' = Traffic");
            System.out.println("  '-' = Road");
            System.out.println("  '=' = Highway");
            System.out.println("  'X' = Route node");
            System.out.println("  'S' = Start");
            System.out.println("  'G' = Goal");
        }
        else {
            System.out.println("No routes available.");
        }
        
        System.out.println("Restart? (y/n)");
        OUTER:
        while (true) {
            String in = scanner.nextLine();
            switch (in) {
                case "y":
                    System.out.println("");
                    listMenuItems();
                    break OUTER;
                case "n":
                    break OUTER;
            }
            System.out.print("> ");
        }
        
    }

}
