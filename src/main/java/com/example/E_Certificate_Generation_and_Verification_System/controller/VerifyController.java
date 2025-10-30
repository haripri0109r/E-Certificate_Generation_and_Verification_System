package com.example.E_Certificate_Generation_and_Verification_System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Certificate;
import com.example.E_Certificate_Generation_and_Verification_System.entity.Verificationlog;
import com.example.E_Certificate_Generation_and_Verification_System.service.CertificateService;
import com.example.E_Certificate_Generation_and_Verification_System.service.VerificationlogService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class VerifyController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private VerificationlogService verificationlogService;

    @GetMapping("/verify")
    public String verifyForm() {
        return "verify";
    }

    @PostMapping("/verify")
    public String verifySubmit(@RequestParam("code") String code, HttpServletRequest request, Model model) {
        var certOpt = certificateService.findByUniqueCode(code);
        Verificationlog log = new Verificationlog();
        if (certOpt.isPresent()) {
            Certificate cert = certOpt.get();
            log.setCertificateId(cert.getId() == null ? null : cert.getId().toString());
            log.setStatus("VALID");
           
            verificationlogService.createVerificationlog(log);
            model.addAttribute("status", "VALID");
            model.addAttribute("certificate", cert);
        } else {
            log.setCertificateId(null);
            log.setStatus("INVALID");
            verificationlogService.createVerificationlog(log);
            model.addAttribute("status", "INVALID");
        }

        model.addAttribute("code", code);
        return "verify-result";
    }

}
