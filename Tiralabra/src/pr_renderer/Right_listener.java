
package pr_renderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Henri Korpela
 * Listens Right button. If button is pressed
 * registers camera movement on tile right to render manager.
 */
public class Right_listener extends Button_listener implements ActionListener{
    /**
     * Creates Right listener.
     * @param manager Render manager which listener registers
     * right camera movement.
     */
    public Right_listener(Render_manager manager)
    {
        super(manager);
    }
    /**
     * Registers right movement when right button is pressed.
     * @param e Button pressed event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.manager.register_movement(1,0);
    }
    
}
