package fi.jleh.reittiopas.utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

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
		String source = "all.zip";
		String destination = System.getProperty("java.io.tmpdir") + "/j_reittiopas";
		
		try {
			return extract(source, destination);
		} catch (ZipException e) {
			try { // I couldn't get Maven to work with tests and JAR file so file paths are handled ugly.
				return extract("src/main/resources/all.zip", destination);
			} catch (Exception e2) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	private static String extract(String source, String destination) throws ZipException {
		ZipFile zipFile = new ZipFile(source);
		
		zipFile.extractAll(destination);
		
		return destination + "/LVM.xml";
	}
}
