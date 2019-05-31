package ro.ubb.labproblems.Service;

import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Repository.Interfaces.IRepository;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author andrei.
 */

public class LabProblemService {

    private IRepository<Long, LabProblem> repository;

    public LabProblem filterDiff(){
        Iterable<LabProblem> labProblems = repository.returnAll();

        Optional<LabProblem> maxentry = StreamSupport.stream(labProblems.spliterator(), false).max(Comparator.comparing(LabProblem::getWeight));
        return maxentry.get();
    }

    public LabProblemService(IRepository<Long, LabProblem> repository)
    {
        this.repository = repository;
    }

    /**
     * Adds a LabProblem to the repository
     * @param labProblem the entity that will be added
     */
    public void addLabProblem(LabProblem labProblem)
    {
        repository.addToRepo(labProblem);
    }

    /**
     *
     * @return all the LabProblems in the repository
     */
    public Set<LabProblem> getAllLabProblems()
    {
        Iterable<LabProblem> labProblems = repository.returnAll();
        return StreamSupport.stream(labProblems.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     *
     * @param id the Long that is used to identify the LabProblem
     * @return a LabProblem that has the specified id
     */
    public Optional<LabProblem> getLabProblemByID(Long id)
    {
        return repository.returnOne(id);
    }

    /**
     * Removes the LabProblem from the repository
     * @param id the Long that is used to identify the LabProblem
     */
    public void removeLabProblem(Long id)
    {
        repository.deleteFromRepo(id);
    }

    /**
     * Updates a LabProblem with a new entity
     * @param labProblem the newly updated LabProblem that will replace the info of the old one
     */
    public void updateLabProblem(LabProblem labProblem)
    {
        repository.update(labProblem);
    }
}
