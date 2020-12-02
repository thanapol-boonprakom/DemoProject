package com.testEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.OTD.StudentModelDTO;
import model.OTD.SubjectModelDTO;
import model.OTD.Subject_StudentDTO;
import model.OTD.TeacherModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        if ((int) studentsSubjectsRepository.countAllByStudent_Id(modelDTO.getStudent_id()) < 8) {
            Students_Subjects studentsSubjects = new Students_Subjects();
            Student student = new Student(modelDTO.getStudent_id());
            Subject subject = new Subject(modelDTO.getSubject_id());
            studentsSubjects.setStudent(student);
            studentsSubjects.setSubject(subject);
            studentsSubjectsRepository.save(studentsSubjects);
            return "Saved " + studentsSubjectsRepository.countAllByStudent_Id(modelDTO.getStudent_id());
        } else {
            return "Cannot Save " + studentsSubjectsRepository.countAllByStudent_Id(modelDTO.getStudent_id());
        }

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

    @GetMapping(path = "/allCourse")
    public @ResponseBody
    Iterable<Students_Subjects> getAllCourse() {
        return studentsSubjectsRepository.findAll();
    }

    @GetMapping(path = "/allStudent")
    public @ResponseBody
    Iterable<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/allByIdStudent")
    public @ResponseBody
    String getAllById(@RequestParam String id) {
//        studentsSubjectsRepository.countAllById(Long.parseLong(id));
        return "count : " + studentsSubjectsRepository.countAllByStudent_Id(Long.parseLong(id));
    }
//    @GetMapping(path = "/countStartAndEnd")
//    public @ResponseBody
//    String countStartAndEnd(@RequestParam String start,@RequestParam String end) {
////        studentsSubjectsRepository.countAllById(Long.parseLong(id));
//        return "count : " + studentsSubjectsRepository.countAllBySubject_Start_dateAndSubject_End_date(start,end);
//    }

}
