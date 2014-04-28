package cs.helsinki.fi;

/**
 * Main class.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */
public class Main {
    
    public static void main(String[] args) {
        
        if (args.length < 4) {
            man();
            return;
        }
        
        Operator op = new Operator(args);
        if (args[0].equals("-e"))
            op.encrypt(args[2]);
        else if (args[0].equals("-d"))
            op.decrypt(args[2]);
        else
            man();
    }
    
    /**
     * Short user help to print in the case command parameters are wrong.
     */
    public static void man() {
        System.out.println(
                "Tira-DESu usage: \n" +
                "  encrypt: '%s -e mode keyfile input output'\n" + 
                "  decrypt: '%s -d mode keyfile input output'\n" +
                "    where 'keyfile', 'input' and 'output' are filenames\n" +
                "  available modes: des and 3des"
        );
    }
}
