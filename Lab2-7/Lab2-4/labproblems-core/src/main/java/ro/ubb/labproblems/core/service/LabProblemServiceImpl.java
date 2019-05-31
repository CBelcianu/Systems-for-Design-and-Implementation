package ro.ubb.labproblems.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.labproblems.core.model.LabProblem;
import ro.ubb.labproblems.core.repository.LabProblemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LabProblemServiceImpl implements LabProblemService {
    private static final Logger log =
            LoggerFactory.getLogger(LabProblemServiceImpl.class);

    @Autowired
    private LabProblemRepository labProblemRepository;

    @Override
    public List<LabProblem> getAllProblems() {
        log.warn("getAllProblems --- method entered");

        List<LabProblem> result = labProblemRepository.findAll();

        log.warn("getAllProblems: result={}", result);

        return result;
    }

    @Override
    public LabProblem saveProblem(LabProblem problem) {
        log.warn("-----saveProblem------ problem={}", problem);

        LabProblem savedProblem = this.labProblemRepository.save(problem);

        log.warn("saveProblem: methon finished");

        return savedProblem;
    }

    @Override
    @Transactional
    public LabProblem update(Long id, LabProblem problem) {
        log.warn("updateProblem: id={},problem={}", id, problem);

        Optional<LabProblem> optionalLabProblem = labProblemRepository.findById(id);
        LabProblem result = optionalLabProblem.orElse(problem);
        result.setSubject(problem.getSubject());
        result.setTeacher(problem.getTeacher());
        result.setDifficulty(problem.getDifficulty());
        result.setProblemStatement(problem.getProblemStatement());
        result.setDueDate(problem.getDueDate());

        log.warn("updateProblem: result={}", result);

        return result;
    }

    @Override
    public void delete(Long id) {
        log.warn("deleteProblem: id={}", id);

        labProblemRepository.deleteById(id);

        log.warn("deleteProblem --- method finished");
    }

}
