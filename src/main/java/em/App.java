package em;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Properties properties= java.lang.System.getProperties();
        System.out.println(properties.getProperty("java.version"));


        String queryPropertyStr="";
        Set<String> set= Arrays.stream(queryPropertyStr.split(","))
                .filter((x)->!StringUtils.isNoneEmpty(x))
                .map(x->x)
                .collect(Collectors.toSet());


    }
}
