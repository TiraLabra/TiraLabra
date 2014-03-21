
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
    
    Finder(){
       current = null;
       neighbour = null;
       path = new ArrayList();
       accessed = new ArrayList();
       checked = new ArrayList();
    }

    public void hello() {
        System.out.println("hei");
    }
    public void findOptimal(Map m, Node start, Node goal) {     //begin path finding
        accessed.add(start);                                    //initialize first node
        start.setToStart(0);
        start.setToGoal(goal.getY(), goal.getX());
        start.setPrev(start);                                 //start doesn't have a previous node
        
        while(!accessed.isEmpty()) {
            current = accessed.get(accessed.size()-1);              //set added node to temp for easier access
            if (current == goal)
                current = goal;                                 //just filling for now

            accessed.remove(current);                           //remove from the list of nodes to check
            checked.add(current);                               //and store it in 
            
            try{neighbour = m.field[current.getY()+1][current.getX()];  //fill in the data for neighbours
                if(!checked.contains(neighbour)) {                      //if node hasn't been checked
                    neighbour.setPrev(current);                         //set this node as followers 'previous'
                    neighbour.setToStart(current.getToStart()+1);       //set distance to start
                    neighbour.setToGoal(goal.getY(),goal.getX());       //set distance to goal
                    if(!accessed.contains(neighbour)) {
                        accessed.add(neighbour);                            //add to visited
                    }
                }
            }catch(Exception e) {}

            try{neighbour = m.field[current.getY()-1][current.getX()];
                if(!checked.contains(neighbour)) {   
                    neighbour.setPrev(current);                        
                    neighbour.setToStart(current.getToStart()+1);      
                    neighbour.setToGoal(goal.getY(),goal.getX());       
                    if(!accessed.contains(neighbour)) {
                        accessed.add(neighbour);                            //add to visited
                    }
                }
            }   catch(Exception e) {}

            try{neighbour = m.field[current.getY()][current.getX()+1];
                if(!checked.contains(neighbour)) {    
                    neighbour.setPrev(current);                         
                    neighbour.setToStart(current.getToStart()+1);       
                    neighbour.setToGoal(goal.getY(),goal.getX()); 
                    if(!accessed.contains(neighbour /*|| UUDESTA SUUNNASTA EDULLISEMPI*/)){ 
                        accessed.add(neighbour);                            //add to visited
                    }
                }
            }   catch(Exception e) {}

            try{neighbour = m.field[current.getY()][current.getX()-1];
                if(!checked.contains(neighbour)) {
                   neighbour.setPrev(current);                         
                   neighbour.setToStart(current.getToStart()-1);       
                   neighbour.setToGoal(goal.getY(),goal.getX()); 
                   if(!accessed.contains(neighbour)) {
                        accessed.add(neighbour);                            //add to visited
                   }
                }
            }   catch(Exception e) {}


        }
    }
}
