package tira.utils;

import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tira.list.LinkedList;

/**
 *
 * @author joonaslaakkonen
 */
public class MapperTest {
    
    private Scanner reader;
    private Mapper mapper;
    
    public MapperTest() {
    }
    
    @Before
    public void setUp() {
        String map = this.doString();
        this.reader = new Scanner(map);
        mapper = new Mapper(this.reader);
    }

    /**
     * Test of initialize method, of class Mapper.
     */
    @Test
    public void testInitialize() {
        mapper.initialize();
    }

    /**
     * Test of print method, of class Mapper.
     */
    @Test
    public void testPrint() {
        mapper.initialize();
        mapper.print();
    }

    /**
     * Test of validKeys method, of class Mapper.
     */
    @Test
    public void testValidKeys() {
        mapper.initialize();
        assertFalse(mapper.validKeys("Y", "X"));
        assertTrue(mapper.validKeys("A", "B"));
    }

    /**
     * Test of getMap method, of class Mapper.
     */
    @Test
    public void testGetMap() {
        mapper.initialize();
        LinkedList map = mapper.getMap();
        assertFalse(map.empty());
        assertEquals(10, map.size());
        Location A = (Location)map.searchWithString("A").getOlio();
        assertEquals(3, A.getTargets().size());
        Location F = (Location)map.searchWithString("F").getOlio();
        assertEquals(5, F.getTargets().size());
    }

    private String doString() {
        String map = "A;150;400;I;770;420;630\n"
                + "A;150;400;G;490;270;410\n"
                + "A;150;400;F;110;190;370\n"
                + "B;75;240;F;110;190;120\n"
                + "B;75;240;C;80;90;200\n"
                + "C;80;90;F;110;190;70\n"
                + "C;80;90;H;610;100;470\n"
                + "D;400;480;E;300;310;50\n"
                + "D;400;480;I;770;420;290\n"
                + "D;400;480;J;870;140;420\n"
                + "E;300;310;I;770;420;315\n"
                + "E;300;310;F;110;190;190\n"
                + "F;110;190;G;490;270;110\n"
                + "H;610;100;I;770;420;510\n"
                + "H;610;100;J;870;140;220";
        return map;
    }    
}