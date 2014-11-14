package com.mycompany.domain;

public class Kasi {

    private String nimi;

    public Kasi(String nimi) {
        nimi = nimi.toUpperCase();
        this.nimi = nimi;
    }

    public String getNimi() {
        return this.nimi;
    }
}
