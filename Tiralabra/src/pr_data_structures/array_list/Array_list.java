/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.array_list;

import pr_data_structures.stack.Stack;

/**
 *
 * @author henrikorpela
 */
public class Array_list<Type> {
    private final int initial_size;
    private int size;
    private int lenght;
    private Node list[];
    private Stack<Type> removed_data;
    
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
        this.removed_data = new Stack<Type>();
    }
    
    public void add(Type data)
    {
        Node new_node = new Node((Object)data);
        this.list[this.lenght] = new_node;
        this.lenght ++;
        this.check_size();
    }
    
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
    
    public void remove(Type data)
    {
        this.removed_data.add(data);
    }
    
    public void remove(int index)
    {
        if(index > this.lenght)
        {
            return;
        }
    }
    
    public int size()
    {
        return this.lenght + 1;
    }
    
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
