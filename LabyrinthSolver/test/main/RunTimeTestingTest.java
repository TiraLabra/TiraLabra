package main;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juri Kuronen
 */
public class RunTimeTestingTest {

    RunTimeTesting rtt;

    @Before
    public void setUp() throws Exception {
        rtt = new RunTimeTesting();
    }

    @Test
    public void test() {
        assertTrue(true);
    }

    @Test
    public void timeFormatting() {
        long time = 123456789;
        assertEquals("123 456,789 ms", rtt.formatTime(time));
        time /= 10;
        assertEquals("12 345,678 ms", rtt.formatTime(time));
        time /= 10;
        assertEquals("1 234,567 ms", rtt.formatTime(time));
        time /= 10;
        assertEquals("123,456 ms", rtt.formatTime(time));
        time = 123;
        assertEquals("0,123 ms", rtt.formatTime(time));
        time = 13;
        assertEquals("0,013 ms", rtt.formatTime(time));
        time = 5;
        assertEquals("0,005 ms", rtt.formatTime(time));
        time = 501;
        assertEquals("0,501 ms", rtt.formatTime(time));
        time = 1000000500000090L;
        assertEquals("1 000 000 500 000,090 ms", rtt.formatTime(time));
    }

    @Test
    public void numberFormatting() {
        long number = 10170157010100751L;
        assertEquals("10 170 157 010 100 751", rtt.formatNumber(number));
        number /= 10;
        assertEquals("1 017 015 701 010 075", rtt.formatNumber(number));
        number /= 10;
        assertEquals("101 701 570 101 007", rtt.formatNumber(number));
        number /= 10;
        assertEquals("10 170 157 010 100", rtt.formatNumber(number));
        number /= 10;
        assertEquals("1 017 015 701 010", rtt.formatNumber(number));
        number /= 10;
        assertEquals("101 701 570 101", rtt.formatNumber(number));
        number /= 10;
        assertEquals("10 170 157 010", rtt.formatNumber(number));
        number /= 10;
        assertEquals("1 017 015 701", rtt.formatNumber(number));
        number /= 10;
        assertEquals("101 701 570", rtt.formatNumber(number));
        number /= 10;
        assertEquals("10 170 157", rtt.formatNumber(number));
        number /= 10;
        assertEquals("1 017 015", rtt.formatNumber(number));
        number /= 10;
        assertEquals("101 701", rtt.formatNumber(number));
        number /= 10;
        assertEquals("10 170", rtt.formatNumber(number));
        number /= 10;
        assertEquals("1 017", rtt.formatNumber(number));
        number /= 10;
        assertEquals("101", rtt.formatNumber(number));
        number /= 10;
        assertEquals("10", rtt.formatNumber(number));
        number /= 10;
        assertEquals("1", rtt.formatNumber(number));
        number /= 10;
        assertEquals("0", rtt.formatNumber(number));
    }
}
