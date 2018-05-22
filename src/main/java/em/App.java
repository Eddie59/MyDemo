package em;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Properties properties= java.lang.System.getProperties();
        System.out.println(properties.getProperty("java.version"));
    }
}
