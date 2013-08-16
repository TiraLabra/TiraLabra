
import Controller.Controller;
import View.MatriisiTaulukko;






public class Main {

    public static void main(String[] args) {
//        Laskin laskin = new Laskin();
//
//        double[][] matriisi1 = {{1, 2},
//            {3, 4}};
//
//        double[][] matriisi2 = {{4, 3},
//            {2, 1}};
//        try {
//            tulostaMatriisi(laskin.kerroMatriisit(matriisi1, matriisi2));
//        } catch (Exception ex) {
//            
//        }    
            
     
    //
    //        tulostaMatriisi(matriisi1);
    //
    //        System.out.println("----");
    //        try {
    //            tulostaMatriisi(laskin.kerroMatriisit(matriisi1, matriisi2));
    //        } catch (Exception e) {
    //            System.out.println(e.getMessage());
    //        }       
        
        Controller matriisilaskin = new Controller();
        matriisilaskin.run();
//        
//        double[][] matriisi3 = {{2, -1,-2},
//            {-4,6,3},
//            {-4,-2,8}};
//        
//        tulostaMatriisi(matriisi3);
//        System.out.println("");
//        try {
//            System.out.println(laskin.laskeDeterminantti(matriisi3));
//  
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }                
//        System.out.println("");
//        tulostaMatriisi(matriisi3);
//        
    }

    static public void tulostaMatriisi(double[][] matriisi) {
        for (int rivi = 0; rivi < matriisi.length; rivi++) {
            for (int sarake = 0; sarake < matriisi[0].length; sarake++) {
                System.out.print(matriisi[rivi][sarake] + ",");
            }
            System.out.println("");
        }
    }
}
