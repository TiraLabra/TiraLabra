package fi.jleh.reittiopas.utils;

import java.io.File;
import java.net.URL;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import org.apache.commons.io.FileUtils;

/**
 * Util class to unzip files.
 *
 */
public class Unzipper {

	private static final String source = "/all.zip";
	private static final String destination = System.getProperty("java.io.tmpdir") + "/j_reittiopas";
	private static final String jarDestination = destination + "/all.zip";
	
	/**
	 * Unzips timetable data XML file to temp dir.
	 * @return Path to XML file.
	 */
	public static String unzipTimetableData() {
		try {
			// Copy zip file from JAR to temp and unzip
			URL url = Unzipper.class.getClass().getResource(source);
			File tmpFile = new File(jarDestination);
			FileUtils.copyURLToFile(url, tmpFile);
			
			return extract(jarDestination, destination);
		} catch (Exception e) {
			// Junit tests have no JAR file and zip is extracted in this catch block
			try {
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
