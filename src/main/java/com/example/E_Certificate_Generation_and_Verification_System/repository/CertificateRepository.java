package com.example.E_Certificate_Generation_and_Verification_System.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.E_Certificate_Generation_and_Verification_System.entity.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	@Query("SELECT c FROM Certificate c WHERE c.unique_code = :code")
	Optional<Certificate> findByUniqueCode(@Param("code") String code);

}
