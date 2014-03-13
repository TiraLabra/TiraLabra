/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.utils;

import java.util.Comparator;
import sanapuuro.sanapuuro.grid.LetterContainer;

/**
 * Used to compare the x and y coordinate values of letter containers.
 * Compares y values if byRow is true, x otherwise.
 * @author skaipio
 */
public class LetterContainerCoordinateComparator implements Comparator<LetterContainer> {
    private final boolean byRow;
    public LetterContainerCoordinateComparator(boolean byRow){
        this.byRow = byRow;
    }

    @Override
    public int compare(LetterContainer o1, LetterContainer o2) {
        if (byRow){
            return Integer.compare(o1.getY(), o2.getY());
        }
        return Integer.compare(o1.getX(), o2.getX());
    }
}
