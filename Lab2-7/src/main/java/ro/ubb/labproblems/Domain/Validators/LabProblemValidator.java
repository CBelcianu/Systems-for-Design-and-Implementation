package ro.ubb.labproblems.Domain.Validators;

import ro.ubb.labproblems.Domain.LabProblem;

/**
 class LabProblemValidator implements interface Validator
 @author Catalin.
 */
public class LabProblemValidator implements Validator<LabProblem> {
    @Override
    public void validate(LabProblem entity) throws ValidatorException {
        assert entity.getTeacher()!=null : "invalid LabProblem data";
        if(entity.getSubject()==null) throw new ValidatorException("invalid LabProblem data");
        if(entity.getProblemStatement()==null) throw new ValidatorException("invalid LabProblem data");
        if(entity.getDueDate()==null) throw new ValidatorException("invalid LabProblem data");
    }
}
