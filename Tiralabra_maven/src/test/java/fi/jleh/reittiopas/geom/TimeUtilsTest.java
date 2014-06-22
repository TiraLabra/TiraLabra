package fi.jleh.reittiopas.geom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fi.jleh.reittiopas.utils.TimeUtils;

public class TimeUtilsTest {

	@Test
	public void testWalkTime1() {
		testWalkTime("0700", "0705", 5);
	}
	
	@Test
	public void testWalkTime2() {
		testWalkTime("0700", "0735", 35);
	}
	
	@Test
	public void testWalkTimeWithHourChange1() {
		testWalkTime("0750", "0805", 15);
	}
	
	@Test
	public void testWalkTimeWithHourChange2() {
		testWalkTime("0950", "1005", 15);
	}
	
	@Test
	public void testWalkTimeWithDayChange() {
		testWalkTime("2350", "2405", 15);
	}
	
	@Test
	public void testWalkTimeWithNight() {
		testWalkTime("2545", "2615", 30);
	}
	
	private void testWalkTime(String startTime, String expectedEndTime, int walk) {
		String endTime = TimeUtils.getTimeAfterWalk(startTime, walk);
		assertEquals(expectedEndTime, endTime);
	}
	
	@Test
	public void testTimeDifference1() {
		String difference = TimeUtils.calculateTimeDifference("0700", "0730");
		assertEquals("0030", difference);
	}
	
	@Test
	public void testTimeDifference2() {
		String difference = TimeUtils.calculateTimeDifference("0700", "0705");
		assertEquals("0005", difference);
	}
	
	@Test
	public void testTimeDifference3() {
		String difference = TimeUtils.calculateTimeDifference("2530", "2630");
		assertEquals("0100", difference);
	}
	
	@Test
	public void testTimeDifference4() {
		String difference = TimeUtils.calculateTimeDifference("2330", "2430");
		assertEquals("0100", difference);
	}
	
	@Test
	public void testTimeDifference5() {
		String difference = TimeUtils.calculateTimeDifference("1560", "1605");
		assertEquals("0005", difference);
	}
	
	@Test
	public void testTimeDifference6() {
		String difference = TimeUtils.calculateTimeDifference("0700", "1038");
		assertEquals("0338", difference);
	}
}
