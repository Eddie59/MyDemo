package em;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Properties properties = java.lang.System.getProperties();
        System.out.println(properties.getProperty("java.version"));


        String queryPropertyStr = "a,b,c,";
        Set<String> stream= Arrays.stream(queryPropertyStr.split(","))
                //.filter((x) -> !StringUtils.isNoneEmpty(x))
                .collect(Collectors.toSet());
    }
}
