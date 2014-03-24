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
public interface ControllerListener {
    void letterAddedFromPoolIndex(int index, int gridX, int gridY);
    void letterSelectedAt(int gridX, int gridY);
    void wordSubmission(int score);
}
