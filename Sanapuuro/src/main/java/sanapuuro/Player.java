/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

import sanapuuro.ui.Controller;
import sanapuuro.words.WordEvaluator.Submission;

/**
 *
 * @author skaipio
 */
public interface Player {
    int getScore();
    LetterPool getLetterPool();
    Submission getSubmission();
    void successfulSubmission(int score);
    void unsuccessfulSubmission();
    Controller getController();
}
