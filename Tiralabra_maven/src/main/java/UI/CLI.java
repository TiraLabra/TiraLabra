package UI;

import java.util.Scanner;
import search.AStarSearch;
import java.io.PrintStream;
import java.util.Random;

/**
 * Command line interface for using the search class.
 */
public class CLI {

    private AStarSearch AStarSearch;
    private Scanner in;
    private PrintStream out;
    
    public CLI(AStarSearch AStarSearch, Scanner in, PrintStream out) {
        this.AStarSearch = AStarSearch;
        this.in = in;
        this.out = out;
    }
    
   /**Prints the menu for the search.
    * 
    */
   public void menu() {
       out.print(AStarSearch.printMap() + "\n");
       while (true) {
           out.print("Please give the route's start and end x and y values "
                   + "separated by commas in x1,y1,x2,y2 format (eg. 1,1,2,3). "
                   + "Write n to create a new map or q to quit.\n");
           String input = in.nextLine();
           if (input.equals("q")) {
               System.out.println("exiting AStarSearch...");
               break;
           }
           long aikaAlussa = System.currentTimeMillis();
           if (validInput(input)) {
               int[] searchCoordinates = parseInput(input, 4);
               out.print("Search result: \n");
               out.print(AStarSearch.search(searchCoordinates[0], searchCoordinates[1], searchCoordinates[2], searchCoordinates[3]) + "\n");
               long aikaLopussa = System.currentTimeMillis();
               System.out.println("Search took " + (aikaLopussa - aikaAlussa) + " ms.");
           }
           else if (input.equals("n")) {
               newMap();
           }
           else {
               out.print("bad input, please use the x1,y1,x2,y2 format\n");
           }
       }
   }
   
   /**
    * Checks if the input is in the x1,y1,x2,y2 format.
    * @param input
    * @return 
    */
   private boolean validInput(String input) {
       return input.matches("\\d+[,]\\d+[,]\\d+[,]\\d+");
   }
   
   /**
    * UI for creating a new map.
    */
   private void newMap() {
       out.print("Enter the new map size in x,y,range format (eg. 10,7,5). Write"
               + "q to return.\n");
       while (true) {
           String input = in.nextLine();
           if (input.equals("q")) {
               return;
           }
           if (validMapSizeInput(input)) {
               int[] mapAttr = parseInput(input, 3);
               if (mapAttr[0] < 1 || mapAttr[1] < 1 || mapAttr[2] < 1) {
                   out.print("please use values larger than 0.\n");
               }
               else {
                   this.AStarSearch = new AStarSearch(createRandomMap(mapAttr[0], mapAttr[1], mapAttr[2]));
                   
                   out.print(AStarSearch.printMap() + "\n");
                   return;
               }
           }
           else {
               out.print("bad input, please use the x,y,range format and only"
                       + "use integers larger than 0.\n");
           }
       }
   }
   
   /**
    * Creates a new random map.
    */
   public static int[][] createRandomMap(int width, int height, int range) {
        Random rand = new Random();
        int[][] map = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[x][y] = rand.nextInt(range) + 1;
            }
        }
        return map;
    }
   
   /**
    * Checks that the input for defining a new map size is correct.
    */
   private boolean validMapSizeInput(String input) {
       return input.matches("\\d+[,]\\d+[,]\\d+");
   }
   
   /**Parses the input into an array of the search coordinates
    * 
    * @param input 
    */
   private int[] parseInput(String input, int inputSize) {
       String[] splittedInput = input.split(",");
       int[] searchValues = new int[inputSize];
       for (int i = 0; i < inputSize; i++) {
           searchValues[i] = Integer.parseInt(splittedInput[i]);
       }
       return searchValues;
   }
}
