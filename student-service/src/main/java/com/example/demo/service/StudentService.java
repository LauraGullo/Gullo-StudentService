package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    @Transactional
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    @Transactional
    public Optional<Student> findById(Long id)  {
        return studentRepository.findById(id);
    }
    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student update(Long id, Student student) {
        Optional<Student> studentOld = studentRepository.findById(id);
        Student studentNew= studentOld.get();
        return studentNew = studentRepository.save(student);
    }

    public int edadPromedio(){
        List<Student> studentList = studentRepository.findAll();
        return (int)studentList.stream().mapToInt(s -> Period.between(s.getBirthday(), LocalDate.now()).getYears()).average().orElseThrow();
    }
    public int mayorEdad(){
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().mapToInt(s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()).max().orElse(0);

    }
    public int menorEdad(){
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().mapToInt(s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()).min().orElse(0);
    }

    public List<Student> mayoresDeEdad(){
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().filter( s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()>= 18)
                .collect(Collectors.toList());
    }

    public List<Student> menoresDeEdad(){
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream().filter( s -> Period.between(s.getBirthday(),LocalDate.now()).getYears()<18)
                .collect(Collectors.toList());
    }


    public int edadPromedioMayores(){
        List<Student> studentList = studentRepository.findAll();
        return (int)studentList.stream()
                .mapToInt(s -> Period.between(s.getBirthday(), LocalDate.now()).getYears())
                .filter(s->s>=18)
                .average().orElseThrow();
    }

    public int edadPromedioMenores(){
        List<Student> studentList = studentRepository.findAll();
        return (int)studentList.stream()
                .mapToInt(s -> Period.between(s.getBirthday(), LocalDate.now()).getYears())
                .filter(s->s<18)
                .average().orElseThrow();
    }

    public String getNameSurnameId(){
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream()
                .map(s->s.getId() + " "+ s.getSurname() + " " + s.getName())
                .collect(Collectors.joining(" - "));
    }
}










