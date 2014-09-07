package tiralabra_maven;
import yleismetodeja.*;
import suorituskykytestaus.*;
/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main(String[] args) throws Exception {
     
     //   Testaus.testaaKaanto();
//   Testaus.testaaDeterminantti();
      // Testaus.testaaSparseDense();
      // Testaus.testaaNaiviSparseDense();
        /*    
        Testaus.testaaNaivi();
        
        
        try{
        Testaus.testaaStrassen();
        }
        catch (Exception e) {
            System.out.println("meni tiedostonkäsittely vituiksi");
    }
    */
    /*
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
        
        
        System.out.println("matriisi m1");
        double[][] m1 = {{1,6,9},{5,7,4},{1,3,9}};
        System.out.print(Taulukko.toString(Peruslasku.gaussjordan(m1)));
        
        System.out.println("Matriisi m2");
        double[][] m2 = {{1,6,9,4,5},{5,7,4,4,8},{1,3,9,4,6},{1,1,4,19,5},{1,3,5,6,9},{1,0,0,0,0}
        ,{0,1,0,0,0},{0,0,1,0,0},{0,0,0,1,0},{0,0,0,0,1}};
        System.out.print(Taulukko.toString(Peruslasku.gaussjordan(m2)));
        */
        
        Kayttoliittyma kayttis = new Kayttoliittyma();
        kayttis.run();
              
    }
}
