package com.mycompany.tiralabra_maven;

/**
 * Lueteltu tyyppi (enum), joka pitää sisällään tässä sovelluksessa toteutettujen
 * reittialgoritmien nimet.
 *
 * @author mikko
 */
public enum AlgoritmiTyyppi {

    BREADTH_FIRST {
                @Override
                public String toString() {
                    return "Breadth First";
                }
            }, DIJKSTRA {
                @Override
                public String toString() {
                    return "Dijkstra";
                }
            }, GREEDY_BEST_FIRST {
                @Override
                public String toString() {
                    return "Greedy Best First";
                }
            }, A_STAR {
                @Override
                public String toString() {
                    return "A*";
                }
            }
}
