package model.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testEntity.*;
import model.OTD.StudentModelDTO;
import model.OTD.SubjectModelDTO;
import model.OTD.TeacherModelDTO;
import model.entity.AddressRepository;
import model.entity.StudentModel;
import model.entity.StudentRepository;
import model.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/demo")
public class MainController {
//    @Autowired
//    private StudentModelRepository studentRepository;
//    @Autowired
//    private TeacherModelRepository teacherRepository;
//    @Autowired
//    private SubjectModelRepository subjectRepository;
//
//    @PostMapping(path = "/addTeacher") // Map ONLY POST Requests
//    public @ResponseBody
//    String addNewTeacher(@RequestBody String jsonObj) {
//        TeacherModelDTO modelDTO = new TeacherModelDTO();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            modelDTO = objectMapper.readValue(jsonObj, TeacherModelDTO.class);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        Teacher n = new Teacher();
//        n.setFirst_name(modelDTO.getFirst_name());
//        n.setLast_name(modelDTO.getLast_name());
//        n.setBranch(modelDTO.getBranch());
//        teacherRepository.save(n);
//        return "Saved";
//    }
//
//    @PostMapping(path = "/addStudent") // Map ONLY POST Requests
//    public @ResponseBody
//    String addNewStudent(@RequestBody String jsonObj) {
//        StudentModelDTO modelDTO = new StudentModelDTO();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            modelDTO = objectMapper.readValue(jsonObj, StudentModelDTO.class);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        Student studentModel = new Student();
//        studentModel.setFirst_name(modelDTO.getFirst_name());
//        studentModel.setLast_name(modelDTO.getLast_name());
//        studentModel.setYear(modelDTO.getYear());
//        studentRepository.save(studentModel);
//        return "Saved";
//    }
//
//    @PostMapping(path = "/addSubject") // Map ONLY POST Requests
//    public @ResponseBody
//    String addNewSubject(@RequestBody String jsonObj) {
//        SubjectModelDTO modelDTO = new SubjectModelDTO();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        try {
//            modelDTO = objectMapper.readValue(jsonObj, SubjectModelDTO.class);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        Subject subjectModel = new Subject();
//        subjectModel.setSubject_name(modelDTO.getSubject_name());
//        subjectModel.setSubject_id(modelDTO.getSubject_id());
//        subjectModel.setStart_date(modelDTO.getStart_date());
//        subjectModel.setEnd_date(modelDTO.getEnd_date());
//        subjectModel.setDay(modelDTO.getDay());
//        return "Saved";
//    }
//
//    @GetMapping(path = "/allTeacher")
//    public @ResponseBody
//    Iterable<Teacher> getAllUsers() {
//        return teacherRepository.findAll();
//    }
//
//    @GetMapping(path = "/allSubject")
//    public @ResponseBody
//    Iterable<Subject> getAllSubject() {
//        return subjectRepository.findAll();
//    }
}