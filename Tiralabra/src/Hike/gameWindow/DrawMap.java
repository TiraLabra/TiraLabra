/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.gameWindow;

import Hike.Algorithms.Dijkstra;
import Hike.ImageTable.ImageTable;
import java.awt.Graphics;
import java.util.Deque;
import javax.swing.JPanel;

/**
 *
 * @author petri
 */
class DrawMap extends JPanel {

    private ShowPicture pic;
    private ImageTable table;
    private Dijkstra droute;
    private Deque stack;

    public DrawMap() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        pic = new ShowPicture("../map1.png");
        g.drawImage(pic.getImage(), 50, 50, this);

        table = new ImageTable(pic);
        droute = new Dijkstra(table);

        stack = droute.getDeque();

        char path = droute.printStack();
        int x = 850;
        int y = 550;

        while (path != 'S') {
            if (path == 'D') {
                y--;
            }
            else if (path == 'U') {
                y++;
            }
            else if (path == 'L') {
                x++;
            }
            else if (path == 'R') {
                x--;
            }
            g.drawLine(x, y, x, y);
            System.out.println(y + " " + x);
            path = droute.printStack();
            System.out.print(path);
        }






    }
}
