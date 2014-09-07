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
        int bots;
        int humans;
        bots = 1;
        humans = 0;
        //(int) args[0].charAt(0), (int) args[1].charAt(0)
        Game newGame = new Game(humans, bots);
        newGame.runGame();
        //newGame.runTests();
        //newGame.testBot();
        
    }
    
    
    
}
