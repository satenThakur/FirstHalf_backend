package com.firsthalf.controller;

import com.firsthalf.entity.User;
import com.firsthalf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String findAllUsers(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String getUserDetail(@PathVariable Long id, Model model){
        User user= userService.findUserById(id);
        model.addAttribute("user",user);
        return "user-details";
    }

    @GetMapping("remove-user/{id}")
    public String deleteUser(@PathVariable Long id, Model model){
        userService.deleteUser(id);
        model.addAttribute("users", userService.findAllUsers());
        return "redirect:/users";
    }

    @GetMapping("/update-user/{id}")
    public String updateUser(@PathVariable long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "update-user";
    }

    @PostMapping("/update-user/{id}")
    public String saveUpdateUser(@PathVariable Long id, User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "update-user";
        userService.updateUser(user);
        model.addAttribute("user", userService.findAllUsers());
        return "redirect:/users";
    }
    @GetMapping("/add-user")
    public String createUser(User user){
        return "add-user";
    }

    @PostMapping("/save-user")
    public String createUser(User user, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add-user";
        }
        userService.createUser(user);
        model.addAttribute("users", userService.findAllUsers());
        return "redirect:/users";
    }

}
