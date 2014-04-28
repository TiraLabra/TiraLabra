package com.mycompany.tiralabra_maven.io;

/**
 *
 * @author yessergire
 */
public class AsciiWithTabsParser extends Parser {
    private String[] strings;

    /**
     *
     * @param i
     * @return
     */
    @Override
    protected Object objectAt(int i) {
        return strings[i];
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    protected int parseIntAt(int i) {
        return Integer.parseInt(strings[i]);
    }

    /**
     *
     * @param row
     */
    @Override
    protected void init(String row) {
        strings = row.trim().split("\\s");
        length = strings.length;
    }
}
