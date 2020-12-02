package com.testEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeacherModelRepository extends JpaRepository<Teacher, Long> {
}
