package ro.ubb.labproblems.core.service;

import ro.ubb.labproblems.core.model.LabProblem;

import java.util.List;

public interface LabProblemService {
    List<LabProblem> getAllProblems();

    LabProblem saveProblem(LabProblem labProblem);


    void delete(Long id);

    LabProblem update(Long id,LabProblem labProblem);
}
