package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import labyrinthsolver.LabyrinthSolver;
import main.Labyrinth;

/**
 * Graafinen käyttöliittymä.
 *
 * @author Juri Kuronen
 */
public class Gui extends MouseAdapter implements Runnable {

    /**
     * Frame.
     */
    JFrame frame;
    /**
     * Labyrintti, jolle gui luodaan.
     */
    Labyrinth labyrinth;
    /**
     * Piirtoalusta.
     */
    Canvas canvas;

    /**
     * Alustaa labyrintillä ja labyrintinratkaisijalla.
     *
     * @param l Labyrintti, jolle gui luodaan.
     */
    public Gui(Labyrinth l) {
        labyrinth = l;
    }

    /**
     * Alustaa ja käynnistää guin.
     */
    @Override
    public void run() {
        frame = new JFrame("Labyrinth solver");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            addCanvas(frame.getContentPane());
        } catch (Exception e) {
            System.out.println("Gui can only handle up to 50x50 labyrinths!");
            Scanner sc = new Scanner(System.in);
            int width = -1;
            while (width < 0 || width > 50) {
                System.out.println("Please insert new width: ");
                width = sc.nextInt();
            }
            int height = -1;
            while (height < 0 || height > 50) {
                System.out.println("Please insert new height: ");
                height = sc.nextInt();
            }
            labyrinth.updateLabyrinth(width, width);
            run();
            return;
        }
        addMenuBar();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Lisää piirtoalustan frameen.
     *
     * @param container Container, mihin piirtoalusta lisätään.
     * @throws Exception Heittää poikkeuksen, jos labyrintin koko on liian iso!
     */
    void addCanvas(Container container) throws Exception {
        int cellSize = 500 / labyrinth.height;
        canvas = new Canvas(labyrinth, cellSize);
        if (labyrinth.height > 50 || labyrinth.width > 50) {
            throw new Exception("Gui can only handle up to 50x50 labyrinths!");
        }
        canvas.setPreferredSize(new Dimension((2 + labyrinth.width) * cellSize, (2 + labyrinth.height) * cellSize));
        container.add(canvas);
    }

    void addMenuBar() {
        JMenuBar menubar = new JMenuBar();

        /*
         Menu labyrintin editoimiseen.
         */
        JMenu lMenu = new JMenu("Labyrinth");
        JMenuItem lSetSize = new JMenuItem("Set size");
        lMenu.add(lSetSize);

        /*
         * Menu labyrintin generoijan valitsemiseen.
         */
        JMenu lgMenu = new JMenu("Generator");
        JMenuItem ka = new JMenuItem("Kruskal's Algorithm");
        JMenuItem pa = new JMenuItem("Prim's Algorithm");
        JMenuItem rb = new JMenuItem("Recursive Backtracker");
        lgMenu.add(ka);
        lgMenu.add(pa);
        lgMenu.add(rb);

        /*
         Menu labyrintin ratkaisijan valitsemiseen.
         */
        JMenu lsMenu = new JMenu("Solver");
        JMenuItem dfs = new JMenuItem("DFS");
        JMenuItem bfs = new JMenuItem("BFS");
        JMenuItem wf = new JMenuItem("Wall follower");
        lsMenu.add(dfs);
        lsMenu.add(bfs);
        lsMenu.add(wf);

        /*
         * Add menu items.
         */
        menubar.add(lMenu);
        menubar.add(lgMenu);
        menubar.add(lsMenu);

        /*
         Add menubar to frame.
         */
        frame.setJMenuBar(menubar);
    }

    /**
     * Myöhemmin lisättävä hiirenkuuntelija...
     *
     * @param e Hiirieventti.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

}
