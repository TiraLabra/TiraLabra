package com.mycompany.tiralabra_maven;

import search.AStarSearch;
import UI.CLI;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int [][] map = {{1, 1, 2, 3, 2},
                        {2, 6, 2, 1, 1},
                        {1, 2, 9, 2, 2},
                        {2, 1, 1, 2, 1},
                        {1, 1, 2, 3, 2}};
        AStarSearch search = new AStarSearch(map);
        CLI cli = new CLI(search, new Scanner(System.in), new PrintStream(System.out));
        cli.menu();
    }
}
