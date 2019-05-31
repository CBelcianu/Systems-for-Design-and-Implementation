package ro.ubb.labproblems.Service;

import ro.ubb.labproblems.CommonService;
import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Student;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ServerService implements CommonService {
    private StudentService studentService;
    private LabProblemService labProblemService;
    private GradeService gradeService;
    private ExecutorService executorService;

    public ServerService(ExecutorService executorService,StudentService studentService, LabProblemService labProblemService, GradeService gradeService){
        this.studentService = studentService;
        this.labProblemService = labProblemService;
        this.gradeService = gradeService;
        this.executorService = executorService;
    }

    @Override
    public CompletableFuture<Boolean> addStudent(Student student) {
        studentService.addStudent(student);
        return null;
    }

    @Override
    public void updateStudent(Student student) {
        studentService.updateStudent(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentService.removeStudent((long) id);
    }

    @Override
    public CompletableFuture<Set<Student>> getNextStudents() {
        return CompletableFuture.supplyAsync(studentService::getNextStudents,executorService);
    }

    @Override
    public CompletableFuture<Set<Student>> getAllStudents() {
        return CompletableFuture.supplyAsync(studentService::getAllStudents,executorService);
    }

    @Override
    public void AddProblem(LabProblem problem) {
        labProblemService.addLabProblem(problem);
    }

    @Override
    public void updatePROBLEM(LabProblem problem) {
        labProblemService.updateLabProblem(problem);
    }

    @Override
    public void deleteProbelm(Integer id) {
        labProblemService.removeLabProblem((long) id);
    }

    @Override
    public Future<Set<LabProblem>> getAllProblems() {
        return executorService.submit(labProblemService::getAllLabProblems);
    }

    @Override
    public void addGrade(Grade grade) {
        gradeService.addGrade(grade);
    }

    @Override
    public void deleteGrade(Integer id) {
        gradeService.removeGrade((long) id);
    }

    @Override
    public Future<Set<Grade>> getAllGrades() {
        return executorService.submit(gradeService::getAllGrades);
    }
}
