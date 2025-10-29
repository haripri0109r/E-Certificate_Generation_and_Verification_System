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

    public Optional<Certificate> findByUniqueCode(String code) {
        return certificateRepository.findByUniqueCode(code);
    }

    public Certificate updateCertificate(Long id, Certificate certificateDetails) {
        return certificateRepository.findById(id).map(c -> {
            c.setUserId(certificateDetails.getUserId());
            c.setUserName(certificateDetails.getUserName());
            c.setUnique_code(certificateDetails.getUnique_code());
            c.setResult(certificateDetails.getResult());
            return certificateRepository.save(c);
        }).orElseGet(() -> {
            // create new if not found
            Certificate newC = new Certificate();
            newC.setUserId(certificateDetails.getUserId());
            newC.setUserName(certificateDetails.getUserName());
            newC.setUnique_code(certificateDetails.getUnique_code());
            newC.setResult(certificateDetails.getResult());
            return certificateRepository.save(newC);
        });
    }

    public void deleteCertificate(Long id) {
        if (certificateRepository.existsById(id)) {
            certificateRepository.deleteById(id);
        }
    }

}
