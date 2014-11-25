/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.rajapinnat;

import verkko.Reitti;

/**
 *
 * @author E
 */
public interface Graph {
    Iterable<Edge> getKaaret( Value alku, Value loppu );
    Iterable<Value> getNaapurit( Value alku );
}


