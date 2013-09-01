
import Controller.Controller;
import Model.LaskinModel;
import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {



        Controller matriisilaskin = new Controller();
        matriisilaskin.run();
        // performanceTest();


    }

    public static void performanceTest() {

        double[][] matriisi3 = {{2, -1, -2},
            {-4, 6, 3},
            {-4, -2, 8}};
        double[][] matriisi4 = {{3, 5, 7, 11},
            {5, 7, 11, 13},
            {7, 11, 13, 17},
            {11, 13, 17, 19}};
        double[][] matriisi5 = {{3, 5, 7, 11, 13},
            {5, 7, 11, 13, 17},
            {7, 11, 13, 17, 19},
            {11, 13, 17, 19, 23},
            {13, 17, 19, 23, 29}};
        double[][] matriisi6 = {{3, 5, 7, 11, 13, 17},
            {5, 7, 11, 13, 17, 19},
            {7, 11, 13, 17, 19, 23},
            {11, 13, 17, 19, 23, 29},
            {13, 17, 19, 23, 29, 31},
            {17, 19, 23, 29, 31, 37}};


        try {

            LaskinModel laskin = new LaskinModel();
            int iteraatiot = 100000;
            System.out.println(laskin.laskeDeterminantti(matriisi6));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));
            System.out.println("Alkioita " + matriisi6.length * matriisi6.length + " Aikaa: " + testDeterminantti(matriisi6, iteraatiot, laskin));





        } catch (Exception e) {
        }

    }

    private static long testDeterminantti(double[][] matriisi, int iteraatiot, LaskinModel laskin) throws Exception {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iteraatiot; i++) {
            laskin.laskeDeterminantti(matriisi);
        }
        return System.currentTimeMillis()
                - startTime;
    }
}
