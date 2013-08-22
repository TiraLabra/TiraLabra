
package pr_map;

/**
 *
 * @author Henri Korpela
 * Describes errors that are related to map.
 * These errors occur mainly during loading map.
 * Each error also contains message that
 * is printed when error occurs.
 */
public enum Map_errors {
    /**
     * Error that indicates that file wasn't found.
     */
    FILE_NOT_FOUND("File not found"),
    /**
     * Map has invalid width.
     */
    INVALID_WIDHT("Invalid widht"),
    /**
     * Map has invalid height.
     */
    INVALID_HEIGHT("Invalid height"),
    /**
     * There is invalid symbol in the map.
     */
    INVALID_MAP_SYMBOL("Invalid map symbol"),
    /**
     * Prisoners starting position is invalid.
     */
    INVALID_PRISONER_POSITION("Invalid prisoner position"),
    /**
     * Police starting position is invalid.
     */
    INVALID_POLICE_POSITION("Invalid police position"),
    /**
     * There are invalid amount of polices on the map.
     */
    INVALID_POLICE_AMMOUNT("Invalid ammount of police officers"),
    /**
     * Map data width is different than the given width
     * in information header.
     */
    MAP_DESCRIPTION_WIDHT_ERROR("Map widht error"),
    /**
     * Map data height is different than the given height
     * in information header.
     */
    MAP_DESCRIPTION_HEIGHT_ERROR("Map height error"),
    /**
     * Map wasn't created properly.
     */
    MAP_CREATION_ERROR("Map wasn't created"),
    /**
     * Get away cars position is invalid.
     */
    INVALID_CAR_POSITION("Invalid car position");
    /**
     * Message that is printed when error occurs.
     */
    private final String message;
    /**
     * Creates error with given error message.
     * @param message Errors message.
     */
    private Map_errors(String message)
    {
        this.message = message;
    }
    /**
     * Return message of the error.
     * @return Message of the error.
     */
    public String get_message()
    {
        return this.message;
    }
}
