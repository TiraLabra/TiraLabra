package fi.jleh.reittiopas.utils;

import org.joda.time.DateTime;
import org.joda.time.Period;


public class TimeUtils {

	/**
	 * Returns time after given minutes.
	 * @param startTime
	 * @param walkTime
	 * @return
	 */
	public static String getTimeAfterWalk(String startTime, int walkTime) {
		int minutes = Integer.parseInt(startTime.substring(2));
		int hours = Integer.parseInt(startTime.substring(0, 2));
		
		int minutes2 = minutes + walkTime;
		
		if (minutes2 > 60) {
			minutes2 = minutes2 - 60;
			hours++;
		}
		
		String strHour = "" + hours;
		String strMinute = "" + minutes2;
		
		if (hours < 10)
			strHour = "0" + strHour;
		if (minutes2 < 10)
			strMinute = "0" + strMinute;
		
		return strHour + strMinute;
	}
	
	/**
	 * Calculates difference between two given time strings.
	 * @param strTime1 start time
	 * @param strTime2 end time
	 * @return
	 */
	public static String calculateTimeDifference(String strTime1, String strTime2) {
		DateTime time1 = getDateTime(strTime1);
		DateTime time2 = getDateTime(strTime2);
		
		Period period = new Period(time1, time2);
		
		int hours = period.getHours();
		int minutes = period.getMinutes();
		
		return ((hours < 10) ? ("0" + hours) : hours) + "" + ((minutes < 10) ? ("0" + minutes) : minutes);
	}
	
	
	private static DateTime getDateTime(String str) {
		DateTime now = DateTime.now();
		int hours = Integer.parseInt(str.substring(0, 2));
		int minutes = Integer.parseInt(str.substring(2));
		
		if (minutes == 60) {
			hours++;
			minutes = 0;
		}
		
		if (hours >= 24) {
			hours = hours - 24;
			now = now.plusDays(1);
		}
		
		return new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), hours, minutes);
	}
}
