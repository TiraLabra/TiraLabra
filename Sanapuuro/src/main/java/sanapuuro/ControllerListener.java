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
    boolean letterAdded(char c, int x, int y);
    boolean letterSelected(int x, int y); 
    void clearLettersFromSubmission();
}
