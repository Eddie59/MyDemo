package cn;

import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

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
