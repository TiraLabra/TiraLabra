package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import labyrinthgenerator.*;
import labyrinthsolver.*;
import main.Labyrinth;

/**
 * Graafinen käyttöliittymä.
 *
 * @author Juri Kuronen
 */
public class Gui implements Runnable {

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
     * Maksimikoko, jonka GUI hyväksyy.
     */
    int sizeLimit;

    /**
     * Alustaa labyrintillä ja labyrintinratkaisijalla.
     *
     * @param l Labyrintti, jolle gui luodaan.
     */
    public Gui(Labyrinth l) {
        labyrinth = l;
        sizeLimit = 100;
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
            System.out.println("Gui can only handle up to " + sizeLimit + "x" + sizeLimit + " labyrinths!");
            Scanner sc = new Scanner(System.in);
            int width = -1;
            while (width < 0 || width > sizeLimit) {
                System.out.println("Please insert new width: ");
                width = sc.nextInt();
            }
            int height = -1;
            while (height < 0 || height > sizeLimit) {
                System.out.println("Please insert new height: ");
                height = sc.nextInt();
            }
            sc.close();
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
        int cellSize;
        if (labyrinth.height > labyrinth.width) {
            cellSize = 500 / labyrinth.height;
        } else {
            cellSize = 500 / labyrinth.width;
        }
        canvas = new Canvas(labyrinth, cellSize);
        if (labyrinth.height > sizeLimit || labyrinth.width > sizeLimit) {
            throw new Exception("Gui can only handle up to 100x100 labyrinths!");
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
        JMenu lgMenu = new JMenu("Choose generator");
        lgMenu.add(createLabyrinthGenerator(new KruskalsAlgorithm(), "Kruskal's Algorithm"));
        lgMenu.add(createLabyrinthGenerator(new PrimsAlgorithm(), "Prim's Algorithm"));
        lgMenu.add(createLabyrinthGenerator(new RecursiveBacktracker(), "Recursive Backtracker"));

        /*
         Menu labyrintin ratkaisijan valitsemiseen.
         */
        JMenu lsMenu = new JMenu("Solver");
        lsMenu.add(createLabyrinthSolver(new DFS(), "DFS"));
        lsMenu.add(createLabyrinthSolver(new BFS(), "BFS"));
        lsMenu.add(createLabyrinthSolver(new WallFollower(), "Wall follower"));

        /*
         Luo päävalinta ja lisää se menubariin.
         */
        JMenu main = new JMenu("Modify labyrinth");
        main.add(lMenu);
        main.add(lgMenu);
        main.add(lsMenu);
        menubar.add(main);

        /*
         Generointi- ja ratkojanappulat.
         */
        menubar.add(createGenerateButton());
        menubar.add(createSolveButton());

        /*
         Add menubar to frame.
         */
        frame.setJMenuBar(menubar);
    }

    ActionListener changeLabyrinthGenerator(final LabyrinthGenerator lg) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labyrinth.setLabyrinthGenerator(lg);
            }
        };
    }

    JMenuItem createLabyrinthGenerator(final LabyrinthGenerator lg, final String title) {
        JMenuItem item = new JMenuItem(title);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labyrinth.setLabyrinthGenerator(lg);
            }
        });
        return item;
    }

    JMenuItem createLabyrinthSolver(final LabyrinthSolver ls, final String title) {
        JMenuItem item = new JMenuItem(title);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labyrinth.setLabyrinthSolver(ls);
            }
        });
        return item;
    }

    JButton createGenerateButton() {
        JButton gen = new JButton("Generate!");
        gen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    labyrinth.generateLabyrinth();
                } catch (Exception ex) {
                }
                canvas.repaint();
            }
        });
        return gen;
    }

    JButton createSolveButton() {
        JButton solve = new JButton("Solve!");
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labyrinth.solveLabyrinth();
                canvas.repaint();
            }
        });
        return solve;
    }

}
