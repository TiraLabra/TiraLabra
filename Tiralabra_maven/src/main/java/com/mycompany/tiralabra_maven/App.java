package com.mycompany.tiralabra_maven;

final class App {

    public static void main(String[] args) {
        startApplication("C:\\Users\\Timo\\Desktop\\test.txt");
    }

    private static void startApplication(final String path) {
        final Compressor c = new Compressor(path);
        if (c.canBeRead()) {
            c.compressFile();
        } else {
            System.err.println("Can't access file...");
        }
    }
}
