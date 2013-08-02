/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_pathfinding;

import pr_map.Map;
import pr_data_structures.array_list.Array_list;

/**
 *
 * @author henrikorpela
 */
public interface Pathfinder {
    public  Pathfinding_errors find_path(int start_x,int start_y,int goal_x,int goal_y,Map map);
    public Array_list<Path_instructions> get_path();
    public boolean path_calculated();
}
