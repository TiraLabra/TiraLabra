
package pr_game;

/**
 *
 * @author Henri Korpela
 * Defines Game states.
 */
public enum Game_state {
    /**
     * Menu state. Indicates that game is displaying menus.
     */
    MENU,
    /**
     * Game state indicates that game is playing the game.
     */
    GAME,
    /**
     * Exit state indicates that program ends during next loop.
     */
    EXIT;
}
