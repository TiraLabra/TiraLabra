
package pr_ai;

import pr_data_structures.array_list.ArrayList;
import pr_map.Map;
import pr_map.Position;
import pr_pathfinding.Path_instructions;
import pr_pathfinding.Pathfinder;

/**
 *
 * @author Henri Korpela
 * Base class for game actors. AIs inherit this class.
 * Contains common variables and methods that AIs use.
 */
public abstract class Actor {
    /**
     * Max moves per turn that any actor can take.
     */
    protected final int MOVES_PER_TURN = 5;
    /**
     * Moves to be used during this turn.
     */
    protected int moves_left;
    /**
     * Actors position.
     */
    protected Position position;
    /**
     * Pathfinder that AI uses to navigate.
     */
    protected Pathfinder pathfinder;
    /**
     * Map that AI is on.
     */
    protected Map map;
    
    /**
     * Creates actor.
     * @param start_x Actors starting x position.
     * @param start_y Actors starting y position.
     * @param pathfinder Pathfinder that AI uses. 
     * @param map Map that AI's on
     */
    public Actor(int start_x,int start_y,Pathfinder pathfinder,Map map)
    {
        this.moves_left = this.MOVES_PER_TURN;
        this.position = new Position(start_x,start_y);
        this.pathfinder = pathfinder;
        this.map = map;
    }
    /**
     * Updates actor. This method
     * is runs every turn of the game.
     * Every AI implements its own update method.
     */
    public abstract void update();
    /**
     * Return actors position.
     * @return actors position.
     */
    public Position get_position()
    {
        return this.position;
    }
    /**
     * Calculates path from actors position
     * to goal position.
     * @param goal_x Goal positions x coordinate.
     * @param goal_y Goal positions y coordinate.
     * @param weight_map map that contains weights of each tile.
     * @return Path instruction from actors position
     * to goal position. Null if no path exists.
     */
    protected ArrayList<Path_instructions> calculate_path(int goal_x,int goal_y,int[][] weight_map)
    {
        this.pathfinder.find_path(this.position.x,this.position.y,goal_x,goal_y,weight_map);
        return this.pathfinder.get_path();
    }
    /**
     * Moves actor according to given path instruction.
     * @param instruction Instruction that defines where
     * actor is to be moved.
     * @return true if move was possible and false if wasn't.
     */
    protected boolean move(Path_instructions instruction)
    {
        try
        {
            Position target_pos = this.count_target_position(instruction);
            if(!this.check_enough_moves(target_pos.x,target_pos.y))
            {
                return false;
            }
            this.map.move(this.position.x,this.position.y,target_pos.x,target_pos.y);
            this.position = target_pos;
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    /**
     * Counts position which given instruction would lead actor to.
     * @param instruction Instruction thats target position is calculated.
     * @return Position which given instruction would lead to.
     */
    private Position count_target_position(Path_instructions instruction)
    {
        int target_x = this.position.x + instruction.get_dx();
        int target_y = this.position.y + instruction.get_dy();
        return new Position(target_x,target_y);
    }
    /**
     * Checks whether actor has enough moves to perform
     * move to target location.
     * @param target_x Target locations x coordinate.
     * @param target_y Target locations y coordinate.
     * @return true if there are enough moves left.
     * False if there aren't.
     * @throws Exception Throws exception if target location coordinates
     * are outside the map.
     */
    private boolean check_enough_moves(int target_x,int target_y) throws Exception
    {
        int move_cost = this.map.get_Tile(target_x,target_y).get_weight();
        if(move_cost > this.moves_left)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
