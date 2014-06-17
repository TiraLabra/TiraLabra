
package Astar;
import java.util.ArrayList;
/**
 *
 * @author Arto
 */


public class Finder {
    
    Node current;
    Node neighbor;
    Heap accessed;
    Heap checked;
    /**
     * Constructor
     */
    Finder(){
       current = null;
       neighbor = null;
       accessed = new Heap();
       checked = new Heap();
    }
    
    
    /**
     * Finds the shortest route from start to goal
     * 
     * @param m the map currently being analyzed
     * @param start
     * @param goal 
     */
    public void findPath(Map2 m, Node start, Node goal) {
        initializeStart(start,goal);
        while(!accessed.isEmpty()) {
            handleCurrentNode();
            if (current == goal){
                current.setValue('.');
                break;
            }
            for(int i=-1;i<=1;i++) {
                for(int j=-1;j<=1;j++)
                    markNeighbour(m,goal,i,j);
            }}
        printPath(goal);
        m.printField();
    }
    
    /**
     * Set the parameters for the first node
     * 
     * @param n start node 
     * @param g goal node
     */
    public void initializeStart(Node s, Node g) {
        accessed.insertNode(s);
        s.setToStart(0);
        s.setToGoal(g.getY(), g.getX());
        s.setPrev(s);
    }
    
    /**
     * Takes a Node from Heap 
     */
    public void handleCurrentNode() {
            current = accessed.getHighest();
            accessed.removeNode();
            checked.insertNode(current);
            current.setValue('-');
    }
    
    /**
     * Set information about necessary nodes and own data
     * 
     * @param n the node to be 'filled'
     * @param y goal on y-axis
     * @param x goal on x-axis
     */
    public void setNodeVariables(Node n, int y,int x) {
        n.setPrev(current);
        n.setToStart(current.getToStart()+1);                  
        n.setToGoal(y,x);
        if(n.getValue() == '0')
            n.setValue('*');
    }
    
    
    /**
     * Goes through the neighbors of current node and marks them 
     * 
     * @param m the map currently being analyzed
     * @param goal 
     * @param y used to define a step in y-axis from current node
     * @param x used to define a step in x-axis from current node
     */
    public void markNeighbour(Map2 m, Node goal, int y, int x) {
        try{neighbor = m.field[current.getY()+y][current.getX()+x];    //fill in the data for neighbors
                if(neighbor.getValue() == 'X')
                    checked.insertNode(neighbor);                             //if a wall, discard
                if(!checked.hasNode(neighbor)) {
                    if(!accessed.hasNode(neighbor)){
                        setNodeVariables(neighbor,goal.getY(),goal.getX());
                        accessed.insertNode(neighbor);
                    }
                    if(accessed.hasNode(neighbor) && neighbor.getPrio()>((current.getToStart()+1)+neighbor.getToGoal())){
                          accessed.updateNode(neighbor,current.getToStart()+1);
                    }}
        } catch(Exception e) {}
    }
    
    /**
     * Print out the path that the algorithm found
     * 
     * @param n Node from which the path starts printing (== goal)
     */
    public void printPath(Node n) {
        while(true){
            System.out.print(n.toString());
            n.setValue('P');
            if(n.getPrev()==n)
                break;
            n = n.getPrev();
        }
        System.out.println();
    }
}
