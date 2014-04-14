
package Astar;
import java.util.ArrayList;
/**
 *
 * @author Arto
 */


public class Finder {
    
    Node current;
    Node neighbor;
    ArrayList<Node> path;
    Heap accessed;
    ArrayList<Node> checked;
    /**
     * Constructor
     */
    Finder(){
       current = null;
       neighbor = null;
       path = new ArrayList();
       accessed = new Heap();
       checked = new ArrayList();
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
            checked.add(current);                               //and store it in collection of checked ones
            current.setValue('-');
            System.out.println("current: ("+current.getX()+","+current.getY()+")");
            if (current == goal){
                current.setValue('.');
                break;                                          //route found
            }
            markNeighbour(m,goal,1,0);              
            markNeighbour(m,goal,-1,0);
            markNeighbour(m,goal,0,1);
            markNeighbour(m,goal,0,-1);
            markNeighbour(m,goal,1,-1);
            markNeighbour(m,goal,1,1);
            markNeighbour(m,goal,-1,-1);
            markNeighbour(m,goal,-1,1);
            
            m.printField();
            System.out.println(accessed.getSize());
            for(int i = 1; i<=accessed.getSize();i++) {
                System.out.print(i+":("+accessed.get(i).getX()+","+accessed.get(i).getY()+") "+accessed.get(i).getPrio());
                System.out.println();
            }
        }
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
        try{neighbor = m.field[current.getY()+(1*y)][current.getX()+(1*x)];    //fill in the data for neighbors
                if(neighbor.getValue() == 'X')
                    checked.add(neighbor);                                     //if a wall, discard
                if(!checked.contains(neighbor)) {                              //if node hasn't been checked
                    if(!accessed.hasNode(neighbor)){
                        accessed.insertNode(neighbor);
                        setNodeVariables(neighbor,goal.getY(),goal.getX());
                    }
                    if(accessed.hasNode(neighbor) && neighbor.getPrio()>((current.getToStart()+1)+neighbor.getToGoal())){
                          neighbor.setToStart(current.getToStart()+1);
                          accessed.sortHeap(1);
                    }
                }
            }   catch(Exception e) {}
    }
}
