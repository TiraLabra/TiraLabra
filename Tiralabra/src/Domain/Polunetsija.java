/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Emleri
 */
public interface Polunetsija {
    /*
     * Palauttaa lyhimmän polun lähtöpisteestä kohteeseen parametrina saadussa 
     * luolassa taulukkona koordinaatteja kulkujärjestyksessä. Käytetty
     * polunetsintäalgoritmi riippuu interfacen toteutuksesta.
     * 
     * @param lahto lähtökoordinaatit
     * @param kohde kohdekoordinaatit
     * @return polku koordinaattitauluna
     */
    Koordinaatit[] etsi(Koordinaatit lahto, Koordinaatit kohde, Luola luola);
}
