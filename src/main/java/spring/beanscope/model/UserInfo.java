package spring.beanscope.model;

/**
 * UserInfo class
 *
 * @author Administrator
 * @date
 */
public class UserInfo {
    private int id;
    private String username;
    private String pswd;

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

}
