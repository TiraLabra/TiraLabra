/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.util.ArrayList;

/**
 * Tämä luokka on hiekkalaatikkokoodia eli ei liity toimivaan koodiin suoraan
 * @author ilkkaporna
 */
public class Kombinaatiot {
    private ArrayList jonot;
    private int pituus = 4;
    
    public void kayLapi(ArrayList jono, int kohta) {
        if (kohta == pituus) {
            jonot.add(jono);
        } 
        else {
            ArrayList jono_uusi = jono;
            jono_uusi.add(kohta);
            kohta++;
            kayLapi(jono_uusi, kohta);
            kayLapi(jono, kohta);
        }
    }
    
}
