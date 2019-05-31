package ro.ubb.labproblems.Repository;

import ro.ubb.labproblems.Domain.BaseEntity;
import ro.ubb.labproblems.Domain.LabProblem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LabProblemDBRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {
    private DbCon connection;

    public LabProblemDBRepository(DbCon connection){
        this.connection=connection;
    }

    @Override
    public Optional<T> returnOne(ID id) {
        List<T> all = new ArrayList<>();
        all= (List<T>) this.returnAll();
        return Optional.ofNullable(all.get((int)id));
    }

    @Override
    public Iterable<T> returnAll() {
        List<T> all = new ArrayList<>();

        try {
            PreparedStatement ps=connection.getConn().prepareStatement("select * from LabProblems");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                long baseid = rs.getLong("ProblemID");
                LabProblem s = new LabProblem(rs.getString("problemStatement"), rs.getString("teacher"), rs.getInt("difficulty"),
                        rs.getString("duedate"), rs.getString("subject"));
                s.setId(baseid);
                all.add((T)s);
            }
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<T> addToRepo(T entity) {
        LabProblem s = (LabProblem) entity;
        Long baseid = s.getId();
        String statement = s.getProblemStatement();
        String teacher = s.getTeacher();
        String duedate = s.getDueDate();
        String subject = s.getSubject();
        int diff = s.getWeight();

        try{
            PreparedStatement ps=connection.getConn().prepareStatement("insert into LabProblems values (?,?,?,?,?,?)");
            ps.setLong(1,baseid);
            ps.setString(2,statement);
            ps.setString(3,teacher);
            ps.setInt(4,diff);
            ps.setString(5,duedate);
            ps.setString(6,subject);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> deleteFromRepo(ID id) {
        Long baseid = (Long) id;

        try{
            PreparedStatement ps=connection.getConn().prepareStatement("delete from LabProblems where ProblemID=?");
            ps.setLong(1,baseid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) {
        LabProblem s = (LabProblem) entity;
        Long baseid = s.getId();
        String statement = s.getProblemStatement();
        String teacher = s.getTeacher();
        String duedate = s.getDueDate();
        String subject = s.getSubject();
        int diff = s.getWeight();

        try{
            PreparedStatement ps=connection.getConn().prepareStatement("update LabProblems set problemStatement=?," +
                    " teacher=?, difficulty=?, duedate=?, subject=? where ProblemID=?");
            ps.setLong(6,baseid);
            ps.setString(1,statement);
            ps.setString(2,teacher);
            ps.setInt(3,diff);
            ps.setString(4,duedate);
            ps.setString(5,subject);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
