
package pr_renderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Henri Korpela
 * Listens Up button. If button is pressed
 * registers camera movement on tile up to render manager.
 */
public class Up_listener extends Button_listener implements ActionListener{
    /**
     * Creates Up listener.
     * @param manager Render manager which listener registers
     * up camera movement.
     */
    public Up_listener(Render_manager manager)
    {
        super(manager);
    }
    /**
     * Registers up movement when up button is pressed.
     * @param e Button pressed event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.manager.register_movement(0,1);
    }
}
