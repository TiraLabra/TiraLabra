package gui;

import java.awt.Container;
import main.Labyrinth;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Juri Kuronen
 */
public class GuiTest {

    Labyrinth l;
    Gui gui;

    @Before
    public void setUp() {
        l = new Labyrinth(50, 50);
        gui = new Gui(l);
    }

    @Test
    public void canvasWithinBoundsAccepted() throws Exception {
        l.updateLabyrinth(gui.minSize, gui.maxSize);
        gui.addCanvas(new Container());
        l.updateLabyrinth(gui.maxSize, gui.minSize);
        gui.addCanvas(new Container());
        l.updateLabyrinth(gui.maxSize / 2 + gui.minSize, gui.minSize * 2);
        gui.addCanvas(new Container());
    }

    @Test(expected = Exception.class)
    public void tooBigCanvasSizeNotAccepetd() throws Exception {
        l.updateLabyrinth(gui.minSize, gui.maxSize + 1);
        gui.addCanvas(new Container());
    }
}
