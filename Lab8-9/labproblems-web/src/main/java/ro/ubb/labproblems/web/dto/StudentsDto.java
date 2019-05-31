package ro.ubb.labproblems.web.dto;
import lombok.*;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StudentsDto {
    private Set<StudentDto> students;


}

