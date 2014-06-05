/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Juri Kuronen
 */
public class LabyrinthTest {

    Labyrinth l;

    @Before
    public void setUp() throws Exception {
        l = new Labyrinth(10, 10);
    }

    @Test
    public void settingUp() {
        assertEquals(10, l.width);
        assertEquals(10, l.height);
        assertEquals(10, l.labyrinth.length);
        assertEquals(10, l.labyrinth[0].length);
    }

    @Test
    public void updating() {
        l.updateLabyrinth(4, 9);
        assertEquals(4, l.width);
        assertEquals(9, l.height);
        assertEquals(9, l.labyrinth.length);
        assertEquals(4, l.labyrinth[0].length);
    }

    @Test(expected = Exception.class)
    public void targetCoordinates() throws Exception {
        for (int i = 0; i < 100; i++) {
            try {
                assertEquals(i - l.width, l.getTargetCoordinate(i, (byte) 1));
            } catch (Exception e) {
                throw e;
            }
            try {
                assertEquals(i + 1, l.getTargetCoordinate(i, (byte) 2));
            } catch (Exception e) {
                throw e;
            }
            try {
                assertEquals(i + l.width, l.getTargetCoordinate(i, (byte) 4));
            } catch (Exception e) {
                throw e;
            }
            try {
                assertEquals(i - 1, l.getTargetCoordinate(i, (byte) 8));
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Test
    public void addImpossiblePassages() throws Exception {
        l.addPassage(0, 4);
        assertEquals(0, l.labyrinth[0][0]);
        assertEquals(0, l.labyrinth[0][4]);
    }

    @Test
    public void addWorkingPassages() throws Exception {
        l.addPassage(0, 1);
        assertEquals(2, l.labyrinth[0][0]);
        assertEquals(8, l.labyrinth[0][1]);
        l.addPassage(0, l.width);
        assertEquals(2 + 4, l.labyrinth[0][0]);
        assertEquals(1, l.labyrinth[1][0]);
    }

    @Test(expected = Exception.class)
    public void addOutOfBoundsPassage() throws Exception {
        try {
            l.addPassage(0, 155);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void getListOfUnvisitedNeighbors() throws Exception {
        int[][] visited = new int[l.height][l.width];
        assertEquals(4, l.getListOfNeighbors(l.width + 3, visited, 0).size());
        assertEquals(3, l.getListOfNeighbors(3, visited, 0).size());
        assertEquals(3, l.getListOfNeighbors(l.width * l.height - 5, visited, 0).size());
        assertEquals(2, l.getListOfNeighbors(0, visited, 0).size());
        assertEquals(2, l.getListOfNeighbors(l.width * l.height - 1, visited, 0).size());
    }

    @Test
    public void getListOfVisitedNeighbors() throws Exception {
        int[][] visited = new int[l.height][l.width];
        visited[3][3] = 2;
        visited[4][3] = 2;
        visited[2][3] = 2;
        visited[3][2] = 2;
        assertEquals(3, l.getListOfNeighbors(l.width * 3 + 3, visited, 2).size());
        assertEquals(1, l.getListOfNeighbors(l.width * 3 + 4, visited, 2).size());
        assertEquals(1, l.getListOfNeighbors(l.width * 3 + 2, visited, 2).size());
        assertEquals(1, l.getListOfNeighbors(l.width * 4 + 3, visited, 2).size());
    }

    @Test
    public void getListOfEdgesToUnvisitedNeighbors() throws Exception {
        l.addPassage(l.width + 3, l.width + 2);
        l.addPassage(l.width + 3, l.width + 4);
        l.addPassage(l.width + 3, 3);
        int[][] visited = new int[l.height][l.width];
        assertEquals(3, l.getListOfEdges(l.width + 3, visited, 0).size());
        assertEquals(1, l.getListOfEdges(l.width + 4, visited, 0).size());
        assertEquals(1, l.getListOfEdges(l.width + 2, visited, 0).size());
        assertEquals(1, l.getListOfEdges(3, visited, 0).size());
    }

    @Test
    public void getListOfEdgesToVisitedNeighbors() throws Exception {
        getListOfEdgesToUnvisitedNeighbors();
        int[][] visited = new int[l.height][l.width];
        visited[1][2] = 2;
        visited[1][3] = 2;
        visited[1][4] = 2;
        visited[0][3] = 2;
        assertEquals(3, l.getListOfEdges(l.width + 3, visited, 2).size());
        assertEquals(1, l.getListOfEdges(l.width + 4, visited, 2).size());
        assertEquals(1, l.getListOfEdges(l.width + 2, visited, 2).size());
        assertEquals(1, l.getListOfEdges(3, visited, 2).size());

        assertEquals(0, l.getListOfEdges(2, visited, 2).size());
        assertEquals(0, l.getListOfEdges(4, visited, 2).size());
        assertEquals(0, l.getListOfEdges(l.width + 5, visited, 2).size());
        assertEquals(0, l.getListOfEdges(l.width + 1, visited, 2).size());
        assertEquals(0, l.getListOfEdges(l.width * 2 + 3, visited, 2).size());
    }

    @Test
    public void timeFormatting() {
        long time = 123456789;
        assertEquals("123 456,789 ms", l.formatTime(time));
        time /= 10;
        assertEquals("12 345,678 ms", l.formatTime(time));
        time /= 10;
        assertEquals("1 234,567 ms", l.formatTime(time));
        time /= 10;
        assertEquals("123,456 ms", l.formatTime(time));
        time = 123;
        assertEquals("0,123 ms", l.formatTime(time));
        time = 13;
        assertEquals("0,013 ms", l.formatTime(time));
        time = 5;
        assertEquals("0,005 ms", l.formatTime(time));
        time = 501;
        assertEquals("0,501 ms", l.formatTime(time));
        time = 1000000500000090L;
        assertEquals("1 000 000 500 000,090 ms", l.formatTime(time));
    }

    @Test
    public void numberFormatting() {
        long number = 10170157010100751L;
        assertEquals("10 170 157 010 100 751", l.formatNumber(number));
        number /= 10;
        assertEquals("1 017 015 701 010 075", l.formatNumber(number));
        number /= 10;
        assertEquals("101 701 570 101 007", l.formatNumber(number));
        number /= 10;
        assertEquals("10 170 157 010 100", l.formatNumber(number));
        number /= 10;
        assertEquals("1 017 015 701 010", l.formatNumber(number));
        number /= 10;
        assertEquals("101 701 570 101", l.formatNumber(number));
        number /= 10;
        assertEquals("10 170 157 010", l.formatNumber(number));
        number /= 10;
        assertEquals("1 017 015 701", l.formatNumber(number));
        number /= 10;
        assertEquals("101 701 570", l.formatNumber(number));
        number /= 10;
        assertEquals("10 170 157", l.formatNumber(number));
        number /= 10;
        assertEquals("1 017 015", l.formatNumber(number));
        number /= 10;
        assertEquals("101 701", l.formatNumber(number));
        number /= 10;
        assertEquals("10 170", l.formatNumber(number));
        number /= 10;
        assertEquals("1 017", l.formatNumber(number));
        number /= 10;
        assertEquals("101", l.formatNumber(number));
        number /= 10;
        assertEquals("10", l.formatNumber(number));
        number /= 10;
        assertEquals("1", l.formatNumber(number));
        number /= 10;
        assertEquals("0", l.formatNumber(number));
    }
}
