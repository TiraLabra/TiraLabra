/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.utils;

import java.util.Comparator;
import sanapuuro.letters.Letter;

/**
 * Used to compare the frequencies of letters.
 * @author skaipio
 */
public class LetterFrequencyComparator implements Comparator<Letter> {

    @Override
    public int compare(Letter o1, Letter o2) {
        return Float.compare(o1.frequency, o2.frequency);
    }
    
}
