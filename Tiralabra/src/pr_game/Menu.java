/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_game;

import pr_map.Loader;
import pr_map.Map;
import pr_map.Map_errors;

/**
 *
 * @author henrikorpela
 */
public class Menu {
    
    public static void menu(Map map)
    {
        System.out.print("Give command:\n");
        Info.print_commands();
        System.out.print("> ");
        String command = Info.read_command();
        interrept_command(command,map);
        System.out.print("\n");
    }
    
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
            play(map);
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
    
    private static void load(Map map)
    {
        String file_name = Info.ask_file_name();
        Map_errors map_error = Loader.load_map(file_name,map);
        Game.error_handeler.add_error(map_error);
    }
    
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
    
    private static void play(Map map)
    {
        Game.state = Game_state.GAME;
    }
    
    private static void exit()
    {
        Game.state = Game_state.EXIT;
    }
}
