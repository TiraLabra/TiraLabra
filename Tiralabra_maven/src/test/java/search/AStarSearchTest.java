    package search;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 *
 */
public class AStarSearchTest {
    
    AStarSearch search;
    Random rand;
    int [][] validMap = {{1, 1, 2, 3, 2},
                         {2, 6, 2, 1, 1},
                         {1, 3, 9, 2, 2},
                         {2, 1, 1, 2, 1}};
    
    @Before
    public void setUp() {
        search = new AStarSearch(validMap);
        rand = new Random();
    }
    
    public int[][] createRandomMap(int width, int height, int range) {
        int[][] map = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                map[x][y] = rand.nextInt(range) + 1;
            }
        }
        return map;
    }
    
    @Test
    public void badSearchTermDoesNotWork() {
        assertEquals("Search value(s) out of map range. Max X: 4, max Y: 3.", search.search(0, 0, 10, 10));
    }
    
    @Test
    public void searchWorksWithCorrectInput() {
        assertEquals("(0, 0: time: 0) -> (0, 1: time: 2) -> (0, 2: time: 3) -> (0, 3: time: 5) -> (1, 3: time: 6) -> (2, 3: time: 7) -> (3, 3: time: 9)", search.search(0, 0, 3, 3));
    }
    
    @Test
    public void routeWithZeroLength() {
        assertEquals("(0, 0: time: 0)", search.search(0, 0, 0, 0));
    }
    
    @Test
    public void downRightUpLeftSearch() {
        assertEquals("(4, 3: time: 0) -> (3, 3: time: 2) -> (2, 3: time: 3) -> (1, 3: time: 4) -> (0, 3: time: 6) -> (0, 2: time: 7) -> (0, 1: time: 9) -> (0, 0: time: 10)", search.search(4, 3, 0, 0));
    }
    
    @Test
    public void mapPrintingWorks() {
        assertEquals("   0 1 2 3 4 X\n" + 
                     " 0 1 1 2 3 2\n" + 
                     " 1 2 6 2 1 1\n" + 
                     " 2 1 3 9 2 2\n" + 
                     " 3 2 1 1 2 1\n" +
                     " Y", search.printMap());
    }
    
    @Test
    public void searchTimeOnASmallMapUnder10ms() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            search.search(0, 0, 4, 4);
        }
        long endTime = System.currentTimeMillis();
        long finalTime = endTime - startTime;
        finalTime = finalTime / 10;
        assertTrue(finalTime < 10);
    }
    
    @Test
    public void randomized10x10MapTimeUnder10ms() {
        int[][] randomMap = createRandomMap(10, 10, 10);
        AStarSearch randMapSearch = new AStarSearch(randomMap);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            randMapSearch.search(0, 0, 9, 9);
        }
        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) / 10 < 10);
    }
    
    @Test
    public void random10x10MapRandomRoutes() {
        int[][] randomMap = createRandomMap(10, 10, 10);
        AStarSearch randMapSearch = new AStarSearch(randomMap);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            randMapSearch.search(rand.nextInt(10), rand.nextInt(10), rand.nextInt(10), rand.nextInt(10));
        }
        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) / 10 < 10);
    }
    
    @Test
    public void randomized100x100MapTimeUnder100ms() {
        int[][] randomMap = createRandomMap(100, 100, 10);
        AStarSearch randMapSearch = new AStarSearch(randomMap);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            randMapSearch.search(0, 0, 99, 99);
        }
        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) / 10 < 100);
    }
}
