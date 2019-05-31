package ro.ubb.labproblems.web.dto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class LabProblemDto extends BaseDto {
    private String problemStatement;
    private String teacher;
    private int difficulty;
    private String dueDate;
    private String subject;
}
