package com.example.E_Certificate_Generation_and_Verification_System.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Certificate;  
import com.example.E_Certificate_Generation_and_Verification_System.service.CertificateService;


@RestController
@RequestMapping("/certificates")    
public class CerfiticateController {
    
    private final CertificateService certificateService;

    public CerfiticateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        List<Certificate> certificates = certificateService.getAllCertificates();
        return ResponseEntity.ok(certificates);
    }

}
