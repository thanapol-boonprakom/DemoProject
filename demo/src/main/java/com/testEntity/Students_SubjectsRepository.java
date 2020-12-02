package com.testEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public interface Students_SubjectsRepository extends CrudRepository<Students_Subjects, Long> {
    long countAllByStudent_Id(Long id);
}
