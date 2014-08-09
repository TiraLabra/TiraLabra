package com.mycompany.tiralabra_maven;

final class App {

    public static void main(String[] args) {
        //compress("C:\\Users\\Timo\\Desktop\\test.txt");
        deCompress("C:\\Users\\Timo\\Desktop\\test.txt.pkx");
    }

    private static void compress(final String path) {
        final Compressor c = new Compressor(path);
        if (!c.canBeRead()) {
            System.err.println("Can't access file...");
        } else if (c.fileExists()) {
            System.err.println("Name that would be for the compressed file already exists in the path");
        } else if (!c.create()) {
            System.err.println("Could not create file");
        } else if (!c.canWrite()) {
            System.err.println("Cannot write to the newly created file");
        } else {
            c.compressFile();
        }
    }

    private static void deCompress(final String path) {
        final HuffmanDecompressor decompressor = new HuffmanDecompressor(path);
        if (!decompressor.fileCanBeRead()) {
            System.err.println("Can't access file...");
        } else if (!decompressor.fileIsValid()) {
            System.err.println("Decompressor can only read .pkx files");
        } else if (!decompressor.validDecompressionName()) {
            System.err.println("Name that would be for the decompressed file already exists in the path");
        } else if (!decompressor.create()) {
            System.err.println("Could not create file");
        } else {
            decompressor.decompress();
        }
    }
}
