package fi.jw.cs.tiralabra;

/**
 * Thrown to indicate a non-binary encoding value while decoding the Huffman code
 *
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-07
 */
public class IllegalHuffmanCodeException extends Exception {
    public IllegalHuffmanCodeException(String s) {
    }
}
