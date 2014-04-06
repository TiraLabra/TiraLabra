/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import sanapuuro.grid.LetterContainer;

/**
 *
 * @author skaipio
 */
public interface LetterPool {

    LetterContainer[] getLetters();
    boolean letterIsFree(char c);
    /**
     * Marks an index as used and returns the LetterContainer it was pointing
     * at.
     *
     * @return LetterContainer at the current selection.
     */
    LetterContainer useLetter(char c);
    
    /**
     * Frees a LetterContainer with the matching character back for use.
     *
     * @param c Character to match and free
     */
    void unpickLetter(char c);

    /**
     * Clears all selections freeing them for use again.
     */
    void clearLetterPicks();

    /**
     * Replaces all LetterContainers that are not free.
     */
    void replacePickedLetters();
}
