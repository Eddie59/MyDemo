package cn.module.validate.validator;

import cn.module.validate.model.UserModel;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * UserModelValidator class
 *
 * @author Administrator
 * @date
 */
public class UserModelValidator implements Validator {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z]\\w{4,19}");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9]{5,20}");

    @Override
    public boolean supports(Class<?> aClass) {
        return UserModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(@Nullable Object o, Errors errors) {

        UserModel userModel=(UserModel)o;

        ValidationUtils.rejectIfEmpty(errors,"username","username.not.empty");

       if(!(USERNAME_PATTERN.matcher(userModel.getUsername())).matches())
       {
           //如果用户名不合法
           errors.rejectValue("username", "username.not.illegal");
       }
    }
}
