package ro.ubb.labproblems.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Student;
import ro.ubb.labproblems.Service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
    Console class
    fields: studentService, labProblemService
 @author Catalin.
 */
@Component
public class Console {

    @Autowired
    private StudentServiceJPA studentService;

    @Autowired
    private LabProblemServiceJPA labProblemService;

    /**
    Function runConsole()
    input: -
    output: -
     */
    public void runConsole() {
        List<ICommand> methods;
        methods = new ArrayList<>();
        methods.add(new addStudent());
        methods.add(new addLabProblem());
        methods.add(new removeStudent());
        methods.add(new removeLabProblem());
        methods.add(new updateStudent());
        methods.add(new updateLabProblem());
        methods.add(new searchStudent());
        methods.add(new searchLabProblem());
        methods.add(new printAllStudents());
        methods.add(new printAllLabProblems());
        methods.add(new printStudentsPaged());
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

    private void displayOptions(){
        System.out.println("Welcome to iteration 1!\n\n");
        System.out.println("1. Add student.");
        System.out.println("2. Add lab problem.");

        System.out.println("\n3. Remove student.");
        System.out.println("4. Remove lab problem.");

        System.out.println("\n5. Update student.");
        System.out.println("6. Update lab problem.");

        System.out.println("\n7. Search for a student.");
        System.out.println("8. Search for a lab problem.");

        System.out.println("\n9. Get all students.");
        System.out.println("10. Get all lab problems.");

        System.out.println("\n11. Add grade");
        System.out.println("12. Remove grade");
        System.out.println("13. Update grade");
        System.out.println("14. Search for a grade");
        System.out.println("15. Get all grades");

        System.out.println("\n\n16. Report 1: Get for each lab problem, print the number of times it was assigned");

        System.out.println("17. Get students paged");

        System.out.println("\n0. Exit.");
    }


    private class printStudentsPaged implements ICommand {

        @Override
        public void execute() {
            //Set<Student> students = studentService.getNextStudents();
            //students.forEach(System.out::println);
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
            List<Student> students = studentService.getAllStudents();
            students.forEach(System.out::println);
        }
    }

    /**
    Class printAllLabProblems prints all labProblems from the repo
    input: -
    output: all the labProblems
     */
    private class printAllLabProblems implements ICommand{

        @Override
        public void execute() {
            List<LabProblem> labProblems = labProblemService.getAllProblems();
            labProblems.forEach(System.out::println);
        }
    }

    /**
    Class addStudent() add a student in the repo
    input: -
    output: -
    */
    private class addStudent implements ICommand{

        @Override
        public void execute() {
            System.out.println("\nType in the details of the student:");
            Student student = readStudent();
            studentService.saveStudent(student);
        }
    }

    /**
    Class addLabProblem() add a labProblem in the repo
    input: -
    output: -
    */
    private class addLabProblem implements ICommand {


        @Override
        public void execute() {
            System.out.println("\nType in the details of the lab problem:");
            LabProblem labProblem = readLabProblem();
            labProblemService.saveProblem(labProblem);
        }

    }

    /**
     * Class removeStudent removes a student from the repo
     */
    private class removeStudent implements ICommand {

        @Override
        public void execute() {
            System.out.println("\nStudent ID:");
            Long id = Objects.requireNonNull(readInteger()).longValue();
            studentService.delete(id);
        }
    }

    /**
     * Class removeLabProblem removes a student from the repo
     */
    private class removeLabProblem implements ICommand {

        @Override
        public void execute() {
            System.out.println("\nLab Problem ID:");
            Long id = Objects.requireNonNull(readInteger()).longValue();
            labProblemService.delete(id);
        }
    }

    /**
     * Class updateStudent updates a student in the repo
     */
    private class updateStudent implements ICommand {

        @Override
        public void execute() {
            System.out.println("\nPlease provide the details of the updated student:");
            Student updatedStudent = readStudent();
            studentService.update(updatedStudent);
        }
    }


    /**
     * Class updateLabProblem updates a lab problem in the repo
     */
    private class updateLabProblem implements ICommand {

        @Override
        public void execute() {
            System.out.println("\nPlease provide the details of the updated student:");
            LabProblem updatedLabProblem = readLabProblem();
            labProblemService.update(updatedLabProblem);
        }
    }


    /**
     * Class searchStudent returns a student from the repo
     */
    private class searchStudent implements ICommand {

        @Override
        public void execute() {
            System.out.println("\nID of the student:");
            Long id = Objects.requireNonNull(readInteger()).longValue();
            //System.out.println(studentService.getStudentByID(id));
        }
    }

    /**
     * Class searchLabProblem returns a lab problem from the repo
     */
    private class searchLabProblem implements ICommand {

        @Override
        public void execute() {
            System.out.println("\nID of the lab problem:");
            Long id = Objects.requireNonNull(readInteger()).longValue();
            //System.out.println(labProblemService.getLabProblemByID(id));
        }
    }


    /**
    Function readStudent() creates a Student from the user input
    input: -
    output: Student
     */
    private static Student readStudent() {
        System.out.println("Read student {serialNumber, name, group}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            //Long id = Long.valueOf(bufferRead.readLine());
            String serialNumber = bufferRead.readLine();
            String name = bufferRead.readLine();
            int group = Integer.parseInt(bufferRead.readLine());

            Student student = new Student(serialNumber, name, group);
            //student.setId(id);

            return student;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
    Function readLabProblem() creates a LabProblem from the user input
    input: -
    output: LabProblem
     */
    private  LabProblem readLabProblem(){
        System.out.println("Read lab problem {statement,teacher,weight,due date, subject}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            //Long id = Long.valueOf(bufferRead.readLine());
            String statement = bufferRead.readLine();
            String teacher = bufferRead.readLine();
            int weight = Integer.parseInt(bufferRead.readLine());
            String dueDate = bufferRead.readLine();
            String subject = bufferRead.readLine();

            LabProblem labProblem = new LabProblem(statement,teacher,weight,dueDate,subject);
            //labProblem.setId(id);

            return labProblem;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
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
