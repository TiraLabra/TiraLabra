package cs.helsinki.fi;

import cs.helsinki.fi.desu.DES;
import cs.helsinki.fi.desu.Decryption;
import cs.helsinki.fi.desu.Encryption;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    private File input;
    private File output;
    private File key;

    public Operator(String args[]) {
        this.des = new DES();
        this.enc = new Encryption();
        this.dec = new Decryption();
        this.key = new File (args[3]);
        this.output = new File(args[4]);
        this.input = new File(args[5]);
    }
    
    /**
     * Initiates encryption procedure by reading data from file, sending it to
     * encryption algorithm and writing the read and encrypted data to output
     * file.
     * 
     * @param mode encryption mode, either DES or triple DES
     */
    public void encrypt(String mode) {
        byte[] encrypted;
        
        if (mode.equals("des"))
            encrypted = enc.encryptSingleDES(this.readFile(input), this.readFile(key));
        else if (mode.equals("3des"))
            encrypted = enc.encryptTripleDES(this.readFile(input), null);
        else {
            System.out.println("Error: Bad arguments\n  Unrecognized mode.");
            return;
        }
        this.writeFile(encrypted, output);
    }
    
    /**
     * Initiates decryption procedure by reading data from file, sending it to
     * decryption algorithm and writing the read and decrypted data to output
     * file.
     * 
     * @param mode decryption mode, either DES or triple DES
     */
    public void decrypt(String mode) {
        byte[] decrypted;
        
        if (mode.equals("des"))
            decrypted = dec.decryptSingleDES(this.readFile(input), this.readFile(key));
        else if (mode.equals("3des"))
            decrypted = enc.encryptTripleDES(this.readFile(input), null);
        else {
            System.out.println("Error: Bad arguments\n  Unrecognized mode.");
            return;
        }
        this.writeFile(decrypted, output);
    }

    /**
     * Reads data from given file and returns it in byte form.
     * 
     * @param  file file to read data from
     * @return      contents of file in byte form
     */
    public byte[] readFile(File file) {
        byte[] data = null;
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(input);
            data = scanner.next().getBytes();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: No such file");
            return data;
        } finally {
            scanner.close();
        }
        return data;
    }
    
    /**
     * Reads keyfile for triple DES algorithm.
     * 
     * @param  file file for keys
     * @return      three keys read from file
     */
    public byte[][] readTripleKey(File file) {
        byte[][] tripleKey = new byte[3][56];
        try {
            Scanner scanner = new Scanner(file);
            for (int i = 0; i < 3; i++) {
                tripleKey[i] = scanner.nextLine().getBytes();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: No such file");
            return null;
        }
        return tripleKey;
    }
    
    /**
     * Writes data to output file after handling.
     * 
     * @param data handled data to write
     * @param file file to write data to
     */
    public void writeFile(byte[] data, File file) {
        FileWriter fw = null;
        try {
            if (!file.exists())
                file.createNewFile();
            fw = new FileWriter(file);
            fw.write(new String(data, "UTF-8"));
        } catch (IOException ioe) {
            System.out.println("ERROR: ");
            ioe.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {}
        }
    }
}
