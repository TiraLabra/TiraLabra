/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

import java.util.List;
import sanapuuro.grid.LetterContainer;
import sanapuuro.letters.LetterPool;

/**
 *
 * @author skaipio
 */
public interface Player {
    int getScore();
    void addScore(int score);
    LetterPool getLetterPool();
    ConsoleController getController();
    List<LetterContainer> getContainersForSubmission();
    boolean hasMadeASubmission();
    boolean isSkipping();
    void makeMove();
    void successfulSubmission();
    void unsuccessfulSubmission();
}
