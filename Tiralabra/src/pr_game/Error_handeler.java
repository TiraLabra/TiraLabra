
package pr_game;

import Image_loader.Image_errors;
import pr_data_structures.stack.Stack;
import pr_map.Map_errors;

/**
 *
 * @author Henri Korpela
 * 
 * Handles errors that occur during run.
 * All java errors are handled instantly as they occur
 * and custom error flag is created.
 */
public class Error_handeler {
    /**
     * Stack that contains all errors that
     * haven't been handled.
     */
    private Stack<Map_errors> map_errors;
    private Stack<Image_errors> image_errors;
    /**
     * Creates error handler.
     */
    public Error_handeler()
    {
        this.map_errors = new Stack<Map_errors>();
        this.image_errors = new Stack<Image_errors>();
    }
    /**
     * Adds error to error stack.
     * @param error Error to be added.
     */
    public void add_error(Map_errors error)
    {
        if(error == null)
        {
            return;
        }
        this.map_errors.add(error);
    }
    
    public void add_error(Image_errors error)
    {
        if(error == null)
        {
            return;
        }
        this.image_errors.add(error);
    }
    /**
     * Handles all errors in the error stack.
     */
    public void handle_errors()
    {
        if(this.map_errors.is_empty() && this.image_errors.is_empty())
        {
            return;
        }
        System.out.print("Errors:\n");
        this.handle_map_errors();
        this.handle_image_errors();
        System.out.print("\n");
    }
    
    private void handle_image_errors()
    {
        if(this.image_errors == null)
        {
            return;
        }
        while(!this.image_errors.is_empty())
        {
            Image_errors error = this.image_errors.exstract_first();
            System.out.print(error.get_message() + "\n");
        }
    }
    
    private void handle_map_errors()
    {
        if(this.map_errors.is_empty())
        {
            return;
        }
        while(!this.map_errors.is_empty())
        {
            Map_errors error = this.map_errors.exstract_first();
            System.out.print(error.get_message() + "\n");
        }
    }
}
