package com.mycompany.tiralabra_maven.io;

import com.mycompany.tiralabra_maven.maze.ArrayMaze;

/**
 *
 * @author yessergire
 */
public abstract class Parser {

    /**
     *
     */
    protected int length;

    /**
     *
     * @param row
     * @return
     */
    public int[] parseRow(String row) {
        init(row);
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            if (MazePrinter.START.equals(objectAt(i) + "")) {
                result[i] = ArrayMaze.START;
            } else if (MazePrinter.GOAL.equals(objectAt(i) + "")) {
                result[i] = ArrayMaze.GOAL;
            } else if (MazePrinter.WALL.equals(objectAt(i) + "")) {
                result[i] = ArrayMaze.WALL;
            } else result[i] = parseIntAt(i);
        }
        return result;
    }

    /**
     *
     * @param ascii
     * @return
     */
    public int[][] parse(String ascii) {
        String[] rows = ascii.split("\n");

        int[][] result = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            result[i] = parseRow(rows[i]);
        }
        return result;
    }

    /**
     *
     * @param i
     * @return
     */
    protected abstract Object objectAt(int i);

    /**
     *
     * @param i
     * @return
     */
    protected abstract int parseIntAt(int i);

    /**
     *
     * @param str
     */
    protected abstract void init(String str);
}
