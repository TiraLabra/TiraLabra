
package pr_ai;

import java.util.Random;
import pr_data_structures.array_list.ArrayList;
import pr_map.Map;
import pr_map.Position;
import pr_pathfinding.Path_instructions;
import pr_pathfinding.Pathfinder;

/**
 *
 * @author Henri Korpela
 * 
 * Class that describes police AI.
 */
public class Police extends Actor{
    /**
     * Enum that defines police states.
     */
    private enum State
    {
        /**
         * State that expresses that police is
         * patrolling and isn't aware of
         * prisoners whereabouts.
         */
        PATROL,
        /**
         * State that expresses that police is
         * pursuing prisoner and is aware prisoners
         * whereabouts.
         */
        PURSUE;
    }
    /**
     * State that police is currently in.
     */
    private State state;
    /**
     * Creates police.
     * @param start_x Polices start x position.
     * @param start_y Polices start y position.
     * @param pathfinder Pathfinder that police uses.
     * @param map Map that police is on.
     */
    public Police(int start_x,int start_y,Pathfinder pathfinder,Map map)
    {
        super(start_x,start_y,pathfinder,map);
        this.state = State.PATROL;
    }
    /**
     * Updates police. If police is on patrol state
     * counts path to random location on the map.
     * If police is in pursue mode counts path to prisoner
     * position. After Path has been calculated moves police
     * according to calculated instructions. Also updates
     * polices state.
     */
    @Override
    public void update() 
    {
        ArrayList<Path_instructions> path;
        Position prisoner = super.map.prisoner_position();
        this.change_state(prisoner);
        if(this.state == State.PATROL)
        {
            Position target = this.random_position();
            path = super.calculate_path(target.x,target.y,this.map.get_weight_map());
        }
        else
        {
            path = super.calculate_path(prisoner.x,prisoner.y,super.map.get_weight_map());
        }
        if(path == null)
        {
            return;
        }
        for(int i = 0;i < path.size();i ++)
        {
            boolean success = super.move(path.getIndex(i));
            if(!success)
            {
                break;
            }
        }
    }
    /**
     * Changes polices state to pursue if prisoner is
     * closer than four squares away. And if prisoner
     * beyond polices perception changes state to patrol.
     * @param prisoner Prisoners position.
     */
    private void change_state(Position prisoner)
    {
        if(this.prisoner_in_sight(prisoner))
        {
            this.state = State.PURSUE;
        }
        else
        {
            this.state = State.PATROL;
        }
    }
    /**
     * Checks whether police detects
     * prisoner or not. Police detects prisoner
     * if prisoner is within tree squares from
     * the police.
     * @param prisoner Prisoners position.
     * @return True if police detects prisoner
     * and false if doesn't.
     */
    private boolean prisoner_in_sight(Position prisoner)
    {
        if(super.position.x - 3 >= prisoner.x &&
                super.position.x + 3 <= prisoner.x &&
                super.position.y - 3 >= prisoner.y &&
                super.position.y + 3 <= prisoner.y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Returns random position that is on the map.
     * @return Random position on the map.
     */
    private Position random_position()
    {
        Random random = new Random();
        int x = random.nextInt(super.map.get_widht());
        int y = random.nextInt(super.map.get_height());
        return new Position(x,y);
    }
}
