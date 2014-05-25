package fi.jleh.reittiopas.utils;


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
}
