
package Astar;
import java.util.ArrayList;
/**
 *
 * @author Arto
 */


public class Finder {
    
    Node current;
    Node neighbour;
    ArrayList<Node> path;
    ArrayList<Node> accessed;
    ArrayList<Node> checked;
    /**
     * Constructor
     */
    Finder(){
       current = null;
       neighbour = null;
       path = new ArrayList();
       accessed = new ArrayList();
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
        accessed.add(start);                                    //initialize first node
        start.setToStart(0);
        start.setToGoal(goal.getY(), goal.getX());
        start.setPrev(start);                                   //start doesn't have a previous node
        
        while(!accessed.isEmpty()) {
            current = accessed.get(0);                          //set added node to temp for easier access
            if (current == goal){
                current.setValue('.');
                break;  }                                       //route found

            markNeighbour(m,goal,1,0);              
            markNeighbour(m,goal,-1,0);
            markNeighbour(m,goal,0,1);
            markNeighbour(m,goal,0,-1);
            markNeighbour(m,goal,1,-1);
            markNeighbour(m,goal,1,1);
            markNeighbour(m,goal,-1,-1);
            markNeighbour(m,goal,-1,1);
            
            accessed.remove(current);                           //remove from the list of nodes to check
            accessed.trimToSize();
            checked.add(current);                               //and store it in 
            current.setValue('.');
            m.printField();
            System.out.println();
        }
    }
    
    /**
     *  Places the neighbour in the correct slot based on its priority
     */
    public void placeNeighbour() {
        for(int i = 0; i<accessed.size();i++) {
            if(accessed.contains(neighbour))                        //just added, no need to continue
               break;
            if(accessed.isEmpty()) {                                //just add if empty
               accessed.add(neighbour);   
               break;}
            if(accessed.get(i).getPrio() > neighbour.getPrio()){    //add if the one in i has lower prio
               accessed.add(i, neighbour);
               break;
            }
        }
        if(!accessed.contains(neighbour) && neighbour.getValue() != 'X')
           accessed.add(neighbour);
    }
    /**
     * Goes through the neighbours of current node and marks them 
     * 
     * @param m the map currently being analyzed
     * @param goal 
     * @param y used to define a step in y-axis from current node
     * @param x used to define a step in x-axis from current node
     */
    public void markNeighbour(Map m, Node goal, int y, int x) {
        try{neighbour = m.field[current.getY()+(1*y)][current.getX()+(1*x)];    //fill in the data for neighbours
                if(neighbour.getValue() == 'X')
                    checked.add(neighbour);                                     //if a wall, discard
                if(!checked.contains(neighbour)) {                              //if node hasn't been checked
                    neighbour.setPrev(current);                                 //set this node as followers 'previous'
                    neighbour.setToStart(current.getToStart()+1);               //set distance to start                    
                    neighbour.setToGoal(goal.getY(),goal.getX());               //set distance to goal
                    if(neighbour.getValue() == '0'){neighbour.setValue('*');}
                    if(!accessed.contains(neighbour)) {
                        placeNeighbour();
                    }
                    if(accessed.contains(neighbour) && neighbour.getPrio()>((current.getToStart()+1)+neighbour.getToGoal())){
                          neighbour.setToStart(current.getToStart()+1);     
                    }
                }
            }   catch(Exception e) {}
    }
}
