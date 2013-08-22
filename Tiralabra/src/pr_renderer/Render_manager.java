
package pr_renderer;

import pr_ai.Actor;
import pr_map.Map;
import pr_map.Position;

/**
 *
 * @author Henri Korpela
 * Render manager is responsible for all rendering and
 * window control. All windows communicate with each other
 * via render manager. Game calls render manager which then
 * updates windows at it's disposal.
 */
public class Render_manager {
    /**
     * Map that render manager renders.
     */
    private Map map;
    /**
     * Actor that camera focuses on.
     */
    private Actor focus_Actor;
    /**
     * Control window.
     */
    private Control_window control_win;
    /**
     * Camera window.
     */
    private Camera_window camera_win;
    /**
     * Creates render manager.
     * @param focus_actor Actor which camera focuses on.
     * @param map Map which render manager renders.
     * @param cameras_initial_position Cameras initial position.
     */
    public Render_manager(Actor focus_actor,Map map,Position cameras_initial_position)
    {
        this.focus_Actor = focus_actor;
        this.map = map;
        this.control_win = new Control_window(this);
        this.camera_win = new Camera_window(this.map,this.focus_Actor,cameras_initial_position.x,cameras_initial_position.y);
    }
    /**
     * Updates all windows. Renders map.
     */
    public void update()
    {
        this.camera_win.render();
    }
    /**
     * Checks whether any windows have been closed(disposed).
     * @return True if some windows have been disposed and
     * false if all windows are still open.
     */
    public boolean windows_disposed()
    {
        if(this.control_win.is_disposed() || this.camera_win.is_disposed())
        {
            return true;
        }
        return false;
    }
    /**
     * Registers movement dx and dy to camera.
     * @param dx movement x;
     * @param dy movement y;
     */
    public void register_movement(int dx,int dy)
    {
        this.camera_win.move_camera(dx,dy);
    }
}
