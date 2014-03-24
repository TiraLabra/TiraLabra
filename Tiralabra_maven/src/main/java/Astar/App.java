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
    
    public static void main( String[] args )
    {
        Finder f = new Finder();
        Map kartta1 = new Map();
        //kartta1.printField();
        f.findOptimal(kartta1, kartta1.field[6][2], kartta1.field[0][6]);
        //System.out.println("Done");
        //System.out.println(kartta1.field[1][6].getPrio());
        //kartta1.printField();
    }
}
