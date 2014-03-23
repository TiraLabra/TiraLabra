package com.mycompany.tiralabra_maven;

public class Delivery {
    private String firstday;
    private String lastday;
    private int companyId;

    Delivery(int c, String f, String l) {
        companyId = c;
        firstday = f;
        lastday = l;
    }

    @Override
    public String toString() {
        return companyId + ", " + firstday + " - " + lastday;
    }
}
