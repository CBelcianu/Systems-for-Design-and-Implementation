package ro.ubb.labproblems.Service;

import ro.ubb.labproblems.Domain.Student;

import java.util.List;

public interface StudentServiceJPA {
    List<Student> getAllStudents();

    void saveStudent(Student student);


    void delete(Long id);

    void update(Student student);
}
