package tira.tiralabra;

/**
 *
 * @author joonaslaakkonen
 */
public class Run {
    
    public static int[][] verkko = new int[][] {
        {12,4,Integer.MAX_VALUE,4,5},
        {2,1,3,Integer.MAX_VALUE,6},
        {3,3,Integer.MAX_VALUE,Integer.MAX_VALUE,9},
        {4,3,Integer.MAX_VALUE,4,7},
        {5,7,5,9,6}
    };
    //Int max value kuvaa tilannetta, jossa yhteyttä solmujen välillä ei ole.
    
    
    public static void main( String[] args ){
        Star star = new Star(verkko);
        Dijkstra diks = new Dijkstra(verkko);
        
        diks.tutki();
        star.tutki();
    }
    
}