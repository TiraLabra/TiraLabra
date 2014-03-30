/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.letters;

import sanapuuro.grid.LetterContainer;
import java.util.HashSet;
import java.util.Set;

/**
 * Letter pool where all the letters a player can place to a letter grid are
 * kept.
 *
 * @author skaipio
 */
public class LetterPool {

    public final int poolSize = 8;  // The maximum number of letters that can be placed at once.
    private final Letters letters;  // For getting random letters.
    private final LetterContainer[] pool = new LetterContainer[poolSize];   // Pool for holding the letters.
    private final boolean[] lettersFree = new boolean[poolSize];

    public LetterPool(Letters letters) {
        this.letters = letters;
        for (int i = 0; i < poolSize; i++) {
            this.lettersFree[i] = true;
            this.pool[i] = new LetterContainer(letters.getRandomLetter(), true, i);
        }
    }

    public LetterContainer[] getLetters() {
        return this.pool.clone();
    }

    /**
     * Marks an index as used and returns the LetterContainer it was pointing
     * at.
     *
     * @return LetterContainer at the current selection.
     */
    public LetterContainer useLetter(char c) {
        for (int i = 0; i < this.poolSize; i++) {
            if (this.lettersFree[i] && this.pool[i].letter.character == c) {
                this.lettersFree[i] = false;
                return this.pool[i];
            }
        }
        return null;
    }

    /**
     * Clears all selections freeing them for use again.
     */
    public void clearLetterPicks() {
        for (int i = 0; i < this.poolSize; i++) {
            this.lettersFree[i] = true;
        }
    }

    /**
     * Replaces all LetterContainers that are not free.
     */
    public void replacePickedLetters() {
        for (int i = 0; i < this.poolSize; i++) {
            if (!this.lettersFree[i]) {
                this.lettersFree[i] = true;
                this.pool[i] = new LetterContainer(letters.getRandomLetter(), true, i);
            }
        }
    }

    /**
     * Frees a LetterContainer with the matching character back for use.
     * @param c Character to match and free
     */
    public void unpickLetter(char c) {
        for (int i = 0; i < this.poolSize; i++) {
            if (!this.lettersFree[i] && this.pool[i].letter.character == c) {
                this.lettersFree[i] = true;
            }
        }
    }

    public String toString() {
        StringBuilder letters = new StringBuilder(8);
        for (LetterContainer letter : this.getLetters()) {
            if (letter != null) {
                letters.append(letter.letter.character);
            } else {
                letters.append('#');
            }
        }
        return letters.toString();
    }

    public boolean hasFreeLetter(char c) {
        for (int i = 0; i < this.poolSize; i++) {
            if (this.lettersFree[i] && this.pool[i].letter.character == c) {
                return true;
            }
        }
        return false;
    }
}
