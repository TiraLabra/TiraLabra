package gui;

import java.awt.Graphics;
import javax.swing.JPanel;
import main.Labyrinth;

/**
 * Graafisen käyttöliittymän piirtoalusta.
 *
 * @author Juri Kuronen
 */
class Canvas extends JPanel {

    Labyrinth labyrinth;
    int cellSize;

    public Canvas(Labyrinth l, int cs) {
        labyrinth = l;
        cellSize = cs;
    }

    void paintCells(Graphics g) {
        for (int i = 0; i < labyrinth.height; i++) {
            for (int j = 0; j < labyrinth.width; j++) {
                if (i == 0 && j == 0) {
                    g.drawLine(cellSize * (j + 1), cellSize * (i + 1),
                            cellSize * (j + 1), cellSize * (i + 2));
                    continue;
                }
                if ((labyrinth.labyrinth[i][j] & 8) == 0) {
                    g.drawLine(cellSize * (j + 1), cellSize * (i + 1),
                            cellSize * (j + 1), cellSize * (i + 2));
                }
                if ((labyrinth.labyrinth[i][j] & 1) == 0) {
                    g.drawLine(cellSize * (j + 1), cellSize * (i + 1),
                            cellSize * (j + 2), cellSize * (i + 1));
                }

            }
        }
        g.drawLine(cellSize, cellSize * (labyrinth.height + 1),
                cellSize * (labyrinth.width + 1), cellSize * (labyrinth.height + 1));
        g.drawLine(cellSize * (labyrinth.width + 1), cellSize,
                cellSize * (labyrinth.width + 1), cellSize * labyrinth.height);
    }

    /**
     *
     * @param g Grafiikka.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintCells(g);
    }

    void update() {
        repaint();
    }

}
