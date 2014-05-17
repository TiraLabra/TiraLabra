package labyrinthsolver;

import java.util.Random;
import main.Labyrinth;

public abstract class LabyrinthSolver {

    public Labyrinth labyrinth;
    public Random random;

    public LabyrinthSolver(Labyrinth l) {
        labyrinth = l;
        random = new Random();
    }
    
    public abstract void solveLabyrinth();

}
