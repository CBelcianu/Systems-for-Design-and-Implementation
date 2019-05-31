package ro.ubb.labproblems.Domain.Validators;

import ro.ubb.labproblems.Domain.Grade;

/**
 * @author Catalin
 */
public class GradeValidator implements Validator<Grade> {
    @Override
    public void validate(Grade entity) throws ValidatorException {
        if(entity.getProblemID()==-1) {
            throw new ValidatorException("invalid Grade");
        }
        if(entity.getStudentID()==-1) throw new ValidatorException("invalid Grade");
    }
}
