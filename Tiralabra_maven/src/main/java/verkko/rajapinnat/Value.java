/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.rajapinnat;

/**
 * Verkon solmu
 * 
 * @author E
 */
public interface Value {
    /**
     * Kahden solmun välinen etäisyys
     * 
     * @param s Vertailtava solmu
     * @return 
     */
    double etaisyys( Value s );
}
