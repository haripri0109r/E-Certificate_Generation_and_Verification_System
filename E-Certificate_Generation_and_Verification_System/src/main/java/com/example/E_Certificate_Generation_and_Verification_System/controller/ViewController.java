package com.example.E_Certificate_Generation_and_Verification_System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Certificate;
import com.example.E_Certificate_Generation_and_Verification_System.entity.User;
import com.example.E_Certificate_Generation_and_Verification_System.entity.Verificationlog;
import com.example.E_Certificate_Generation_and_Verification_System.service.CertificateService;
import com.example.E_Certificate_Generation_and_Verification_System.service.UserService;
import com.example.E_Certificate_Generation_and_Verification_System.service.VerificationlogService;

@Controller
public class ViewController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationlogService verificationlogService;

    @GetMapping("/certificate/list")
    public String certificateList(Model model) {
        model.addAttribute("certificates", certificateService.getAllCertificates());
        return "certificate/list";
    }

    @GetMapping("/certificate/form")
    public String certificateForm(Model model) {
        model.addAttribute("certificate", new Certificate());
        return "certificate/form";
    }

    @GetMapping("/certificate/edit/{id}")
    public String certificateEdit(@PathVariable Long id, Model model) {
        certificateService.getCertificateById(id).ifPresentOrElse(
                c -> model.addAttribute("certificate", c),
                () -> model.addAttribute("certificate", new Certificate())
        );
        return "certificate/form";
    }

    @GetMapping("/user/list")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/user/form")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }
    
    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        userService.getUserById(id).ifPresentOrElse(
            user -> model.addAttribute("user", user),
            () -> model.addAttribute("user", new User())
        );
        return "user/form";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }

    @GetMapping("/verificationlog/list")
    public String verificationlogList(Model model) {
        model.addAttribute("vlogs", verificationlogService.getAllVerificationlogs());
        return "verificationlog/list";
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        // add counts or lists if desired; for now provide simple model attributes for display
        model.addAttribute("certificateCount", certificateService.getAllCertificates().size());
        model.addAttribute("userCount", userService.getAllUsers().size());
        model.addAttribute("vlogCount", verificationlogService.getAllVerificationlogs().size());
        return "home";
    }

    @GetMapping("/verificationlog/form")
    public String verificationlogForm(Model model) {
        model.addAttribute("verificationlog", new Verificationlog());
        return "verificationlog/form";
    }

    @GetMapping("/verificationlog/edit/{id}")
    public String verificationlogEdit(@PathVariable Long id, Model model) {
        verificationlogService.getVerificationlogById(id).ifPresentOrElse(
                v -> model.addAttribute("verificationlog", v),
                () -> model.addAttribute("verificationlog", new Verificationlog())
        );
        return "verificationlog/form";
    }
}
