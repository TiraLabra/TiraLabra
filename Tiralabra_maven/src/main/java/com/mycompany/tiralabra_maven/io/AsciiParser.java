package com.mycompany.tiralabra_maven.io;

/**
 *
 * @author yessergire
 */
public class AsciiParser extends Parser {
    private char[] chars;

    /**
     *
     * @param i
     * @return
     */
    @Override
    protected int parseIntAt(int i) {
        return Character.getNumericValue(chars[i]);
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    protected Object objectAt(int i) {
        return chars[i];
    }

    /**
     *
     * @param row
     */
    @Override
    protected void init(String row) {
        chars = row.toCharArray();
        length = chars.length;
    }
}
