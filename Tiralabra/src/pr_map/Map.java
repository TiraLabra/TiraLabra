/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_map;
import java.util.ArrayList;
/**
 *
 * @author henrikorpela
 */
public class Map {
    private String name;
    private int widht;
    private int height;
    private Position prisoner_start;
    private Tile map[][];
    private ArrayList<Position> polices;
    
    public Map()
    {
        this.polices = new ArrayList<Position>();
    }
    
    public boolean has_map_been_created()
    {
        if(this.map == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void create_map(String name,int widht,int height)
    {
        this.name = name;
        this.widht = widht;
        this.height = height;
        this.map = new Tile[this.widht][this.height];
    }
    
    public Map_errors build_map()
    {
        Map_errors error;
        error = this.put_prisoner_on_tile();
        if(error != null)
        {
            return error;
        }
        error = this.put_polices_on_tiles();
        if(error != null)
        {
            return error;
        }
        error = this.check_map();
        return error;
    }
    
    public void set_prisoner_start(int x,int y)
    {
        this.prisoner_start = new Position(x,y);
    }
    
    public String get_name()
    {
        return this.name;
    }
    
    public void set_tile(int x,int y,Map_symbols symbol)
    {
        this.map[x][y] = new Tile(0,null,symbol);
    }
    
    public void register_police(int x,int y)
    {
        Position position = new Position(x,y);
        this.polices.add(position);
    }
    
    public void clear_map()
    {
        this.map = null;
        this.name = null;
        this.height = -1;
        this.widht = -1;
        this.prisoner_start = null;
        this.polices = new ArrayList<Position>();
    }
    
    @Override
    public String toString()
    {
        String map = "";
        for(int y = 0;y < this.height;y ++)
        {
            for(int x = 0;x < this.widht;x ++)
            {
                map = map + this.map[x][y].toString();
            }
            map = map + "\n";
        }
        return map;
    }
    
    public void print_map()
    {
        System.out.print("widht: " + this.widht + " ");
        System.out.print("height: " + this.height + "\n");
        for(int y = 0;y < this.height;y ++)
        {
            for(int x = 0;x < this.widht;x ++)
            {
                System.out.print(this.map[x][y]);
            }
            System.out.print("\n");
        }
    }
    
    private Map_errors check_map()
    {
        for(int y = 0;y < this.height;y ++)
        {
            for(int x = 0;x < this.widht;x ++)
            {
                Map_errors error = this.check_tile(x,y);
                if(error != null)
                {
                    return error;
                }
            }
        }
        return null;
    }
    
    private Map_errors put_prisoner_on_tile()
    {
        Tile tile = this.map[this.prisoner_start.x][this.prisoner_start.y];
        if(!this.is_tile_taken(tile))
        {
            tile.set_actor_on_tile(Map_symbols.PRISONER);
            return null;
        }
        else
        {
            return Map_errors.INVALID_PRISONER_POSITION;
        }
    }
    
    private Map_errors put_polices_on_tiles()
    {
        for(int i = 0;i < this.polices.size();i ++)
        {
            Position police = this.polices.get(i);
            Map_errors error = this.put_police_on_tile(police.x,police.y);
            if(error != null)
            {
                return error;
            }
        }
        return null;
    }
    
    private Map_errors put_police_on_tile(int x,int y)
    {
        Tile tile = this.map[x][y];
        if(!this.is_tile_taken(tile))
        {
            tile.set_actor_on_tile(Map_symbols.POLICE);
            return null;
        }
        else
        {
            return Map_errors.INVALID_POLICE_POSITION;
        }
    }
    
    private boolean is_tile_taken(Tile tile)
    {
        if(tile.get_actor_on_tile() != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private Map_errors check_tile(int x,int y)
    {
        Tile tile = this.map[x][y];
        if(tile.get_in_tile() == Map_symbols.WALL)
        {
            if(tile.get_actor_on_tile() != null)
            {
                return this.interrept_tile_error(tile);
            }
        }
        return null;
    }
    
    private Map_errors interrept_tile_error(Tile tile)
    {
        Map_symbols on_tile = tile.get_actor_on_tile();
        switch(on_tile)
        {
            case POLICE: return Map_errors.INVALID_POLICE_POSITION;
            case PRISONER: return Map_errors.INVALID_PRISONER_POSITION;
            default: return Map_errors.INVALID_MAP_SYMBOL;
        }
    }
}
