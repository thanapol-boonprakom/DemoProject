package com.testEntity;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Students_SubjectsRepository extends CrudRepository<Students_Subjects, Long> {
    long countAllByStudent_Id(Long id);
    Optional<Students_Subjects> findById(Long id);
    List<Students_Subjects> findAllByOrderByIdAsc();
    Iterable<Students_Subjects> findAllByStudent_Id(Long id);

}
