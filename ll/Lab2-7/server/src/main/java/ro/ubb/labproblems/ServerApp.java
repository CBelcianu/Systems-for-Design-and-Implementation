package ro.ubb.labproblems;

import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Message;
import ro.ubb.labproblems.Domain.Student;
import ro.ubb.labproblems.Repository.DBRepositories.DbCon;
import ro.ubb.labproblems.Repository.DBRepositories.GradeDBRepository;
import ro.ubb.labproblems.Repository.DBRepositories.LabProblemDBRepository;
import ro.ubb.labproblems.Repository.DBRepositories.StudentDBRepository;
import ro.ubb.labproblems.Repository.Interfaces.IPagingRepository;
import ro.ubb.labproblems.Repository.Interfaces.IRepository;
import ro.ubb.labproblems.Service.GradeService;
import ro.ubb.labproblems.Service.LabProblemService;
import ro.ubb.labproblems.Service.ServerService;
import ro.ubb.labproblems.Service.StudentService;
import ro.ubb.labproblems.Tcp.TcpServer;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerApp {
    public static void main(String[] args) {
        ExecutorService executorService =
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors());

        TcpServer tcpServer = new TcpServer(executorService,
                CommonService.SERVER_PORT);

        DbCon conn = new DbCon();

        IPagingRepository<Long, Student> studentRepository = new StudentDBRepository<>(conn);
        IRepository<Long, LabProblem> labProblemIRepository = new LabProblemDBRepository<>(conn);
        IRepository<Long, Grade> gradeIRepository = new GradeDBRepository<>(conn);
        StudentService studentService = new StudentService(studentRepository);
        LabProblemService labProblemService = new LabProblemService(labProblemIRepository);
        GradeService gradeService = new GradeService(gradeIRepository);

        CommonService serverService = new ServerService(executorService, studentService, labProblemService, gradeService);

        tcpServer.addHandler(serverService.ADD_STUDENT, (request) -> {
            Student student = (Student) request.getBody();
            try {
                serverService.addStudent(student);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(serverService.UPDATE_STUDENT, (request) -> {
            Student student = (Student)request.getBody();
            try {
                serverService.updateStudent(student);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(serverService.DELETE_STUDENT, (request) -> {
            Integer id = (Integer)request.getBody();
            try {
                serverService.deleteStudent(id);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(CommonService.GET_STUDENTS_PAGED, (request) -> {
            try {
                Future<Set<Student>> clients = serverService.getNextStudents();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(CommonService.GET_ALL_STUDENTS, (request) -> {
            try {
                Future<Set<Student>> clients = serverService.getAllStudents();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(serverService.ADD_PROBLEM, (request) -> {
            LabProblem problem = (LabProblem) request.getBody();
            try {
                serverService.AddProblem(problem);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(serverService.UPDATE_PROBLEM, (request) -> {
            LabProblem problem = (LabProblem) request.getBody();
            try {
                serverService.updatePROBLEM(problem);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(serverService.DELETE_PROBLEM, (request) -> {
            Integer id = (Integer)request.getBody();
            try {
                serverService.deleteProbelm(id);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(CommonService.GET_ALL_PROBLEMS, (request) -> {
            try {
                Future<Set<LabProblem>> clients = serverService.getAllProblems();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(serverService.ADD_GRADE, (request) -> {
            Grade grade = (Grade) request.getBody();
            try {
                serverService.addGrade(grade);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(serverService.DELETE_GRADE, (request) -> {
            Integer id = (Integer)request.getBody();
            try {
                serverService.deleteGrade(id);
                return new Message(Message.OK);
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.addHandler(CommonService.GET_ALL_GRADES, (request) -> {
            try {
                Future<Set<Grade>> clients = serverService.getAllGrades();
                return new Message(Message.OK, clients.get());
            }
            catch (Exception exc) {
                return new Message(Message.ERROR);
            }
        });

        tcpServer.start();
    }

}
