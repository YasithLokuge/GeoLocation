package com.yasith.geolocation.config;

import com.yasith.geolocation.Exception.GEOException;
import com.yasith.geolocation.model.ConfigDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;


/**
 * The Class YamlReader.
 * @author yasith
 */
public class YamlReader {

	/** The log. */
	final static Logger log = Logger.getLogger(YamlReader.class);

	/**
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	public static ConfigDTO getConfiguration(String filePath) throws GEOException {

		File file = new File(filePath);
		ConfigDTO configPojo;

		final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

		try {
			configPojo = mapper.readValue(file, ConfigDTO.class);
		} catch (JsonParseException e) {
			log.error("Yaml Parsing Error", e);
            throw new GEOException("Yaml Parsing Error", e);
		} catch (JsonMappingException e) {
			log.error("Yaml Mapping Error",e);
            throw new GEOException("Yaml Mapping Error", e);
		} catch (IOException e) {
			log.error("Yaml File Error",e);
            throw new GEOException("Yaml File Error", e);
		}

		return configPojo;
	}
}
