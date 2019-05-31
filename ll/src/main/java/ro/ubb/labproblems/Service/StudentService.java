package ro.ubb.labproblems.Service;

import ro.ubb.labproblems.Domain.Student;
import ro.ubb.labproblems.Repository.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author andrei.
 */

public class StudentService {

    private IPagingRepository<Long, Student> repository;
    private IPageable pageableObj = new PageableImpl(0, 1);

    public StudentService(IPagingRepository<Long, Student> repository)
    {
        this.repository = repository;
    }

    /**
     * Adds a student to the repository
     * @param student the entity that will be added
     */
    public void addStudent(Student student)
    {
        repository.addToRepo(student);
    }

    /**
     *
     * @return all the students in the repository
     */
    public Set<Student> getAllStudents()
    {
        Iterable<Student> students = repository.returnAll();
        return StreamSupport.stream(students.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     *
     * @param id the Long that is used to identify the student
     * @return a student that has the specified id
     */
    public Optional<Student> getStudentByID(Long id)
    {
        return repository.returnOne(id);
    }

    /**
     * Removes the student from the repository
     * @param id the Long that is used to identify the student
     */
    public void removeStudent(Long id)
    {
        repository.deleteFromRepo(id);
    }

    /**
     * Updates a student with a new entity
     * @param student the newly updated student that will replace the info of the old one
     */
    public void updateStudent(Student student)
    {
        repository.update(student);
    }

    /**
     * Sort students by name
     */
    public List<Student> sortStudentsByName()
    {
        Iterable<Student> students = repository.returnAll();
        return StreamSupport.stream(students.spliterator(), false).sorted(
                Comparator.comparing(Student::getName)
        ).collect(Collectors.toList());
    }

    public Set<Student> getNextStudents() {
        IPage<Student> clientsPage = repository.findAll(pageableObj);
        Set<Student> clients = clientsPage.getContent().collect(Collectors.toSet());
        pageableObj = clientsPage.nextPageable();
        return clients;
    }

}
