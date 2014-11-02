package A_Star;

/**
 * 
 * @author Lutikka
 */
public interface IA_Star {
    
    public INode[] findShortestPath(INode start, INode finish, IHeuristic heuristic);
}
