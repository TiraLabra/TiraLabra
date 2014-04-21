package sequencealgorithms;

/**
 * An interface for handling different kind of problems.
 *
 * @author Jari Haavisto
 */
public interface Problem {
    
    /**
     * This method delivers user input for the problem algorithms to use in setting up the problem.
     * 
     * @param score1
     * @param score2
     * @param score3
     * @param score4 
     */
    public void setUpScoring(double score1, double score2, double score3, double score4);
    /**
     * Solves the problem, storing the solution in the variable 'solution'.
     */
    public void solve();
    /**
     * Getter for the solution.
     * @return The solution
     */
    public char[][] getSolution();
    
}
