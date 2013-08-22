/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.priority_queue;

/**
 * Interface that data structures that are used as priority queue implement.
 * @author henrikorpela
 */
public interface Priority_queue<Type> {
    public Type get_min();
    public Type extract_min();
    public void add(Type data,int key);
    public void decrease_key(Type type,int new_key);
}
