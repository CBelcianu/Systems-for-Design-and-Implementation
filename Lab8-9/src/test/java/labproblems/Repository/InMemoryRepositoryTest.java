package labproblems.Repository;

import org.junit.After;
import org.junit.Before;
import ro.ubb.labproblems.Domain.Student;
import ro.ubb.labproblems.Domain.Validators.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author andrei
 */

public class InMemoryRepositoryTest {

    IRepository<Long, Student> studentRepository;

    private static final Long ID = 1L;
    private static final Integer CURRENT_LENGTH = 3;
    private static final String SERIAL_NUMBER = "1";
    private static final String NEW_SERIAL_NUMBER = "2";
    private static final String NAME = "Borza";
    private static final String NEW_NAME = "Barza";
    private static final int GROUP = 921;
    private static final int NEW_GROUP = 926;
    @Before
    public void setUp() throws Exception{
        Validator<Student> studentValidator = new StudentValidator();
        studentRepository  = new InMemoryRepository<>(studentValidator);

        Student student = new Student("1", "Borza", 921);
        student.setId(1L);
        studentRepository.addToRepo(student);

        student = new Student("13", "Tomas", 921);
        student.setId(2L);
        studentRepository.addToRepo(student);

        student = new Student("14", "Gheorghe", 921);
        student.setId(3L);
        studentRepository.addToRepo(student);
    }

    @After
    public void tearDown() throws Exception{
        studentRepository=null;
    }

    @Test
    public void testReturnOne() throws Exception{
        Student foundStudent = studentRepository.returnOne(ID).get();
        assertEquals("IDs should be equal", ID, foundStudent.getId());
        assertEquals("Serial numbers should be equal", SERIAL_NUMBER, foundStudent.getSerialNumber());
        assertEquals("Names should be equal", NAME, foundStudent.getName());
        assertEquals("Groups should be equal", GROUP, foundStudent.getGroup());
    }

    @Test
    public void testReturnAll() throws Exception {
        Iterable<Student> students = studentRepository.returnAll();
        Integer count = 0;
        for (Student student: students) {
            count++;
        }
        assertEquals("Length should be equal to 3", CURRENT_LENGTH, count);
    }

    @Test
    public void testAddToRepo() throws Exception {
        Student student = new Student("15", "Ion", 921);
        student.setId(4L);
        studentRepository.addToRepo(student);

        int counter = 0;
        for (Student i: studentRepository.returnAll())
        {
            counter++;
        }

        assertEquals("Current length should have increased by 1", CURRENT_LENGTH + 1, counter);
    }

    @Test(expected = ValidatorException.class)
    public void testAddToRepoException() throws Exception {
        Student student = new Student("14", null, 921);
        student.setId(3L);
        studentRepository.addToRepo(student);
    }

    @Test
    public void testDeleteFromRepo() throws Exception {
        studentRepository.deleteFromRepo(1L);

        int counter = 0;
        for (Student i: studentRepository.returnAll())
        {
            counter++;
        }

        assertEquals("Current length should have decreased by 1", CURRENT_LENGTH - 1, counter);
    }

    @Test
    public void testUpdate() throws Exception {
        Student updatedStudent = new Student("2", "Barza", 926);
        updatedStudent.setId(1L);
        studentRepository.update(updatedStudent);
        updatedStudent = studentRepository.returnOne(1L).get();
        assertEquals("IDs should be equal", ID, updatedStudent.getId());
        assertEquals("Serial number should be equal to new one", NEW_SERIAL_NUMBER, updatedStudent.getSerialNumber());
        assertEquals("Name should be equal to new one", NEW_NAME, updatedStudent.getName());
        assertEquals("Group should be equal to new one", NEW_GROUP, updatedStudent.getGroup());
    }

    @Test(expected = ValidatorException.class)
    public void testUpdateException() throws Exception {
        Student student = new Student("2", null, 926);
        student.setId(1L);
        studentRepository.update(student);
    }
}
