/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_data_structures.stack;

/**
 *
 * @author henrikorpela
 */
public class Node {
    public Node next;
    public Node prev;
    private Object data;
    
    public Node(Object data)
    {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
