package ro.ubb.labproblems.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.labproblems.client.UI.Console;
import org.springframework.web.client.RestTemplate;
import ro.ubb.labproblems.web.dto.StudentDto;
import ro.ubb.labproblems.web.dto.StudentsDto;

public class ClientApp {
    public static void main(String args[]){

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "ro.ubb.labproblems.client.config"
                );

        Console console = context.getBean(Console.class);
        console.runConsole();
        //RestTemplate restTemplate = context.getBean(RestTemplate.class);

/*
        StudentDto newStudent = StudentDto.builder()
                .serialNumber("afjknsa")
                .sname("John")
                .sgroup(921)
                .build();

        StudentDto savedStudent = restTemplate.postForObject(
                "http://localhost:8080/api/students",
                newStudent,
                StudentDto.class
        );


        savedStudent.setSgroup(922);
        restTemplate.put(
                "http://localhost:8080/api/students",
                savedStudent,
                savedStudent.getId()
        );
*/
/*
        restTemplate.delete(
                "http://localhost:8080/api/students",
                savedStudent.getId()
        );
*/
/*
        StudentsDto studentsDto = restTemplate.getForObject(
                "http://localhost:8080/api/students",
                StudentsDto.class
        );
        System.out.println(studentsDto);
        */

        System.out.println("bye ");

    }
}
