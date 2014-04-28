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
    private Scanner fr;
    private FileWriter fw;
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
            encrypted = enc.encryptSingleDES(this.readFile(input), this.readKey(key));
        else if (mode.equals("3des"))
            encrypted = enc.encryptTripleDES(this.readFile(input), this.readTripleKey(key));
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
            decrypted = dec.decryptTripleDES(this.readFile(input), this.readTripleKey(key));
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
        byte[] data;
        
        try {
            String contents = "";
            fr = new Scanner(file);
            while (fr.hasNextLine()) {
                contents += fr.nextLine();
            }
            data = contents.getBytes();
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: No such file");
            return null;
        }
        return data;
    }

    /**
     * Reads keyfile for single DES algorithm.
     *
     * @param  file file for key
     * @return      key read from file in a byte array
     */
    public byte[] readKey(File file) {
        byte[] key;
        try {
            fr = new Scanner(file);
            key = fr.nextLine().getBytes();
            key = this.chopKey(key);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: file not found.");
            return null;
        }
        return key;
    }
    
    /**
     * Reads keyfile for triple DES algorithm.
     * 
     * @param  file file for keys
     * @return      three keys read from file in byte arrays
     */
    public byte[][] readTripleKey(File file) {
        byte[][] tripleKey = new byte[3][56];
        try {
            fr = new Scanner(file);
            for (int i = 0; i < 3; i++) {
                tripleKey[i] = fr.nextLine().getBytes();
                tripleKey[i] = chopKey(tripleKey[i]);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: No such file");
            return null;
        }
        return tripleKey;
    }

    /**
     * Chops key bytes to length of 56 bits and returns it.
     *
     * @param key key of arbitrary size to chop to 56 bits
     * @return    correct length key
     */
    public byte[] chopKey(byte[] key) {
        byte[] newKey = new byte[7];
        System.arraycopy(key, 0, newKey, 0, 7);
        return newKey;
    }
    
    /**
     * Writes data to output file after handling.
     * 
     * @param data handled data to write
     * @param file file to write data to
     */
    public void writeFile(byte[] data, File file) {
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
            } catch (IOException ioe) {
                System.out.println("ERROR: ");
                ioe.printStackTrace();
            }
        }
    }
}
