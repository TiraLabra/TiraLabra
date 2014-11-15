package com.mycompany.tiralabra_maven;

import search.AStarSearch;
import UI.CLI;

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
                        {2, 1, 1, 2, 1}};
        AStarSearch search = new AStarSearch(map);
        CLI cli = new CLI(search);
        cli.menu();
    }
}
