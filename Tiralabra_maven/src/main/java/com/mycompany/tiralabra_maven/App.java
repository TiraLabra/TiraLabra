package com.mycompany.tiralabra_maven;

import search.AStarSearch;
import UI.CLI;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Random;

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
        AStarSearch search = new AStarSearch(createRandomMap(30, 30, 9));
        CLI cli = new CLI(search, new Scanner(System.in), new PrintStream(System.out));
        cli.menu();
    }
    
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
}
