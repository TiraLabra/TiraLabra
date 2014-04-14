/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.utils;

import java.util.List;
import sanapuuro.datastructures.HashFuncs;
import sanapuuro.datastructures.MyHashSet;
import sanapuuro.letters.LetterContainer;

/**
 *
 * @author skaipio
 */
public class Util {
    public static String stringFromLetterContainers(List<LetterContainer> containers){
        StringBuilder letters = new StringBuilder();
        for(LetterContainer container : containers){
            letters.append(container.letter.character);
        }
        return letters.toString();
    }
    
    public static String stringFromLetterContainers(LetterContainer[] containers){
        StringBuilder letters = new StringBuilder();
        for(LetterContainer container : containers){
            letters.append(container.letter.character);
        }
        return letters.toString();
    }

    /**
     * Creates a new MyHashSet from the objects in the given list.
     * @param <T> Type of objects.
     * @param objs List of objects to add to the set.
     * @param hashFunc Hash function for the set.
     * @return A new MyHashSet of type T.
     */
    public static <T> MyHashSet<T> convertListToMyHashSet(List<T> objs, HashFuncs<T> hashFunc){
        MyHashSet<T> set = new MyHashSet<>(objs.size(), hashFunc);
        for (T obj : objs){
            set.add(obj);
        }
        return set;
    }

    public static String getStringFromFirstNLetterContainers(LetterContainer[] containers, int lettersFromStart) {
        StringBuilder letters = new StringBuilder();
        for(int i = 0; i < lettersFromStart; i++){
            letters.append(containers[i].letter.character);
        }
        return letters.toString();
    }
}
