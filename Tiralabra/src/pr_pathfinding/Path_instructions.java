/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_pathfinding;

/**
 *
 * @author henrikorpela
 */
public enum Path_instructions {
    NORTH(0,1),NORTH_EAST(1,1),EAST(1,0),SOUTH_EAST(1,-1),
    SOUTH(0,-1),SOUTH_WEST(-1,-1),WEST(-1,0),NORTH_WEST(-1,1);
    
    private final int dx;
    private final int dy;
    
    private Path_instructions(int dx,int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }
    
    public int get_dx()
    {
        return this.dx;
    }
    
    public int get_dy()
    {
        return this.dy;
    }
}
