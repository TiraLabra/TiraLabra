package labyrinthsolver;

import main.Labyrinth;

/**
 * "Oikean käden sääntö" -algoritmi labyrintin ratkaisemiseen.
 *
 * @author Juri Kuronen
 */
public class WallFollower extends LabyrinthSolver {

    /**
     * @param l Labyrintti, jolle algoritmi ajetaan.
     * @see labyrinthsolver.LabyrinthSolver#LabyrinthSolver(main.Labyrinth)
     */
    public WallFollower(Labyrinth l) {
        super(l);
    }

    /**
     * @see labyrinthsolver.LabyrinthSolver#routine()
     */
    @Override
    public void routine() {
        System.out.print("Wall Follower");
        super.routine();
    }

    /**
     * Etenee labyrintissä "oikeaa kättä seinässä kiinni pitäen" niin kauan,
     * kunnes ratkaisu löytyy.
     *
     * @return Palauttaa true, jos labyrintti ratkaistiin.
     */
    @Override
    public boolean solveLabyrinth() {
        int coordinate = 0;
        int targetCoordinate = labyrinth.width * labyrinth.height - 1;
        /*
         Set initially wall direction to WEST and walk direction to SOUTH.   
         */
        byte wallDirection = 8, walkDirection = 4;
        while (coordinate != targetCoordinate) {
            /*
             If there's a hole in the wall direction, start going there.
             */
            if ((labyrinth.labyrinth[coordinate / labyrinth.width][coordinate % labyrinth.width] & wallDirection) != 0) {
                walkDirection = wallDirection;
                wallDirection = (byte) ((wallDirection * 2) % 15);
            }
            /*
             Find a walk direction which isn't blocked.
             */
            while ((labyrinth.labyrinth[coordinate / labyrinth.width][coordinate % labyrinth.width] & walkDirection) == 0) {
                wallDirection = walkDirection;
                if (walkDirection == 1) {
                    walkDirection = 8;
                } else {
                    walkDirection /= 2;
                }
            }
            /*
             Update coordinate.
             */
            coordinate = labyrinth.getTargetCoordinate(coordinate, walkDirection);
        }
        return true;
    }

    /**
     * Laskee visited-solujen määrän. Käytännössä ajaa solveLabyrinthin
     * uudelleen, niin että laskee samalla vieraillut solut.
     */
    @Override
    protected void getExploredCells() {
        int coordinate = 0;
        exploredCells = 1;
        int targetCoordinate = labyrinth.width * labyrinth.height - 1;
        /*
         Set initially wall direction to WEST and walk direction to SOUTH.   
         */
        byte wallDirection = 8, walkDirection = 4;
        while (coordinate != targetCoordinate) {
            /*
             If there's a hole in the wall direction, start going there.
             */
            if ((labyrinth.labyrinth[coordinate / labyrinth.width][coordinate % labyrinth.width] & wallDirection) != 0) {
                walkDirection = wallDirection;
                wallDirection = (byte) ((wallDirection * 2) % 15);
            }
            /*
             Find a walk direction which isn't blocked.
             */
            while ((labyrinth.labyrinth[coordinate / labyrinth.width][coordinate % labyrinth.width] & walkDirection) == 0) {
                wallDirection = walkDirection;
                if (walkDirection == 1) {
                    walkDirection = 8;
                } else {
                    walkDirection /= 2;
                }
            }
            /*
             Update coordinate.
             */
            coordinate = labyrinth.getTargetCoordinate(coordinate, walkDirection);
            exploredCells++;
        }
    }

}
