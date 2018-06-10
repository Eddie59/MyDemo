package cn.module.validate.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * UserModel class
 *
 * @author Administrator
 * @date
 */
public class UserModel {
    @NotNull(message="{username.not.empty}")
    private String username;
    @NotNull()
    @Size(min=6 ,max= 20 ,message = "密码长度不符合标准")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
