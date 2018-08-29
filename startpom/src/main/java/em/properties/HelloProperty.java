package em.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * HelloProperty class
 *
 * @author Administrator
 * @date
 */
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "hello")
public class HelloProperty {
    /**
     * msg的默认值
     */
    private final static String MSG="eddie";

    public String msg=MSG;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
