package kolmiopeli.domain;

/**
 * Koordinaatti olio joka on sama kuin toinen koordinaatti jos niitten rivi ja
 * sarake ovat samat.
 *
 * @author Eemi
 */
public class Koordinaatti implements Comparable {

    private int rivi;
    private int sarake;

    /**
     * Luo annetusta rivista ja sarakkeesta koordinaatin.
     *
     * @param rivi
     * @param sarake
     */
    public Koordinaatti(int rivi, int sarake) {
        this.rivi = rivi;
        this.sarake = sarake;
    }

    public int getRivi() {
        return rivi;
    }

    public int getSarake() {
        return sarake;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.rivi;
        hash = 97 * hash + this.sarake;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Koordinaatti other = (Koordinaatti) obj;
        if (this.rivi != other.rivi) {
            return false;
        }
        if (this.sarake != other.sarake) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + rivi + ", " + sarake + "}";
    }

    @Override
    public int compareTo(Object o) {
        Koordinaatti verrattava = (Koordinaatti) o;
        if (this == verrattava) {
            return 0;
        } else if (this.rivi > verrattava.getRivi()) {
            return 1;
        } else if (this.rivi < verrattava.getRivi()) {
            return -1;
        } else {
            if (this.sarake > verrattava.getSarake()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public boolean osoittaakoKoordinaatinRuutuYlospain() {
        if ((rivi + sarake) % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
