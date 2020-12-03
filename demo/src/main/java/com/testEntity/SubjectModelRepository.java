package com.testEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectModelRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findById(Long id);
}
