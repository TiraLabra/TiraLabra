package chess.ai;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TranspositionTableTest
{
	private TranspositionTable tt;

	private static final long state1 = 1234567890123456789L;

	private static final long state2 = 876543210987654321L;

	@Before
	public void setUp()
	{
		tt = new TranspositionTable(8);
	}

	@Test
	public void newTableIsEmpty()
	{
		assertEquals(0, tt.size());
	}

	@Test
	public void getReturnsNullForEmptyTable()
	{
		assertNull(tt.get(state1));
	}

	@Test
	public void getReturnsNullIfNotFound()
	{
		StateInfo info = new StateInfo(state1);
		tt.put(info);
		assertNull(tt.get(state2));
	}

	@Test
	public void insertedEntryFoundWithGet()
	{
		StateInfo info = new StateInfo(state1);
		tt.put(info);
		assertSame(info, tt.get(state1));
	}

	@Test
	public void insertingIncreasesSize()
	{
		tt.put(new StateInfo(state1));
		assertEquals(1, tt.size());
	}

	@Test
	public void insertingExistingEntryDoesntIncreaseSize()
	{
		tt.put(new StateInfo(state1));
		assertEquals(1, tt.size());
		tt.put(new StateInfo(state1));
		assertEquals(1, tt.size());
	}

	@Test
	public void insertingExistingEntryReplacesValue()
	{
		tt.put(new StateInfo(state1));
		StateInfo inf2 = new StateInfo(state1);
		tt.put(inf2);
		assertSame(inf2, tt.get(state1));
	}

	@Test
	public void testHashCollisions()
	{
		long s1 = 1L << 32 | 13;
		StateInfo inf1 = new StateInfo(s1);
		tt.put(inf1);

		long s2 = 2L << 32 | 13;
		StateInfo inf2 = new StateInfo(s2);
		tt.put(inf2);

		assertEquals(2, tt.size());
		assertSame(inf1, tt.get(s1));
		assertSame(inf2, tt.get(s2));
	}

	@Test
	public void testGrowth()
	{
		StateInfo[] values = new StateInfo[20];
		for (int i = 1; i < values.length; ++i) {
			values[i] = new StateInfo(i / 7l << 32 | i % 7l);
			tt.put(values[i]);
		}

		assertEquals(values.length - 1, tt.size());

		for (int i = 1; i < values.length; ++i)
			assertSame(values[i], tt.get(i / 7l << 32 | i % 7l));
	}

	@Test
	public void throwsIfInitCapacityTooSmall()
	{
		try {
			tt = new TranspositionTable(4);
			fail("IllegalArgumentException not thrown");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void clearEmptiesTableContents()
	{
		tt.put(new StateInfo(state1));
		tt.put(new StateInfo(state2));
		tt.clear();
		assertNull(tt.get(state1));
		assertNull(tt.get(state2));
	}
}
