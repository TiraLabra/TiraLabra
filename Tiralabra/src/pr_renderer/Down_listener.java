
package pr_renderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Henri Korpela
 * Listens Down button. If button is pressed
 * registers camera movement on tile down to render manager.
 */
public class Down_listener extends Button_listener implements ActionListener{
    /**
     * Creates Down listener.
     * @param manager Render manager which listener registers
     * down camera movement.
     */
    public Down_listener(Render_manager manager)
    {
        super(manager);
    }
    /**
     * Registers down movement when down button is pressed.
     * @param e Button pressed event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.manager.register_movement(0,-1);
    }
    
}
