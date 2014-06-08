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
    byte[][] labyrinth;
    int width;
    int height;

    @Before
    public void setUp() throws Exception {
        l = new Labyrinth(10, 10);
        labyrinth = generateByteArray(l);
        width = l.getWidth();
        height = l.getHeight();
    }
    
    byte[][] generateByteArray(Labyrinth lab) {
        byte[][] array = new byte[lab.getHeight()][lab.getWidth()];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                array[i][j] = lab.getEdges(i * width + j);
            }
        }
        return array;
    }
    @Test
    public void settingUp() {
        assertEquals(10, width);
        assertEquals(10, height);
        assertEquals(10, labyrinth.length);
        assertEquals(10, labyrinth[0].length);
    }

    @Test
    public void updating() {
        l.updateLabyrinth(4, 9);
        width = l.getWidth();
        height = l.getHeight();
        labyrinth = generateByteArray(l);
        assertEquals(4, width);
        assertEquals(9, height);
        assertEquals(9, labyrinth.length);
        assertEquals(4, labyrinth[0].length);
    }

    @Test(expected = Exception.class)
    public void targetCoordinates() throws Exception {
        for (int i = 0; i < 100; i++) {
            try {
                assertEquals(i - width, l.getTargetCoordinate(i, (byte) 1));
            } catch (Exception e) {
                throw e;
            }
            try {
                assertEquals(i + 1, l.getTargetCoordinate(i, (byte) 2));
            } catch (Exception e) {
                throw e;
            }
            try {
                assertEquals(i + width, l.getTargetCoordinate(i, (byte) 4));
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
        assertEquals(0, labyrinth[0][0]);
        assertEquals(0, labyrinth[0][4]);
    }

    @Test
    public void addWorkingPassages() throws Exception {
        l.addPassage(0, 1);
        labyrinth = generateByteArray(l);
        assertEquals(2, labyrinth[0][0]);
        assertEquals(8, labyrinth[0][1]);
        l.addPassage(0, width);
        labyrinth = generateByteArray(l);
        assertEquals(2 + 4, labyrinth[0][0]);
        assertEquals(1, labyrinth[1][0]);
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
        int[][] visited = new int[height][width];
        assertEquals(4, l.getListOfNeighbors(width + 3, visited, 0).size());
        assertEquals(3, l.getListOfNeighbors(3, visited, 0).size());
        assertEquals(3, l.getListOfNeighbors(width * height - 5, visited, 0).size());
        assertEquals(2, l.getListOfNeighbors(0, visited, 0).size());
        assertEquals(2, l.getListOfNeighbors(width * height - 1, visited, 0).size());
    }

    @Test
    public void getListOfVisitedNeighbors() throws Exception {
        int[][] visited = new int[height][width];
        visited[3][3] = 2;
        visited[4][3] = 2;
        visited[2][3] = 2;
        visited[3][2] = 2;
        assertEquals(3, l.getListOfNeighbors(width * 3 + 3, visited, 2).size());
        assertEquals(1, l.getListOfNeighbors(width * 3 + 4, visited, 2).size());
        assertEquals(1, l.getListOfNeighbors(width * 3 + 2, visited, 2).size());
        assertEquals(1, l.getListOfNeighbors(width * 4 + 3, visited, 2).size());
    }

    @Test
    public void getListOfEdgesToUnvisitedNeighbors() throws Exception {
        l.addPassage(width + 3, width + 2);
        l.addPassage(width + 3, width + 4);
        l.addPassage(width + 3, 3);
        int[][] visited = new int[height][width];
        assertEquals(3, l.getListOfEdges(width + 3, visited, 0).size());
        assertEquals(1, l.getListOfEdges(width + 4, visited, 0).size());
        assertEquals(1, l.getListOfEdges(width + 2, visited, 0).size());
        assertEquals(1, l.getListOfEdges(3, visited, 0).size());
    }

    @Test
    public void getListOfEdgesToVisitedNeighbors() throws Exception {
        getListOfEdgesToUnvisitedNeighbors();
        int[][] visited = new int[height][width];
        visited[1][2] = 2;
        visited[1][3] = 2;
        visited[1][4] = 2;
        visited[0][3] = 2;
        assertEquals(3, l.getListOfEdges(width + 3, visited, 2).size());
        assertEquals(1, l.getListOfEdges(width + 4, visited, 2).size());
        assertEquals(1, l.getListOfEdges(width + 2, visited, 2).size());
        assertEquals(1, l.getListOfEdges(3, visited, 2).size());

        assertEquals(0, l.getListOfEdges(2, visited, 2).size());
        assertEquals(0, l.getListOfEdges(4, visited, 2).size());
        assertEquals(0, l.getListOfEdges(width + 5, visited, 2).size());
        assertEquals(0, l.getListOfEdges(width + 1, visited, 2).size());
        assertEquals(0, l.getListOfEdges(width * 2 + 3, visited, 2).size());
    }

}
