package com.mycompany.tiralabra_maven;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public final class HuffmanCompressorTest {

    private HuffmanCompressor compressor;

    @Before
    public void setUp() {
    }

    private void setBadFileName() {
        compressor = new HuffmanCompressor("bad file name");
    }

    @Test
    public void testCanReadBadFileName() {
        setBadFileName();
        assertFalse(compressor.canBeRead());
    }

    @Test
    public void testFileExistsBadFileName() {
        setBadFileName();
        assertFalse(compressor.fileExists());
    }

    @Test
    public void testCreateBadFileName() {
        compressor = new HuffmanCompressor("Ö://ASDWASDASDASDCSADASDASDASDASD//bad file name");
        assertFalse(compressor.create());
    }

    @Test
    public void testcanWriteBadFileName() {
        setBadFileName();
        assertFalse(compressor.canWrite());
    }
}
