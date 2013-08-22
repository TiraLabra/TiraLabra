
package pr_renderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Henri Korpela
 * Listens Left button. If button is pressed
 * registers camera movement on tile left to render manager.
 */
public class Left_listener extends Button_listener implements ActionListener{
    /**
    * Creates left listener.
    * @param manager Render manager which listener registers
    * left camera movement.
    */
    public Left_listener(Render_manager manager)
    {
        super(manager);
    }
    /**
     * Registers left movement when left button is pressed.
     * @param e Button pressed event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.manager.register_movement(-1,0);
    }
    
}
