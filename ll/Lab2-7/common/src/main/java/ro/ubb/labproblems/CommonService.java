package ro.ubb.labproblems;

import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Student;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface CommonService {
    String SERVER_HOST = "localhost";
    int SERVER_PORT = 1234;

    String ADD_STUDENT = "addStudent";
    CompletableFuture<Boolean> addStudent(Student student);

    String UPDATE_STUDENT = "updateStudent";
    void updateStudent(Student student);

    String DELETE_STUDENT = "deleteStudent";
    void deleteStudent(Integer id);

    String GET_STUDENTS_PAGED = "getNextStudents";
    CompletableFuture<Set<Student>> getNextStudents();

    String GET_ALL_STUDENTS = "getAllStudents";
    CompletableFuture<Set<Student>> getAllStudents();


    String ADD_PROBLEM = "addProblem";
    void AddProblem(LabProblem problem);

    String UPDATE_PROBLEM = "updateProblem";
    void updatePROBLEM(LabProblem problem);

    String DELETE_PROBLEM = "deleteProblem";
    void deleteProbelm(Integer id);

    String GET_ALL_PROBLEMS = "getAllProblems";
    Future<Set<LabProblem>> getAllProblems();


    String ADD_GRADE = "addGrade";
    void addGrade(Grade grade);

    String DELETE_GRADE = "deleteGrade";
    void deleteGrade(Integer id);

    String GET_ALL_GRADES = "getAllGrades";
    Future<Set<Grade>> getAllGrades();

}
