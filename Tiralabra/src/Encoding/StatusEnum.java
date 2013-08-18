package Encoding;

/**
 * Enumerators for the status of operations. NULL: operations have not started.
 * BUILDING: analyzing the data and building a hashset of keys. ENCODING:
 * analyzing and encoding the data with the keys. DATAERROR: if the data is
 * random enough there may be no keys to encode, this will be its status.
 * INTERRUPTED: the user may interrupt all operations eg. if they see that it
 * takes too long.
 */
public enum StatusEnum {

    NULL,
    BUILDING,
    ENCODING,
    DONE,
    DATAERROR,
    INTERRUPTED
}