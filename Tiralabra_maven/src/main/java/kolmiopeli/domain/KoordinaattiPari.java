
package kolmiopeli.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Kahden kolmion koordinaatit jotka vaihdettuna tuottavat pisteita.
 * @author Eemi
 */
public class KoordinaattiPari {
    private Set<Koordinaatti> koordinaatit;

    /**
     * Luo joukon josta voi tarkistaa nopeasti kuuluuko jokin koordinaatti pariin.
     * @param rivi1
     * @param sarake1
     * @param rivi2
     * @param sarake2 
     */
    public KoordinaattiPari(int rivi1, int sarake1, int rivi2, int sarake2) {
        this.koordinaatit = new HashSet(2);
        this.koordinaatit.add(new Koordinaatti(rivi1, sarake1));
        this.koordinaatit.add(new Koordinaatti(rivi2, sarake2));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        Koordinaatti[] k = (Koordinaatti[]) this.koordinaatit.toArray();
        hash = 71 * hash + k[0].hashCode() + k[1].hashCode();
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
        final KoordinaattiPari other = (KoordinaattiPari) obj;
        if (this.koordinaatit.size() != other.koordinaatit.size()) {
            return false;
        }
        if (!this.koordinaatit.containsAll(other.koordinaatit)) {
            return false;
        }
        return true;
    }
    

    public Set<Koordinaatti> getKoordinaatit() {
        return koordinaatit;
    }


    
    
    
    
    
    
    
    
    
}
