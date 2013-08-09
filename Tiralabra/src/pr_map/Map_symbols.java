/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_map;

import pr_data_structures.stack.Stack;

/**
 *
 * @author henrikorpela
 */
public enum Map_symbols {
    WALL("W",1000000,"Wall"),
    ROAD("R",1,"Road"),
    GRASS("G",2,"Grass"),
    CAR("C",0,"Get away car"),
    POLICE("P",0,"Police officer"),
    PRISONER("@",0,"Escaping prisoner");
    
    private final String symbol;
    private final int weight;
    private final String description;
    private static Stack<String> symbols = new Stack<String>();
            
    private Map_symbols(String symbol,int weight,String description)
    {
        this.symbol = symbol;
        this.weight = weight;
        this.description = description;
    }
    
    public int get_weight()
    {
        return this.weight;
    }
    
    public String get_symbol()
    {
        return this.symbol;
    }
    
    public static boolean check_map_symbol(String symbol)
    {
        if(symbols.is_empty())
        {
            initialze_symbols();
        }
        return symbols.contains(symbol);
    }
    
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
    
    private static void initialze_symbols()
    {
        symbols.add("W");
        symbols.add("R");
        symbols.add("G");
        symbols.add("C");
    }
}
