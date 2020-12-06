package com.example.springbootProject.repository;

import com.example.springbootProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentModelRepository extends JpaRepository<Student, Long> {
}
