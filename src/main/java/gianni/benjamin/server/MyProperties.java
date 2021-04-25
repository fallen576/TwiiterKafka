package gianni.benjamin.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class MyProperties {

    private static final Properties PROPERTIES = new Properties();
    private static final String PROP_FILE = "apikey.properties";

    private MyProperties() {

    }

    static {
        try {
            InputStream stream = new FileInputStream("src/main/resources/"+PROP_FILE);
            PROPERTIES.load(stream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static String getProperty(final String name) {
        return PROPERTIES.getProperty(name);
    }
}
