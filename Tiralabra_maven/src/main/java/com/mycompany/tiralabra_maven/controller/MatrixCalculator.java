
package com.mycompany.tiralabra_maven.controller;

import com.mycompany.tiralabra_maven.view.Io;

/**
 *
 * @author gabriel
 */
public class MatrixCalculator {
    
    private Io io;
    private Command[] commands;

    public MatrixCalculator(Io io) {
        this.io = io;
        commands = new Command[3];
        commands[0] = new Exit();
        commands[1] = new Addition(io);
        commands[2] = new Menu(commands, io);
    }
    
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

    private boolean isValidCommand(int command) {
        return (command >=0 && command < commands.length);
    }
    
}
