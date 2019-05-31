package ro.ubb.labproblems.Repository.FileRepositories;

import ro.ubb.labproblems.Domain.LabProblem;
import ro.ubb.labproblems.Domain.Validators.Validator;
import ro.ubb.labproblems.Domain.Validators.ValidatorException;
import ro.ubb.labproblems.Repository.InMemoryRepositories.InMemoryRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author andrei
 */

public class LabProblemFileRepository extends InMemoryRepository<Long, LabProblem> {

    private String fileName;

    public LabProblemFileRepository(Validator<LabProblem> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData()
    {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                String problemStatement = items.get(1);
                String teacher = items.get((2));
                int weight = Integer.parseInt(items.get(3));
                String dueDate = items.get((4));
                String subject = items.get((5));

                LabProblem labProblem = new LabProblem(problemStatement, teacher, weight, dueDate, subject);
                labProblem.setId(id);

                try {
                    super.addToRepo(labProblem);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<LabProblem> addToRepo(LabProblem entity) throws ValidatorException {
        Optional<LabProblem> optional = super.addToRepo(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(LabProblem entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getProblemStatement() + "," + entity.getTeacher() + "," + entity.getWeight() + "," + entity.getDueDate() + "," + entity.getSubject());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
