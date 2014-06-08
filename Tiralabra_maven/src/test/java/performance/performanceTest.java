package performance;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import lib.compressors.CompressorLZ77;
import lib.compressors.CompressorLZW;
import lib.decompressors.DecompressorLZ77;
import lib.decompressors.DecompressorLZW;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class performanceTest {
    String input = "isolorem.txt";
    String output = "testout.comp";
    String packedlzw = "test.lzw";
    String packedlz77 = "test.lz77";
    String unpacked = "testdecomp.txt";
    PrintWriter log;
    CompressorLZW lzwcomp;
    CompressorLZ77 lz77comp;
    DecompressorLZW lzwdecomp;
    DecompressorLZ77 lz77decomp;
    
    
    public performanceTest() {
    }
    
    @Before
    public void setUp() throws IOException {
        log = new PrintWriter(new FileWriter("Suoritusaikaloki.txt",true));
    
    }
    
    @After
    public void tearDown(){
        log.close();
    }
    
    @Test
    public void testCompressLZW() throws Exception {
        lzwcomp = new CompressorLZW(input, output);
        long start = System.currentTimeMillis();
        lzwcomp.compress();
        long end = System.currentTimeMillis();
        
        write(start,end, " LZW:n pakkausaika: ");
    }
    
    @Test
    public void testDecompressLZW() throws Exception {
        lzwdecomp = new DecompressorLZW(packedlzw, unpacked);
        long start = System.currentTimeMillis();
        lzwdecomp.decompress();
        long end = System.currentTimeMillis();
        
        write(start,end," LZW:n purkuaika: ");
    }
    
    @Test
    public void testCompressLZ77() throws Exception {
        lz77comp = new CompressorLZ77(input, output);
        long start = System.currentTimeMillis();
        lz77comp.compress();
        long end = System.currentTimeMillis();
        
        write(start,end," LZ77:n pakkausaika: ");
    }
    
    @Test
    public void testDecompressLZ77() throws Exception {
        lz77decomp = new DecompressorLZ77(packedlz77, unpacked);
        long start = System.currentTimeMillis();
        lz77decomp.decompress();
        long end = System.currentTimeMillis();
        
        write(start,end," LZ77:n purkuaika: ");
    }
    
    private void write(long start, long end, String message){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();      
        
        String str = dateFormat.format(date)+message+(end-start);
        log.println(str);    
    }
    

}
