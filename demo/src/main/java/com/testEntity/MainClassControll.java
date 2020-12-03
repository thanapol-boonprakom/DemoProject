package com.testEntity;

import com.example.demo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class MainClassControll {
    public static void main(String[] args) {
        SpringApplication.run(MainClassControll.class, args);
    }

    @Bean
    public CommandLineRunner mappingDemo(TeacherModelRepository teacherModelRepository, SubjectModelRepository subjectModelRepository, StudentModelRepository studentModelRepository) {
        return args -> {
//            Teacher teacher1 = new Teacher("สมชาย", "หมายปอง", "ไทย");
//            Teacher teacher2 = new Teacher("สมทอง", "หมายปอง", "ไทย");
//            Teacher teacher3 = new Teacher("สมยศ", "หมายปอง", "ไทย");
//            Teacher teacher4 = new Teacher("สมหมาย", "หมายปอง", "ไทย");
//            Teacher teacher5 = new Teacher("สมยอม", "หมายปอง", "ไทย");
//            Teacher teacher6 = new Teacher("สมพร้อม", "หมายปอง", "ไทย");
//            Teacher teacher7 = new Teacher("สมน้ำหน้า", "หมายปอง", "ไทย");
//            Teacher teacher8 = new Teacher("สมบุญ", "หมายปอง", "ไทย");
//            Teacher teacher9 = new Teacher("สมกัน", "หมายปอง", "ไทย");
//            Teacher teacher10 = new Teacher("สมน้ำหน้า", "หมายปอง", "ไทย");
//            Teacher teacher11 = new Teacher("สมบุญ", "หมายปอง", "ไทย");
//            Teacher teacher12 = new Teacher("สมกัน", "หมายปอง", "ไทย");
//
//            Subject subject1 = new Subject(teacher1, "10001", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("07:00:00"), LocalTime.parse("07:59:00"), "จันทร์");
//            Subject subject2 = new Subject(teacher2, "10002", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("08:00:00"), LocalTime.parse("08:59:00"), "จันทร์");
//            Subject subject3 = new Subject(teacher3, "10003", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("09:00:00"), LocalTime.parse("09:59:00"), "จันทร์");
//            Subject subject4 = new Subject(teacher4, "10004", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("10:00:00"), LocalTime.parse("10:59:00"), "จันทร์");
//            Subject subject5 = new Subject(teacher5, "10005", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("11:00:00"), LocalTime.parse("11:59:00"), "จันทร์");
//            Subject subject6 = new Subject(teacher6, "10006", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("12:00:00"), LocalTime.parse("12:59:00"), "จันทร์");
//            Subject subject7 = new Subject(teacher7, "10007", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("13:00:00"), LocalTime.parse("13:59:00"), "จันทร์");
//            Subject subject8 = new Subject(teacher8, "10008", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("14:00:00"), LocalTime.parse("14:59:00"), "จันทร์");
//            Subject subject9 = new Subject(teacher9, "10009", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("15:00:00"), LocalTime.parse("15:59:00"), "จันทร์");
//            Subject subject10 = new Subject(teacher10, "10010", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("07:00:00"), LocalTime.parse("07:59:00"), "จันทร์");
//            Subject subject11 = new Subject(teacher11, "10011", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("08:00:00"), LocalTime.parse("08:59:00"), "อังคาร");
//            Subject subject12 = new Subject(teacher12, "10012", "ไทย", "3.0", "ลงได้ทุกคน", LocalTime.parse("09:00:00"), LocalTime.parse("09:59:00"), "อังคาร");
//
//            Student student1 = new Student("ธนพล", "บุญประคม");
//            Student student2 = new Student("กฤษณะ", "ฐานวิเศษ");
//            Student student3 = new Student("สุระสิทธฺ", "สุวรรณระ");
////            LocalTime date;
////            date = LocalTime.now();
////            System.out.println(date.toString());
//            teacherModelRepository.saveAll(Arrays.asList(teacher1, teacher2, teacher3, teacher4, teacher5, teacher6, teacher7, teacher8, teacher9, teacher10, teacher11, teacher12));
//            studentModelRepository.saveAll(Arrays.asList(student1, student2, student3));
//            subjectModelRepository.saveAll(Arrays.asList(subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8, subject9, subject10, subject11, subject12));

        };
    }

}
