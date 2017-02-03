package com.yasith.geolocation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;



/**
 * The Class ConfigDTO.
 * @author yasith
 */
public class ConfigDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8356032431902326005L;



	/** The Google GEO Location. */
	@JsonProperty
	private Object GoogleGEOLocation;

	/**
	 * Gets the Google GEO Location.
	 *
	 * @return the Google GEO Location
	 */
	public Object getGoogleGEOLocation() {
		return GoogleGEOLocation;
	}

}
