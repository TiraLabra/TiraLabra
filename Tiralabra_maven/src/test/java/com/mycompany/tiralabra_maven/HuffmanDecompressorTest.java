package com.mycompany.tiralabra_maven;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public final class HuffmanDecompressorTest {

    private HuffmanDecompressor decompressor;

    @Before
    public void setUp() {
        decompressor = new HuffmanDecompressor("Ö://ASFOBRMIOMFKSDMFKSDFMKDS//asdas");
    }

    @Test
    public void testFileIsNotValid() {
        assertFalse(decompressor.fileIsValid());
    }

    @Test
    public void testFileCannotBeRead() {
        assertFalse(decompressor.fileCanBeRead());
    }

    @Test
    public void testInvalidDecompressionName() {
        assertFalse(decompressor.fileExists());
    }

    @Test
    public void testCantCreate() {
        assertFalse(decompressor.create());
    }
}
