package ro.ubb.labproblems.core.service;

import ro.ubb.labproblems.core.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);


    void delete(Long id);

    Student update(Long id, Student student);
}