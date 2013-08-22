
package pr_ai;

import pr_data_structures.array_list.ArrayList;
import pr_map.Map;
import pr_map.Position;
import pr_pathfinding.Path_instructions;
import pr_pathfinding.Pathfinder;

/**
 *
 * @author Henri Korpela
 * 
 * Class that describes prisonerAI.
 */
public class Prisoner extends Actor{
    /**
     * Creates prisoner.
     * @param start_x Prisoner start x position.
     * @param start_y Prisoner start y position.
     * @param pathfinder Pathfinder that prisoner uses.
     * @param map Map that prisoner is on.
     */
    public Prisoner(int start_x,int start_y,Pathfinder pathfinder,Map map)
    {
        super(start_x,start_y,pathfinder,map);
    }
    /**
     * Updates prisoner. Calculates path to escape car.
     * Prisoner calculates path so that it avoids polices
     * as much as possible while still trying to make
     * its way to the escape car.
     */
    @Override
    public void update() {
        Position get_away_car_pos = this.map.get_away_car_position();
        
        ArrayList<Path_instructions> instructions = super.calculate_path(get_away_car_pos.x,get_away_car_pos.x,this.count_weight_map());
        if(instructions == null)
        {
            return;
        }
        for(int i = 0;i < instructions.size();i ++)
        {
            if(!super.move(instructions.getIndex(i)))
            {
                break;
            }
        }
    }
    /**
     * Counts weight map that is based on the maps
     * weight map but also adds weight to squares
     * based on polices positions.
     * @return Weight map where police positions
     * have been considered.
     */
    private int[][] count_weight_map()
    {
        int[][] weight_map = this.map.copy_weight_map();
        ArrayList<Position> polices = this.map.get_polices();
        return weight_map;
    }
}
