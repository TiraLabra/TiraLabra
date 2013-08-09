/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 * Rajapinta objekteille, jotka sijaitsevat koordinaateissa pelialueella.
 * @author Emleri
 */
public interface Peliobjekti {
    Koordinaatit getKoordinaatit();
    void setKoordinaatit(Koordinaatit k);
}
