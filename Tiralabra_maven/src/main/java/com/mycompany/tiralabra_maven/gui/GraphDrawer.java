
package com.mycompany.tiralabra_maven.gui;

import java.io.File;
import javax.swing.JPanel;

/**
 * A JPanel that can draw a graph.
 * @author Yessergire Mohamed
 */
public abstract class GraphDrawer extends JPanel {

    /**
     * Redraws its graph.
     */
    public abstract void draw();

    /**
     * Finds a path in a graph and then draws it.
     */
    public abstract void drawPath();

    /**
     * Draws a randomly generated graph.
     */
    public abstract void drawRandom();

    /**
     * Reads and draws a graph stored in a file.
     * @param file
     */
    public abstract void readFile(File file);
}
