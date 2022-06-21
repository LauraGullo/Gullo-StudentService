package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student estudiante = studentService.createStudent(student);
        return new ResponseEntity<Student>(estudiante, HttpStatus.CREATED);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneById(@PathVariable("id") Long id) {
            Optional<Student> student = studentService.findById(id);
            return ResponseEntity.ok(student);
     }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
           studentService.deleteStudent(id);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Student student)  {
            studentService.update(id, student);
    }

    @GetMapping("/edadPromedio")
    public int edadPromedio(){
        return studentService.edadPromedio();
    }

    @GetMapping("/mayorEdad")
    public int mayorEdad(){
        return studentService.mayorEdad();
    }

    @GetMapping("/menorEdad")
    public int menorEdad(){
        return studentService.menorEdad();
    }

    @GetMapping("/mayores")
    public List<Student> mayoresDeEdad(){
        return studentService.mayoresDeEdad();
    }

    @GetMapping("/menores")
    public List<Student> menoresDeEdad(){
        return studentService.menoresDeEdad();
    }

    @GetMapping("/PromedioMayores")
    public int edadPromedioMayores(){

        return studentService.edadPromedioMayores();
    }
    @GetMapping("/PromedioMenores")
    public int edadPromedioMenores(){

        return studentService.edadPromedioMenores();
    }

    @GetMapping("/getNombres")
    public String getNameSurnameId(){
        return studentService.getNameSurnameId();
    }



}
