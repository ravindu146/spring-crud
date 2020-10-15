package com.example.interview.controller;

import com.example.interview.models.Error;
import com.example.interview.models.Student;
import com.example.interview.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v0/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("lists")
    public ResponseEntity<Iterable<Student>>  getAllStudent() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK) ;
    }

    @PostMapping("create")
    public ResponseEntity<Student> createStudent(@Validated @RequestBody Student student) {

        Student saveStudent = studentService.save(student);

        return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateStudent(@Validated @RequestBody Student student) {
        if(studentService.findById(student.getId()).isPresent()) {
            Student saveStudent = studentService.save(student);

            return new ResponseEntity<>(saveStudent, HttpStatus.OK);
        }
        Error err = new Error("error");
        return  new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("list/{id}")
    public ResponseEntity<Object>  getStudentById(@PathVariable Integer id) {
        Optional<Student> byId = studentService.findById(id);
        if(byId.isPresent()) {
            return new ResponseEntity<>(byId, HttpStatus.OK);
        }
        Error err = new Error("error");
        return  new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteById(id);
    }

}
