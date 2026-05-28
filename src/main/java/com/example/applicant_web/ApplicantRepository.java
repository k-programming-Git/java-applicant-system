package com.example.applicant_web;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository
        extends JpaRepository<Applicant, Long> {

}