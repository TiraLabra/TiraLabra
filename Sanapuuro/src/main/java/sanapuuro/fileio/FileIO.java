/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.fileio;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sanapuuro.letters.Letter;

/**
 *
 * @author skaipio
 */
public class FileIO {
    private final Pattern letterRowPattern = Pattern.compile("([a-z])\\s(\\d\\d?)\\s(0\\.\\d+)\\D*");
    
    public List<Letter> readInLettersFromFile(String path) {
        List<Letter> letters = new ArrayList<>();
        try {
            InputStream file = ClassLoader.getSystemResourceAsStream(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                letters.add(parseLetterFrom(line));
            }
        } catch (Exception e) {
            System.out.println("Letters could not be read from " + path);
            System.exit(-1);
        }
        return letters;
    }
    
    /**
     * @param path
     * @return All valid English words given by a file.
     */
    public List<String> readInWordsFromFile(String path, int maxLength) {
        List<String> scannedWords = new ArrayList<>();
        try {
            InputStream file = ClassLoader.getSystemResourceAsStream(path);
            Scanner reader = new Scanner(file);            
            while (reader.hasNext()) {
                String word = reader.nextLine();
                if (word.length() <= maxLength)
                    scannedWords.add(word);
            }
        } catch (Exception e) {
            System.out.println("Words could not be read from " + path);
            System.exit(-1);
        }
        return scannedWords;
    }

    private Letter parseLetterFrom(String line) {
        Matcher matcher = letterRowPattern.matcher(line);
        matcher.matches();
        char character = matcher.group(1).charAt(0);
        int score = Integer.parseInt(matcher.group(2));
        float frequency = Float.parseFloat(matcher.group(3));
        return new Letter(character, score, frequency);
    }
}
