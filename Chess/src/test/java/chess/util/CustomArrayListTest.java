package chess.util;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CustomArrayListTest
{
	private List<Integer> list;

	@Before
	public void setUp()
	{
		list = new CustomArrayList<Integer>();
	}

	@Test
	public void newListHasZeroSize()
	{
		assertEquals(0, list.size());
	}

	@Test
	public void isEmptyReturnsTrueForNewList()
	{
		assertTrue(list.isEmpty());
	}

	@Test
	public void isEmptyReturnsFalseForAfterAdding()
	{
		list.add(66);
		assertFalse(list.isEmpty());
	}

	@Test
	public void addingItemsIncreasesSize()
	{
		list.add(10);
		list.add(11);
		list.add(12);
		assertEquals(3, list.size());
	}

	@Test
	public void addedItemsCanBeAccessedWithGet()
	{
		list.add(10);
		list.add(11);
		list.add(12);
		assertEquals(10, list.get(0).intValue());
		assertEquals(11, list.get(1).intValue());
		assertEquals(12, list.get(2).intValue());
	}

	@Test
	public void canGrowCapacity()
	{
		for (int i = 0; i < 50; ++i)
			list.add(i);
		assertEquals(50, list.size());
		for (int i = 0; i < 50; ++i)
			assertEquals(i, list.get(i).intValue());
	}

	@Test
	public void getThrowsWhenIndexNegative()
	{
		list.add(2);
		try {
			list.get(-1);
			fail("IndexOutOfBoundsException not thrown.");
		} catch (IndexOutOfBoundsException e) {
		}
	}

	@Test
	public void getThrowsWhenIndexTooBig()
	{
		list.add(2);
		list.add(3);
		try {
			list.get(2);
			fail("IndexOutOfBoundsException not thrown.");
		} catch (IndexOutOfBoundsException e) {
		}
	}
}
