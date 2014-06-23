package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.tools.*;
import java.io.IOException;
import java.util.Scanner;

public class App {

//    private int intti;
    /**
     * Main-metodi
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Io io = new Console(new Scanner(System.in));
        Pakkaaja pakkaaja = new Pakkaaja(io);
        pakkaaja.run();
    }

}
