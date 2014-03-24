
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

    public void findOptimal(Map m, Node start, Node goal) {     //begin path finding
        accessed.add(start);                                    //initialize first node
        start.setToStart(0);
        start.setToGoal(goal.getY(), goal.getX());
        start.setPrev(start);                                 //start doesn't have a previous node
        
        while(!accessed.isEmpty()) {
            current = accessed.get(0);              //set added node to temp for easier access
            if (current == goal)
                current = goal;                                 //just filling for now


            
            try{neighbour = m.field[current.getY()+1][current.getX()];  //fill in the data for neighbours
                if(!checked.contains(neighbour)) {                      //if node hasn't been checked
                    neighbour.setPrev(current);                         //set this node as followers 'previous'
                    neighbour.setToStart(current.getToStart()+1);       //set distance to start                    
                    neighbour.setToGoal(goal.getY(),goal.getX());       //set distance to goal
                    if(neighbour.getValue() == '0'){neighbour.setValue('x');}
                    if(!accessed.contains(neighbour)) {
                        for(int i = 0; i<accessed.size();i++) {
                            if(accessed.isEmpty())                     //just add if empty
                                accessed.add(neighbour);
                            if(accessed.get(i).getPrio() > current.getPrio()){   //add if the one in i has lower prio
                                accessed.add(i, neighbour);
                                if(i==accessed.size())
                                    accessed.add(neighbour);
                            }
                        }
                        if(!accessed.contains(neighbour) && neighbour.getValue() != '*')
                            accessed.add(neighbour);
                    }
                    if(accessed.contains(neighbour) && neighbour.getPrio()>((current.getToStart()+1)+neighbour.getToGoal())){
                          neighbour.setToStart(current.getToStart()+1);     //shorter way to node found. update it's prio
                    }
                }
            }catch(Exception e) {}

            try{neighbour = m.field[current.getY()-1][current.getX()];
                if(!checked.contains(neighbour)) {   
                    neighbour.setPrev(current);                        
                    neighbour.setToStart(current.getToStart()+1);      
                    neighbour.setToGoal(goal.getY(),goal.getX());
                    if(neighbour.getValue() == '0'){neighbour.setValue('x');}
                    if(!accessed.contains(neighbour)) {
                        for(int i = 0; i<accessed.size();i++) {
                            if(accessed.isEmpty())                      //just add if empty
                                accessed.add(neighbour);                            
                            if(accessed.get(i).getPrio() > current.getPrio()){   //add if the one in i has lower prio
                                accessed.add(i, neighbour);
                                if(i==accessed.size())
                                    accessed.add(neighbour);
                            }
                        }
                        if(!accessed.contains(neighbour) && neighbour.getValue() != '*')
                            accessed.add(neighbour);
                    }
                    if(accessed.contains(neighbour) && neighbour.getPrio()>((current.getToStart()+1)+neighbour.getToGoal())){
                          neighbour.setToStart(current.getToStart()+1);     
                    }
                }
            }   catch(Exception e) {System.out.println("hei2");}

            try{neighbour = m.field[current.getY()][current.getX()+1];
                if(!checked.contains(neighbour)) {    
                    neighbour.setPrev(current);                         
                    neighbour.setToStart(current.getToStart()+1);       
                    neighbour.setToGoal(goal.getY(),goal.getX());
                    if(neighbour.getValue() == '0'){neighbour.setValue('x');}
                    if(!accessed.contains(neighbour)) {
                        for(int i = 0; i<accessed.size();i++) {
                            if(accessed.isEmpty())                      //just add if empty
                                accessed.add(neighbour);                            
                            if(accessed.get(i).getPrio() > current.getPrio()){   //add if the one in i has lower prio
                                accessed.add(i, neighbour);
                                if(i==accessed.size())
                                    accessed.add(neighbour);
                            }
                        }
                        if(!accessed.contains(neighbour) && neighbour.getValue() != '*')
                            accessed.add(neighbour);
                    }
                    if(accessed.contains(neighbour) && neighbour.getPrio()>((current.getToStart()+1)+neighbour.getToGoal())){
                          neighbour.setToStart(current.getToStart()+1);     
                    }
                }
            }   catch(Exception e) {System.out.println("hei3");}

            try{neighbour = m.field[current.getY()][current.getX()-1];
                if(!checked.contains(neighbour)) {
                   neighbour.setPrev(current);                         
                   neighbour.setToStart(current.getToStart()-1);       
                   neighbour.setToGoal(goal.getY(),goal.getX());
                   if(neighbour.getValue() == '0'){neighbour.setValue('x');}
                   if(!accessed.contains(neighbour)) {
                        for(int i = 0; i<accessed.size();i++) {
                            if(accessed.isEmpty())                      //just add if empty
                                accessed.add(neighbour);                            
                            if(accessed.get(i).getPrio() > current.getPrio()){   //add if the one in i has lower prio
                                accessed.add(i, neighbour);
                                if(i==accessed.size())
                                    accessed.add(neighbour);
                            }
                        }
                        if(!accessed.contains(neighbour) && neighbour.getValue() != '*')
                            accessed.add(neighbour);
                    }
                  if(accessed.contains(neighbour) && neighbour.getPrio()>((current.getToStart()+1)+neighbour.getToGoal())){
                          neighbour.setToStart(current.getToStart()+1);     
                   }
                }
            }catch(Exception e) {System.out.println(e);}
        
            
            accessed.remove(current);                           //remove from the list of nodes to check
            accessed.trimToSize();
            checked.add(current);                               //and store it in 
            current.setValue('H');
            m.printField();
            for(int i =0; i<accessed.size();i++) {
                System.out.print("("+accessed.get(i).getY()+","+accessed.get(i).getX()+")");
            }
            System.out.println("");
        }
    }
}
