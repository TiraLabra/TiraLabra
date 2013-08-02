/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr_map;

/**
 *
 * @author henrikorpela
 */
public enum Map_errors {
    FILE_NOT_FOUND("File not found"),INVALID_WIDHT("Invalid widht"),
    INVALID_HEIGHT("Invalid height"),INVALID_MAP_SYMBOL("Invalid map symbol"),
    MAP_DESCRIPTION_WIDHT_ERROR("Map widht error"),
    MAP_DESCRIPTION_HEIGHT_ERROR("Map height error"),
    MAP_CREATION_ERROR("Map wasn't created");
    
    private final String message;
    
    private Map_errors(String message)
    {
        this.message = message;
    }
    
    public String get_message()
    {
        return this.message;
    }
}
