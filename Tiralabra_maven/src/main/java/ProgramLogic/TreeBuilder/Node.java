package ProgramLogic.TreeBuilder;

/**
 *
 * @author alpa
 */

/*
 * This class is an implemantation of a node for a binary tree
 */
public class Node {

    int symbol;
    int weight;

    /*
     * A node has two values: 
     * int symbol, representing a byte
     * int weight, the value of the symbol in question (rare symbols
     * are more valuable)
     */
    public Node(int symbol, int weight) {
        this.symbol = symbol;
        this.weight = weight;
    }

    /*
     * Returns the value of symbol
     */
    public int getSymbol() {
        return symbol;
    }

    /*
     * Returns the value of weight
     */
    public int getWeight() {
        return weight;
    }
}
