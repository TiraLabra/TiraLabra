package com.mycompany.tiralabra_maven;

import java.util.List;

/**
 * Sovelluksen pääluokka.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Maze maze = new Maze(10, 10, new State(), new State(2, 5));
        Searcher s = new Searcher(maze);
        List<State> list = s.findOptimalPath();
        System.out.println(list);
    }
}
