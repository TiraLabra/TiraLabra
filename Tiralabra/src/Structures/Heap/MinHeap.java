package Structures.Heap;

import Structures.Hashtable.Hashtable;

/**
 * Minium heap structure
 */
public class MinHeap<T> {
    private Node heap[];
    private int heapsize;
    private Hashtable<T,Integer> location;
    /**
     * Creates a minium heap of certain size
     */
    public MinHeap(int size){
        this.heap=new Node[size*2];
        this.heapsize=0;
        this.location=new Hashtable<T,Integer>(size*2);
    }
    private int parent(int i){
        return i/2;
    }
    private int left(int i){
        return 2*i;
    }
    private int right(int i){
        return 2*i+1;
    }
    /**
     * Drops node towards the bottom of the heap via the smallest node (left or right child) if such exists
     * so that the heap rule remains valid
     */
    private void heapify(int i){
        int l = left(i);
        int r = right(i);
        if(r<=this.heapsize){
            int min;
            if(this.heap[l].getKey()<this.heap[r].getKey()){
                min=l;
            }else{
                min=r;
            }
            if(this.heap[i].getKey()>this.heap[min].getKey()){
                Node temp = this.heap[i];
                this.location.put((T)(temp.getData()), min);
                this.location.put((T)(this.heap[min].getData()),i);
                this.heap[i]=this.heap[min];
                this.heap[min]=temp;
                heapify(min);
            }
        }else if(l==this.heapsize && this.heap[i].getKey()>this.heap[l].getKey()){
            Node temp = this.heap[i];
            this.location.put((T)(temp.getData()), l);
            this.location.put((T)(this.heap[l].getData()), i);
            this.heap[i]=this.heap[l];
            this.heap[l]=temp;
        }
    }
    /**
     * Inserts data with a certain key to the heap
     * @param Key which determines place in the heap and the object to store
     */
    public void insert(int key, T data){
        Node n = new Node(key,data);
        this.heapsize++;
        int i = this.heapsize;
        while(i>1 && this.heap[parent(i)].getKey()>key){
            this.location.put((T)(this.heap[parent(i)].getData()), i);
            this.heap[i]=this.heap[parent(i)];
            i=parent(i);
        }
        this.heap[i]=n;
        this.location.put((T)(data), i);
    }
    /**
     * Pops out the smallest key in the heap
     * @return Object of the smallest key in the heap
     */
    public T pop(){
        if(isEmpty()){
            return null;
        }
        Object min = this.heap[1].getData();
        this.location.put((T)(this.heap[1].getData()), -1);
        this.heap[1]=this.heap[this.heapsize];
        this.location.put((T)(this.heap[this.heapsize].getData()), 1);
        this.heapsize--;
        heapify(1);
        return (T)min;
    }
    /**
     * Checks if certain object is currently in the heap
     * @return true if object is in the heap, false otherwise
     */
    public boolean inHeap(T node){
        return this.location.get(node)!=-1 && this.location.get(node)!=0;
    }
    /**
     * Decreases the key of a certain object
     */
    public void decrease(T node, int key){
        if(this.location.get(node)==-1 || this.location.get(node)==0){
            return;
        }
        int i = this.location.get(node);
        if(key<this.heap[i].getKey()){
            this.heap[i].setKey(key);
            while(i>1 && this.heap[parent(i)].getKey()>this.heap[i].getKey()){
                Node temp = this.heap[i];
                this.location.put((T)(this.heap[parent(i)].getData()), i);
                this.location.put((T)(temp.getData()), parent(i));
                this.heap[i]=this.heap[parent(i)];
                this.heap[parent(i)]=temp;
                i=parent(i);
            }
        }
    }
    /**
     * Checks if the heap is empty
     */
    public boolean isEmpty(){
        return this.heapsize==0;
    }
}
