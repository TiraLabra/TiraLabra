package Collections;

import PackerX.Node;
import PackerX.TreeMember;
import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public final class PriorityQueueTest {

    private static final int MANY_SIZE = 100;
    private PriorityQueue<Integer> queue;

    private void enqueueMany() {
        for (int i = 0; i < MANY_SIZE; i++) {
            queue.enqueue(i);
        }
    }

    @Before
    public void setUp() {
        queue = new PriorityQueue<>(Integer.class);
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(0);
        assertEquals(1, queue.size());
    }

    @Test
    public void testDequeueSizeCorrect() {
        queue.enqueue(0);
        queue.dequeue();
        assertEquals(0, queue.size());
    }

    @Test
    public void testDequeueReturnCorrect() {
        final Integer value = 23;
        queue.enqueue(value);
        assertEquals(value, queue.dequeue());
    }

    @Test
    public void testEnqueueMany() {
        enqueueMany();
        assertEquals(MANY_SIZE, queue.size());
    }

    @Test
    public void testEnqueueNodes() {
        PriorityQueue<TreeMember> nodes = new PriorityQueue<>(TreeMember.class);
        final int lowestValue = 10;
        final int multi = 2;
        for (int i = lowestValue; i < lowestValue + 10; i++) {
            nodes.enqueue(new Node('c', i * multi, null, null));
        }
        int result = lowestValue * multi;
        assertEquals(result, nodes.dequeue().getWeight());
    }

    @Test
    @SuppressWarnings("UnnecessaryUnboxing") //Necessary unboxing!
    public void testEnqueueManyHeadCorrect() {
        enqueueMany();
        assertEquals(MANY_SIZE - 1, queue.dequeue().intValue());
    }

    @Test
    public void testEnqueueManyOrderCorrect() {
        enqueueMany();
        boolean correct = true;
        for (int i = MANY_SIZE - 1; i >= 0; i--) {
            final int queued = queue.dequeue();
            if (queued != i) {
                System.err.println(queued + " is incorrect, expected " + i);
                correct = false;
            }
        }
        assertTrue("Wrong order for priority queue", correct);
    }

    @Test
    public void testEnqueueManyAndRandomOrderCorrect() {
        Random rand = new Random();
        enqueueMany();
        final Integer intToAdd = Integer.MAX_VALUE;
        queue.enqueue(intToAdd);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(rand.nextInt(100));
        }
        assertEquals(intToAdd, queue.dequeue());
    }

    @Test
    public void testEmptyQueueError() {
        try {
            queue.dequeue();
            fail("No error on empty queue");
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }

    @Test
    public void testHeapifyLeft() {
        final int first = 5544;
        final int second = 234;
        queue.enqueue(first);
        queue.enqueue(second);
        queue.dequeue();
        int res = queue.dequeue();
        assertEquals(second, res);
    }

    @Test
    public void testHeapifyRight() {
        final int first = 5544;
        final int second = 234;
        queue.enqueue(first);
        for (int i = 0; i < 4; i++) {
            queue.enqueue(i);
        }
        queue.enqueue(second);
        queue.dequeue();
        int res = queue.dequeue();
        assertEquals(second, res);
    }

    @Test
    public void testCorrectOrderSetUnordered() {
        int unSorted[] = new int[]{
            -3,
            4,
            344,
            29,
            -123,
            54,
            23,
            454,
            -45,
            -34545,
            343,
            540,
            32,
            55,
            4
        };
        int sorted[] = new int[]{
            540,
            454,
            344,
            343,
            55,
            54,
            32,
            29,
            23,
            4,
            4,
            -3,
            -45,
            -123,
            -34545
        };
        for (int i = 0; i < unSorted.length; i++) {
            queue.enqueue(unSorted[i]);
        }
        for (int i = 0; i < queue.size(); i++) {
            final int n = queue.dequeue();
            if (n != sorted[i]) {
                fail("Order incorrect, was " + n + " expected " + sorted[i]);
            }
        }
    }
}
