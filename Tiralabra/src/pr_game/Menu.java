
package pr_game;

import pr_ai.Police;
import pr_ai.Prisoner;
import pr_data_structures.array_list.ArrayList;
import pr_map.Loader;
import pr_map.Map;
import pr_map.Map_errors;
import pr_map.Position;
import pr_pathfinding.A_star;
import pr_pathfinding.Pathfinder;

/**
 *
 * @author Henri Korpela
 * Contains methods for menu control.
 */
public class Menu {
    /**
     * Plays menu. Prints commands that
     * user can give, asks command from user
     * and then executes given command.
     * @param map Map that menu currently handles.
     */
    public static void menu(Map map)
    {
        System.out.print("Give command:\n");
        Info.print_commands();
        System.out.print("> ");
        String command = Info.read_command();
        interrept_command(command,map);
        System.out.print("\n");
    }
    /**
     * Interprets given command and executes corresponding action.
     * @param command Command to be interpreted.
     * @param map Map that is currently handled.
     */
    private static void interrept_command(String command,Map map)
    {
        if(command.equals("LOAD"))
        {
            load(map);
        }
        else if(command.equals("SHOW"))
        {
            show(map);
        }
        else if(command.equals("PLAY"))
        {
            play();
        }
        else if(command.equals("EXIT"))
        {
            exit();
        }
        else
        {
            System.out.print("Invalid command\n");
        }
    }
    /**
     * Loads map into given map.
     * @param map 
     */
    private static void load(Map map)
    {
        String file_name = Info.ask_file_name();
        Map_errors map_error = Loader.load_map(file_name,map);
        if(map_error != null)
        {
            Game.error_handeler.add_error(map_error);
            return;
        }
        add_actors(map);
        Turn.set_map(map);
        Turn.initialize_render_manager();
    }
    /**
     * Prints current map.
     * @param map Map to be printed.
     */
    private static void show(Map map)
    {
        if(map.get_name() != null)
        {
            System.out.print("Currently map " + map.get_name() +
                    " is loaded in memory\n");
            map.print_map();
        }
        else
        {
            System.out.print("No map has been loaded\n");
        }
    }
    /**
     * Changes game state to GAME. 
     */
    private static void play()
    {
        Game.state = Game_state.GAME;
    }
    /**
     * Changes game state to EXIT.
     */
    private static void exit()
    {
        Game.state = Game_state.EXIT;
    }
    /**
     * Creates actor list. Adds actors to the list
     * and sets list active.
     * @param map Map that holds all actor data.
     */
    private static void add_actors(Map map)
    {
        ArrayList<Position> polices = map.get_polices();
        Game_actors actor_list = new Game_actors();
        for(int i = 0;i < polices.size();i ++)
        {
            Pathfinder pathfinder = new A_star();
            Position start_position = polices.getIndex(i);
            actor_list.add_actor(new Police(start_position.x,start_position.y,pathfinder,map));
        }
        
        Position start_position = map.prisoner_position();
        Pathfinder pathfinder = new A_star();
        actor_list.add_actor(new Prisoner(start_position.x,start_position.y,pathfinder,map));
        
        Turn.set_actor_list(actor_list);
    }
}
