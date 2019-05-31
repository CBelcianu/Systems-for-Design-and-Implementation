package ro.ubb.labproblems.Domain;


/**
 * @author Catalin
 */
public class Grade extends BaseEntity<Long> {
    private int studentID = -1;
    private int problemID = -1;
    private int grade;

    /**
     * Default constructor for Grade class
     */
    public Grade(){}

    /**
     * Constructor for Grade class
     * @param studentID int
     * @param problemID int
     * @param grade int
     */
    public Grade(int studentID, int problemID, int grade){
        this.studentID=studentID;
        this.problemID=problemID;
        this.grade=grade;
    }

    /**
     * Returns the StudentID field
     * @return studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Sets the studentID field
     * @param studentID int
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * Returns the problem field
     * @return problemID
     */
    public int getProblemID() {
        return problemID;
    }

    /**
     * Sets the problem field
     * @param problemID int
     */
    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    /**
     * Return the grade field
     * @return grade int
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Sets the grade field
     * @param grade int
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * Verifies if an the instance is equal with the given object
     * @param o Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        return this==o;
    }

    @Override
    public int hashCode() {
        return studentID + problemID + grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "studentID='" + studentID + '\'' +
                ", labProblemID='" + problemID + '\'' +
                ", grade=" + grade +
                "} " + super.toString();
    }
}
