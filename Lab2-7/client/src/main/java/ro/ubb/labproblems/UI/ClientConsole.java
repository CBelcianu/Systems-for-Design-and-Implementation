package ro.ubb.labproblems.UI;

import ro.ubb.labproblems.CommonService;
import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ClientConsole {
    private CommonService clientService;
    private List<ICommand> methods;

    public ClientConsole(CommonService clientService){
        this.clientService = clientService;
        methods = new ArrayList<>();
        methods.add(new addStudent());
        methods.add(new addProblem());
        methods.add(new deleteStudent());
        methods.add(new deleteProblem());
        methods.add(new updateStudent());
        methods.add(new updateProblem());
        methods.add(new printAllStudents());
        methods.add(new printAllProblems());
        methods.add(new addGrade());
        methods.add(new deleteGrade());
        methods.add(new printAllGrades());
        methods.add(new printStudentsPaged());
    }

    private void displayOptions(){
        System.out.println("Welcome to iteration 1!\n\n");
        System.out.println("1. Add student.");
        System.out.println("2. Add lab problem.");

        System.out.println("\n3. Remove student.");
        System.out.println("4. Remove lab problem.");

        System.out.println("\n5. Update student.");
        System.out.println("6. Update lab problem.");

        System.out.println("\n7. Get all students.");
        System.out.println("8. Get all lab problems.");

        System.out.println("\n9. Add grade");
        System.out.println("10. Remove grade");
        System.out.println("11. Get all grades");

        System.out.println("12. Get students paged");

        System.out.println("\n0. Exit.");
    }

    private class printStudentsPaged implements ICommand {

        @Override
        public void execute()  {
            CompletableFuture<Set<Student>> all = clientService.getNextStudents();
            try {
                all.get().forEach(System.out::println);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     Class printAllStudents prints all students from the repo
     input: -
     output: all the students
     */
    private class printAllStudents implements ICommand {

        @Override
        public void execute() {
            CompletableFuture<Set<Student>> all = clientService.getAllStudents();
            try {
                all.get().forEach(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private class addStudent implements ICommand{

        @Override
        public void execute() {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nStudent ID:");
            Long studentID = Objects.requireNonNull(readInteger()).longValue();
            System.out.println("\nname:");
            String sname = null, serialNumber = null;
            try {
                sname = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nserial number:");
            try {
                serialNumber = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\ngroup:");
            Integer group = Objects.requireNonNull(readInteger());
            Student student = new Student(serialNumber, sname, group);
            student.setId(studentID);

            clientService.addStudent(student);
        }
    }

    private class deleteStudent implements ICommand{

        @Override
        public void execute() {
            System.out.println("\nStudent ID:");
            Integer studentID = Objects.requireNonNull(readInteger());
            clientService.deleteStudent(studentID);
        }
    }

    private class updateStudent implements ICommand{

        @Override
        public void execute() {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nStudent ID:");
            Long studentID = Objects.requireNonNull(readInteger()).longValue();
            System.out.println("\nname:");
            String sname = null, serialNumber = null;
            try {
                sname = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nserial number:");
            try {
                serialNumber = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\ngroup:");
            Integer group = Objects.requireNonNull(readInteger());
            Student student = new Student(serialNumber, sname, group);
            student.setId(studentID);

            clientService.updateStudent(student);
        }
    }

    private class printAllProblems implements ICommand {

        @Override
        public void execute() {
            CompletableFuture<Set<LabProblem>> all = (CompletableFuture<Set<LabProblem>>) clientService.getAllProblems();
            try {
                all.get().forEach(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private class addProblem implements ICommand{

        @Override
        public void execute() {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nProblem ID:");
            Long studentID = Objects.requireNonNull(readInteger()).longValue();
            System.out.println("\nstatement:");
            String stmt = null, teacher = null;
            try {
                stmt = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nteacher:");
            try {
                teacher = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\ndifficulty:");
            Integer diff = Objects.requireNonNull(readInteger());
            System.out.println("\ndueDate:");
            String dued = null, subj = null;
            try {
                dued = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nsubject:");
            try {
                subj = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LabProblem problem = new LabProblem(stmt, teacher, diff, dued, subj);
            problem.setId(studentID);

            clientService.AddProblem(problem);
        }
    }

    private class deleteProblem implements ICommand{

        @Override
        public void execute() {
            System.out.println("\nProblem ID:");
            Integer studentID = Objects.requireNonNull(readInteger());
            clientService.deleteProbelm(studentID);
        }
    }

    private class updateProblem implements ICommand{

        @Override
        public void execute() {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nProblem ID:");
            Long studentID = Objects.requireNonNull(readInteger()).longValue();
            System.out.println("\nstatement:");
            String stmt = null, teacher = null;
            try {
                stmt = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nteacher:");
            try {
                teacher = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\ndifficulty:");
            Integer diff = Objects.requireNonNull(readInteger());
            System.out.println("\ndueDate:");
            String dued = null, subj = null;
            try {
                dued = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\nsubject:");
            try {
                subj = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            LabProblem problem = new LabProblem(stmt, teacher, diff, dued, subj);
            problem.setId(studentID);

            clientService.updatePROBLEM(problem);
        }
    }

    private class printAllGrades implements ICommand {

        @Override
        public void execute() {
            CompletableFuture<Set<Grade>> all = (CompletableFuture<Set<Grade>>) clientService.getAllGrades();
            try {
                all.get().forEach(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private class addGrade implements ICommand{

        @Override
        public void execute() {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nGrade ID:");
            Long gradeID = Objects.requireNonNull(readInteger()).longValue();
            System.out.println("\nProblem ID:");
            int problemID = Objects.requireNonNull(readInteger());
            System.out.println("\nStudent ID:");
            int studentID = Objects.requireNonNull(readInteger());
            System.out.println("\ngrade:");
            int gradeval = Objects.requireNonNull(readInteger());

            Grade grade = new Grade(studentID, problemID, gradeval);
            grade.setId(gradeID);

            clientService.addGrade(grade);
        }
    }

    private class deleteGrade implements ICommand{

        @Override
        public void execute() {
            System.out.println("\nProblem ID:");
            Integer studentID = Objects.requireNonNull(readInteger());
            clientService.deleteGrade(studentID);
        }
    }

    public void runConsole() {
        displayOptions();
        Integer choice = readInteger();
        assert choice != null;
        while(!choice.equals(0))
        {
            try {
                methods.get(choice - 1).execute();
            }
            catch (RuntimeException exp)
            {
                exp.printStackTrace();
            }
            displayOptions();
            choice = readInteger();
            assert choice != null;
        }
    }

    private Integer readInteger(){
        System.out.println(">");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.valueOf(bufferedReader.readLine());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

