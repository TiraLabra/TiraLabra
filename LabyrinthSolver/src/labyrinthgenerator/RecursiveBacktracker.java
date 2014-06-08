package labyrinthgenerator;

import main.MyList;
import main.MyStack;

/**
 * Satunnaistettu rekursiivinen peruuttava haku labyrintin generoimiseen.
 *
 * @author Juri Kuronen
 */
public class RecursiveBacktracker extends LabyrinthGenerator {

    /**
     * Yliluokka alustaa random-olion.
     *
     * @see labyrinthgenerator.LabyrinthGenerator#LabyrinthGenerator()
     */
    public RecursiveBacktracker() {
        super();
        name = "Recursive Backtracker";
    }

    /**
     * <u>Alustus:</u><br>
     * Alustetaan pino ja visited-array. 
     * <br><br>
     * <u>Toiminta:</u><br>
     * Lähtee liikkeelle lähtösolusta. Siirtyy satunnaiseen vierailemattomaan
     * soluun, ja asettaa liikkuessa edellisen solun pinon päällimmäiseksi.
     * Toistaa tätä niin kauan, kunnes tullaan soluun, jolla ei enää ole
     * vierailemattomia naapureita. Tällöin algoritmi peruuttaa palaamalla pinon
     * päällimmäiseen soluun niin kauan, kunnes löytyy solu, jolla on
     * vierailemattomia naapureita. Pinon tyhjetessä labyrintti on generoitu.
     * <br><br>
     * Labyrintin toiminnasta löytyy tietoa myös määrittelydokumentista.
     *
     * @throws java.lang.Exception Labyrintti-luokka heittää poikkeuksen, jos
     * algoritmi yrittää käsitellä labyrintin ulkopuolista koordinaattia.
     * @see main.Labyrinth#getListOfNeighbors(int, int[][], int) 
     * @see main.Labyrinth#addPassage(int, int)
     * @see main.MyList
     * @see main.MyStack
     */
    @Override
    public void generateLabyrinth() throws Exception {
        MyStack<Integer> stack = new MyStack();
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        int[][] visited = new int[height][width];
        int coordinate = 0;
        stack.push(coordinate);
        visited[0][0] = 2;
        while (!stack.empty()) {
            MyList<Integer> list = labyrinth.getListOfNeighbors(coordinate, visited, 0);
            while (!list.empty()) {
                int oldCoordinate = coordinate;
                coordinate = list.get(random.nextInt(list.size()));
                stack.push(coordinate);
                visited[coordinate / width][coordinate % width] = 2;
                labyrinth.addPassage(oldCoordinate, coordinate);
                list = labyrinth.getListOfNeighbors(coordinate, visited, 0);
            }
            coordinate = stack.pop();
        }
    }

}
