package ro.ubb.labproblems;

import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Student;
import ro.ubb.labproblems.Domain.Validators.GradeValidator;
import ro.ubb.labproblems.Domain.Validators.LabProblemValidator;
import ro.ubb.labproblems.Domain.Validators.StudentValidator;
import ro.ubb.labproblems.Domain.Validators.Validator;
import ro.ubb.labproblems.Repository.*;
import ro.ubb.labproblems.Service.GradeService;
import ro.ubb.labproblems.Service.LabProblemService;
import ro.ubb.labproblems.Service.StudentService;
import ro.ubb.labproblems.UI.Console;

public class Main {
    public static void main(String[] args){
        System.out.println("hello");

        DbCon conn = new DbCon();

        Validator<Student> studentValidator = new StudentValidator();
        Validator<LabProblem> labProblemValidator = new LabProblemValidator();
        Validator<Grade> gradeValidator = new GradeValidator();
        //IRepository<Long, Student> studentRepository = new InMemoryRepository<>(studentValidator);
        //IRepository<Long, Student> studentRepository = new StudentXMLRepository(studentValidator, "./students.xml");
        IPagingRepository<Long, Student> studentRepository = new StudentDBRepository<>(conn);
        //IRepository<Long, LabProblem> labProblemRepository = new InMemoryRepository<>(labProblemValidator);
        //IRepository<Long, LabProblem> labProblemRepository =
        //        new LabProblemXMLRepository(labProblemValidator, "./labProblems.xml");
        //IRepository<Long, Grade> gradeRepository = new GradeXMLRepository(gradeValidator, "./grades.xml");
        IRepository<Long, LabProblem> labProblemRepository = new LabProblemDBRepository<>(conn);
        IRepository<Long, Grade> gradeRepository = new GradeDBRepository<>(conn);
        StudentService studentService = new StudentService(studentRepository);
        LabProblemService labProblemService = new LabProblemService(labProblemRepository);
        GradeService gradeService = new GradeService(gradeRepository);
        //Console console = new Console(studentService, labProblemService, gradeService);
        //console.runConsole();
    }
}
