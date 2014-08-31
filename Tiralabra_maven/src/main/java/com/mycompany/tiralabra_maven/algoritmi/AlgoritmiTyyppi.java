/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.algoritmi;

/**
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
