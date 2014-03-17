/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.letters;

/**
 * Used to store the character, score and spawn frequency associated with the letter.
 * @author skaipio
 */
public class Letter{
    public final char character;    // Alphabet character
    public final int score;         // Score for the letter on word submission.
    public final float frequency;   // How often this letter appears in the letter pool
    public Letter(char character, int score, float frequency){
        this.character = character;
        this.score = score;
        this.frequency = frequency;
    }
    
    @Override
    public String toString(){
        return (this.character + "").toUpperCase();
    }
}
