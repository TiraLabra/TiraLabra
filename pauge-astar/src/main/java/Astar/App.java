package Astar;

/**
 * Hello world!
 *
 */
public class App 
{
    public Finder f;
    App() { 
    }
    /**
     * Main program
     * 
     * @param args 
     */
    public static void main( String[] args )
    {
        Finder f = new Finder();
        Map kartta1 = new Map();
        long start = System.currentTimeMillis();
        f.findOptimal(kartta1, kartta1.field[9][2], kartta1.field[0][17]);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
