/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_game;

import pr_map.Map;

/**
 *
 * @author henrikorpela
 */
public class Game {
    public static Game_state state;
    private static Map map;
    public static Error_handeler error_handeler;
    
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
    }
    
    private static void interrept_game_state()
    {
        switch(state)
        {
            case MENU: Menu.menu(map);
                break;
            case GAME: 
                break;
        }
    }
    
    private static void validate_game_state()
    {
        if(state == null)
        {
            state = Game_state.MENU;
        }
    }
    
    private static void initialize()
    {
        state = Game_state.MENU;
        map = new Map();
        error_handeler = new Error_handeler();
    }
}
