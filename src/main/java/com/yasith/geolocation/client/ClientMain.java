package com.yasith.geolocation.client;

import com.yasith.geolocation.Exception.GEOException;
import org.apache.log4j.Logger;

/**
 * Created by yasith on 2/2/17.
 */
public class ClientMain {

    final static Logger log = Logger.getLogger(ClientMain.class);

    static String configFilePath;
    static String inputFilePath;
    static String outputFilePath;

    public static void main(String[] args) throws GEOException {

        if(log.isDebugEnabled()){
            log.debug("Process Started");
        }

        configFilePath = args[0];
        inputFilePath = args[1];
        outputFilePath = args[2];

        Process.doProcess(configFilePath, inputFilePath, outputFilePath);
    }
}
