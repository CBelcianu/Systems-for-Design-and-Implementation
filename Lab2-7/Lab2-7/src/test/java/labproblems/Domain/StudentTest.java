package labproblems.Domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.ubb.labproblems.Domain.Student;

import static org.junit.Assert.*;

public class StudentTest {
    Student s;

    @Before
    public void setUp() throws Exception{
        s=new Student("1","Ion",22);
    }

    @After
    public void tearDown() throws Exception{
        s=null;
    }

    @Test
    public void getSerialNumber() {
        assert s.getSerialNumber().equals("1");
    }

    @Test
    public void setSerialNumber() {
        s.setSerialNumber("23");
        assert s.getSerialNumber().equals("23");
    }

    @Test
    public void getName() {
        assert s.getName().equals("Ion");
    }

    @Test
    public void setName() {
        s.setName("John");
        assert s.getName().equals("John");
    }

    @Test
    public void getGroup() {
        assert s.getGroup()==22;
    }

    @Test
    public void setGroup() {
        s.setGroup(45);
        assert s.getGroup()==45;
    }
}