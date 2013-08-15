
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
//        laskin.kaynnista();
        long aikaAlussa1 = System.currentTimeMillis();
        int tulos = laskin.ratkaiseLaskutoimitus("2+2+1+2+3+3+3+4+4+4+4+4+4+4+4+4+4+4+4");
        long aikaLopussa1 = System.currentTimeMillis();
        System.out.println("(2+2)*5000 = " + tulos+ " Aikaa kului: " + (aikaLopussa1 - aikaAlussa1) + "ms");

        String laskutoimitus = "2+2+(1+2+(3+3+3*(4+4)/4+4)*4+4+4)+4+4+4+4+42+2+(1+2+(3+3+3*(4+4)/4+4)*4+4+4)+4+4+4+4+42+2+(1+2+(3+3+3*(4+4)/4+4)*4+4+4)+4+4+4+4+42+2+(1+2+(3+3+3*(4+4)/4+4)*4+4+4)+4+4+4+4+42+2+(1+2+(3+3+3*(4+4)/4+4)*4+4+4)+4+4+4+4+42+2+(1+2+(3+3+3*(4+4)/4+4)*4+4+4)+4+4+4+4+4";
        aikaAlussa1 = System.currentTimeMillis();
        tulos = laskin.ratkaiseLaskutoimitus(laskutoimitus);
        aikaLopussa1 = System.currentTimeMillis();
        System.out.println("Aikaa kului: " + (aikaLopussa1 - aikaAlussa1) + "ms  --  " + laskutoimitus + "="+tulos);

    }
}