/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tietorakenteet;

/**
 *
 * @author Omistaja
 */
public interface OmaQueue<T> {
    
    
    public int size();

    public boolean isEmpty();

    public boolean push(T e);
    public T pop();

}
