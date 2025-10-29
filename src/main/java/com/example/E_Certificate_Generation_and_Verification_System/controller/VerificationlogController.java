package com.example.E_Certificate_Generation_and_Verification_System.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Verificationlog;
import com.example.E_Certificate_Generation_and_Verification_System.service.VerificationlogService;

@RestController
@RequestMapping("/verificationlogs")
public class VerificationlogController {
    
    @Autowired
    private VerificationlogService verificationlogService;

    @GetMapping
    public List<Verificationlog> getAllVerificationlogs() {
        return verificationlogService.getAllVerificationlogs();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Verificationlog> getVerificationlogById(@PathVariable Long id)    
    {
        Optional<Verificationlog> verificationlog = verificationlogService.getVerificationlogById(id);
        return verificationlog.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // JSON API: accept application/json
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Verificationlog createVerificationlog(@RequestBody Verificationlog verificationlog) {
        return verificationlogService.createVerificationlog(verificationlog);
    }

    // Form submission: redirect to html list
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createVerificationlogForm(Verificationlog verificationlog) {
        verificationlogService.createVerificationlog(verificationlog);
        return "redirect:/verificationlog/list";
    }

    

}
