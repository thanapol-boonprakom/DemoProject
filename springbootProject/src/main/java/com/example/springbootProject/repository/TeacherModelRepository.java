package com.example.springbootProject.repository;

import com.example.springbootProject.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherModelRepository extends JpaRepository<Teacher, Long> {
}
