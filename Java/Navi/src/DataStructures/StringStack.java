package DataStructures;

/**
 *
 * @author Leevi
 */
public class StringStack {

    private int capacity;
    private String[] stackArray;
    private int top;

    /**
     *
     * @param capacity of the stack.
     */
    public StringStack(int capacity) {

        this.capacity = capacity;
        stackArray = new String[capacity];
        top = -1;

    }

    /**
     * Pushes an item to the top of the stack.
     *
     * @param s, the position of a node on a map.
     */
    public void push(String s) {

        stackArray[++top] = s;

    }

    /**
     * Pops an item off of the top of the stack.
     *
     * @return Item that was most recently pushed to stack.
     */
    public String pop() {

        return stackArray[top--];

    }

    /**
     *
     * @return Most recently added item.
     */
    public String peek() {

        return stackArray[top];

    }

    /**
     *
     * @return true if stack has no items.
     */
    public boolean isEmpty() {

        return (top == -1);

    }

    /**
     *
     * @return true if stack is full.
     */
    public boolean isFull() {
        return (top == capacity - 1);
    }

}
