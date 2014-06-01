
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.view.Io;
import java.util.Objects;

/**
 * Performs matrix operations on matrices entered by the user.
 * @author gabriel
 */
public class MatrixCalculator {
    
    /**
     * The io used for I/O operations.
     */
    private Io io;
    /**
     * All available commands.
     */
    private Command[] commands;

    /**
     * Constructs a matrix calculator, which uses the specified io.
     * @param io the io used for I/O operations.
     * @throws NullPointerException if the io parameter is null
     */
    public MatrixCalculator(Io io) {
        Objects.requireNonNull(io, "The io parameter must not be null.");
        this.io = io;
        commands = new Command[7];
        commands[0] = new Exit();
        commands[1] = new Addition(io);
        commands[2] = new Subtraction(io);
        commands[3] = new Multiplication(io);
        commands[4] = new Determinant(io);
        commands[5] = new Transposition(io);
        commands[commands.length-1] = new Menu(commands, io);
    }  
    
    /**
     * Launches the calculator. 
     */
    public void run(){
        io.printLine("Select a choice, then press Enter.\n");
        Command command = commands[commands.length-1];
        command.execute();
        while (true){
            int nextCommand = io.readInt("");
            if (nextCommand == 0){
                return;
            }
            else if (isValidCommand(nextCommand)){
                command = commands[nextCommand];
            }
            else{
                command = commands[commands.length-1];
            }
            command.execute();
        }
    }
    
    /**
     * Checks if the specified number is a valid representation of a command.
     * @param intCommand the number being checked
     * @return true if the specified number is a valid index in the commands table; false otherwise.
     */
    private boolean isValidCommand(int intCommand) {
        return (intCommand >=0 && intCommand < commands.length);
    }
    
}
