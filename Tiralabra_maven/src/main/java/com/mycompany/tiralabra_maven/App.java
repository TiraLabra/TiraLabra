package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.maze.ArrayMazeDrawer;
import com.mycompany.tiralabra_maven.gui.Window;
import com.mycompany.tiralabra_maven.maze.ArrayMaze;
import java.io.IOException;

/**
 * Sovelluksen pääluokka.
 *
 */
public class App {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
        int s = ArrayMaze.START;
        int g = ArrayMaze.GOAL;
        int[][] array = new int[][]{
            new int[]{s, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            new int[]{2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 1},
            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            new int[]{2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            new int[]{2, 0, 2, 0, 1, 0, 2, 0, 2, 0, 2},
            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            new int[]{2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            new int[]{2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2},
            new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, g},
        };
                ArrayMaze maze = new ArrayMaze(array);
                ArrayMazeDrawer drawer = new ArrayMazeDrawer(maze);
                new Window(drawer).setVisible(true);
            }
        });
    }
}
