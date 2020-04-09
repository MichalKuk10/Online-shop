package connection;

import org.junit.jupiter.api.Test;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPropertiesReaderTest {

    @Test
    public void should_read_properties_file_correctly() {
        // given:
        String filepath = "connection/test.properties";
        ConnectionPropertiesReader cpReader = new ConnectionPropertiesReader();
        String expectedPropertyValue = "test.value";
        // when:
        Properties properties = cpReader.readProperties(filepath);
        String testPropertyValue = properties.getProperty("test.name");
        // then:
        assertEquals(testPropertyValue, expectedPropertyValue);
    }
}

    //url = properties.getProperty("db.url");
    //    public Properties readProperties(String filepath) {
//        Properties properties = new Properties();
//        Path path = Paths.get(filepath);
//
//        try {
//            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
//            properties.load(reader);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return properties;
//    }