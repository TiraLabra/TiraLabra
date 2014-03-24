/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.letters;

/**
 * Interface for getting random letters.
 * @author skaipio
 */
public interface Letters {
    /**
     * Gets a Letter instance matching a given character. This is probably obsolete.
     * @param c character to match to a letter.
     * @return A letter matching the character.
     */
    public Letter getLetterMatchingCharacter(char c);
    /**
     * Gets a random letter from the alphabet.
     * @return A random letter.
     */
    public Letter getRandomLetter();
}
