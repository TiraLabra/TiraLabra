/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro;

/**
 *
 * @author skaipio
 */
public interface Controller {
    int getSubmissionStartX();
    int getSubmissionStartY();
    int getSubmissionDeltaX();
    int getSubmissionDeltaY();
    String getSubmission();
}
