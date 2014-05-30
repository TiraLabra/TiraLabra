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

    public AstarKekoEntry(Solmu solmu, Double priority) {
        this.solmu = solmu;
        this.priority = priority;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AstarKekoEntry other = (AstarKekoEntry) obj;
        return Objects.equals(this.solmu, other.getSolmu());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.solmu);
        return hash;
    }

}
