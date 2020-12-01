package model.entity;

import org.springframework.data.repository.CrudRepository;
import model.entity.SubjectModel;

public interface SubjectRepository extends CrudRepository<SubjectModel, Long> {
}
