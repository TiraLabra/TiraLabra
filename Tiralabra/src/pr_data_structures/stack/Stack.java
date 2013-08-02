/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.stack;

/**
 *
 * @author henrikorpela
 */

public class Stack<Type> {
    private Node first;
    private int size;
    
    private enum Remove_identifier {
        FIRST,MID,END;
    }
    
    public Stack()
    {
        this.first = null;
        this.size = 0;
    }
    
    public void add(Type data)
    {
        Node new_node = new Node((Object)data);
        if(this.first == null)
        {
            this.first = new_node;
            this.size ++;
            return;
        }
        Node temp = this.first;
        temp.next = new_node;
        this.first = new_node;
        this.first.next = null;
        this.first.prev = temp;
        this.size ++;
    }
    
    public Type get_in_index(int index)
    {
        Node node = this.first;
        int i = 1;
        while(node != null && i <= index)
        {
            if(i == index)
            {
                return (Type)node.getData();
            }
            node = node.prev;
            i ++;
        }
        return null;
    }
    
    public Type get_first()
    {
        return (Type)this.first.getData();
    }
    
    public boolean contains(Type data)
    {
        Node node = this.first;
        while(node != null)
        {
            if(node.getData().equals(data))
            {
                return true;
            }
            node = node.prev;
        }
        return false;
    }
    
    public void remove(Type data)
    {
        Node node = this.first;
        while(node != null)
        {
            Type node_data = (Type)node.getData();
            if(node_data.equals(data))
            {
                Remove_identifier place = this.identify_place(node);
                this.remove_from_stack(node,place);
                this.size --;
            }
            node = node.prev;
        }
    }
    
    public int size()
    {
        return this.size;
    }
    
    public boolean is_empty()
    {
        if(this.first == null)
        {
            return true;
        }
        return false;
    }
    
    private Remove_identifier identify_place(Node node)
    {
        if(node.next == null)
        {
            return Remove_identifier.FIRST;
        }
        else if(node.prev == null)
        {
            return Remove_identifier.END;
        }
        else
        {
            return Remove_identifier.MID;
        }
    }
    
    private void remove_from_stack(Node node,Remove_identifier place)
    {
        switch(place)
        {
            case FIRST: this.remove_first(node);
                        break;
            case MID: this.remove_mid(node);
                        break;
            case END: this.remove_end(node);
                        break;
        }
    }
    
    private void remove_first(Node node)
    {
        this.first = node.prev;
        this.first.next = null;
    }
    
    private void remove_mid(Node node)
    {
        Node temp_1 = node.next;
        Node temp_2 = node.prev;
        temp_1.prev = temp_2;
        temp_2.next = temp_1;
    }
    
    private void remove_end(Node node)
    {
        Node temp = node.next;
        temp.prev = null;
    }
    
    @Override
    public String toString()
    {
        String stack_string = "";
        Node node = this.first;
        while(node != null)
        {
            if(node.prev == null)
            {
                stack_string = stack_string + node.getData().toString();
            }
            else
            {
                stack_string = stack_string + node.getData().toString() + " ";
            }
            node = node.prev;
        }
        return stack_string;
    }
}
