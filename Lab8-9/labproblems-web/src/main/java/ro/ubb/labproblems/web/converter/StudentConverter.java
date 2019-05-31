package ro.ubb.labproblems.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.labproblems.core.model.Student;
import ro.ubb.labproblems.web.dto.StudentDto;
import ro.ubb.labproblems.web.dto.StudentsDto;

/**
 * author: radu
 */
@Component
public class StudentConverter extends BaseConverter<Student, StudentDto> {
    @Override
    public Student convertDtoToModel(StudentDto dto) {
        Student student = Student.builder()
                .serialNumber(dto.getSerialNumber())
                .sname(dto.getSname())
                .sgroup(dto.getSgroup())
                .build();
        student.setId(dto.getId());
        return student;
    }

    @Override
    public StudentDto convertModelToDto(Student student) {
        StudentDto dto = StudentDto.builder()
                .serialNumber(student.getSerialNumber())
                .sname(student.getSname())
                .sgroup(student.getSgroup())
                .build();
        dto.setId(student.getId());
        return dto;
    }
}
