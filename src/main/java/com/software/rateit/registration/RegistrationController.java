package com.software.rateit.registration;


import com.software.rateit.User;
import com.software.rateit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {

    @Autowired
    UserService service;

    @Autowired
    RegistrationValidator validator;

    @PostMapping("/registration")
    String createNewAccount(@RequestBody User userForm, BindingResult result){
        validator.validate(userForm, result);
        if (result.hasErrors())
            return ("/registration");
        service.registerNewUser(userForm);
        return ("redirect:/users");
    }


}
