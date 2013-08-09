/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.array_list;

/**
 *
 * @author henrikorpela
 * 
 * My ArrayList class includes same operations as Javas
 * ArrayList class.
 */
public class Array_list<Type> {
    private final int initial_size;
    private int size;
    private int lenght;
    private Node list[];
    
    public Array_list()
    {
        this(100);
    }
    
    public Array_list(int initial_size)
    {
        this.initial_size = initial_size;
        this.lenght = 0;
        this.size = this.initial_size;
        this.list = new Node[this.initial_size];
    }
    /*
     * Adds new element into array list.
     */
    public void add(Type data)
    {
        Node new_node = new Node((Object)data);
        this.list[this.lenght] = new_node;
        this.lenght ++;
        this.check_size();
    }
    /**
     * Checks does array list include given data.
     * @param data Data to be searched
     * @return true if data is in the list. Otherwise false.
     */
    public boolean contains(Type data)
    {
        int in_index = this.search(data);
        if(in_index == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * Searches given data from list.
     * @param data
     * @return index of data in the list. Return (-1)
     * if data wasn't found.
     */
    public int search(Type data)
    {
        for(int i = 0;i < this.lenght;i ++)
        {
            Node node = this.list[i];
            Type in_index = (Type)node.getData();
            if(data.equals(in_index))
            {
                return i;
            }
        }
        return -1;
    }
    /**
     * Sets given data in list given index.
     * @param data data to be stored.
     * @param index index where data is saved.
     * Index has to be within lists limits.
     */
    public void set_in_index(Type data,int index)
    {
        if(this.lenght <= index ||index < 0)
        {
            return;
        }
        this.list[index] = new Node(data);
    }
    /**
     * Increases size of the list.
     * @param increase how many indexes are added into list.
     */
    public void increase_size(int increase)
    {
        this.lenght = this.lenght + increase;
        this.check_size();
    }
    /**
     * removes data from list.
     * @param data 
     */
    public void remove(Type data)
    {
        
    }
    /**
     * Removes data in index from list.
     * @param index index of data to be removed.
     */
    public void remove(int index)
    {
        if(index > this.lenght)
        {
            return;
        }
    }
    /**
     * return size of the list.
     * @return 
     */
    public int size()
    {
        return this.lenght + 1;
    }
    /**
     * Gets element that is in lists in given index.
     * @param index index of data.
     * @return Data in given index.
     */
    public Type getIndex(int index)
    {
        Node node;
        try
        {
            node = this.list[index];
        }
        catch(Exception e)
        {
            return null;
        }
        return (Type)node.getData();
    }
    
    private void check_size()
    {
        if(this.lenght < this.size)
        {
            return;
        }
        this.double_size();
    }
    
    private void double_size()
    {
        this.size = this.size * 2;
        Node new_list[] = new Node[this.size];
        this.copy_array(new_list,this.list);
    }
    
    private void copy_array(Node copy[],Node target[])
    {
        for(int i = 0;i < copy.length;i ++)
        {
            target[i] = copy[i];
        }
    }
}
