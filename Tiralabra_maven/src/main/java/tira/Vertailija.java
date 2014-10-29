/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tira;

/**
 * Prioriteettijonon järjestämiseen
 * 
 * @author E
 */
public interface Vertailija<E> {
    public double vertaa( E e1, E e2 );
}
