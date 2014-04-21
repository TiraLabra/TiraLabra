/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sanapuuro.datastructures.DJB2ForStrings;
import sanapuuro.datastructures.FNVOneForStrings;
import sanapuuro.datastructures.GeneralHashFuncForStrings;
import sanapuuro.datastructures.GeneralHashFuncForStrings2;
import sanapuuro.datastructures.HashFunction;
import sanapuuro.datastructures.MurmurHash3ForStrings;
import sanapuuro.fileio.FileIO;

/**
 *
 * @author skaipio
 */
public class PerformanceTests {

    public static void main(String[] args) {
        FileIO fileIO = new FileIO();
        List<String> words = fileIO.readInWordsFromFile("words/english_words", 8);
        int generalStringHashDuplicates = duplicatesWithHashFunc(words, new GeneralHashFuncForStrings());
        int djb2Duplicates = duplicatesWithHashFunc(words, new DJB2ForStrings());
        int fnvOneDuplicates = duplicatesWithHashFunc(words, new FNVOneForStrings());
        int murmurDuplicates = duplicatesWithHashFunc(words, new MurmurHash3ForStrings());
        System.out.println("Number of duplicate hash values with general hash function for strings: " + generalStringHashDuplicates);
        System.out.println("Number of duplicate hash values with djb2: " + djb2Duplicates);
        System.out.println("Number of duplicate hash values with FNVOne: " + fnvOneDuplicates);
        System.out.println("Number of duplicate hash values with MurmurHash3: " + murmurDuplicates);
    }

    private static int duplicatesWithHashFunc(List<String> words, HashFunction<String> function) {
        Set<Integer> hashDuplicates = new HashSet<>();
        int duplicates = 0;
        int m = function.calculateM(words.size(), 0.20);
        System.out.println("m = " + m);
        for (String string : words) {
            int hash = function.getNormalHash(string, m);
            if (hashDuplicates.contains(hash)) {
                duplicates++;
            } else {
                hashDuplicates.add(hash);
            }
        }
        return duplicates;
    }
}
