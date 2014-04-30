/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.utils;

import java.util.Comparator;
import sanapuuro.letters.Letter;

/**
 * Used to compare the x and y coordinate values of letter containers.
 * Compares y values if byRow is true, x otherwise.
 * @author skaipio
 */
public class LetterAlphabeticalComparator implements Comparator<Letter> {
    @Override
    public int compare(Letter o1, Letter o2) {
        return Character.compare(o1.character, o2.character);
    }
}
