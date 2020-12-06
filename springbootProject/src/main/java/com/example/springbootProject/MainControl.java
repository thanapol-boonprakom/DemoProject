package com.example.springbootProject;

import com.example.springbootProject.DTO.StudentModelDTO;
import com.example.springbootProject.DTO.SubjectModelDTO;
import com.example.springbootProject.DTO.Subject_StudentDTO;
import com.example.springbootProject.DTO.TeacherModelDTO;
import com.example.springbootProject.entity.*;
import com.example.springbootProject.repository.StudentModelRepository;
import com.example.springbootProject.repository.Students_SubjectsRepository;
import com.example.springbootProject.repository.SubjectModelRepository;
import com.example.springbootProject.repository.TeacherModelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@org.springframework.stereotype.Controller
@RequestMapping(path = "/demoProject")
public class MainControl {
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
            Teacher n = new Teacher();
            n.setFirst_name(modelDTO.getFirst_name());
            n.setLast_name(modelDTO.getLast_name());
            n.setBranch(modelDTO.getBranch());
            teacherRepository.save(n);
            return "Saved";
        } catch (Exception e) {
            System.out.println(e);
            return "notSaved";
        }


    }

    @PostMapping(path = "/addStudent")
    public @ResponseBody
    String addNewStudent(@RequestBody String jsonObj) {
        StudentModelDTO modelDTO = new StudentModelDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            modelDTO = objectMapper.readValue(jsonObj, StudentModelDTO.class);
            Student studentModel = new Student();
            studentModel.setFirst_name(modelDTO.getFirst_name());
            studentModel.setLast_name(modelDTO.getLast_name());
            studentModel.setYear(modelDTO.getYear());
            studentRepository.save(studentModel);
            return "Saved";
        } catch (Exception e) {
            System.out.println(e);
            return "not saved";
        }
    }

    @PostMapping(path = "/addSubject")
    String addNewSubject(@RequestBody String jsonObj) {
        SubjectModelDTO modelDTO = new SubjectModelDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            modelDTO = objectMapper.readValue(jsonObj, SubjectModelDTO.class);
            Teacher teacher = new Teacher(modelDTO.getTeacher_id());
            Subject subjectModel = new Subject(teacher, modelDTO.getSubject_id(), modelDTO.getSubject_name(), modelDTO.getSubject_credit(), modelDTO.getSubject_detail(), LocalTime.parse(modelDTO.getStart_date()), LocalTime.parse(modelDTO.getEnd_date()), modelDTO.getDay());
            subjectRepository.save(subjectModel);
            return "Saved";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }

    }

    @PostMapping(path = "/addCourse")
    public @ResponseBody
    String addNewCourse(@RequestBody String jsonObj) {
        Subject_StudentDTO modelDTO = new Subject_StudentDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        Iterable<Students_Subjects> students_subjectsIterable = new ArrayList<>();
        Iterator<Students_Subjects> subjectsIterator = students_subjectsIterable.iterator();
        Optional<Subject> optionalStudentsSubjects = null;
        LocalTime startDt = LocalTime.now();
        LocalTime endDt = LocalTime.now();
        try {
            modelDTO = objectMapper.readValue(jsonObj, Subject_StudentDTO.class);
            students_subjectsIterable = studentsSubjectsRepository.findAllByStudent_Id(modelDTO.getStudent_id());
            boolean check = false;
            if ((int) studentsSubjectsRepository.countAllByStudent_Id(modelDTO.getStudent_id()) < 8) {
                optionalStudentsSubjects = subjectRepository.findById(modelDTO.getSubject_id());
                startDt = optionalStudentsSubjects.get().getStart_date();
                endDt = optionalStudentsSubjects.get().getEnd_date();
                for (Students_Subjects str : students_subjectsIterable) {
                    if ((((str.getSubject().getStart_date().isAfter(startDt) || str.getSubject().getStart_date().equals(startDt)) && (str.getSubject().getStart_date().isBefore(endDt) || str.getSubject().getStart_date().equals(endDt))) ||
                            ((str.getSubject().getEnd_date().isAfter(startDt) || str.getSubject().getEnd_date().equals(startDt)) && (str.getSubject().getEnd_date().isBefore(endDt) || str.getSubject().getEnd_date().equals(endDt)))) &&
                            (str.getSubject().getDay()).equalsIgnoreCase(optionalStudentsSubjects.get().getDay())) {
                        check = true;
                        break;
                    }
                }

                if (check == false) {
                    Students_Subjects studentsSubjects = new Students_Subjects();
                    Student student = new Student(modelDTO.getStudent_id());
                    Subject subject = new Subject(modelDTO.getSubject_id());
                    studentsSubjects.setStudent(student);
                    studentsSubjects.setSubject(subject);
                    studentsSubjectsRepository.save(studentsSubjects);
                    return "Saved";
                } else {
                    return "Cannot Save Duplicate time";
                }

            } else {
                return "Cannot register no more than 8 subjects";
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return "error";
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

    @GetMapping(path = "/exportToExcel")
    public @ResponseBody
    void exportToExel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Students_Subjects> listUsers = studentsSubjectsRepository.findAllByOrderByIdAsc();

        Student_SubjectExelExporter excelExporter = new Student_SubjectExelExporter(listUsers);

        excelExporter.export(response);
    }

}
