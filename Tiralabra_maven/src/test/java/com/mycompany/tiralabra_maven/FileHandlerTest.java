package com.mycompany.tiralabra_maven;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mycompany.tiralabra_maven.data_structures.Node;
import com.mycompany.tiralabra_maven.data_structures.Stack;
import com.mycompany.tiralabra_maven.player.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author Joel Nummelin
 */
public class FileHandlerTest extends TestCase {
    FileHandler fileHandler;

    public FileHandlerTest() {
    }

    @Override
    public void setUp() {
        try {
            fileHandler = new FileHandler(new File("profiles/forTests"));
        } catch (IOException ex) {
            Logger.getLogger(FileHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void testReadingAndWriting() {
        for (int i = 0; i < 10; i++) {
            fileHandler.saveLine(0, 0);
        }
        Stack s = new Stack();
        try {
            s = fileHandler.getLines();
        } catch (IOException ex) {
            Logger.getLogger(FileHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
            assertTrue("Could not read file", false);
        }

        Stack s2 = new Stack();

        for (int i = 0; i < 10; i++) {
            s2.put(new Node(0, 0));
        }

        assertEquals(s.pop(), s2.pop());
    }
}