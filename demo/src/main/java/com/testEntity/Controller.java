package com.testEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.OTD.StudentModelDTO;
import model.OTD.SubjectModelDTO;
import model.OTD.Subject_StudentDTO;
import model.OTD.TeacherModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

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
            Teacher n = new Teacher();
            n.setFirst_name(modelDTO.getFirst_name());
            n.setLast_name(modelDTO.getLast_name());
            n.setBranch(modelDTO.getBranch());
            System.out.println(n.getFirst_name() + " " + n.getLast_name() + " " + n.getBranch());
            teacherRepository.save(n);
            return "Saved";
        } catch (Exception e) {
            System.out.println(e);
            return "notSaved";
        }


    }

    @PostMapping(path = "/addStudent") // Map ONLY POST Requests
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

    @PostMapping(path = "/addSubject") // Map ONLY POST Requests
    public @ResponseBody
    String addNewSubject(@RequestBody String jsonObj) {
        SubjectModelDTO modelDTO = new SubjectModelDTO();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            modelDTO = objectMapper.readValue(jsonObj, SubjectModelDTO.class);
            Teacher teacher = new Teacher(modelDTO.getTeacher_id());

            Subject subjectModel = new Subject(teacher, modelDTO.getSubject_id(), modelDTO.getSubject_name(), modelDTO.getSubject_credit(), modelDTO.getSubject_detail(), LocalTime.parse(modelDTO.getStart_date()), LocalTime.parse(modelDTO.getEnd_date()), modelDTO.getDay());
            System.out.println(subjectModel.toString());
            subjectRepository.save(subjectModel);
            return "Saved";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }

    }

    @PostMapping(path = "/addCourse") // Map ONLY POST Requests
    public @ResponseBody
    String addNewCourse(@RequestBody String jsonObj) {
        Subject_StudentDTO modelDTO = new Subject_StudentDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        Iterable<Students_Subjects> students_subjectsIterable = new ArrayList<>();
        Iterator<Students_Subjects> subjectsIterator = students_subjectsIterable.iterator();
        Optional<Subject> optionalStudentsSubjects = null;
        LocalTime startDt = LocalTime.now();
        LocalTime endDt = LocalTime.now();
//        System.out.println(optionalStudentsSubjects.get().getSubject().getSubject_id());
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
                        System.out.println("find time " + str.getSubject().getId());
                        System.out.println("Is time fine " + startDt + " <>" + endDt + " ** "
                                + str.getSubject().getStart_date().getHour() + ":" + str.getSubject().getStart_date().getMinute() + " <> " + str.getSubject().getEnd_date().getHour() + ":" + str.getSubject().getEnd_date().getMinute());
                        check = true;
                        break;
                    }
                    System.out.println(" >>>(" + str.getSubject().getStart_date().isAfter(startDt) +
                            "+" + str.getSubject().getStart_date().equals(startDt) +
                            "+" + str.getSubject().getStart_date().isBefore(endDt) +
                            "+" + str.getSubject().getStart_date().equals(endDt) +
                            ")+(" + ((str.getSubject().getEnd_date().isAfter(startDt)) +
                            "+" + str.getSubject().getEnd_date().equals(startDt) +
                            "+" + (str.getSubject().getEnd_date().isBefore(endDt) +
                            "+" + str.getSubject().getEnd_date().equals(endDt) + ") && (" + (str.getSubject().getDay()).equalsIgnoreCase(optionalStudentsSubjects.get().getDay()) + ") && (" + str.getSubject().getDay() + " | " + optionalStudentsSubjects.get().getDay())));
                    System.out.println(startDt + " <>" + endDt + " ** "
                            + str.getSubject().getStart_date().getHour() + ":" + str.getSubject().getStart_date().getMinute() + " <> " + str.getSubject().getEnd_date().getHour() + ":" + str.getSubject().getEnd_date().getMinute());
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

    @GetMapping(path = "/allByIdStudent")
    public @ResponseBody
    String getAllById(@RequestParam String id) {
//        studentsSubjectsRepository.countAllById(Long.parseLong(id));
        return "count : " + studentsSubjectsRepository.countAllByStudent_Id(Long.parseLong(id));
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

//    @GetMapping(path = "/countStartAndEnd")
//    public @ResponseBody
//    String countStartAndEnd(@RequestParam String start, @RequestParam String end, @RequestParam Long id) {
//        LocalTime startDt = LocalTime.parse(start);
//        LocalTime endDt = LocalTime.parse(end);
//        Iterable<Students_Subjects> studentsSubjects = new ArrayList<>();
//        Optional<Students_Subjects> optionalStudentsSubjects;
//        studentsSubjects = studentsSubjectsRepository.findAll();
//        Iterator<Students_Subjects> subjectsIterator = studentsSubjects.iterator();
//        Students_Subjects obj = new Students_Subjects();
//        optionalStudentsSubjects = studentsSubjectsRepository.findById(id);
//        System.out.println(optionalStudentsSubjects.get().getSubject().getSubject_id());
//        startDt = optionalStudentsSubjects.get().getSubject().getStart_date();
//        endDt = optionalStudentsSubjects.get().getSubject().getEnd_date();
//        boolean check = false;
////        for (Students_Subjects str : studentsSubjects) {
////
////            if ((((str.getSubject().getStart_date().isAfter(startDt) || str.getSubject().getStart_date().equals(startDt)) && (str.getSubject().getStart_date().isBefore(endDt) || str.getSubject().getStart_date().equals(endDt))) ||
////                    ((str.getSubject().getEnd_date().isAfter(startDt) || str.getSubject().getEnd_date().equals(startDt)) && (str.getSubject().getEnd_date().isBefore(endDt) || str.getSubject().getEnd_date().equals(endDt)))) &&
////                    (str.getSubject().getDay()).equalsIgnoreCase(optionalStudentsSubjects.get().getSubject().getDay())) {
////                System.out.println("xxxx");
////                check = true;
////            }
////            System.out.println(" >>>(" + str.getSubject().getStart_date().isAfter(startDt) +
////                    "+" + str.getSubject().getStart_date().equals(startDt) +
////                    "+" + str.getSubject().getStart_date().isBefore(endDt) +
////                    "+" + str.getSubject().getStart_date().equals(endDt) +
////                    ")+(" + ((str.getSubject().getEnd_date().isAfter(startDt)) +
////                    "+" + str.getSubject().getEnd_date().equals(startDt) +
////                    "+" + (str.getSubject().getEnd_date().isBefore(endDt) +
////                    "+" + str.getSubject().getEnd_date().equals(endDt) + ") && (" + (str.getSubject().getDay()).equalsIgnoreCase(optionalStudentsSubjects.get().getSubject().getDay()) + ")")));
////            System.out.println(startDt + " <>" + endDt + " ** "
////                    + str.getSubject().getStart_date().getHour() + ":" + str.getSubject().getStart_date().getMinute() + " <> " + str.getSubject().getEnd_date().getHour() + ":" + str.getSubject().getEnd_date().getMinute());
////        }
////        if
////        while(subjectsIterator.hasNext()) {
////            String element = subjectsIterator.next().getStudent().getFirst_name();
////            System.out.println( element +" <<");
////        }
////        studentsSubjectsRepository.countAllById(Long.parseLong(id));
//        return "count : ";
//    }

}
