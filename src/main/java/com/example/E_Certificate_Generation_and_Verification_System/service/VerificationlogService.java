package com.example.E_Certificate_Generation_and_Verification_System.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Verificationlog; 
import com.example.E_Certificate_Generation_and_Verification_System.repository.VerificationlogRepository;

@Service
public class VerificationlogService {
    
    @Autowired
    private VerificationlogRepository verificationlogRepository;

    public Verificationlog createVerificationlog(Verificationlog verificationlog) {
        return verificationlogRepository.save(verificationlog);
    }

    public List<Verificationlog> getAllVerificationlogs() {
        return verificationlogRepository.findAll();
    }

    public Optional<Verificationlog> getVerificationlogById(Long id) {
        return verificationlogRepository.findById(id);
    }


}
