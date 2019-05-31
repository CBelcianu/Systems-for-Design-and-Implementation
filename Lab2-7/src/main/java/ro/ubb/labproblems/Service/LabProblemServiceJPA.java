package ro.ubb.labproblems.Service;

import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Student;

import java.util.List;

public interface LabProblemServiceJPA {
    List<LabProblem> getAllProblems();

    void saveProblem(LabProblem labProblem);


    void delete(Long id);

    void update(LabProblem labProblem);
}
