package UI;

import java.util.Scanner;
import search.AStarSearch;

public class CLI {

    private AStarSearch AStarSearch;
    private Scanner scanner;
    
    public CLI(AStarSearch AStarSearch) {
        this.AStarSearch = AStarSearch;
        this.scanner = new Scanner(System.in);
    }
    
   /**Prints the menu for the search.
    * 
    */
   public void menu() {
       System.out.println(AStarSearch.printMap());
       System.out.println("Please give the route's start and end x and y values separated by commas in x1,y1,x2,y2 format (eg. 1,1,2,3)");
       String input = scanner.nextLine();
       int[] searchCoordinates = parseInput(input);
       System.out.println(AStarSearch.search(searchCoordinates[0], searchCoordinates[1], searchCoordinates[2], searchCoordinates[3]));
   }
   
   
   /**Parses the input into an array of the search coordinates
    * 
    * @param input 
    */
   private int[] parseInput(String input) {
       String[] splittedInput = input.split(",");
       int[] searchValues = new int[4];
       searchValues[0] = Integer.parseInt(splittedInput[0]);
       searchValues[1] = Integer.parseInt(splittedInput[1]);
       searchValues[2] = Integer.parseInt(splittedInput[2]);
       searchValues[3] = Integer.parseInt(splittedInput[3]);
       return searchValues;
   }
}
