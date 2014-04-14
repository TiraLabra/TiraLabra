/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencealgorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.junit.After;

/**
 *
 * @author riha
 */
public class TestFileOperations {

    final String TESTFILENAME = "UnitTestTmp";

    protected void writeNewTestSequenceFile(String input) {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(TESTFILENAME), "utf-8"));
            writer.write(input);
        } catch (IOException ex) {
            System.out.println("Virhe testissä writeFile " + ex);
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    @After
    public void tearDown() {
        File f = new File(TESTFILENAME);
        f.delete();
    }

}
