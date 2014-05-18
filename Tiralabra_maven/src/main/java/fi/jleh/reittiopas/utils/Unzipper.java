package fi.jleh.reittiopas.utils;

import net.lingala.zip4j.core.ZipFile;

/**
 * Util class to unzip files.
 *
 */
public class Unzipper {

	/**
	 * Unzips timetable data XML file to temp dir.
	 * @return Path to XML file.
	 */
	public static String unzipTimetableData() {
		String source = "src/main/resources/all.zip";
		String destination = System.getProperty("java.io.tmpdir") + "/j_reittiopas";
		
		try {
			ZipFile zipFile = new ZipFile(source);
			
			zipFile.extractAll(destination);
			
			return destination + "/LVM.xml";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
