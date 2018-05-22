package spring.beanscope.model;

import java.math.BigInteger;

/**
 * User class
 *
 * @author Administrator
 * @date
 */
public class User {
    private BigInteger id;
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }
}
