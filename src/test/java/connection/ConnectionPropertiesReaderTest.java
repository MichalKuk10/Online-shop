package connection;


import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ConnectionPropertiesReaderTest {

    @Test
    public void should_read_properties_file_correctly() {
        // given:
        String filepath = "src/test/resources/test.properties";
        ConnectionPropertiesReader cpReader = new ConnectionPropertiesReader();
        String expectedPropertyValue = "test.value";
        // when:
        Properties properties = cpReader.readProperties(filepath);
        String testPropertyValue = properties.getProperty("test.name");
        // then:
        assertEquals(testPropertyValue, expectedPropertyValue);
    }
}