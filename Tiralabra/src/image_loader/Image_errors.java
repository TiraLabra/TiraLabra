/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Image_loader;

/**
 *
 * @author henrikorpela
 */
public enum Image_errors {
    POLICE_IMAGE_FILE_NOT_FOUND("Police image file not found"),
    PRISONER_IMAGE_FILE_NOT_FOUND("Prisoner image file not found"),
    CAR_IMAGE_FILE_NOT_FOUND("Car image file not found");
    
    private final String message;
    
    private Image_errors(String message)
    {
        this.message = message;
    }
    
    public String get_message()
    {
        return this.message;
    }
}
