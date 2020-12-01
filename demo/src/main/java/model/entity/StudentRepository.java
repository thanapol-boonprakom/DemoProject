package model.entity;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentModel, Integer> {
}
