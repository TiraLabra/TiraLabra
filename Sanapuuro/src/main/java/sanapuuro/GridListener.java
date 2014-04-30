/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

import sanapuuro.letters.LetterContainer;

/**
 *
 * @author skaipio
 */
public interface GridListener {
    void containerSetToGrid(LetterContainer container, int x, int y);  
}
