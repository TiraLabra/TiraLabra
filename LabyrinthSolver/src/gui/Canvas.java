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
     * Visited-solun väri. (Vaalean punainen)
     */
    Color visitedCellColor;
    /**
     * Reitin väri. (Punainen)
     */
    Color pathColor;
    /**
     * Jos labyrintin leveys on hyvin pieni, labyrintti piirtyy reunaan. Tämä
     * kokonaisluku auttaa keskittämään labyrintin.
     */
    int fixPosition;

    /**
     * Asettaa labyrintin ja solunkoon. Alustaa värit. Laskee tarvitseeko
     * labyrintti keskittää.
     *
     * @param l Labyrintti, jolle gui luodaan.
     * @param cs Labyrintin solun koko.
     */
    public Canvas(Labyrinth l, int cs) {
        labyrinth = l;
        cellSize = cs;
        wallColor = new Color(0, 0, 0);
        visitedCellColor = new Color(255, 235, 235);
        pathColor = new Color(255, 0, 0);
        if ((2 + l.getWidth()) * cellSize < 250) {
            fixPosition = (250 - (2 + l.getWidth()) * cellSize) / 2;
        } else {
            fixPosition = 0;
        }
    }

    /**
     * Jos labyrintin ratkaisija on ratkaissut labyrintin, piirretään löydetty
     * polku.
     *
     * @param g Grafiikka.
     */
    void paintPath(Graphics g) {
        g.setColor(pathColor);
        MyList<Integer> path = labyrinth.ls.getPath();
        int coordinate = path.get(0);
        g.drawLine(fixPosition + (int) (cellSize * 1.5),
                cellSize,
                fixPosition + (int) (cellSize * 1.5),
                (int) (cellSize * 1.5));
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        for (int i = 1; i < path.size(); i++) {
            int oldCoordinate = coordinate;
            coordinate = path.get(i);
            int x1 = oldCoordinate % width;
            int y1 = oldCoordinate / width;
            int x2 = coordinate % width;
            int y2 = coordinate / width;
            g.drawLine(fixPosition + (int) (cellSize * (x1 + 1.5)),
                    (int) (cellSize * (y1 + 1.5)),
                    fixPosition + (int) (cellSize * (x2 + 1.5)),
                    (int) (cellSize * (y2 + 1.5)));
        }
        g.drawLine(fixPosition + (int) (cellSize * (0.5 + width)),
                (int) (cellSize * (0.5 + height)),
                fixPosition + (int) (cellSize * (1 + width)),
                (int) (cellSize * (0.5 + height)));
    }

    /**
     * Jos labyrintin ratkaisija on ratkaissut labyrintin, väritetään vieraillut
     * solut.
     *
     * @param g Grafiikka.
     */
    void paintVisitedCells(Graphics g) {
        g.setColor(visitedCellColor);
        int[][] visited = labyrinth.ls.getVisitedCells();
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (visited[i][j] == 2) {
                    g.fillRect(fixPosition + cellSize * (j + 1),
                            cellSize * (i + 1),
                            cellSize,
                            cellSize);
                }
            }
        }
    }

    /**
     * Jos labyrintin generoija on generoinut labyrintin, piirretään labyrintin
     * muurit.
     *
     * @param g Grafiikka.
     */
    void paintWalls(Graphics g) {
        g.setColor(wallColor);
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j == 0) {
                    g.drawLine(fixPosition + cellSize * (j + 1),
                            cellSize * (i + 1),
                            fixPosition + cellSize * (j + 1),
                            cellSize * (i + 2));
                    continue;
                }
                if (!labyrinth.hasEdge(i * width + j, (byte) 8)) {
                    g.drawLine(fixPosition + cellSize * (j + 1),
                            cellSize * (i + 1),
                            fixPosition + cellSize * (j + 1),
                            cellSize * (i + 2));
                }
                if (!labyrinth.hasEdge(i * width + j, (byte) 1)) {
                    g.drawLine(fixPosition + cellSize * (j + 1),
                            cellSize * (i + 1),
                            fixPosition + cellSize * (j + 2),
                            cellSize * (i + 1));
                }

            }
        }
        g.drawLine(fixPosition + cellSize,
                cellSize * (height + 1),
                fixPosition + cellSize * (width + 1),
                cellSize * (height + 1));
        g.drawLine(fixPosition + cellSize * (width + 1),
                cellSize,
                fixPosition + cellSize * (width + 1),
                cellSize * height);
    }

    /**
     * Piirtää labyrintin.
     *
     * @param g Grafiikka.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (labyrinth.ls != null && labyrinth.ls.getPath() != null) {
            paintVisitedCells(g);
            paintPath(g);
        }
        if (labyrinth.isGenerated()) {
            paintWalls(g);
        }
    }

    /**
     * Kutsuu uudelleen repaint()-metodia, mikä uudelleenpiirtää labyrintin.
     *
     * @see paintComponent(Graphics g)
     */
    void update() {
        repaint();
    }

}
