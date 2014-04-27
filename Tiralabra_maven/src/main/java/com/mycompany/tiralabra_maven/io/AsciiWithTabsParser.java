package com.mycompany.tiralabra_maven.io;

public class AsciiWithTabsParser extends Parser {
    private String[] strings;

    @Override
    protected Object objectAt(int i) {
        return strings[i];
    }

    @Override
    protected int parseIntAt(int i) {
        return Integer.parseInt(strings[i]);
    }

    @Override
    protected void init(String row) {
        strings = row.trim().split("\\s");
        length = strings.length;
    }
}
