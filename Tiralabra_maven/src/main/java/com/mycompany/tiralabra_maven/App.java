package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.ui.Ui;

/**
 * Main class
 *
 * @author Joel Nummelin
 */
public class App {

    /**
     * Starts the Rock, paper, scissors app
     * @param args 
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.run();
    }
}
