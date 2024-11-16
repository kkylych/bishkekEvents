package com.bishevents.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.bishevents.DTO.UserDTO;
import com.bishevents.exception.ResourceNotFoundException;
import com.bishevents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);  // Add users to model for Thymeleaf
        return "user-list";  // Thymeleaf template (user-list.html) will be rendered
    }


    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        UserDTO user = userService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        model.addAttribute("user", user);
        return "user-detail";  // Thymeleaf view for rendering user detail
    }

    @PostMapping
    public String saveUser(@ModelAttribute UserDTO userDTO, Model model) {
        UserDTO createdUser = userService.saveUser(userDTO);
        model.addAttribute("user", createdUser);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";  // Redirect to the user list page
    }
}
