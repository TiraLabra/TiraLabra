
import sovelluslogiikka.Laskin;

/**
 * Ohjelman suoritusluokka
 *
 * @author jukkapit
 */
public class Main {

    /**
     * Suorittaa ohjelman annetuilla parametreilla
     *
     * @param args Komentoriviparametrit
     */
    public static void main(String[] args) {
        Laskin laskin = new Laskin();
        laskin.kaynnista();

        //Suorituskykytestit
        String laskutoimitus1 = "3*9-5";
        long testi1AikaAlussa = System.currentTimeMillis();
        int ratkaisu1 = laskin.ratkaiseLaskutoimitus(laskutoimitus1);
        long testi1AikaLopussa = System.currentTimeMillis();
        System.out.println("Laskutoimitukseen " + laskutoimitus1 + " = " + ratkaisu1 + " kului aikaa: " + (testi1AikaLopussa - testi1AikaAlussa) + "ms.");

        String laskutoimitus2 = "45/5+(500-30))";
        long testi2AikaAlussa = System.currentTimeMillis();
        int ratkaisu2 = laskin.ratkaiseLaskutoimitus(laskutoimitus2);
        long testi2AikaLopussa = System.currentTimeMillis();
        System.out.println("Laskutoimitukseen " + laskutoimitus2 + " = " + ratkaisu2 + " kului aikaa: " + (testi2AikaLopussa - testi2AikaAlussa) + "ms.");
    }
}