package com.example.E_Certificate_Generation_and_Verification_System.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Certificate;


public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    
}
