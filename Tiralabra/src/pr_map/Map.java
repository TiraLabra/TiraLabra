/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_map;

/**
 *
 * @author henrikorpela
 */
public class Map {
    private String name;
    private int widht;
    private int height;
    private Tile map[][];
    
    public Map()
    {
        
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
    
    public void set_tile(int x,int y,Map_symbols symbol)
    {
        this.map[x][y] = new Tile(0,symbol);
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
}
