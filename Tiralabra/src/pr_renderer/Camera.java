
package pr_renderer;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import pr_ai.Actor;
import pr_map.Map;
import pr_map.Tile;

/**
 *
 * @author Henri Korpela
 * Camera paints picture of the map on window
 * it's on. It draws 10x10 squares part of the map.
 * Camera has methods for moving camera around and
 * updating which actor it should focus on.
 */
public class Camera extends JPanel{
    /**
     * Map which camera draws.
     */
    private Map map;
    /**
     * Cameras x coordinate.
     */
    private int camera_x;
    /**
     * Cameras y coordinate.
     */
    private int camera_y;
    /**
     * Actor which camera focuses on.
     */
    private Actor center_actor;
    /**
     * Amount of tiles in camera window vertical.
     */
    private final int TILES_IN_CAMERA_VERTICAL = 10;
    /**
     * Amount of tiles in camera window horizontal.
     */
    private final int TILES_IN_CAMERA_HORIZONTAL = 10;
    /**
     * Height of tile in pixels.
     */
    private final int TILE_HEIGHT = 80;
    /**
     * Width of tile in pixels.
     */
    private final int TILE_WIDHT = 80;
    /**
     * Creates camera.
     * @param map Map that camera draws.
     * @param center_actor Actor that camera focuses on.
     * @param start_x Cameras initial x coordinate.
     * @param start_y Cameras initial y coordinate.
     */
    public Camera(Map map,Actor center_actor,int start_x,int start_y)
    {
        this.map = map;
        this.camera_x = start_x;
        this.camera_y = start_y;
        this.center_actor = center_actor;
    }
    /**
     * Updates cameras position.
     * @param dx Movement x. 
     * @param dy Movement y. 
     */
    public void update_position(int dx,int dy)
    {
        this.camera_x = this.camera_x + dx;
        this.camera_y = this.camera_y + dy;
        this.center_actor = null;
    }
    /**
     * Sets new actor that camera focuses on.
     * @param center_actor Actor that camera focuses on.
     */
    public void set_center_actor(Actor center_actor)
    {
        this.center_actor = center_actor;
    }
    /**
     * Paints picture on the window.
     * @param g Graphics used for drawing.
     */
    @Override
    public void paint(Graphics g)
    {
        if(this.center_actor != null)
        {
            this.camera_x = this.center_actor.get_position().x;
            this.camera_y = this.center_actor.get_position().y;
        }
        int render_start_x = this.camera_x - 5;
        int render_start_y = this.camera_y - 5;
        for(int i = 0;i < this.TILES_IN_CAMERA_HORIZONTAL;i ++)
        {
            for(int j = 0;j < this.TILES_IN_CAMERA_VERTICAL;j ++)
            {
                int tile_x = render_start_x + j * this.TILE_WIDHT;
                int tile_y = render_start_y + i * this.TILE_HEIGHT;
                this.render_tile(this.map.get_Tile(render_start_x,render_start_y),g,tile_x,tile_y);
            }
        }
    }
    /**
     * Draws on tile on the tile.
     * @param tile Tile to be drawn.
     * @param g Graphics used for drawing.
     * @param x Window x coordinate where tile is drawn(down left corner).
     * @param y Window y coordinate where tile is drawn(down left corner).
     */
    private void render_tile(Tile tile,Graphics g,int x,int y)
    {
        Color color;
        if(tile == null)
        {
            this.render_empty_tile(g,x,y);
            return;
        }
        else
        {
            color = tile.get_in_tile().get_color();
        }
        g.setColor(color);
        if(tile.get_actor_on_tile() == null)
        {
            this.render_square(x,y,g);
        }
        else
        {
            this.render_actor(tile,x,y,g);
        }
    }
    /**
     * Renders tile that is outside the map.
     * @param g Graphics used for drawing.
     * @param x X coordinate of picture.
     * @param y Y coordinate of picture.
     */
    private void render_empty_tile(Graphics g,int x,int y)
    {
            Color color = new Color(255,255,255);
            g.setColor(color);
            this.render_square(x,y,g);
    }
    /**
     * Renders square in given position with given graphics.
     * @param x X coordinate where square is drawn(down left corner).
     * @param y Y coordinate where square is drawn(down left corner).
     * @param g Graphics used for drawing.
     */
    private void render_square(int x,int y,Graphics g)
    {
        g.drawRect(x,y,this.TILE_WIDHT,this.TILE_HEIGHT);
    }
    /**
     * Renders tile that has actor on it.
     * @param tile Tile to be rendered.
     * @param x X coordinate of the picture.
     * @param y Y coordinate of the picture.
     * @param g Graphics used for drawing.
     */
    private void render_actor(Tile tile,int x,int y,Graphics g)
    {
        g.drawImage(tile.get_actor_on_tile().get_image(),x,y, this);
    }
}
