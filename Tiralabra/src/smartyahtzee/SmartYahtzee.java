/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

/**
 *
 * @author essalmen
 */
public class SmartYahtzee {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int bots = 1;
        int humans = 0;

        Game newGame = new Game(humans, bots);
        newGame.runGame();
        //newGame.runTests();
        //newGame.testBot();
        
    }
    
    
    
}
