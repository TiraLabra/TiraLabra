
package pr_map;

import Image_loader.Images;
import java.awt.Color;
import java.awt.image.BufferedImage;
import pr_data_structures.stack.Stack;
/**
 *
 * @author Henri Korpela
 * Describes all symbols that can be on the map.
 * Symbols are characters.
 * Symbols also include color if the are landscape
 * symbols. This color is used to color the tile
 * that symbol is in. Actor symbols have null color
 * but have picture that is used to present them.
 */
public enum Map_symbols {
    /**
     * Wall. Can't be passed through.
     * Has gray color.
     */
    WALL("W",1000000,"Wall",new Color(132,129,129),null),
    /**
     * Road is quick to travel.
     * Has brown color.
     */
    ROAD("R",1,"Road",new Color(129,83,31),null),
    /**
     * Grass is easy to travel (slower than road).
     * Has green color.
     */
    GRASS("G",2,"Grass",new Color(58,161,37),null),
    /**
     * Car can't be passed through.
     * Car is prisoners goal.
     * Has a picture of a car.
     */
    CAR("C",0,"Get away car",null,Images.CAR_IMAGE),
    /**
     * Symbol for police. Can't be passed through.
     * Has picture of a police.
     */
    POLICE("P",0,"Police officer",null,Images.POLICE_IMAGE),
    /**
     * Symbol for a prisoner. Can't be
     * passed through. Has picture of a prisoner.
     */
    PRISONER("@",0,"Escaping prisoner",null,Images.PRISONER_IMAGE);
    /**
     * Character symbol.
     */
    private final String symbol;
    /**
     * weight of the symbol to pass through.
     */
    private final int weight;
    /**
     * Description of the symbol.
     */
    private final String description;
    /**
     * Color of the symbol. Only landscape symbols
     * have color.
     */
    private final Color color;
    /**
     * Actors image.
     */
    private final BufferedImage image;
    /**
     * Stack that holds all valid landscape symbols.
     */
    private static Stack<String> landscape_symbols = new Stack<String>();
    /**
     * Creates symbol.
     * @param symbol Symbol character.
     * @param weight Weight of the symbol.
     * @param description Description of the symbol.
     * @param color Color of the symbol null if actor symbol.
     */
    private Map_symbols(String symbol,int weight,String description,Color color,BufferedImage image)
    {
        this.symbol = symbol;
        this.weight = weight;
        this.description = description;
        this.color = color;
        this.image = image;
    }
    
    public int get_weight()
    {
        return this.weight;
    }
    
    public String get_symbol()
    {
        return this.symbol;
    }
    
    public Color get_color()
    {
        return this.color;
    }
    
    public BufferedImage get_image()
    {
        return this.image;
    }
    /**
     * Checks whether map symbol is valid or not.
     * @param symbol symbol to be checked.
     * @return True if symbol is valid and
     * false if isn't.
     */
    public static boolean check_landscape_symbol(String symbol)
    {
        if(landscape_symbols.is_empty())
        {
            initialze_landscape_symbols();
        }
        return landscape_symbols.contains(symbol);
    }
    /**
     * Creates symbol that corresponds given
     * character symbol.
     * @param symbol Character symbol.
     * @return New symbol that corresponds given
     * symbol and null if symbol is invalid.
     */
    public static Map_symbols create_corresponding_symbol(String symbol)
    {
        if(symbol.equals("W"))
        {
            return WALL;
        }
        else if(symbol.equals("R"))
        {
            return ROAD;
        }
        else if(symbol.equals("G"))
        {
            return GRASS;
        }
        else if(symbol.equals("C"))
        {
            return CAR;
        }
        else if(symbol.equals("P"))
        {
            return POLICE;
        }
        else if(symbol.equals("@"))
        {
            return PRISONER;
        }
        else
        {
            return null;
        }
    }
    
    public String get_description()
    {
        return this.description;
    }
    /**
     * Initializes landscape symbols stack.
     */
    private static void initialze_landscape_symbols()
    {
        landscape_symbols.add("W");
        landscape_symbols.add("R");
        landscape_symbols.add("G");
    }
}
