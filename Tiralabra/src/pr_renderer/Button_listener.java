
package pr_renderer;

/**
 *
 * @author Henri Korpela
 * Button listener is class that all button listeners inherit.
 * It includes render manager which buttons
 * use to register camera movement.
 */
public abstract class Button_listener {
    /**
     * Render manager that is used to register
     * camera movement.
     */
    protected Render_manager manager;
    /**
     * Creates button listener.
     * @param manager Render manager for button listener.
     */
    public Button_listener(Render_manager manager)
    {
        this.manager = manager;
    }
}
