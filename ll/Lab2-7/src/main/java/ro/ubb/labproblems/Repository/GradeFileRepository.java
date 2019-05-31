package ro.ubb.labproblems.Repository;

import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Domain.Validators.Validator;
import ro.ubb.labproblems.Domain.Validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GradeFileRepository extends InMemoryRepository<Long, Grade> {

    private String fileName;

    public GradeFileRepository(Validator<Grade> validator, String fileName) {
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
                int studentID = Integer.parseInt(items.get(1));
                int problemID = Integer.parseInt(items.get(2));
                int grade = Integer.parseInt(items.get(3));

                Grade newGrade = new Grade(studentID, problemID, grade);
                newGrade.setId(id);

                try {
                    super.addToRepo(newGrade);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Grade> addToRepo(Grade entity) throws ValidatorException {
        Optional<Grade> optional = super.addToRepo(entity);
        if (optional.isPresent()) {
            return optional;
        }
        saveToFile(entity);
        return Optional.empty();
    }

    private void saveToFile(Grade entity) {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            bufferedWriter.write(
                    entity.getId() + "," + entity.getStudentID() + "," + entity.getProblemID() + "," + entity.getGrade());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
