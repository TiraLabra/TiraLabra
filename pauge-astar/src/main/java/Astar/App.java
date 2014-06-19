package Astar;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Hello world!
 *
 */
public class App 
{
    private static FileReader fr;
    private static BufferedReader br;
    private static String s, ts;
    private static Map m;
    
    App() { 
        fr = null;
        br = null;
        s = null;
        ts = null;
        m = null;
    }
    
    /**
     * Main program
     * 
     * @param args 
     */
    public static void main( String[] args ) {
        
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Syötä tiedoston nimi, nolla lopettaa ohjelman");
            s = in.nextLine();
            if(s.equals("0")) {
              System.out.println("Exit");
              break;
            }
            try {
                br = new BufferedReader(fr = new FileReader(s));
                int i = 0;
                while((ts=br.readLine()) != null){                  //determine the number of rows the map has
                    i++;
                }
                br = new BufferedReader(fr = new FileReader(s));    //now fill the rows
                ts = br.readLine();
                m = new Map(i,ts);
                m.insertRow(ts);
                while((ts = br.readLine()) != null) {
                    m.insertRow(ts);
                }

                m.printField();
                long duration = performSearch(m);
                System.out.println("Aikaa reitin etsimiseen meni: "+(duration)+"ms");

            }   catch(Exception e) {
                System.out.println("Tiedoston lukeminen ei onnistunut: "+e);
        }}
    }
    
    /**
     * Performs the search
     * 
     * @param m the map in question
     * @return the time it took to find a path
     */
    public static long performSearch(Map m) {
        System.out.println("\n\n Löydetty reitti: \n\n");
        Finder f = new Finder();
        long start = System.currentTimeMillis();
        f.findPath(m, m.getGoal(), m.getStart());
        long end = System.currentTimeMillis();
        return end-start;
    }
}
