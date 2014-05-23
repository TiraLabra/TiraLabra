
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.view.Io;

/**
 * Displays a list of all the available commands.
 * @author gabriel
 */
public class Menu implements Command {
    
    private Io io;
    private Command[] commands;

    public Menu(Command[] commands, Io io) {
        this.commands = commands;
        this.io = io;
    }

    public void execute() {
        for (int i = 0; i < commands.length; i++){
            io.printLine(i + ". " + commands[i] + "\n");
        }
    }        

    @Override
    public String toString() {
        return "Display this menu";
    }
    
    
}
