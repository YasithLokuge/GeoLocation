import com.yasith.geolocation.Exception.GEOException;
import com.yasith.geolocation.client.Process;

/**
 * Created by yasith on 2/2/17.
 */
public class Test {

    @org.junit.Test
    public void testAll() throws GEOException {

        String configFilePath = "deploy/config.yml";
        String inputFilePath = "deploy/input.txt";
        String outputFilePath = "deploy/output.txt";

        Process.doProcess(configFilePath, inputFilePath, outputFilePath);
    }
}
