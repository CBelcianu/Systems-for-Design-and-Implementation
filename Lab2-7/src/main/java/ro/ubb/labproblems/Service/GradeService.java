package ro.ubb.labproblems.Service;

import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Repository.IRepository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Catalin 
 */

public class GradeService {
    private IRepository<Long, Grade> repository;

    public GradeService(IRepository<Long, Grade> repository) {
        this.repository = repository;
    }

    /**
     * Adds a Grade to the repository
     * @param grade the Grade that we want to add
     */
    public void addGrade(Grade grade){
        repository.addToRepo(grade);
    }

    /**
     * Returns the set of all the grades in the repository
     * @return all the Grades
     */
    public Set<Grade> getAllGrades(){
        Iterable<Grade> grades=repository.returnAll();
        return StreamSupport.stream(grades.spliterator(),false).collect(Collectors.toSet());
    }

    /**
     * Filters the Grades by id
     * @param id the specified id
     * @return all the Grades with the given id
     */
    public Optional<Grade> getGradeById(Long id){
        return repository.returnOne(id);
    }

    /**
     * removes a Grade from the repository
     * @param id the id of the Grade that we want to remove
     */
    public void removeGrade(Long id){
        repository.deleteFromRepo(id);
    }

    /**
     * updates a Grade
     * @param grade the updated Grade
     */
    public void updateGrade(Grade grade){
        repository.update(grade);
    }

    public Map<Integer, Long> reportLabProblemNoAssigned(){
        return StreamSupport.stream(repository.returnAll().spliterator(), false).collect(
                Collectors.groupingBy(Grade::getProblemID, Collectors.counting()));
    }
}
