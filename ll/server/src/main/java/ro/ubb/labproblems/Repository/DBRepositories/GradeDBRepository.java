package ro.ubb.labproblems.Repository.DBRepositories;

import ro.ubb.labproblems.Domain.BaseEntity;
import ro.ubb.labproblems.Domain.Grade;
import ro.ubb.labproblems.Repository.Interfaces.IRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
public class GradeDBRepository<ID, T extends BaseEntity<ID>> implements IRepository<ID, T> {
    private DbCon connection;

    public GradeDBRepository(DbCon connection){
        this.connection=connection;
    }

    @Override
    public Optional<T> returnOne(ID id) {
        List<T> all;
        all= (List<T>) this.returnAll();
        return Optional.ofNullable(all.get((int)id));
    }

    @Override
    public Iterable<T> returnAll() {
        List<T> all = new ArrayList<>();

        try {
            PreparedStatement ps=connection.getConn().prepareStatement("select * from Grades");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                long baseid = rs.getLong("GradeID");
                Grade s = new Grade(rs.getInt("StudentID"),rs.getInt("ProblemID"),rs.getInt("grade"));
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
        Grade s = (Grade) entity;
        Long baseid = s.getId();
        int sid = s.getStudentID();
        int pid = s.getProblemID();
        int gr = s.getGrade();

        try{
            PreparedStatement ps=connection.getConn().prepareStatement("insert into Grades values (?,?,?,?)");
            ps.setLong(1,baseid);
            ps.setInt(2,sid);
            ps.setInt(3,pid);
            ps.setInt(4,gr);
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
            PreparedStatement ps=connection.getConn().prepareStatement("delete from Grades where GradeID=?");
            ps.setLong(1,baseid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) {
        Grade s = (Grade) entity;
        Long baseid = s.getId();
        int sid = s.getStudentID();
        int pid = s.getProblemID();
        int gr = s.getGrade();

        try{
            PreparedStatement ps=connection.getConn().prepareStatement("update Grades set StudentID=?, GradeID=?, grade=? where GradeID=?");
            ps.setLong(4,baseid);
            ps.setInt(1,sid);
            ps.setInt(2,pid);
            ps.setInt(3,gr);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
