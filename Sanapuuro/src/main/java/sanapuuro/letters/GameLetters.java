/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.letters;

import sanapuuro.utils.LetterFrequencyComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Used for reading in valid English letters from a file.
 *
 * @author skaipio
 */
public class GameLetters implements Letters {
    private final Random random;
    private final List<Letter> letters;

    public GameLetters(Random randomizer, List<Letter> letters) {
        this.random = randomizer;
        this.letters = letters;
        Collections.sort(this.letters, new LetterFrequencyComparator());
    }

    /**
     * Builds a letter matching the given character.
     *
     * @param c Character to match with letter.
     * @return A letter based on the given character.
     */
    @Override
    public Letter getLetterMatchingCharacter(char c) {
        for (Letter letter : this.letters) {
            if (letter.character == c) {
                return letter;
            }
        }
        return null;
    }

    /**
     * Gets a random letter based on its frequency value.
     *
     * @return A random letter.
     */
    @Override
    public Letter getRandomLetter() {
        try {
            if (this.letters.isEmpty()) {
                throw new Exception(
                        "No letters have been read in. Check that the file english_letters exists.");
            }
            float rnd = (float) random.nextDouble();
            float accumulated = 0;
            int i = 0;
            Letter letter;
            do {
                letter = this.letters.get(i);
                accumulated += letter.frequency;
                i++;
            } while (accumulated < rnd && i < this.letters.size());
            return letter;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
            return null;
        }
    }

    @Override
    public List<Letter> getAllLetters() {
        return new ArrayList<>(this.letters);
    }
}
