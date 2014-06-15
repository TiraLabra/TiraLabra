
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
    public void findOptimal(Map m, Node start, Node goal) {     //begin path finding
        accessed.insertNode(start);                             //initialize first node
        start.setToStart(0);
        start.setToGoal(goal.getY(), goal.getX());
        start.setPrev(start);                                   //start doesn't have a previous node
        
        while(!accessed.isEmpty()) {
            current = accessed.getHighest();                    //mark node that is checked as current
            accessed.removeNode();                              //remove current from the list of nodes to check
            checked.insertNode(current);                               //and store it in collection of checked ones
            current.setValue('-');
            if (current == goal){
                current.setValue('.');
                break;                                          //route found
            }
            
            for(int i=-1;i<=1;i++) {
                for(int j=-1;j<=1;j++)
                    markNeighbour(m,goal,i,j);
            }
        }
        printPath(goal);
        m.printField();
    }
    
    /**
     * 
     * @param n the node to be 'filled'
     * @param y goal on y-axis
     * @param x goal on x-axis
     */
    public void setNodeVariables(Node n, int y,int x) {
        n.setPrev(current);                                 //set this node as followers 'previous'
        n.setToStart(current.getToStart()+1);               //set distance to start                    
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
    public void markNeighbour(Map m, Node goal, int y, int x) {
        try{neighbor = m.field[current.getY()+y][current.getX()+x];    //fill in the data for neighbors
                if(neighbor.getValue() == 'X')
                    checked.insertNode(neighbor);                             //if a wall, discard
                if(!checked.hasNode(neighbor)) {                              //if node hasn't been checked
                    if(!accessed.hasNode(neighbor)){
                        setNodeVariables(neighbor,goal.getY(),goal.getX());
                        accessed.insertNode(neighbor);
                    }
                    if(accessed.hasNode(neighbor) && neighbor.getPrio()>((current.getToStart()+1)+neighbor.getToGoal())){
                          accessed.updateNode(neighbor,current.getToStart()+1);
                    }
                }
            }   catch(Exception e) {}
    }
    
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
