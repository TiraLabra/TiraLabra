/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.words;

/**
 * Holds valid words.
 * @author skaipio
 */
public interface WordList {
    /**
     * Checks if the given word is on the word list.
     * @param word Word to check.
     * @return True if the word was found on the list, false otherwise.
     */
    boolean hasWord(String word);
}
