/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

import java.util.List;
import sanapuuro.grid.LetterContainer;

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
}
