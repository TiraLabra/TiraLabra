/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_game;

import pr_data_structures.stack.Stack;
import pr_map.Map_errors;

/**
 *
 * @author henrikorpela
 */
public class Error_handeler {
    private Stack<Map_errors> map_errors;
    
    public Error_handeler()
    {
        this.map_errors = new Stack<Map_errors>();
    }
    
    public void add_error(Map_errors error)
    {
        if(error == null)
        {
            return;
        }
        this.map_errors.add(error);
    }
    
    public void handle_errors()
    {
        if(this.map_errors.is_empty())
        {
            return;
        }
        System.out.print("Errors:\n");
        while(!this.map_errors.is_empty())
        {
            Map_errors error = this.map_errors.exstract_first();
            System.out.print(error.get_message() + "\n");
        }
        System.out.print("\n");
    }
}
