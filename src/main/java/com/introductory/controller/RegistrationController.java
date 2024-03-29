package com.introductory.controller;

import com.introductory.entity.Role;
import com.introductory.entity.User;
import com.introductory.repository.UserRepository;
import com.introductory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (user.getPassword() != null && !user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }
//OLD VERSION OF ADDUSER METHOD
//    @PostMapping("/registration")
//    public String addUser(User user, Map<String, Object> model) {
//
//        if (!userService.addUser(user)) {
//            model.put("message", "User exists!");
//            return "registration";
//        }
//
//        return "redirect:/login";
//    }

//    MAIL ACTIVATION METHOD
////    @GetMapping("/activate/{code}")
////    public String activate(Model model, @PathVariable String code) {
////        boolean isActivated = userService.activateUser(code);
////
////        if (isActivated) {
////            model.addAttribute("message", "User successfully activated");
////        } else {
////            model.addAttribute("message", "Activation code is not found!");
////        }
////
////        return "login";
////    }
}
