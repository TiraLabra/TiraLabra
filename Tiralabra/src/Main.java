
import Controller.Controller;
import Model.LaskinModel;
import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
 
 

        Controller matriisilaskin = new Controller();
        matriisilaskin.run();


    }
    
    public static void performanceTest() {
    
           double[][] matriisi4 = {{2, -1, -2},
            {-4, 6, 3},
            {-4, -2, 8}};
        double[][] matriisi5 = {{3, 5, 7, 11, 13},
            {5, 7, 11, 13, 17},
            {7, 11, 13, 17, 19},
            {11, 13, 17, 19, 23},
            {13, 17, 19, 23, 29}};
        
        
        try {
 
            LaskinModel laskin = new LaskinModel();
            
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                laskin.laskeDeterminantti(matriisi5);
            }
            long endTime = System.currentTimeMillis();
            long difference1 = endTime - startTime;

            double kerroin1 = difference1 / (matriisi5.length * matriisi5.length);
            System.out.println("Aika: " + difference1);
            System.out.println("Aika jaettuna alkioilla. Alkioita: " + (matriisi5.length * matriisi5.length) + " Kerroin: " + kerroin1);
            
            System.out.println("");
            startTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                laskin.laskeDeterminantti(matriisi4);
            }
            endTime = System.currentTimeMillis();
            difference1 = endTime - startTime;

            kerroin1 = difference1 / (matriisi4.length * matriisi4.length);
            System.out.println("Aika: " + difference1);
            System.out.println("Aika jaettuna alkioilla. Alkioita: " + (matriisi4.length * matriisi4.length) + " Kerroin: " + kerroin1);
            
            startTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                laskin.laskeDeterminantti(matriisi5);
            }
            endTime = System.currentTimeMillis();
            System.out.println("");
            long difference2 = endTime - startTime;
            double kerroin2 = difference2 / (matriisi5.length * matriisi5.length);
            System.out.println("Aika: " + difference2);
            System.out.println("Aika jaettuna alkioilla. Alkioita: " + (matriisi5.length * matriisi5.length) + " Kerroin: " + kerroin2);
            
            startTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                laskin.laskeDeterminantti(matriisi5);
            }
            endTime = System.currentTimeMillis();
            System.out.println("");
            difference2 = endTime - startTime;
            kerroin2 = difference2 / (matriisi5.length * matriisi5.length);
            System.out.println("Aika: " + difference2);
            System.out.println("Aika jaettuna alkioilla. Alkioita: " + (matriisi5.length * matriisi5.length) + " Kerroin: " + kerroin2);
            
            
            System.out.println("");
            startTime = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                laskin.laskeDeterminantti(matriisi4);
            }
            endTime = System.currentTimeMillis();
            difference1 = endTime - startTime;

            kerroin1 = difference1 / (matriisi4.length * matriisi4.length);
            System.out.println("Aika: " + difference1);
            System.out.println("Aika jaettuna alkioilla. Alkioita: " + (matriisi4.length * matriisi4.length) + " Kerroin: " + kerroin1);
            
            
        } catch (Exception e) {
        }
    
    }
}
    
