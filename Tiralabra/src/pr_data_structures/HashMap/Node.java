
package pr_data_structures.HashMap;

/**
 *
 * @author henrikorpela
 */
public class Node {
    private final Object key;
    private Object data;
    
    public Node(Object data,Object key)
    {
        this.data = data;
        this.key = key;
    }
    
    public Object get_key()
    {
        return this.key;
    }
    
    public Object get_data()
    {
        return this.data;
    }
    
    public void set_data(Object new_data)
    {
        this.data = new_data;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this.key.equals(o))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
