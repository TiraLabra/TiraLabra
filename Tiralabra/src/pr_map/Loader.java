/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author henrikorpela
 */
public class Loader {
    private static String name;
    private static int widht;
    private static int height;
    private static int header_size;
    private static final int MAX_SIZE = 500;
    private static final int MIN_SIZE = 1;
    
    
    public static Map_errors load_map(String file_name,Map map)
    {
        initialize_modifiers();
        map.clear_map();
        File file = new File(file_name);
        Scanner reader;
        try 
        {
            reader = new Scanner(file);
        } 
        catch (FileNotFoundException ex) 
        {
            return Map_errors.FILE_NOT_FOUND;
        }
        int line_number = 1;
        while(reader.hasNextLine())
        {
            if(line_number > 5 && line_number > (height + header_size))
            {
                return Map_errors.INVALID_HEIGHT;
            }
            String line = reader.nextLine();
            if(line_number == 4)
            {
                map.create_map(name,widht,height);
            }
            Map_errors error = interrept_line(line,line_number,map);
            if(error != null)
            {
                return error;
            }
            line_number++;
        }
        return null;
    }
    
    private static void initialize_modifiers()
    {
        widht = -1;
        height = -1;
        header_size = -1;
        name = null;
    }
    
    private static Map_errors interrept_line(String line,int line_number,Map map)
    {
        if(police_line(line_number))
        {
            return interrept_police(line,map);
        }
        switch(line_number)
        {
            case 1: name = line;
                break;
                
            case 2:
                try
                {
                    widht = Integer.parseInt(line);
                    if(widht < MIN_SIZE || widht > MAX_SIZE)
                    {
                        throw new Exception();
                    }
                }
                catch(Exception e)
                {
                    return Map_errors.INVALID_WIDHT;
                }
                break;
                
            case 3: try
                    {
                        height = Integer.parseInt(line);
                        if(height < MIN_SIZE || height > MAX_SIZE)
                        {
                            throw new Exception();
                        }
                    }
                    catch(Exception e)
                    {
                        return Map_errors.INVALID_HEIGHT;
                    }
                break;
            case 4: try
                    {
                        int prisoner_coordinates[] = interrept_coordinates(line);
                        check_coordinates(prisoner_coordinates);
                        map.set_prisoner_start(prisoner_coordinates[0],prisoner_coordinates[1]);
                    }
                    catch(Exception e)
                    {
                        return Map_errors.INVALID_PRISONER_POSITION;
                    }
                break;
            case 5: try
                    {
                        int ammount_of_police = Integer.parseInt(line);
                        header_size = 5 + ammount_of_police;
                    }
                    catch(Exception e)
                    {
                        return Map_errors.INVALID_POLICE_AMMOUNT;
                    }
                break;
                
            default: return interrept_map_line(line,line_number,map);
        }
        return null;
    }
    
    private static boolean police_line(int line_number)
    {
        if(header_size != -1 && line_number > 5 && line_number <= header_size)
        {
            return true;
        }
        return false;
    }
    
    private static Map_errors interrept_police(String line,Map map)
    {
        try
        {
            int coordinates[] = interrept_coordinates(line);
            check_coordinates(coordinates);
            map.register_police(coordinates[0],coordinates[1]);
        }
        catch(Exception e)
        {
            return Map_errors.INVALID_POLICE_POSITION;
        }
        return null;
    }
    
    private static int[] interrept_coordinates(String line) throws Exception
    {
        String coordinate_strings[] = line.split(" ");
        if(coordinate_strings.length != 2)
        {
            throw new Exception();
        }
        int coordinates[] = new int[2];
        coordinates[0] = Integer.parseInt(coordinate_strings[0]);
        coordinates[1] = Integer.parseInt(coordinate_strings[1]);
        return coordinates;
    }
    
    private static void check_coordinates(int [] coordinates) throws Exception
    {
        if(coordinates.length != 2)
        {
            throw new Exception();
        }
        if(coordinates[0] < 0 || coordinates[0] >= widht)
        {
            throw new Exception();
        }
        if(coordinates[1] < 0 || coordinates[1] >= height)
        {
            throw new Exception();
        }
    }
    
    private static Map_errors interrept_map_line(String line,int line_number,Map map)
    {
        if(!map.has_map_been_created())
        {
            return Map_errors.MAP_CREATION_ERROR;
        }
        if(line.length() != widht)
        {
            return Map_errors.MAP_DESCRIPTION_WIDHT_ERROR;
        }
        int map_line_number = line_number - header_size - 1;
        if(map_line_number < 0 || map_line_number >= height)
        {
            return Map_errors.MAP_DESCRIPTION_HEIGHT_ERROR;
        }
        for(int x = 0;x < widht;x ++)
        {
            String symbol = get_symbol(line,x);
            if(!Map_symbols.check_map_symbol(symbol))
            {
                return Map_errors.INVALID_MAP_SYMBOL;
            }
            map.set_tile(x,map_line_number,Map_symbols.create_corresponding_symbol(symbol));
        }
        return null;
    }
    
    private static String get_symbol(String line,int index)
    {
        char symbol = line.charAt(index);
        return "" + symbol;
    }
}
