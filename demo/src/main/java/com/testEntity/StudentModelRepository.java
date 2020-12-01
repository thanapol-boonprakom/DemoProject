package com.testEntity;

import org.springframework.data.repository.CrudRepository;

public interface StudentModelRepository extends CrudRepository<Student, Long> {
}
