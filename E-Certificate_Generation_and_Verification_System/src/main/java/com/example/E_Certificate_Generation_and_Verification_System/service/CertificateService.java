package com.example.E_Certificate_Generation_and_Verification_System.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Certificate;
import com.example.E_Certificate_Generation_and_Verification_System.repository.CertificateRepository;

@Service
public class CertificateService {
     @Autowired
        private CertificateRepository certificateRepository;

    public Certificate createCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public Optional<Certificate> getCertificateById(Long id) {
        return certificateRepository.findById(id);
    }

}
