package ro.ubb.labproblems.Domain.Validators;

import ro.ubb.labproblems.Domain.Student;


/**
 class StudentValidator implements interface Validator
 @author Catalin.
 */
public class StudentValidator implements Validator<Student> {
    @Override
    public void validate(Student entity) throws ValidatorException {
        //assert entity.getName() == null : "invalid Student data";
        if(entity.getSerialNumber()==null) throw new ValidatorException("invalid Student data");
    }
}