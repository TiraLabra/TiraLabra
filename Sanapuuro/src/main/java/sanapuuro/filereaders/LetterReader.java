/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.filereaders;

import sanapuuro.utils.LetterFrequencyComparator;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sanapuuro.letters.Letter;
import sanapuuro.letters.Letters;

/**
 * Used for reading in valid English letters from a file.
 *
 * @author skaipio
 */
public class LetterReader implements Letters {

    private final Pattern linePattern = Pattern.compile("([a-z])\\s(\\d\\d?)\\s(0\\.\\d+)\\D*");
    private final String englishLettersPath = "letters/english_letters";
    private final Random random;
    private final LetterFrequencyComparator letterFreqComparator = new LetterFrequencyComparator();
    private final List<Letter> sortedLetters;

    public LetterReader(Random randomizer) {
        this.random = randomizer;
        this.sortedLetters = this.readAndSortLetters();
    }

    /**
     * Builds a letter matching the given character.
     *
     * @param c Character to match with letter.
     * @return A letter based on the given character.
     */
    @Override
    public Letter getLetterMatchingCharacter(char c) {
        for (Letter letter : this.sortedLetters) {
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
            if (this.sortedLetters.isEmpty()) {
                throw new Exception(
                        "No letters have been read in. Check that the file english_letters exists.");
            }
            float rnd = (float) random.nextDouble();
            float accumulated = 0;
            int i = 0;
            Letter letter;
            do {
                letter = this.sortedLetters.get(i);
                accumulated += letter.frequency;
                i++;
            } while (accumulated < rnd && i < this.sortedLetters.size());
            return letter;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Letter(' ', 0, 0);
        }
    }

    private List<Letter> readAndSortLetters() {
        List<Letter> letters = new ArrayList<>();
        try {
            InputStream file = ClassLoader.getSystemResourceAsStream(englishLettersPath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                letters.add(this.parseLetterFrom(line));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println(e.getCause());
        }
        Collections.sort(letters, this.letterFreqComparator);
        return letters;
    }

    private Letter parseLetterFrom(String line) {
        Matcher matcher = this.linePattern.matcher(line);
        matcher.matches();
        char character = matcher.group(1).charAt(0);
        int score = Integer.parseInt(matcher.group(2));
        float frequency = Float.parseFloat(matcher.group(3));
        return new Letter(character, score, frequency);
    }

    @Override
    public List<Letter> getAllLetters() {
        return new ArrayList<>(this.sortedLetters);
    }
}
