package ro.ubb.labproblems.web.dto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class StudentDto extends BaseDto {
    private String serialNumber;
    private String sname;
    private int sgroup;
}

