package ro.ubb.labproblems.Domain;

public class Student extends BaseEntity<Long> {
    private String serialNumber;
    private String name;
    private int group;

    /**
    Default constructor for Student class
    input: -
    output: instance of Student class
     */
    public Student() {
    }

    /**
    Constructor for Student class
    input: serialNumber - String, name - String, group - int
    output: instance of Student class
     */
    public Student(String serialNumber, String name, int group) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.group = group;
    }

    /**
    Function getSerialNumber() returns the serialNumber field of a instance of Student class
    input: -
    output: serialNumber - String
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
    Function setSerialNumber() sets the serialNumber field of a instance of Student class
    input: serialNumber - String
    output: -
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
    Function getName() returns the name field of a instance of Student class
    input: -
    output: Name - String
     */
    public String getName() {
        return name;
    }

    /**
    Function setName() sets the name field of a instance of Student class
    input: Name - String
    output: -
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
    Function getGroup() returns the group field of a instance of Student class
    input: -
    output: group - int
     */
    public int getGroup() {
        return group;
    }

    /**
    Function setGroup() sets the group field of a instance of Student class
    input: group - String
    output: -
     */
    public void setGroup(int group) {
        this.group = group;
    }

    /**
    Function equals verifies if an Object is equal with a instance of Student class
    input: o - Object
    output: true/false
     */
    @Override
    public boolean equals(Object o) {
        return this==o;
    }

    /**
    Function hashCode() returns the hashCode of a instance of Student class
    input: -
    output: result - int
     */
    @Override
    public int hashCode() {
        int result = serialNumber.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + group;
        return result;
    }

    /**
    Function toString() returns the toString() representation a instance of LabProblem class
    input: -
    output: String
     */
    @Override
    public String toString() {
        return "Student{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", group=" + group +
                "} " + super.toString();
    }
}
