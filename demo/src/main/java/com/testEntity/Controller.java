package com.testEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.OTD.StudentModelDTO;
import model.OTD.SubjectModelDTO;
import model.OTD.Subject_StudentDTO;
import model.OTD.TeacherModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping(path = "/demoProject")
public class Controller {
    @Autowired
    private StudentModelRepository studentRepository;
    @Autowired
    private TeacherModelRepository teacherRepository;
    @Autowired
    private SubjectModelRepository subjectRepository;
    @Autowired
    private Students_SubjectsRepository studentsSubjectsRepository;


    @PostMapping(path = "/addTeacher") // Map ONLY POST Requests
    public @ResponseBody
    String addNewTeacher(@RequestBody String jsonObj) {
        TeacherModelDTO modelDTO = new TeacherModelDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            modelDTO = objectMapper.readValue(jsonObj, TeacherModelDTO.class);
        } catch (Exception e) {
            System.out.println(e);
        }

        Teacher n = new Teacher();
        n.setFirst_name(modelDTO.getFirst_name());
        n.setLast_name(modelDTO.getLast_name());
        n.setBranch(modelDTO.getBranch());
        teacherRepository.save(n);
        return "Saved";
    }

    @PostMapping(path = "/addStudent") // Map ONLY POST Requests
    public @ResponseBody
    String addNewStudent(@RequestBody String jsonObj) {
        StudentModelDTO modelDTO = new StudentModelDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            modelDTO = objectMapper.readValue(jsonObj, StudentModelDTO.class);
        } catch (Exception e) {
            System.out.println(e);
        }

        Student studentModel = new Student();
        studentModel.setFirst_name(modelDTO.getFirst_name());
        studentModel.setLast_name(modelDTO.getLast_name());
        studentModel.setYear(modelDTO.getYear());
        studentRepository.save(studentModel);
        return "Saved";
    }

    @PostMapping(path = "/addSubject") // Map ONLY POST Requests
    public @ResponseBody
    String addNewSubject(@RequestBody String jsonObj) {
        SubjectModelDTO modelDTO = new SubjectModelDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            modelDTO = objectMapper.readValue(jsonObj, SubjectModelDTO.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        Teacher teacher = new Teacher(modelDTO.getTeacher_id());

        Subject subjectModel = new Subject(teacher, modelDTO.getSubject_id(), modelDTO.getSubject_name(), modelDTO.getSubject_credit(), modelDTO.getSubject_detail(), modelDTO.getStart_date(), modelDTO.getEnd_date(), modelDTO.getDay());
        subjectRepository.save(subjectModel);
        return "Saved";
    }

    @PostMapping(path = "/addCourse") // Map ONLY POST Requests
    public @ResponseBody
    String addNewCourse(@RequestBody String jsonObj) {
        Subject_StudentDTO modelDTO = new Subject_StudentDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            modelDTO = objectMapper.readValue(jsonObj, Subject_StudentDTO.class);
        } catch (Exception e) {
            System.out.println(e);
        }
        Students_Subjects studentsSubjects = new Students_Subjects();
        Student student = new Student(modelDTO.getStudent_id());
        Subject subject = new Subject(modelDTO.getSubject_id());
        studentsSubjects.setStudent(student);
        studentsSubjects.setSubject(subject);
        studentsSubjectsRepository.save(studentsSubjects);
        return "Saved";
    }

    @GetMapping(path = "/allTeacher")
    public @ResponseBody
    Iterable<Teacher> getAllUsers() {
        return teacherRepository.findAll();
    }

    @GetMapping(path = "/allSubject")
    public @ResponseBody
    Iterable<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @GetMapping(path = "/allStudent")
    public @ResponseBody
    Iterable<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
