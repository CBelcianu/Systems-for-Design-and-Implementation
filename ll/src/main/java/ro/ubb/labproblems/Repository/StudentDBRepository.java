package ro.ubb.labproblems.Repository;

import ro.ubb.labproblems.Domain.BaseEntity;
import ro.ubb.labproblems.Domain.Student;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDBRepository<ID extends Serializable, T extends BaseEntity<ID>> implements IPagingRepository<ID,T> {
    private DbCon connection;

    public StudentDBRepository(DbCon connection){
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
            PreparedStatement ps=connection.getConn().prepareStatement("select * from Students");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                long baseid = rs.getLong("StudentID");
                Student s = new Student(rs.getString("serialNumber"), rs.getString("name"), rs.getInt("groupNr"));
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
        Student s = (Student) entity;
        Long baseid = s.getId();
        String serialNr = s.getSerialNumber();
        String name = s.getName();
        int gr = s.getGroup();

        try{
            PreparedStatement ps=connection.getConn().prepareStatement("insert into Students values (?,?,?,?)");
            ps.setLong(1,baseid);
            ps.setString(2,serialNr);
            ps.setString(3,name);
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
            PreparedStatement ps=connection.getConn().prepareStatement("delete from Students where StudentID=?");
            ps.setLong(1,baseid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T entity) {
        Student s = (Student) entity;
        Long baseid = s.getId();
        String serialNr = s.getSerialNumber();
        String name = s.getName();
        int gr = s.getGroup();

        try{
            PreparedStatement ps=connection.getConn().prepareStatement("update Students set serialNumber=?, name=?, groupNr=? where StudentID=?");
            ps.setLong(4,baseid);
            ps.setString(1,serialNr);
            ps.setString(2,name);
            ps.setInt(3,gr);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public IPage<T> findAll(IPageable pageable) {
        return Paginator.paginate(this.returnAll(), pageable);
    }
}
