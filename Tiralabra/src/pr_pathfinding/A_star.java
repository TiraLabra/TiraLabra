/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_pathfinding;

import pr_map.Map;
import pr_data_structures.array_list.ArrayList;

/**
 *
 * @author henrikorpela
 */
public class A_star implements Pathfinder{
    ArrayList<Path_instructions> path;
    
    public A_star()
    {
        this.path = new ArrayList<Path_instructions>();
    }
    
    @Override
    public Pathfinding_errors find_path(int start_x, int start_y, int goal_x, int goal_y,int[][] weight_map) {
        return null;
    }

    @Override
    public ArrayList<Path_instructions> get_path() {
        return this.path;
    }

    @Override
    public boolean path_calculated() {
        if(this.path != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
