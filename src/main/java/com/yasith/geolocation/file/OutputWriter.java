package com.yasith.geolocation.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.yasith.geolocation.Exception.GEOException;
import org.apache.log4j.Logger;


/**
 * The Class OutputWriter.
 * @author Yasith Lokuge
 */
public class OutputWriter {

	/** The log. */
	final static Logger log = Logger.getLogger(OutputWriter.class);

	/**
	 * Write to file.
	 */
	public void writeToFile(List<String> outputEntries , String filePath) throws GEOException {
		
		File file = new File(filePath);
		FileWriter fw;
		
		try {

			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile());

			for (String entry : outputEntries) {
				fw.write(entry + "\r\n");
			}

			BufferedWriter bw = new BufferedWriter(fw);			
			bw.close();
			fw.close();

		} catch (IOException e) {
			log.error("Output File IO Error" , e);
			throw new GEOException("Output File IO Error" , e);
		}

	}

}
