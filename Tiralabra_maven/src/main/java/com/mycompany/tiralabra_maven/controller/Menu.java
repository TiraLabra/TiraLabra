
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.view.Io;
import java.util.Objects;

/**
 * Displays a list of all the available commands.
 * @author gabriel
 */
public class Menu implements Command {
    
    /**
     * The io used for displaying the menu.
     */
    private Io io;
    /**
     * All available commands.
     */
    private Command[] commands;

    /**
     * Constructs a menu consisting of the specified commands and using the specified io.
     * @param commands all available commands
     * @param io the io used for displaying the list of commands
     */
    public Menu(Command[] commands, Io io) {
        Objects.requireNonNull(commands, "The commands parameter must not be null.");
        Objects.requireNonNull(io, "The io parameter must not be null");
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
