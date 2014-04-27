package com.mycompany.tiralabra_maven.io;

public class AsciiParser extends Parser {
    private char[] chars;

    @Override
    protected int parseIntAt(int i) {
        return Character.getNumericValue(chars[i]);
    }


    @Override
    protected Object objectAt(int i) {
        return chars[i];
    }

    @Override
    protected void init(String row) {
        chars = row.toCharArray();
        length = chars.length;
    }
}
