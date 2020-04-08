package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPropertiesReader {
    public Properties readProperties(String filepath) {
        Properties properties = new Properties();
        Path path = Paths.get(filepath);

        try {
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            properties.load(reader);
        } catch (IOException e) {
            Logger.getLogger(ConnectionPropertiesReader.class.getName()).log(
                    Level.SEVERE, null, e);
        }
        return properties;
    }
}
