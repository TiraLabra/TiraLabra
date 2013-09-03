package Encoding;

/**
 * Enumerators for the status of operations. 
 * NULL: operations have not started.
 * BUILDING: encoder is analyzing the data and building a hashset of keys. 
 * EXTRACTING: decoder is extracting data from the encoded data.
 * ENCODING: encoder is analyzing and encoding the data with the keys. 
 * DECODING: decoder is decoding the encoded data.
 * DATAERROR: if the data is random enough there may be no keys to encode, this will be its status.
 * INTERRUPTED: the user may interrupt all operations eg. if they see that it takes too long.
 */
public enum StatusEnum {

    NULL,
    BUILDING,
    EXTRACTING,
    ENCODING,
    DECODING,
    DONE,
    DATAERROR,
    INTERRUPTED
}