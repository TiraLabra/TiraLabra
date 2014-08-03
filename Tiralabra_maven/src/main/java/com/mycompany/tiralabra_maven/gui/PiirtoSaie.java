/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Simulaatio;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mikko
 */
public class PiirtoSaie extends Thread{
    private Piirtoalusta piirtoalusta;
    private Simulaatio simulaatio;
    public PiirtoSaie(Piirtoalusta piirtoalusta, Simulaatio simulaatio) {
        this.piirtoalusta = piirtoalusta;
        this.simulaatio = simulaatio;
    }
    
    @Override
    public void run() {
        simulaatio.start();
        while (!simulaatio.onkoValmis()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
            this.piirtoalusta.paivita();
        }
    }
}
