
package com.mycompany.tiralabra_maven.controller;

/**
 * Represents the action of exiting the application.
 * This class does not implement any functionality. It exists merely for convenience.
 * @author gabriel
 */
public class Exit implements Command {

    public void execute() {        
    }

    @Override
    public String toString() {
        return "Exit";
    }
    
    
}
