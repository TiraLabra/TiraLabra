/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verkko.satunnainen;

/**
 * Erilaisia satunnaismuuttujia varten. Käytetään satunnaisgeneroiduissa verkoissa.
 * 
 * @author E
 */
public interface Funktio {
    /**
     * Satunnaismuuttujan arvo
     * 
     * @param kerroin Parametri laskemiseen
     * @return Satunnaismuuttujan arvo
     */
    public double laskeSatunnaisluku( double kerroin );
}
