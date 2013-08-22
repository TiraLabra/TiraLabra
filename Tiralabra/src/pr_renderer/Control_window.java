
package pr_renderer;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Henri Korpela
 * Control window is used to control
 * Camera. It contains buttons that
 * can be used to move camera around.
 */
public class Control_window {
    /**
     * Window which contains buttons.
     */
    private JFrame window;
    /**
     * Creates control window.
     * @param manager Control windows manager.
     */
    public Control_window(Render_manager manager)
    {
        this.window = new JFrame("Control");
        this.window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.window.setSize(new Dimension(400,100));
        this.window.setResizable(false);
        this.add_buttons(this.window.getContentPane(),manager);
        this.window.pack();
        this.window.setVisible(true);
    }
    /**
     * Tells whether window has been disposed or not.
     * @return True if window has been disposed
     * and false if hasn't.
     */
    public boolean is_disposed()
    {
        if(this.window.isDisplayable())
        {
            return true;
        }
        return false;
    }
    /**
     * Adds buttons and layout to window.
     * @param container Windows container.
     * @param manager Windows manager.
     */
    private void add_buttons(Container container,Render_manager manager)
    {
        JButton up = new JButton("UP");
        JButton down = new JButton("DOWN");
        JButton left = new JButton("LEFT");
        JButton right = new JButton("RIGHT");
        
        GridLayout layout = new GridLayout(1,4);
        
        container.setLayout(layout);
        
        up.addActionListener(new Up_listener(manager));
        down.addActionListener(new Down_listener(manager));
        left.addActionListener(new Left_listener(manager));
        right.addActionListener(new Right_listener(manager));
        
        container.add(up);
        container.add(down);
        container.add(left);
        container.add(right);
    }
}
