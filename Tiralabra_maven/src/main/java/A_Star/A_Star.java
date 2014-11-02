/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A_Star;

/**
 *
 * @author Lutikka
 */
public class A_Star implements IA_Star{

    public INode[] findShortestPath(INode start, INode finish, IHeuristic heuristic) {
        IA_Heap closedSet = constructHeap();
        
        
        return null;
    }
    
    /**
     * Constructs new Heap and returns it
     * @return 
     */
    private IA_Heap constructHeap(){
        //need comparator, null atm
        return new Heap(Heap.MIN_HEAP, null);
    }
}
