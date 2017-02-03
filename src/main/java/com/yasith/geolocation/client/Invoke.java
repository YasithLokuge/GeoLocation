package com.yasith.geolocation.client;

import com.yasith.geolocation.Exception.GEOException;
import com.yasith.geolocation.config.YamlReader;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasith on 2/2/17.
 */
public class Invoke {

    final static Logger log = Logger.getLogger(Invoke.class);
    static String apiKey = null;
    static String remoteUrl = null;

    public Invoke(String configFilePath) throws GEOException {
        Map<Object,Object> geoAPI = (Map<Object, Object>) YamlReader.getConfiguration(configFilePath).getGoogleGEOLocation();
        apiKey = (String) geoAPI.get("APIkey");
        remoteUrl = (String) geoAPI.get("remoteUrl");
        if(log.isDebugEnabled()){
            log.debug("apiKey : " + apiKey);
        }
    }

    public static Map<Object,Object> invokeGeoLocationApi(String latitude, String longitude, String timestamp) throws GEOException {

        if(log.isDebugEnabled()){
            log.debug("latitude : " + latitude);
            log.debug("longitude : " + longitude);
            log.debug("timestamp : " + timestamp);
        }

        URI callUrl = UriBuilder.fromUri(remoteUrl)
                .queryParam("location", latitude+","+longitude)
                .queryParam("timestamp",timestamp)
                .queryParam("key",apiKey)
                .build();
        if(log.isDebugEnabled()){
            log.debug("remote Call URL : " + callUrl.toString());
        }

        Map<Object,Object> records = new HashMap<>();
        HttpGet request = new HttpGet(callUrl);
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response;

        try {
            response = client.execute(request);
            String responseString = EntityUtils.toString(response.getEntity());
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(responseString).getAsJsonObject();

            JsonElement dstOffset = jsonObject.get("dstOffset");
            JsonElement rawOffset = jsonObject.get("rawOffset");
            JsonElement status = jsonObject.get("status");
            JsonElement timeZoneId = jsonObject.get("timeZoneId");
            JsonElement timeZoneName = jsonObject.get("timeZoneName");

            if(log.isDebugEnabled()){
                log.debug("Response Code : " + response.getStatusLine().getStatusCode());
                log.debug("JSON Response : " + jsonObject.toString());
            }

            records.put("dstOffset", dstOffset.getAsInt());
            records.put("rawOffset", rawOffset.getAsLong());
            records.put("status", status.getAsString());
            records.put("timeZoneId", timeZoneId.getAsString());
            records.put("timeZoneName", timeZoneName.getAsString());

        } catch (Exception e) {
            log.error("Exception Occured while Calling GET" , e);
            throw new GEOException("Exception Occured while Calling GET", e);
        }
        return records;
    }
}
