
package pr_game;

import pr_map.Map;

/**
 *
 * @author Henri Korpela
 * 
 * Class contains the game loop that is responsible
 * of program execution and static variables that
 * control program behavior.
 */
public class Game {
    /**
     * Defines games state.
     */
    public static Game_state state;
    /**
     * Map that is loaded and played if 
     * game is in GAME state.
     */
    private static Map map;
    /**
     * Games error handler.
     */
    public static Error_handeler error_handeler;
    /**
     * Game loop controls program execution.
     */
    public static void game_loop()
    {
        Info.welcome_user();
        initialize();
        while(state != Game_state.EXIT)
        {
            validate_game_state();
            interrept_game_state();
            error_handeler.handle_errors();
        }
        Info.say_good_bye_to_user();
    }
    /**
     * Interprets current game state and
     * chooses action accordingly.
     */
    private static void interrept_game_state()
    {
        switch(state)
        {
            case MENU: Menu.menu(map);
                break;
            case GAME: Turn.run();
                break;
        }
    }
    /**
     * Makes sure that game state is valid.
     */
    private static void validate_game_state()
    {
        if(state == null)
        {
            state = Game_state.MENU;
        }
    }
    /**
     * Initializes game. Sets all variables to default values.
     */
    private static void initialize()
    {
        state = Game_state.MENU;
        map = new Map();
        error_handeler = new Error_handeler();
    }
}
