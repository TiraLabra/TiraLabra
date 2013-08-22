
package pr_data_structures.HashMap;

import pr_data_structures.stack.Stack;

/**
 *
 * @author Henri Korpela
 * HashMap data structure. Includes put, get and delete methods.
 * Uses xxx to handle collisions. Uses equals and hashcode methods.
 */
public class HashMap<Key,Value> {
    private Node map[];
    public HashMap(int aproximate_size)
    {
        this.map = new Node[aproximate_size];
    }
    
    public void put(Key key,Value value)
    {
        
    }
    
    public Value get(Key key)
    {
        return null;
    }
    
    public void delete(Key key)
    {
        
    }
}
