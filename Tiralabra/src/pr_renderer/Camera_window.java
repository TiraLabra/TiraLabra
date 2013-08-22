
package pr_renderer;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import pr_ai.Actor;
import pr_map.Map;

/**
 *
 * @author Henri Korpela
 * Window which camera draws picture on.
 * Window is also responsible for camera updating.
 */
public class Camera_window {
    /**
     * Map that is drawn on the window.
     */
    private Map map;
    /**
     * Actor that camera focuses on.
     */
    private Actor center_actor;
    /**
     * Window that camera draws picture on.
     */
    private JFrame window;
    /**
     * Camera that draws picture.
     */
    private Camera camera;
    /**
     * Width of the window in pixels.
     */
    public final int WIDHT = 800;
    /**
     * Height of the window in pixels.
     */
    public final int HEIGHT = 800;
    /**
     * Creates new camera window.
     * @param map Map that is drawn on the window.
     * @param center_actor Actor that camera focuses on.
     * @param camera_start_x Cameras initial x coordinate.
     * @param camera_start_y Cameras initial y coordinate.
     */
    public Camera_window(Map map,Actor center_actor,int camera_start_x,int camera_start_y)
    {
        this.map = map;
        this.center_actor = center_actor;
        this.create_window(camera_start_x,camera_start_y);
    }
    /**
     * Moves camera.
     * @param dx Movement x.
     * @param dy Movement y.
     */
    public void move_camera(int dx,int dy)
    {
        this.camera.update_position(dx,dy);
        this.center_actor = null;
    }
    /**
     * Sets actor that camera focuses on.
     * @param center_actor Actor that camera focuses on.
     */
    public void set_center_actor(Actor center_actor)
    {
        this.center_actor = center_actor;
        this.camera.set_center_actor(center_actor);
    }
    /**
     * Repaints window. Uses cameras paint method.
     */
    public void render()
    {
        this.window.repaint();
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
     * Creates window and camera.
     * @param camera_start_x Cameras initial x coordinate.
     * @param camera_start_y Cameras initial y coordinate.
     */
    private void create_window(int camera_start_x,int camera_start_y)
    {
        this.window = new JFrame(map.get_name());
        this.window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setSize(WIDHT,HEIGHT);
        this.window.setLocation(0,0);
        this.window.setIgnoreRepaint(true);
        
        this.camera = new Camera(this.map,this.center_actor,camera_start_x,camera_start_y);
        this.camera.setIgnoreRepaint(true);
        this.camera.setSize(WIDHT,HEIGHT);
        
        this.window.add(this.camera);
        
        this.window.pack();
        this.window.setVisible(true);
    }
}
