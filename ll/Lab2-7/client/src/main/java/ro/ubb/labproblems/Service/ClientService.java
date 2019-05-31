package ro.ubb.labproblems.Service;

import ro.ubb.labproblems.CommonService;
import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Message;
import ro.ubb.labproblems.Domain.Student;
import ro.ubb.labproblems.Tcp.TcpClient;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@SuppressWarnings("unchecked")
public class ClientService implements CommonService {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ClientService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Boolean> addStudent(Student student) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(CommonService.ADD_STUDENT, student);
            Message response = tcpClient.sendAndReceive(request);
            if (response.getHeader().equals("ERROR"))
                throw new RuntimeException("Error");
            else return true;
        }, executorService);
    }

    @Override
    public void updateStudent(Student student) {
        executorService.execute(() -> {
            Message request = new Message(CommonService.UPDATE_STUDENT, student);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public void deleteStudent(Integer id) {
        executorService.execute(() -> {
            Message request = new Message(CommonService.DELETE_STUDENT, id);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public CompletableFuture<Set<Student>> getNextStudents() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(CommonService.GET_STUDENTS_PAGED);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Student>)response.getBody();
        }, executorService);
    }

    @Override
    public CompletableFuture<Set<Student>> getAllStudents() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(CommonService.GET_ALL_STUDENTS);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Student>)response.getBody();
        });
    }

    @Override
    public void AddProblem(LabProblem problem) {
         CompletableFuture.supplyAsync(() -> {
            Message request = new Message(CommonService.ADD_PROBLEM, problem);
            Message response = tcpClient.sendAndReceive(request);
            if (response.getHeader().equals("ERROR"))
                throw new RuntimeException("Error");
            else return true;
        }, executorService);
    }

    @Override
    public void updatePROBLEM(LabProblem problem) {
        executorService.execute(() -> {
            Message request = new Message(CommonService.UPDATE_PROBLEM, problem);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public void deleteProbelm(Integer id) {
        executorService.execute(() -> {
            Message request = new Message(CommonService.DELETE_PROBLEM, id);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public Future<Set<LabProblem>> getAllProblems() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(CommonService.GET_ALL_PROBLEMS);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<LabProblem>)response.getBody();
        });
    }

    @Override
    public void addGrade(Grade grade) {
        CompletableFuture.supplyAsync(() -> {
            Message request = new Message(CommonService.ADD_GRADE, grade);
            Message response = tcpClient.sendAndReceive(request);
            if (response.getHeader().equals("ERROR"))
                throw new RuntimeException("Error");
            else return true;
        }, executorService);
    }

    @Override
    public void deleteGrade(Integer id) {
        executorService.execute(() -> {
            Message request = new Message(CommonService.DELETE_GRADE, id);
            tcpClient.sendAndReceive(request);
        });
    }

    @Override
    public Future<Set<Grade>> getAllGrades() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(CommonService.GET_ALL_GRADES);
            Message response = tcpClient.sendAndReceive(request);
            return (Set<Grade>)response.getBody();
        });
    }
}
