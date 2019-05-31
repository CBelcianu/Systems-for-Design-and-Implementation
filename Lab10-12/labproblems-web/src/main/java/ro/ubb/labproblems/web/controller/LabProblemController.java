package ro.ubb.labproblems.web.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.labproblems.core.model.LabProblem;
import ro.ubb.labproblems.core.service.LabProblemService;
import ro.ubb.labproblems.web.converter.LabProblemConverter;
import ro.ubb.labproblems.web.dto.LabProblemDto;
import ro.ubb.labproblems.web.dto.LabProblemsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class LabProblemController {
    private static final Logger log =
            LoggerFactory.getLogger(LabProblemController.class);

    @Autowired
    private LabProblemService labProblemService;

    @Autowired
    private LabProblemConverter labProblemConverter;

    @RequestMapping(value = "/problems", method = RequestMethod.GET)
    List<LabProblemDto> getAllProblems() {
        log.warn("getAllProblems --- method entered");

        List<LabProblem> problems = labProblemService.getAllProblems();
        Set<LabProblemDto> dtos = labProblemConverter.convertModelsToDtos(problems);
        LabProblemsDto result = new LabProblemsDto(dtos);

        log.warn("getAllProblems: result={}", result);

        return new ArrayList<>(dtos);
    }

    @RequestMapping(value = "/problems", method = RequestMethod.POST)
    LabProblemDto saveProblem(@RequestBody LabProblemDto dto) {
        log.warn("saveProblem: dto={}", dto);

        LabProblem saved = this.labProblemService.saveProblem(
                labProblemConverter.convertDtoToModel(dto)
        );
        LabProblemDto result = labProblemConverter.convertModelToDto(saved);

        log.warn("saveProblem: result={}", result);

        return result;
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.PUT)
    LabProblemDto updateProblem(@PathVariable Long id,
                             @RequestBody LabProblemDto dto) {
        log.warn("updateProblem: id={},dto={}", id, dto);

        LabProblem update = labProblemService.update(
                id,
                labProblemConverter.convertDtoToModel(dto));
        LabProblemDto result = labProblemConverter.convertModelToDto(update);

        return result;
    }

    @RequestMapping(value = "/problems/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteProblem(@PathVariable Long id) {
        log.warn("deleteProblem: id={}", id);

        labProblemService.delete(id);

        log.warn("deleteStudent --- method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
