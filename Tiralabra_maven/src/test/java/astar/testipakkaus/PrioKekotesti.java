/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar.testipakkaus;

import astar.tietorakenteet.PrioKeko;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sasumaki
 */
public class PrioKekotesti {

    private PrioKeko<Integer> intkeko;
    private final PrioKeko<String> stringkeko;

    public PrioKekotesti() {
        this.intkeko = new PrioKeko<>();
        this.stringkeko = new PrioKeko<>(new Comparator<String>() {

            @Override
            public int compare(String t, String t1) {
                if (t.length() > t1.length()) {
                    return 1;

                }
                if (t.length() < t1.length()) {
                    return -1;
                } else {
                    return 0;
                }

            }
        });
    }

    @Test
    public void heapInsertTesti() {
        int i = 0;
        intkeko.heapInsert(i);

        assertEquals((int) intkeko.pullDelete(), i);
    }

    @Test
    public void pullDeleteTesti() {
        int i = 0;
        int i1 = 1;
        int i2 = 2;
        int i3 = 3;
        intkeko.heapInsert(i);
        intkeko.heapInsert(i1);
        intkeko.heapInsert(i2);
        intkeko.heapInsert(i3);

        assertEquals((int) intkeko.pullDelete(), i);
        assertEquals((int) intkeko.pullDelete(), i1);
        assertEquals((int) intkeko.pullDelete(), i2);
        assertEquals((int) intkeko.pullDelete(), i3);

    }

    @Test
    public void pullDeleteTesti2() {
        int i = 0;
        int i1 = 2;
        int i2 = 1;
        int i3 = 3;
        intkeko.heapInsert(i);
        intkeko.heapInsert(i1);
        intkeko.heapInsert(i2);
        intkeko.heapInsert(i3);

        assertEquals((int) intkeko.pullDelete(), i);
        assertEquals((int) intkeko.pullDelete(), i2);
        assertEquals((int) intkeko.pullDelete(), i1);
        assertEquals((int) intkeko.pullDelete(), i3);

    }

    @Test
    public void isEmptytesti() {
        assertEquals(intkeko.isEmpty(), true);
    }

    @Test
    public void isEmptytesti2() {
        intkeko.heapInsert(1);

        assertEquals(intkeko.isEmpty(), false);
    }

    @Test
    public void isEmptytesti3() {
        intkeko.heapInsert(1);
        intkeko.pullDelete();

        assertEquals(intkeko.isEmpty(), true);
    }

    @Test
    public void comparatorTesti() {
        String s = "aasi";
        String s1 = "mäpäntuhooja666ultrabot";
        String s2 = "Kappa";

        stringkeko.heapInsert(s);
        stringkeko.heapInsert(s1);
        stringkeko.heapInsert(s2);

        assertEquals(stringkeko.pullDelete(), s1);
    }

    @Test
    public void heapInsertTest() {
        for (int i = 0; i < 10000; i++) {
            intkeko.heapInsert(i);
        }
        for (int i = 0; i < 5000; i++) {
            intkeko.pullDelete();
        }
        assertEquals((int) intkeko.pullDelete(), 5000);
    }
}
