package cs.helsinki.fi;

import cs.helsinki.fi.desu.DES;
import cs.helsinki.fi.desu.Decryption;
import cs.helsinki.fi.desu.Encryption;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Container and logic class for the program. Handles everything from chopping
 * data into blocks to passing it to encryption and decryption.
 *
 * @author Juha Lindqvist <juha.lindqvist@cs.helsinki.fi>
 */
public class Operator {

    private DES des;
    private Encryption enc;
    private Decryption dec;

    public Operator() {
        this.des = new DES();
        this.enc = new Encryption();
        this.dec = new Decryption();
    }
    
    public void encrypt(String[] args) {
        
        if (args[1].equals("des"))
            enc.encryptSingleDES(this.readFile(args[4]), this.readFile(args[3]));
        else {
            byte[][] keys;
            enc.encryptTripleDES(this.readFile(args[4]), null);
        }
    }
    
    public void decrypt(String[] args) {
        Operator op = new Operator();
        
        if (args[1].equals("des"))
            dec.decryptSingleDES(this.readFile(args[4]), this.readFile(args[3]));
        else
            enc.encryptTripleDES(this.readFile(args[4]), null);
    }

    public byte[] encrypt64Block() {
        return null;
    }

    /**
     * Reads data from given file and returns it in byte form.
     * 
     * @param  filename file to read data from
     * @return          contents of file in byte form
     */
    public byte[] readFile(String filename) {
        File file = new File(filename);
        byte[] output = null;
        
        try {
            Scanner scanner = new Scanner(file);
            output = scanner.next().getBytes();
        } catch (FileNotFoundException ioe) {
            System.out.println("Error: ");
            ioe.printStackTrace();
        }
        return output;
    }
}