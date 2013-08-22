
package pr_map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Henri Korpela
 * Loads map from file that
 * is given by the user.
 */
public class Loader {
    /**
     * Name of the map that is loaded.
     */
    private static String name;
    /**
     * Width of the map that is loaded. 
     */
    private static int width;
    /**
     * Height of the map that is loaded.
     */
    private static int height;
    /**
     * Size of the information header of the map.
     * Size of the header depends amount of polices
     * on the map. Header size is used to count offset
     * for actual map data.
     */
    private static int header_size;
    /**
     * Max size for map width and height.
     */
    private static final int MAX_SIZE = 500;
    /**
     * Min size for map width and height.
     */
    private static final int MIN_SIZE = 1;
    /**
     * Loads map from file. If errors occur during
     * load operation loading is interrupted.
     * @param file_name Name of the file where
     * map is saved.
     * @param map Map object that loaded map
     * is loaded.
     * @return Errors if that occur
     * during loading. Null if no errors occur.
     */
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
                map.create_map(name,width,height);
            }
            Map_errors error = interpert_line(line,line_number,map);
            if(error != null)
            {
                return error;
            }
            line_number++;
        }
        return null;
    }
    /**
     * Initializes all Loader variables.
     * This methods is called at the start
     * of the loading process.
     */
    private static void initialize_modifiers()
    {
        width = -1;
        height = -1;
        header_size = -1;
        name = null;
    }
    /**
     * Interprets one line of the file.
     * 
     * @param line Line to be interpret.
     * @param line_number Number of the
     * line in the file.
     * @param map Map where map is loaded.
     * @return Errors that occur. Null if no errors occur.
     */
    private static Map_errors interpert_line(String line,int line_number,Map map)
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
                    width = Integer.parseInt(line);
                    if(width < MIN_SIZE || width > MAX_SIZE)
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
                        int[] car_position = interrept_coordinates(line);
                        map.set_get_away_car_position(car_position[0],car_position[1]);
                    }
                    catch(Exception e)
                    {
                        return Map_errors.INVALID_CAR_POSITION;
                    }
            case 6: try
                    {
                        int ammount_of_police = Integer.parseInt(line);
                        header_size = 6 + ammount_of_police; // Six lines is all
                        // other header information except pölices.
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
    /**
     * Checks whether line is a line that
     * contains information of polices
     * position or not. This done by checking
     * that given linenumber is within header
     * and that is after all other data in the header.
     * Police positions are last information in
     * the header.
     * @param line_number
     * @return True if line is police line
     * false if isn't.
     */
    private static boolean police_line(int line_number)
    {
        if(header_size != -1 && line_number > 5 && line_number <= header_size)
        {
            return true;
        }
        return false;
    }
    /**
     * Interprets police line.
     * @param line Police line that contains
     * information of polices position.
     * @param map Map that police is added.
     * @return Errors that occur.  Null if no errors occur.
     * Errors occur if police has invalid position.
     */
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
    /**
     * Reads coordinates from given line.
     * @param line Line that includes coordinates.
     * @return Array that contains coordinates.
     * @throws Exception Throws Exception
     * if line doesn't contain coordinates.
     */
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
    /**
     * Checks that coordinates are valid.
     * Coordinates have to be inside the map.
     * @param coordinates Coordinates to be checked.
     * @throws Exception Throws exception id coordinates
     * are invalid.
     */
    private static void check_coordinates(int [] coordinates) throws Exception
    {
        if(coordinates.length != 2)
        {
            throw new Exception();
        }
        if(coordinates[0] < 0 || coordinates[0] >= width)
        {
            throw new Exception();
        }
        if(coordinates[1] < 0 || coordinates[1] >= height)
        {
            throw new Exception();
        }
    }
    /**
     * Interprets line that contains
     * map data.
     * @param line Line that contains map data.
     * @param line_number number of the line.
     * @param map Map that data is loaded to.
     * @return Errors that occur.  Null if no errors occur.
     */
    private static Map_errors interrept_map_line(String line,int line_number,Map map)
    {
        if(!map.has_map_been_created())
        {
            return Map_errors.MAP_CREATION_ERROR;
        }
        if(line.length() != width)
        {
            return Map_errors.MAP_DESCRIPTION_WIDHT_ERROR;
        }
        int map_line_number = line_number - header_size - 1;
        if(map_line_number < 0 || map_line_number >= height)
        {
            return Map_errors.MAP_DESCRIPTION_HEIGHT_ERROR;
        }
        for(int x = 0;x < width;x ++)
        {
            String symbol = get_symbol(line,x);
            if(!Map_symbols.check_landscape_symbol(symbol))
            {
                return Map_errors.INVALID_MAP_SYMBOL;
            }
            map.set_tile(x,map_line_number,Map_symbols.create_corresponding_symbol(symbol));
        }
        return null;
    }
    /**
     * Return symbol in lines index that is given.
     * @param line Line thats symbol is read.
     * @param index Symbols index.
     * @return Symbol in line in given index.
     */
    private static String get_symbol(String line,int index)
    {
        char symbol = line.charAt(index);
        return "" + symbol;
    }
}
