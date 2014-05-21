package labyrinthgenerator;

import main.MyList;
import main.Labyrinth;
import main.MyStack;

/**
 * Satunnaistettu rekursiivinen peruuttava haku labyrintin generoimiseen.
 *
 * @author Juri Kuronen
 */
public class RecursiveBacktracker extends LabyrinthGenerator {

    /**
     * @param l Labyrintti, jolle algoritmi ajetaan.
     * @see
     * labyrinthgenerator.LabyrinthGenerator#LabyrinthGenerator(main.Labyrinth)
     */
    public RecursiveBacktracker(Labyrinth l) {
        super(l);
    }

    /**
     * @see labyrinthgenerator.LabyrinthGenerator#routine
     */
    @Override
    public void routine() throws Exception {
        System.out.print("Recursive Backtracker");
        super.routine();
    }

    /**
     * Lähtee liikkeelle lähtösolusta. Siirtyy satunnaiseen vierailemattomaan
     * soluun, ja asettaa liikkuessa edellisen solun pinon päällimmäiseksi.
     * Toistaa tätä niin kauan, kunnes tullaan soluun, jolla ei enää ole
     * vierailemattomia naapureita. Tällöin algoritmi peruuttaa palaamalla pinon
     * päällimmäiseen soluun niin kauan, kunnes löytyy solu, jolla on
     * vierailemattomia naapureita. Pinon tyhjetessä labyrintti on generoitu.
     * <br>
     * Labyrintin toiminnasta löytyy tietoa myös määrittelydokumentista.
     *
     * @see labyrinthgenerator.LabyrinthGenerator#getListOfUnvisitedNeighbors
     * @see main.MyStack
     * @see main.MyList
     * @see main.Labyrinth#addPassage(int, int)
     */
    @Override
    public void generateLabyrinth() {
        MyStack<Integer> stack = new MyStack();
        int[][] visited = new int[labyrinth.height][labyrinth.width];
        int coordinate = 0; // Start at (0, 0)
        stack.push(coordinate);
        visited[0][0] = 2;
        while (!stack.empty()) {
            MyList<Integer> list = labyrinth.getListOfUnvisitedNeighbors(coordinate, visited);
            while (!list.empty()) {
                int oldCoordinate = coordinate;
                coordinate = list.get(random.nextInt(list.size()));
                stack.push(coordinate);
                visited[coordinate / labyrinth.width][coordinate % labyrinth.width] = 2;
                labyrinth.addPassage(oldCoordinate, coordinate);
                list = labyrinth.getListOfUnvisitedNeighbors(coordinate, visited);
            }
            coordinate = stack.pop();
        }
    }

}
