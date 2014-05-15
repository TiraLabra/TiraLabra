/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Algorithms;

import Hike.ImageTable.ImageTable;
import Hike.Values;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 *
 * @author petri
 */
public class Dijkstra {

    private int[][] table;
    private int[][] d;
    private char[][] previous;
    private int startx = 0;
    private int starty = 0;
    private int goalx = 799;
    private int goaly = 499;
    private final int max = 2000000;

    public Dijkstra(ImageTable table) {
        this.table = table.getTable();
        d = new int[Values.IMAGEHEIGHT][Values.IMAGEWIDTH];
        previous = new char[Values.IMAGEHEIGHT][Values.IMAGEWIDTH];
        initialize();
        distances();


    }

    private void initialize() {
        for (int y = 0; y < Values.IMAGEHEIGHT; y++) {
            for (int x = 0; x < Values.IMAGEWIDTH; x++) {
                d[y][x] = max;
                previous[y][x] = 'S';

            }
        }
        d[0][0] = 0;

    }

    private void relax(int sy, int sx, int ty, int tx) {
        if (d[ty][tx] > d[sy][sx] + w(sx, sy, tx, ty)) {
            d[ty][tx] = d[sy][sx] + w(sx, sy, tx, ty);
            if (sy < ty) {
                previous[ty][tx] = 'D';
            }
            if (sy > ty) {
                previous[ty][tx] = 'U';
            }
            if (sx < tx) {
                previous[ty][tx] = 'R';
            }
            if (sx > tx) {
                previous[ty][tx] = 'L';
            }


        }
    }

    private int w(int sx, int sy, int tx, int ty) {
        return table[ty][tx];
    }

    private void distances() {

        for (int y = 0; y < Values.IMAGEHEIGHT; y++) {
            for (int x = 0; x < Values.IMAGEWIDTH; x++) {

                relaxNeighbours(y, x);





            }



        }
        printDistances();
//        printPrevious();
        printPath(Values.IMAGEHEIGHT - 1, Values.IMAGEWIDTH - 1);
    }

    private void relaxNeighbours(int y, int x) {
        if (x == 0 && y == 0) {
            relax(y, x, y, x + 1);
            relax(y, x, y + 1, x);
        } else if ((x != 0 && x != Values.IMAGEWIDTH - 1) && (y != 0 && y != Values.IMAGEHEIGHT - 1)) {
            relax(y, x, y, x + 1);
            relax(y, x, y, x - 1);
            relax(y, x, y + 1, x);
            relax(y, x, y - 1, x);
        } else if (x == 0 && y < Values.IMAGEHEIGHT - 1) {
            relax(y, x, y, x + 1);
            relax(y, x, y + 1, x);
            relax(y, x, y - 1, x);
        } else if (x == 0 && y == Values.IMAGEHEIGHT - 1) {
            relax(y, x, y, x + 1);
            relax(y, x, y - 1, x);
        } else if (x < Values.IMAGEWIDTH - 1 && y == 0) {
            relax(y, x, y, x + 1);
            relax(y, x, y + 1, x);
        } else if (x == Values.IMAGEWIDTH - 1 && y < Values.IMAGEHEIGHT - 1) {
            relax(y, x, y, x - 1);
            relax(y, x, y + 1, x);
        } else if (x == Values.IMAGEWIDTH && y == Values.IMAGEHEIGHT) {
            relax(y, x, y, x - 1);
            relax(y, x, y - 1, x);
        }

    }

    public void printDistances() {
        for (int y = 0; y < Values.IMAGEHEIGHT; y++) {
            System.out.println("");
            for (int x = 0; x < Values.IMAGEWIDTH; x++) {
                System.out.print(d[y][x] + " ");

            }


        }
    }

    public void printPath(int ty, int tx) {
        Deque<Character> stack = new ArrayDeque<Character>();
        char u = previous[ty][tx];
        while (u != 'S') {
            stack.addFirst(u);
            if (u == 'D') {
                ty--;
                u = previous[ty][tx];

            } else if (u == 'U') {
                ty++;
                u = previous[ty][tx];

            } else if (u == 'L') {
                tx++;
                u = previous[ty][tx];

            } else if (u == 'R') {
                tx--;
                u = previous[ty][tx];

            }
        }

        System.out.println("Ready to print!");
        int laskuri = 0;
        while (stack.size() > 0)  {
            u = stack.pop();
            System.out.print(u);
            laskuri++;
        }
        System.out.print(laskuri);



    }

    private void printPrevious() {
        for (int y = 0; y < Values.IMAGEHEIGHT; y++) {
            System.out.println("");
            for (int x = 0; x < Values.IMAGEWIDTH; x++) {
                System.out.print(previous[y][x]);

            }


        }
    }
}
