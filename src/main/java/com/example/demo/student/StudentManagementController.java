package com.example.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private  static  final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Sachwini"),
            new Student(2, "james"),
            new Student(3,"annasmith"),
            new Student(4,"linda"),
            new Student(5,"tom")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public  List<Student> getAllStudents(){
        System.out.println("getAllStudents");
        return  STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public  void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public  void updateStudent(@RequestBody Student student ,@PathVariable("studentId") Integer studentId){
        System.out.println("updateStudent");
        System.out.println(String.format("%s %s",studentId, student));
    }
}
