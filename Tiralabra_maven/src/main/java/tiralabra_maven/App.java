package tiralabra_maven;
import yleismetodeja.*;
/**
 * Hello world!
 *
 */
public class App 
{
    
    
    public static void print(char[][] printattava) {
        for (int i = 0; i < printattava[0].length;i++) {
            for (int j = 0; j < printattava[1].length; j++) {
                System.out.print(printattava[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    public static void print(String[][] printattava) {
        for (int i = 0; i < printattava[0].length;i++) {
            for (int j = 0; j < printattava[1].length; j++) {
                System.out.print(printattava[i][j] + "\t");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }    
    
    public static  void print(String[] printattava) {
        for (int i = 0; i < printattava.length; i++) {
            System.out.print(printattava[i] + " ");
        }
        System.out.println("");
    }
    
    public static void main( String[] args ) throws Exception
    {
        
        String[][] testi = new String[2][3];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                testi[i][j] = i + "" + j;
            }
        }

        double[][] matriisi = {{1,2,3},{1,2,3},{4,5,6}};        
        double[][] matriisi2 = {{1,2,3},{1,2,3},{4,5,6}};        
                
        
        System.out.println("testi[0].length " + testi[0].length);
        System.out.println("testi[1].length " + testi[1].length);
        System.out.println("testi.length " + testi.length);
        System.out.print(Taulukko.toString(matriisi));
        if (Taulukko.toString(matriisi).equals(Taulukko.toString(matriisi2))) {
            System.out.println("toimii");
        }
        /*
        Kayttoliittyma kayttis = new Kayttoliittyma();
        kayttis.run();
                */
    }
}
