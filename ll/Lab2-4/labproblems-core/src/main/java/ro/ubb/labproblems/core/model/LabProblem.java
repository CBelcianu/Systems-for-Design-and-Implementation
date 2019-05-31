package ro.ubb.labproblems.core.model;

import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class LabProblem extends BaseEntity<Long> {
    private String problemStatement;
    private String teacher;
    private int difficulty;
    private String dueDate;
    private String subject;
}
