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
}
