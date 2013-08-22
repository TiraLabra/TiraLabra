
package pr_data_structures.heap_min;
import java.util.ArrayList;
import java.util.HashMap;
import pr_data_structures.priority_queue.Priority_queue;
/**
 *
 * @author Henri Korpela
 * 
 * Min heap.
 */
public class Heap<Type> implements Priority_queue{
    private Node heap[];
    private int heap_size;
    private HashMap<Type,Integer> location;
    
    public Heap(int size)
    {
        this.heap = new Node[size];
        this.heap_size = 0;
        this.location = new HashMap<Type,Integer>();
    }
    
    @Override
    public Object get_min() {
        if(this.heap_size == 0)
        {
            return null;
        }
        return this.heap[1].get_data();
    }

    @Override
    public Object extract_min() {
        if(this.heap_size == 0)
        {
            return null;
        }
        Object data = this.heap[1].get_data();
        this.heap[1] = this.heap[this.heap_size];
        this.location.put((Type)this.heap[this.heap_size].get_data(),1);
        this.heap_size --;
        this.heapify(1);
        return data;
    }

    @Override
    public void add(Object data, int key) {
        Node node = new Node(key,data);
        this.heap_size ++;
        int index = this.heap_size;
        while(index > 1 && this.heap[this.parent(index)].key > node.key)
        {
            this.location.put((Type)this.heap[this.parent(index)].get_data(),index);
            this.heap[index] = this.heap[this.parent(index)];
            index = this.parent(index);
        }
        this.heap[index] = node;
        this.location.put((Type)node.get_data(),index);
    }
    
    @Override
    public void decrease_key(Object type, int new_key) {
        int index = this.location.get((Type)type);
        if(this.heap[index].key > new_key)
        {
            this.heap[index].key = new_key;
            while(index > 1 && this.heap[this.parent(index)].key > this.heap[index].key)
            {
                swap(index,this.parent(index));
                index = this.parent(index);
            }
        }
    }
    
    private void heapify(int index)
    {
        int left = this.child_left(index);
        int right = this.child_right(index);
        
        int min = 0;
        if(right <= this.heap_size)
        {
            if(this.heap[left].key < this.heap[right].key)
            {
                min = left;
            }
            else
            {
                min = right;
            }
            if(this.heap[index].key > this.heap[min].key)
            {
                swap(index,min);
                this.heapify(min);
            }
        }
        else if(left == this.heap_size && this.heap[index].key > this.heap[left].key)
        {
            swap(index,left);
        }
    }
    
    private void swap(int i1,int i2)
    {
        Node temp1 = this.heap[i1];
        Node temp2 = this.heap[i2];
        this.heap[i1] = this.heap[i2];
        this.heap[i2] = temp1;
        
        this.location.put((Type)temp2.get_data(),i1);
        this.location.put((Type)temp1.get_data(),i2);
    }
    
    private int parent(int index)
    {
        return index / 2;
    }
    
    private int child_left(int index)
    {
        return 2 * index;
    }
    
    private int child_right(int index)
    {
        return 2 * index + 1;
    }
}
