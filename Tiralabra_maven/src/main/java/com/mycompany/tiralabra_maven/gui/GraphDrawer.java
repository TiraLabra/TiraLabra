
package com.mycompany.tiralabra_maven.gui;

import javax.swing.JPanel;

public abstract class GraphDrawer extends JPanel {
    public abstract void draw();
    public abstract void drawPath();

    public abstract void drawRandom();
}
