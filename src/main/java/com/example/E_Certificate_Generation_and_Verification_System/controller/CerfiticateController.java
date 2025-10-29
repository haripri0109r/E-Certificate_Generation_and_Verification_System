package com.example.E_Certificate_Generation_and_Verification_System.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Certificate;  
import com.example.E_Certificate_Generation_and_Verification_System.service.CertificateService;


@Controller
@RequestMapping("/certificates")    
public class CerfiticateController {
    
    private final CertificateService certificateService;

    public CerfiticateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        List<Certificate> certificates = certificateService.getAllCertificates();
        return ResponseEntity.ok(certificates);
    }

    // JSON API: accept application/json and return created Certificate
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Certificate createCertificate(@RequestBody Certificate certificate) {
        return certificateService.createCertificate(certificate);
    }

    // Form submission (from Thymeleaf form): accept form-url-encoded and redirect to UI list
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createCertificateForm(Certificate certificate) {
        certificateService.createCertificate(certificate);
        return "redirect:/certificate/list";
    }

    // Update via form
    @PostMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateCertificateForm(@PathVariable Long id, Certificate certificate) {
        certificateService.updateCertificate(id, certificate);
        return "redirect:/certificate/list";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteCertificate(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return "redirect:/certificate/list";
    }

}
