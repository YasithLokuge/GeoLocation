package com.yasith.geolocation.client;

import com.yasith.geolocation.Exception.GEOException;
import com.yasith.geolocation.file.ListingsReader;
import com.yasith.geolocation.file.OutputWriter;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yasith on 2/2/17.
 */
public class Process {

    /** The log. */
    final static Logger log = Logger.getLogger(Process.class);

    public static void doProcess(String configfile, String inputfile, String outputfile) throws GEOException {

        ListingsReader listingsReader = new ListingsReader();
        List<String> records = listingsReader.getListings(inputfile);
        Invoke invoke = new Invoke(configfile);
        List<String> outputListings = new ArrayList<>();

        if(log.isDebugEnabled()){
            log.debug("config file path : " + configfile);
            log.debug("input file path : " + inputfile);
            log.debug("output file path : " + outputfile);
        }

        for (String record: records) {
            String[] ary = record.split(",");
            long epoch = getEpoch(ary[0]);
            String epochString = Long.toString(epoch);
            String latitude = ary[1];
            String longitude = ary[2];
            Map<Object,Object> results =  invoke.invokeGeoLocationApi(latitude, longitude, epochString);
            long rawOffset = (long) results.get("rawOffset");
            String timeZoneId = (String) results.get("timeZoneId");
            long convertedEpoch = epoch + rawOffset;
            String convertedEpochString = getTimeStamp(convertedEpoch);
            String processedString = record + "," + timeZoneId + "," + convertedEpochString;
            outputListings.add(processedString);
            if(log.isDebugEnabled()){
                log.debug("processed String : " + processedString);
            }
        }

        OutputWriter outputWriter = new OutputWriter();
        outputWriter.writeToFile(outputListings, outputfile);
    }

    private static long getEpoch(String datetime) throws GEOException {
        long epoch;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Date date = simpleDateFormat.parse(datetime);
            epoch = date.getTime();
        } catch (ParseException e) {
            log.error("Time Conversion Error", e);
            throw new GEOException("Time Conversion Error", e);
        }
        return epoch/1000;
    }

    private static String getTimeStamp(long epoch){
        Date date = new Date(epoch * 1000L);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return format.format(date);
    }
}
