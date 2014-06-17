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
        
        FileReader fr = null;
        BufferedReader br = null;
        String s, ts = null;
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
            while((ts=br.readLine()) != null){
                i++;
            }
            
            br = new BufferedReader(fr = new FileReader(s));
            ts = br.readLine();
            
            Map2 m = new Map2(i,ts);
            m.insertRow(ts);
            
            while((ts = br.readLine()) != null) {
                m.insertRow(ts);
            }
            
            m.printField();
            System.out.println("\n\n Löydetty reitti \n\n");
            
            Finder f = new Finder();
            long start = System.currentTimeMillis();
            f.findPath(m, m.getGoal(), m.getStart());
            long end = System.currentTimeMillis();
            
            System.out.println("Aikaa reitin etsimiseen meni: "+(end-start)+"ms");
        } catch(Exception e) {
            System.out.println("Tiedoston lukeminen ei onnistunut: "+e);
        }
        
        }
        
        
    }
}
