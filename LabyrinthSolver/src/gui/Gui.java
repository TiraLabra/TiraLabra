package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import main.Labyrinth;

/**
 * Graafinen käyttöliittymä.
 *
 * @author Juri Kuronen
 */
public class Gui extends MouseAdapter implements Runnable {

    JFrame frame;
    Labyrinth labyrinth;
    Canvas canvas;

    public Gui(Labyrinth l) {
        labyrinth = l;
    }

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
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void addCanvas(Container container) throws Exception {
        int cellSize = 500 / labyrinth.height;
        canvas = new Canvas(labyrinth, cellSize);
        if (labyrinth.height > 50 || labyrinth.width > 50) {
            throw new Exception("Gui can only handle up to 50x50 labyrinths!");
        }
        canvas.setPreferredSize(new Dimension((2 + labyrinth.height) * cellSize, (2 + labyrinth.width) * cellSize));
        container.add(canvas);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

}
