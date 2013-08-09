/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_game;

import pr_map.Map_symbols;
import java.util.Scanner;

/**
 *
 * @author henrikorpela
 */
public class Info {
    
    public static String read_command()
    {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }
    
    public static void print_commands()
    {
        System.out.print("LOAD: load map\n");
        System.out.print("SHOW: show which map has been loaded\n");
        System.out.print("PLAY: play current map\n");
        System.out.print("EXIT: exit program\n");
    }
    
    public static String ask_file_name()
    {
        Scanner reader = new Scanner(System.in);
        System.out.print("Type path to file that you wish to load: ");
        String file_name = reader.nextLine();
        return file_name;
    }
    
    public static void welcome_user()
    {
        System.out.println("Welcome to prison break!");
    }
    
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
