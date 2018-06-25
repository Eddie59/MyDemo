package cn.module.validate.controller;

import cn.module.validate.model.UserModel;
import cn.module.validate.validator.UserModelValidator;
import com.alibaba.druid.sql.dialect.oracle.ast.OracleDataTypeIntervalYear;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * RegisterSimpleFormController class
 *
 * @author Administrator
 * @date
 */

@Controller
public class RegisterSimpleFormController {

    UserModelValidator validator=new UserModelValidator();

    @ModelAttribute("user")
    public UserModel getUser() {
        return new UserModel();
    }

    @RequestMapping(value = "/validator", method = RequestMethod.GET)
    public String showRegisterForm() {
        return "validate/registerAndValidator";
    }


    @ResponseBody
    @RequestMapping(value = "/validator", method = RequestMethod.POST)
    public String submitForm(@Validated UserModel user, Errors errors) {

        validator.validate(user,errors);

        if (errors.hasErrors()) {
            return showRegisterForm();
        }
        return "redirect:success";
    }

}
