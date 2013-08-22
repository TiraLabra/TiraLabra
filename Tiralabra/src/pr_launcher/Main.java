
package pr_launcher;

import pr_game.Game;
/**
 *
 * @author Henri Korpela
 * Main class is programs entrypoint includes main method.
 * Calls games game loop.
 */
public class Main {
    /**
     * Main method.Calls Games game loop
     * to start program.
     * @param args should be empty since program
     * doesn't take any arguments.
     */
    public static void main(String args[])
    {
         Game.game_loop();
    }
}
