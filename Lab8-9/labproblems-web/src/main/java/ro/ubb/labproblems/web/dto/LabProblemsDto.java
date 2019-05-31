package ro.ubb.labproblems.web.dto;

import lombok.*;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LabProblemsDto {
    private Set<LabProblemDto> problems;
}
