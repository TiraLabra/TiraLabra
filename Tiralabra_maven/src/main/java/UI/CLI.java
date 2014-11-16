package UI;

import java.util.Scanner;
import search.AStarSearch;
import java.io.PrintStream;

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
       out.print("Please give the route's start and end x and y values separated by commas in x1,y1,x2,y2 format (eg. 1,1,2,3)\n");
       String input = in.nextLine();
       if (validInput(input)) {
           int[] searchCoordinates = parseInput(input);
           out.print("Search result: \n");
           out.print(AStarSearch.search(searchCoordinates[0], searchCoordinates[1], searchCoordinates[2], searchCoordinates[3]) + "\n");
       }
       else {
           out.print("bad input, please use the x1,y1,x2,y2 format");
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
