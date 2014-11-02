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
