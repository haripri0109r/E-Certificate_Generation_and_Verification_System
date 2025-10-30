package com.example.E_Certificate_Generation_and_Verification_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.E_Certificate_Generation_and_Verification_System.entity.User;
import com.example.E_Certificate_Generation_and_Verification_System.service.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   
    @GetMapping
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

   
    @PostMapping
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/user/list";
    }

 
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id).orElse(new User());
        model.addAttribute("user", user);
        return "user/form";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, User user) {
        userService.updateUser(id, user);
        return "redirect:/user/list";
    }

 
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }

  
    @GetMapping("/{id}")
    @ResponseBody
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

}
