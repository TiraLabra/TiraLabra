package gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import main.Labyrinth;
import main.MyList;

/**
 * Graafisen käyttöliittymän piirtoalusta.
 *
 * @author Juri Kuronen
 */
class Canvas extends JPanel {

    /**
     * Labyrintti, jolle gui luodaan.
     */
    Labyrinth labyrinth;
    /**
     * Labyrintin solun koko.
     */
    int cellSize;
    /**
     * Muurin väri. (Musta)
     */
    Color wallColor;
    /**
     * Visited-solun väri. (~Pinkki)
     */
    Color visitedCellColor;
    /**
     * Reitin väri. (Punainen)
     */
    Color pathColor;

    /**
     * Alustaa labyrintillä ja labyrintinratkaisijalla ja alustaa
     * koko-/väritiedot.
     *
     * @param l Labyrintti, jolle gui luodaan.
     * @param ls Labyrintinratkaisija, joka ratkaisi labyrintin.
     * @param cs Labyrintin solun koko.
     */
    public Canvas(Labyrinth l, int cs) {
        labyrinth = l;
        cellSize = cs;
        wallColor = new Color(0, 0, 0);
        visitedCellColor = new Color(255, 235, 235);
        pathColor = new Color(255, 0, 0);
    }

    /**
     * Piirtää polun.
     *
     * @param g Grafiikka.
     */
    void paintPath(Graphics g) {
        g.setColor(pathColor);
        MyList<Integer> path = labyrinth.ls.getPath();
        int coordinate = path.get(0);
        g.drawLine((int) (cellSize * 1.5), cellSize,
                (int) (cellSize * 1.5), (int) (cellSize * 1.5));
        for (int i = 1; i < path.size(); i++) {
            int oldCoordinate = coordinate;
            coordinate = path.get(i);
            int x1 = oldCoordinate % labyrinth.width;
            int y1 = oldCoordinate / labyrinth.width;
            int x2 = coordinate % labyrinth.width;
            int y2 = coordinate / labyrinth.width;
            g.drawLine((int) (cellSize * (x1 + 1.5)), (int) (cellSize * (y1 + 1.5)),
                    (int) (cellSize * (x2 + 1.5)), (int) (cellSize * (y2 + 1.5)));
        }
        g.drawLine((int) (cellSize * (0.5 + labyrinth.width)), (int) (cellSize * (0.5 + labyrinth.height)),
                (int) (cellSize * (1 + labyrinth.width)), (int) (cellSize * (0.5 + labyrinth.height)));
    }

    /**
     * Piirtää vieraillut solut.
     *
     * @param g Grafiikka.
     */
    void paintVisitedCells(Graphics g) {
        g.setColor(visitedCellColor);
        int[][] visited = labyrinth.ls.getVisitedCells();
        for (int i = 0; i < labyrinth.height; i++) {
            for (int j = 0; j < labyrinth.width; j++) {
                if (visited[i][j] == 2) {
                    g.fillRect(cellSize * (j + 1), cellSize * (i + 1),
                            cellSize, cellSize);
                }
            }
        }
    }

    /**
     * Piirtää muurit.
     *
     * @param g Grafiikka.
     */
    void paintWalls(Graphics g) {
        g.setColor(wallColor);
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
     * Piirtäjä.
     *
     * @param g Grafiikka.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (labyrinth.labyrinth[0][0] != 0) {
            paintWalls(g);
        }
        if (labyrinth.ls != null && labyrinth.ls.getPath() != null) {
            paintVisitedCells(g);
            paintPath(g);
        }
    }

    /**
     * Päivittää.
     */
    void update() {
        repaint();
    }

}
