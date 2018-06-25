package cn.module.validate.controller;

import cn.module.validate.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * HelloWorldController class
 *
 * @author Administrator
 * @date
 */
@Controller
public class HelloWorldController {

    @ModelAttribute("user")
    public UserModel getUser() {
        return new UserModel();
    }

    @RequestMapping(value = "/validator/hello", method = RequestMethod.GET)
    public String showRegisterForm() {
        return "validate/registerAndValidator";
    }

    @ResponseBody
    @RequestMapping(value = "/validator/hello",method = RequestMethod.POST)
    public String postForm(@Valid UserModel user, BindingResult result) {
        if(result.hasErrors()) {
            return showRegisterForm();
        }
        return "redirect:/success";
    }
}
