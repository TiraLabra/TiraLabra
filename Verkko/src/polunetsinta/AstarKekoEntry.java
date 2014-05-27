/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package polunetsinta;

import java.util.Objects;
import verkko.Solmu;

public class AstarKekoEntry {

    private Solmu solmu;
    private Double priority;

    public Solmu getSolmu() {
        return solmu;
    }

    public void setSolmu(Solmu s) {
        this.solmu = s;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    public AstarKekoEntry(Solmu solmu, Double priority) {
        this.solmu = solmu;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AstarKekoEntry other = (AstarKekoEntry) obj;
        if (!Objects.equals(this.solmu, other.solmu)) {
            return false;
        }
        return true;
    }


    
}
