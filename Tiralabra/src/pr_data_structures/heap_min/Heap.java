/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.heap_min;
import java.util.ArrayList;
import pr_data_structures.priority_queue.Priority_queue;
/**
 *
 * @author henrikorpela
 * 
 * Min heap that uses Array_list to store information.
 */
public class Heap<Type> implements Priority_queue{
    private ArrayList<Node> heap;
    
    public Heap()
    {
        this.heap = new ArrayList<Node>();
    }
    
    /*
     * Return heaps element with lowest key value.
     */
    @Override
    public Type get_min()
    {
        return (Type)this.heap.get(1).get_data();
    }
    
    /*
     * Return heaps element with lowest key value
     * and removes it from heap.
     */
    @Override
    public Type extract_min()
    {
        Node min = this.heap.get(0);
        this.heap.set(0,this.heap.get(this.heap.size()-1));
        this.heap.remove(this.heap.size()-1);
        this.heapify(0);
        return (Type)min.get_data();
    }
    /*
     * Adds new element into heap.
     */
    @Override
    public void add(Object data, int key) {
        int index = this.heap.size() + 1;
        while(index > 0 && this.heap.get(this.parents_index(index)).key > key)
        {
            this.heap.set(index,this.heap.get(this.parents_index(index)));
            index = this.parents_index(index);
        }
        this.heap.set(index,new Node(key,data));
    }
    /**
     * Decreases key of the element in given index. If key of
     * the element is lower than new key method doesn't do anything.
     * @param index index of the element that needs that key is decreased.
     * @param new_key new key for the element.
     */
    @Override
    public void decrease_key(int index,int new_key)
    {
        if(new_key < this.heap.get(index).key)
        {
            this.heap.get(index).key = new_key;
            while(index > 0 && this.heap.get(this.parents_index(index)).key > this.heap.get(index).key)
            {
                this.swap(index,this.parents_index(index));
                index = this.parents_index(index);
            }
        }
    }
    
    private void heapify(int index)
    {
        int left = this.left_child_index(index);
        int right = this.right_child_index(index);
        
        if(right <= this.heap.size())
        {
            int smallest;
            if(this.heap.get(left).key < this.heap.get(right).key)
            {
                smallest = left;
            }
            else
            {
                smallest = right;
            }
            if(this.heap.get(index).key > this.heap.get(smallest).key)
            {
                this.swap(index,smallest);
                this.heapify(smallest);
            }
        }
        
        else if(left == this.heap.size() && this.heap.get(index).key > this.heap.get(left).key)
        {
            this.swap(index,left);
        }
    }
    
    private void swap(int index_1,int index_2)
    {
        Node temp_1 = this.heap.get(index_1);
        this.heap.set(index_1,this.heap.get(index_2));
        this.heap.set(index_2, temp_1);
    }
    
    private int left_child_index(int parent)
    {
        return 2 * parent + 1;
    }
    
    private int right_child_index(int parent)
    {
        return 2 * parent + 2;
    }
    
    private int parents_index(int child)
    {
        int index = (int)((child - 1) / 2);
        if(child % 2 != 0)
        {
            return index - 1;
        }
        else
        {
            return index;
        }
    }
}
