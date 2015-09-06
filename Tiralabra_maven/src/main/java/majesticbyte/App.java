package majesticbyte;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        ProcessBuilder builder = new ProcessBuilder("java", "-jar", "src/main/resources/kgsGtp.jar");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        OutputStream stdin = process.getOutputStream();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        boolean cont = true;
        while (cont) {
            String line = reader.readLine();
            if (line != null) {
                System.out.println(line);
            } else {
                cont = false;
            }
        }
    }

}
