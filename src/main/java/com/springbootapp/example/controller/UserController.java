package com.springbootapp.example.controller;

import com.springbootapp.example.model.User;
import com.springbootapp.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("user", userService.getUsers());
        return "/index";
    }

    @GetMapping("/new_user")
    public String addUser(Model model) {
        return "/new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userService.find(id));
        return "/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user){
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@ModelAttribute("id") long id){
        //User user = userService.find(id);
        userService.delete(id);
        return "redirect:/users";
    }
}