
package pr_game;

import pr_map.Map_symbols;
import java.util.Scanner;

/**
 *
 * @author Henri Korpela
 * Info class contains methods for printing menus
 * and reading user input.
 */
public class Info {
    /**
     * Reads command from the user.
     * @return String that contains users input.
     */
    public static String read_command()
    {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }
    /**
     * Prints all commands.
     */
    public static void print_commands()
    {
        System.out.print("LOAD: load map\n");
        System.out.print("SHOW: show which map has been loaded\n");
        System.out.print("PLAY: play current map\n");
        System.out.print("EXIT: exit program\n");
    }
    /**
     * Asks filename from user.
     * @return String that contains given filename.
     */
    public static String ask_file_name()
    {
        Scanner reader = new Scanner(System.in);
        System.out.print("Type path to file that you wish to load: ");
        String file_name = reader.nextLine();
        return file_name;
    }
    /**
     * Welcomes user.
     */
    public static void welcome_user()
    {
        System.out.println("Welcome to prison break!");
    }
    /**
     * Says good bye to user.
     */
    public static void say_good_bye_to_user()
    {
        System.out.print("Farewell!\n");
        System.out.print("Program exits.");
    }
    /**
     * Prints all map symbol meanings.
     */
    public void print_symbol_meanings()
    {
        System.out.println(Map_symbols.PRISONER.get_symbol() + ": " + Map_symbols.PRISONER.get_description());
        System.out.println(Map_symbols.POLICE.get_symbol() + ": " + Map_symbols.POLICE.get_description());
        System.out.println(Map_symbols.GRASS.get_symbol() + ": " + Map_symbols.GRASS.get_description());
        System.out.println(Map_symbols.ROAD.get_symbol() + ": " + Map_symbols.ROAD.get_description());
        System.out.println(Map_symbols.WALL.get_symbol() + ": " + Map_symbols.WALL.get_description());
        System.out.println(Map_symbols.CAR.get_symbol() + ": " + Map_symbols.CAR.get_description());
    }
}
