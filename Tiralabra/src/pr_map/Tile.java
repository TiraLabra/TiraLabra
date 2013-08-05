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
    private Map_symbols on_tile;
    
    public Tile(int weight,Map_symbols on_tile)
    {
        this.on_tile = on_tile;
        this.update_weight();
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
    
    public Map_symbols get_on_tile()
    {
        return this.on_tile;
    }
    
    public void set_on_tile(Map_symbols on_tile)
    {
        this.on_tile = on_tile;
        this.update_weight();
    }
    
    @Override
    public String toString()
    {
        return this.on_tile.get_symbol();
    }
    
    private void update_weight()
    {
        this.weight = weight + this.on_tile.get_weight();
    }
}
