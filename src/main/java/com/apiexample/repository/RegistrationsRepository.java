package com.apiexample.repository;

import com.apiexample.entity.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationsRepository extends JpaRepository<Registrations, Long> {
}