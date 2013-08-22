
package pr_map;
import pr_data_structures.array_list.ArrayList;
/**
 *
 * @author Henri Korpela
 * Map describes a map that
 * game is played on.
 */
public class Map {
    /**
     * Name of the map.
     */
    private String name;
    /**
     * Width of the map.
     */
    private int width;
    /**
     * Height of the map.
     */
    private int heigth;
    /**
     * Prisoners position.
     */
    private Position prisoner;
    /**
     * Get away cars position.
     */
    private Position get_away_car_position;
    /**
     * Array that is the actual map.
     * Array consists of tiles that
     * hold information of each coordinate.
     */
    private Tile map[][];
    /**
     * Map that is constructed from
     * the map. Holds values of weight
     * of each coordinate.
     */
    private int[][] weight_map;
    /**
     * List that contains all positions of polices.
     */
    private ArrayList<Position> polices;
    /**
     * Creates initialized map.
     * This can't be played map
     * is empty before its loaded.
     */
    public Map()
    {
        this.polices = new ArrayList<Position>();
    }
    /**
     * Tells whether map has been loaded or not.
     * @return True if map has been loaded.
     * False if isn't.
     */
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
    /**
     * Creates map. Map has to be created before
     * data can be inserted into map.
     * @param name Name of the map.
     * @param widht Width of the map.
     * @param height Height of the map.
     */
    public void create_map(String name,int widht,int height)
    {
        this.name = name;
        this.width = widht;
        this.heigth = height;
        this.map = new Tile[this.width][this.heigth];
        this.weight_map = new int[this.width][this.heigth];
    }
    /**
     * Builds map. Puts all polices and prisoner on tiles.
     * Checks that map is valid. Polices or prisoner aren't
     * inside any walls.
     * @return Errors if map is invalid. Null if map is valid.
     */
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
        if(error != null)
        {
            return error;
        }
        this.build_weight_map();
        return error;
    }
    /**
     * Sets prisoner start position.
     * @param x Prisoner start x coordinate.
     * @param y Prisoner start x coordinate.
     */
    public void set_prisoner_start(int x,int y)
    {
        this.prisoner = new Position(x,y);
    }
    /**
     * Return prisoner Position.
     * @return Prisoner position.
     */
    public Position prisoner_position()
    {
        return this.prisoner;
    }
    /**
     * Sets get away car position.
     * @param x X coordinate.
     * @param y Y coordinate.
     */
    public void set_get_away_car_position(int x,int y)
    {
        this.get_away_car_position = new Position(x,y);
    }
    /**
     * Returns width of the map.
     * @return Width of the map.
     */
    public int get_widht()
    {
        return this.width;
    }
    /**
     * Returns height of the map.
     * @return Height of the map.
     */
    public int get_height()
    {
        return this.heigth;
    }
    /**
     * Return name of the map.
     * @return Name of the map.
     */
    public String get_name()
    {
        return this.name;
    }
    /**
     * Return get away cars position.
     * @return Get away car position.
     */
    public Position get_away_car_position()
    {
        return this.get_away_car_position;
    }
    /**
     * Makes and return copy of the maps weight map.
     * @return Copy of the weight map.
     */
    public int[][] copy_weight_map()
    {
        int[][] copy = new int[this.width][this.heigth];
        for(int i = 0;i < this.heigth;i ++)
        {
            for(int j = 0;j < this.width;j ++)
            {
                copy[j][i] = this.weight_map[j][i];
            }
        }
        return copy;
    }
    /**
     * Return maps weight map.
     * @return Maps weight map.
     */
    public int[][] get_weight_map()
    {
        this.build_weight_map();
        return this.weight_map;
    }
    /**
     * Return list that contains positions of polices.
     * @return List that contains positions of polices.
     */
    public ArrayList<Position> get_polices()
    {
        return this.polices;
    }
    /**
     * Moves actor in coordinates x and y.
     * to coordinates to_x and to_y.
     * @param x Actors x coordinate.
     * @param y Actors y coordinate.
     * @param to_x X coordinate where actor is moved.
     * @param to_y Y coordinate where actor is moved.
     * @throws Exception Throws exception if move is invalid.
     * Target location is inside a wall, two actors overlap
     * or target location is outside the map.
     */
    public void move(int x,int y,int to_x,int to_y) throws Exception
    {
        Tile from_tile = this.map[x][y];
        Tile to_tile = this.map[to_x][to_y];
        Map_symbols actor_on_tile = from_tile.get_actor_on_tile();
        
        if(actor_on_tile == null || to_tile.get_actor_on_tile() != null)
        {
            throw new Exception();
        }
        
        to_tile.set_actor_on_tile(actor_on_tile);
        from_tile.set_actor_on_tile(null);
    }
    /**
     * Sets tile to the map.
     * @param x X coordinate of the tile.
     * @param y Y coordinate of the tile.
     * @param symbol Symbol in the tile.
     */
    public void set_tile(int x,int y,Map_symbols symbol)
    {
        this.map[x][y] = new Tile(0,null,symbol);
    }
    /**
     * Registers police to the map.
     * Police is not set on the map when its registered.
     * Its just added to the police list.
     * @param x Polices x coordinate.
     * @param y Polices y coordinate.
     */
    public void register_police(int x,int y)
    {
        Position position = new Position(x,y);
        this.polices.add(position);
    }
    /**
     * Return tile in given x and y coordinate.
     * @param x Tiles x coordinate.
     * @param y Tiles y coordinate.
     * @return Tile in given x and y coordinate.
     * Null if coordinates are outside the map.
     */
    public Tile get_Tile(int x,int y)
    {
        try
        {
            return this.map[x][y];
        }
        catch(Exception e)
        {
            return null;
        }
    }
    /**
     * Clears map. After map is cleared
     * it can't be played before its reloaded.
     */
    public void clear_map()
    {
        this.map = null;
        this.name = null;
        this.heigth = -1;
        this.width = -1;
        this.prisoner = null;
        this.polices = new ArrayList<Position>();
    }
    /**
     * Return map in string format.
     * In String format each line present
     * line of the map and each letter is the
     * symbol in that coordinate. Game actors
     * can't be seen in String format.
     * @return Maps String presentation.
     */
    @Override
    public String toString()
    {
        String map = "";
        for(int y = 0;y < this.heigth;y ++)
        {
            for(int x = 0;x < this.width;x ++)
            {
                map = map + this.map[x][y].toString();
            }
            map = map + "\n";
        }
        return map;
    }
    /**
     * Prints maps string presentation.
     */
    public void print_map()
    {
        System.out.print("widht: " + this.width + " ");
        System.out.print("height: " + this.heigth + "\n");
        for(int y = 0;y < this.heigth;y ++)
        {
            for(int x = 0;x < this.width;x ++)
            {
                System.out.print(this.map[x][y]);
            }
            System.out.print("\n");
        }
    }
    /**
     * Checks whether prisoner has 
     * reached get away car or not.
     * @return True if prisoner has
     * reached get away car and
     * false if hasn't.
     */
    public boolean check_prisoner_escape()
    {
        return false;
    }
    /**
     * Checks that all tiles in the map
     * are valid. There shouldn't be
     * any tiles that have actor and
     * present a wall.
     * @return Error if invalid tiles
     * exist.
     */
    private Map_errors check_map()
    {
        for(int y = 0;y < this.heigth;y ++)
        {
            for(int x = 0;x < this.width;x ++)
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
    /**
     * Puts prisoner on tile.
     * @return Error if prisoner position is invalid.
     * Prisoner position can't be inside a wall
     * or outside the map.
     */
    private Map_errors put_prisoner_on_tile()
    {
        Tile tile = this.map[this.prisoner.x][this.prisoner.y];
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
    /**
     * Builds weight map. By calculating weight
     * of each tile of the map.
     */
    private void build_weight_map()
    {
        for(int i = 0;i < this.heigth;i ++)
        {
            for(int j = 0;j < this.width;j ++)
            {
                this.weight_map[j][i] = this.map[j][i].get_weight();
            }
        }
    }
    /**
     * Puts all registered polices on tiles.
     * Checks that polices are in valid positions.
     * @return Error if some of the polices are
     * in invalid position.
     */
    private Map_errors put_polices_on_tiles()
    {
        for(int i = 0;i < this.polices.size();i ++)
        {
            Position police = this.polices.getIndex(i);
            Map_errors error = this.put_police_on_tile(police.x,police.y);
            if(error != null)
            {
                return error;
            }
        }
        return null;
    }
    /**
     * Puts one police on tile and
     * checks that it's position is valid.
     * @param x Polices x coordinate.
     * @param y Polices y coordinate.
     * @return Error if coordinates are invalid.
     */
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
    /**
     * Checks whether tile holds actor or not.
     * @param tile Tile to be checked.
     * @return True if tile holds actor and
     * false if doesn't.
     */
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
    /**
     * Checks that if tile is a wall it doesn't
     * hold actor. If tile is a wall and
     * holds an actor returns error.
     * @param x Tiles x coordinate.
     * @param y Tiles y coordinate.
     * @return Errors if tile is a wall and
     * holds an actor.
     */
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
    /**
     * Interprets error
     * which came up during
     * tile checks.
     * @param tile Tile where error came up.
     * @return Error that corresponds current situation
     * on tile.
     */
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
