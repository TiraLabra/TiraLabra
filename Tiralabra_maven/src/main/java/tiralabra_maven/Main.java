package tiralabra_maven;

/**
 * Luokka joka suorittaa ohjelman export MAVEN_OPTS="-Xmx2048m -Xms512m" mvn exec:java
 *
 * @author esaaksvu
 */
public class Main {

    public static void main(String[] args) {
        suoritusKyky s = new suoritusKyky();
        PuuRajapinta[] puut = null;

        for (;;) {
            if (puut == null) {
                puut = s.luoPuut();
            }
            String t = s.operoiPuita(puut);
            if (t.equals("nollaa")) {
                puut = null;
                continue;
            }
            if (t.equals("")) {
                return;
            }
            System.out.println(t);
        }

    }
}
