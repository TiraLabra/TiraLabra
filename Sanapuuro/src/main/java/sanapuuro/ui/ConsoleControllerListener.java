/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.ui;

import sanapuuro.Controller;

/**
 *
 * @author skaipio
 */
public interface ConsoleControllerListener {
    void selectorMoved(Controller controller);
    void failedToPlaceLetters();
    void makingMove(Controller controller);
}
