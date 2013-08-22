
package Image_loader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pr_game.Game;

/**
 *
 * @author henrikorpela
 */
public class Loader {
    private static String image_file_names[];
    
    public static void load_images()
    {
        Images.PRISONER_IMAGE = load_image(image_file_names[0]);
        Images.POLICE_IMAGE = load_image(image_file_names[1]);
        Images.CAR_IMAGE = load_image(image_file_names[2]);
        check_load();
    }
    
    public static void initialize_loader()
    {
        image_file_names = new String[3];
        Images.CAR_IMAGE = null;
        Images.POLICE_IMAGE = null;
        Images.PRISONER_IMAGE = null;
    }
    
    private static void check_load()
    {
        if(Images.PRISONER_IMAGE == null)
        {
            Game.error_handeler.add_error(Image_errors.PRISONER_IMAGE_FILE_NOT_FOUND);
        }
        if(Images.CAR_IMAGE == null)
        {
            Game.error_handeler.add_error(Image_errors.CAR_IMAGE_FILE_NOT_FOUND);
        }
        if(Images.POLICE_IMAGE == null)
        {
            Game.error_handeler.add_error(Image_errors.POLICE_IMAGE_FILE_NOT_FOUND);
        }
    }
    
    private static BufferedImage load_image(String file_name)
    {
        File image_file = new File(file_name);
        try
        {
            return ImageIO.read(image_file);
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
