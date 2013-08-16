package tiralabra_maven;

/**
 * Luokka joka suorittaa ohjelman
 * @author esaaksvu
 */
public class Main {

    public static void main(String[] args) {
        PuuRajapinta a = new AVLHakuPuu();
        PuuRajapinta b = new BinaariHakupuu();
        
        long startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            a.lisaaSolmu(new Solmu(i));
        }
        long e1 = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
         for (int i = 0; i < 100; i++) {
            b.lisaaSolmu(new Solmu(i));
        }
        long e2 = System.nanoTime() - startTime;
        
        System.out.println(a);
        System.exit(0);
        System.out.println("AVLpuu: "+ e1+"ns");
        System.out.println("--------vs---------\n" + "BinääriPuu: "+e2+ "ns");
        long win = (e1<e2) ? e2-e1 : e2-e1;
        win = win/10000000;
        System.out.println("--------------------------\n");
        System.out.println("AVLpuu oli: "+(win)+"ms nopeampi/hitaampi");
        System.out.println("--------------------------\n");

    }
}
