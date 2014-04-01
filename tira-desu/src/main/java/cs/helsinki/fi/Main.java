package cs.helsinki.fi;

/**
 * Main class.
 * 
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */
public class Main {
    
    public static void main(String[] args) {
        
        if (args.length < 4 || args[0].isEmpty()) {
            man();
            return;
        }
        
        Operator op = new Operator();
        if (args[0].equals("-e")) {
            op.encrypt(args);
        } else if (args[0].equals("-d")) {
            op.decrypt(args);
        } else
            man();
    }
    
    public static void man() {
        System.out.println(
                "Tira-DESu usage: \n" +
                "  encrypt: '%s -e --mode keyfile input output'\n" + 
                "  decrypt: '%s -d --mode keyfile input output'\n" +
                "    where 'input' and 'output' are filenames\n" +
                "  available modes: des and 3des"
        );
    }
}
