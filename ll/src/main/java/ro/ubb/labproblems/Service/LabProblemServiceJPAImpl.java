package ro.ubb.labproblems.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Repository.LabProblemJPARepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LabProblemServiceJPAImpl implements LabProblemServiceJPA {
    private static final Logger log = LoggerFactory.getLogger(
            LabProblemServiceJPAImpl.class);

    @Autowired
    private LabProblemJPARepository labProblemJPARepository;

    @Override
    public List<LabProblem> getAllProblems() {

        log.warn("getAllProblems --- method entered");

        List<LabProblem> result = labProblemJPARepository.findAll();

        log.warn("getAllProblems: result={}", result);

        return result;

    }

    @Override
    public void saveProblem(LabProblem labProblem) {
        log.warn("saveProblem: student={}", labProblem);

        labProblemJPARepository.save(labProblem);

        log.warn("saveProblem--- method finished");
    }

    @Override
    @Transactional
    public void update(LabProblem labProblem) {
        log.warn("update: labProblem={}", labProblem);

        labProblemJPARepository.findById(labProblem.getId())
                .ifPresent(labProblem1 -> {
                    labProblem1.setProblemStatement(labProblem.getProblemStatement());
                    labProblem1.setTeacher(labProblem.getTeacher());
                    labProblem1.setWeight(labProblem.getWeight());
                    labProblem1.setDueDate(labProblem.getDueDate());
                    labProblem1.setSubject(labProblem.getSubject());
                    log.debug("update --- labProblem updated? --- " +
                            "labProblem={}", labProblem1);
                });

        log.warn("update --- method finished");
    }


    @Override
    public void delete(Long id) {
        log.warn("delete: id={}", id);

        labProblemJPARepository.deleteById(id);

        log.warn("delete --- method finished");

    }
}
