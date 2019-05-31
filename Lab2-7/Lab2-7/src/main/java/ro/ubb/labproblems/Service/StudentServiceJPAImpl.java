package ro.ubb.labproblems.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.labproblems.Domain.Student;
import ro.ubb.labproblems.Repository.StudentJPARepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceJPAImpl implements StudentServiceJPA {
    private static final Logger log = LoggerFactory.getLogger(
            StudentServiceJPAImpl.class);

    @Autowired
    private StudentJPARepository studentRepository;

    @Override
    public List<Student> getAllStudents() {

        log.warn("getAllStudents --- method entered");

        List<Student> result = studentRepository.findAll();

        log.warn("getAllStudent: result={}", result);

        return result;

    }

    @Override
    public void saveStudent(Student student) {
        log.warn("saveStudent: student={}", student);

        studentRepository.save(student);

        log.warn("saveStuent--- method finished");
    }

    @Override
    @Transactional
    public void update(Student student) {
        log.warn("update: student={}", student);

        studentRepository.findById(student.getId())
                .ifPresent(student1 -> {
                    student1.setSerialNumber(student.getSerialNumber());
                    student1.setName(student.getName());
                    student1.setGroup(student.getGroup());
                    log.debug("update --- student updated? --- " +
                            "student={}", student1);
                });

        log.warn("update --- method finished");
    }


    @Override
    public void delete(Long id) {
        log.warn("delete: id={}", id);

        studentRepository.deleteById(id);

        log.warn("delete --- method finished");

    }
}
