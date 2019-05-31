package ro.ubb.labproblems.Domain;

/**
    @author Catalin.
    LabProblem class
    fields: problemStatement - String, teacher - String, weight - int, dueDate - String, subject - String
 */
public class LabProblem extends BaseEntity<Long> {
    private String problemStatement;
    private String teacher;
    private int difficulty;
    private String dueDate;
    private String subject;

    /**
    Default constructor for LabProblem class
    input: -
    output: instance of LabProblem class
     */
    public LabProblem(){

    }

    /**
    Constructor for LabProblem class
    input: problemStatement - String, teacher - String, weight - int, dueDate - String, subject - String
    output: instance of LabProblem class
     */
    public LabProblem(String problemStatement, String teacher, int difficulty, String dueDate, String subject){
        this.problemStatement=problemStatement;
        this.teacher=teacher;
        this.difficulty=difficulty;
        this.dueDate=dueDate;
        this.subject=subject;
    }

    /**
    Function getProblemStatement() returns the problemStatement field of a instance of LabProblem class
    input: -
    output: problemStatement - String
     */
    public String getProblemStatement() {
        return problemStatement;
    }

    /**
    Function setProblemStatement() sets the problemStatement field of a instance of LabProblem class
    input: problemStatement - String
    output: -
     */
    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }

    /**
    Function getTeacher() returns the teacher field of a instance of LabProblem class
    input: -
    output: teacher - String
     */
    public String getTeacher() {
        return teacher;
    }

    /**
    Function setTeacher() sets the teacher field of a instance of LabProblem class
    input: teacher - String
    output: -
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
    Function getWeight() returns the weight field of a instance of LabProblem class
    input: -
    output: weight - int
     */
    public int getWeight() {
        return difficulty;
    }

    /**
    Function setWeight() sets the weight field of a instance of LabProblem class
    input: weight - int
    output: -
     */
    public void setWeight(int difficulty) { this.difficulty = difficulty; }

    /**
    Function getDueDate() returns the dueDate field of a instance of LabProblem class
    input: -
    output: dueDate - String
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
    Function setDueDate() sets the dueDate field of a instance of LabProblem class
    input: dueDate - String
    output: -
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
    Function getSubject() returns the subject field of a instance of LabProblem class
    input: -
    output: subject - String
     */
    public String getSubject() {
        return subject;
    }

    /**
    Function setSubject() sets the subject field of a instance of LabProblem class
    input: subject - String
    output: -
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
    Function equals verifies if an Object is equal with a instance of LabProblem class
    input: o - Object
    output: true/false
     */
    @Override
    public boolean equals(Object o) {
        return this==o;
    }

    /**
    Function hashCode() returns the hashCode of a instance of LabProblem class
    input: -
    output: result - int
     */
    @Override
    public int hashCode() {
        int result = problemStatement.hashCode();
        result = 31 * result + teacher.hashCode();
        result = 31 * result + difficulty;
        result = 31 * result + dueDate.hashCode();
        result = 31 * result + subject.hashCode();
        return result;
    }

    /**
    Function toString() returns the toString() representation a instance of LabProblem class
    input: -
    output: String
     */
    @Override
    public String toString() {
        return "LabProblem{" +
                "statement='" + problemStatement + '\'' +
                ", teacher='" + teacher + '\'' +
                ", weight='" + difficulty + '\''+
                ", due date='" + dueDate + '\''+
                ", subject='" + subject + '\''+
                "} " + super.toString();
    }
}
