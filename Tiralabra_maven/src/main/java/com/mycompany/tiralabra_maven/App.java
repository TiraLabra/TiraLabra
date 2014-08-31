package com.mycompany.tiralabra_maven;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("MATRIISILASKIN");
        run();
    }    
    
    /**
     * Program loop, may need some refactoring.
     */
    public static void run() {        
        UI ui = new UI();
        while (true) {
            int op = ui.decideOperation();
            if (op == 0) {
                break;
            }else if (op < 9) {
                ui.doOperation(op);
            }else {
                
            }
        }
    }
}
