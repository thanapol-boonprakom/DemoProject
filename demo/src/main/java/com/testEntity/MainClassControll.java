package com.testEntity;

import com.example.demo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainClassControll {
    public static void main(String[] args) {
        SpringApplication.run(MainClassControll.class, args);
    }

    @Bean
    public CommandLineRunner mappingDemo(TeacherModelRepository teacherModelRepository,SubjectModelRepository subjectModelRepository) {
        return args -> {

        };
    }
}
