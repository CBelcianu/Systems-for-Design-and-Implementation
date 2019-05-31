package ro.ubb.labproblems.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.labproblems.core.model.Student;
import ro.ubb.labproblems.core.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger log =
            LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        log.warn("getAllStudents --- method entered");

        List<Student> result = studentRepository.findAll();

        log.warn("getAllStudents: result={}", result);

        return result;
    }

    @Override
    public Student saveStudent(Student student) {
        log.warn("-----saveStudent------ student={}", student);

        Student savedStudent = this.studentRepository.save(student);

        log.warn("saveStudent: methon finished");

        return savedStudent;
    }

    @Override
    @Transactional
    public Student update(Long id, Student student) {
        log.warn("updateStudent: id={},student={}", id, student);

        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student result = optionalStudent.orElse(student);
        result.setSname(student.getSname());
        result.setSerialNumber(student.getSerialNumber());
        result.setSgroup(student.getSgroup());

        log.warn("updateStudent: result={}", result);

        return result;
    }

    @Override
    public void delete(Long id) {
        log.warn("deleteStudent: id={}", id);

        studentRepository.deleteById(id);

        log.warn("deleteStudent --- method finished");
    }


}

