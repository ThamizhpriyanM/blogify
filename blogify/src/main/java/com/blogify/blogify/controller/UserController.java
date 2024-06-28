package com.blogify.blogify.controller;

import com.blogify.blogify.Dto.UserDTO;
import com.blogify.blogify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session) {
        if (userService.verifyUser(userDTO)) {
            session.setAttribute("username", userDTO.getUsername());
            return "redirect:/home";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(UserDTO userDTO) {
        userService.createUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "home";
    }

    @GetMapping("/create")
    public String createPage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "create";
    }

    @GetMapping("/update")
    public String updatePage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "update";
    }

    @GetMapping("/delete")
    public String deletePage(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "delete";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
