package com.yasith.geolocation.file;

import com.yasith.geolocation.Exception.GEOException;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class ListingsReader.
 * 
 * @author Yasith Lokuge
 */
public class ListingsReader {

	/** The log. */
	final static Logger log = Logger.getLogger(ListingsReader.class);

	/**
	 * Gets the listings.
	 *
	 * @return the listings
	 */
	public List<String> getListings(String filePath) throws GEOException {

		List<String> entries = new ArrayList<>();
		FileInputStream fstream;

		try {
			String strLine;
			fstream = new FileInputStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			while ((strLine = br.readLine()) != null) {
				if(strLine != null && !strLine.isEmpty()) {
					entries.add(strLine);
				}else{
					continue;
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			log.error("Input File not found " , e);
			throw new GEOException("Input File not found " , e);
		} catch (IOException e) {
			log.error("Input File IO Error" , e);
			throw new GEOException("Input File IO Error " , e);
		}
		return entries;
	}
}
