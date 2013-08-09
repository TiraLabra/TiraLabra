/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_map;

/**
 *
 * @author henrikorpela
 */
public class Tile {
    private int weight;
    private Map_symbols in_tile;
    private Map_symbols actor_on_tile;
    
    public Tile(int weight,Map_symbols in_tile,Map_symbols on_tile)
    {
        this.actor_on_tile = on_tile;
        this.update_weight();
        this.in_tile = in_tile;
    }
    
    public int get_weight()
    {
        return this.weight;
    }
    
    public void set_weight(int new_weight)
    {
        this.weight = new_weight;
        this.update_weight();
    }
    
    public Map_symbols get_in_tile()
    {
        return this.in_tile;
    }
    
    public void set_in_tile(Map_symbols in_tile)
    {
        this.in_tile = in_tile;
    }
    
    public Map_symbols get_actor_on_tile()
    {
        return this.actor_on_tile;
    }
    
    public void set_actor_on_tile(Map_symbols on_tile)
    {
        this.actor_on_tile = on_tile;
        this.update_weight();
    }
    
    @Override
    public String toString()
    {
        if(this.actor_on_tile != null)
        {
            return this.actor_on_tile.get_symbol();
        }
        else
        {
            return this.in_tile.get_symbol();
        }
    }
    
    private void update_weight()
    {
        this.weight = weight + this.actor_on_tile.get_weight();
    }
}
