package ro.ubb.labproblems.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.labproblems.core.model.LabProblem;
import ro.ubb.labproblems.web.dto.LabProblemDto;

@Component
public class LabProblemConverter extends BaseConverter<LabProblem, LabProblemDto> {

    @Override
    public LabProblem convertDtoToModel(LabProblemDto dto) {
        LabProblem problem = LabProblem.builder()
                .problemStatement(dto.getProblemStatement())
                .teacher(dto.getTeacher())
                .difficulty(dto.getDifficulty())
                .subject(dto.getSubject())
                .dueDate(dto.getDueDate())
                .build();
        problem.setId(dto.getId());
        return problem;
    }

    @Override
    public LabProblemDto convertModelToDto(LabProblem problem) {
        LabProblemDto dto = LabProblemDto.builder()
                .problemStatement(problem.getProblemStatement())
                .teacher(problem.getTeacher())
                .difficulty(problem.getDifficulty())
                .subject(problem.getSubject())
                .dueDate(problem.getDueDate())
                .build();
        dto.setId(problem.getId());
        return dto;
    }
}
