/*
 * Saa käyttää ihan vapasti
 * Public domain
 */
package polunetsinta;

import java.util.Objects;
import verkko.Solmu;

/**
 * Astar luokka käyttää näitä tallettaakseen solmuja kekoon.
 *
 * @see Astar
 * @author Arvoitusmies
 */
public class AstarKekoEntry {

    /**
     * solmu
     */
    private final Solmu solmu;

    /**
     * Astar käyttää tätä kenttää matka+heuristiikka arvon talletukseen.
     */
    private final Double priority;

    /**
     * Perus asettaja-konstruktori
     *
     * @param solmu
     * @param priority
     */
    public AstarKekoEntry(Solmu solmu, Double priority) {
        this.solmu = solmu;
        this.priority = priority;
    }

    /**
     * getteri
     *
     * @return
     */
    public Solmu getSolmu() {
        return solmu;
    }

    /**
     * getteri
     *
     * @return
     */
    public Double getPriority() {
        return priority;
    }

    /**
     * Huom! Vertaa vain solmuja keskenään, priorityllä ei väliä. Tämä on
     * kätevää koska silloin on helpompi etsiä tiettyä solmua keossa luokassa
     * Astar.
     *
     * @param obj
     * @return true jos solmut equals, muulloin false.
     */
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
