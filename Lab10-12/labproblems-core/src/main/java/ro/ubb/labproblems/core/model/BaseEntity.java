package ro.ubb.labproblems.core.model;

/**
 * @author Catalin.
 */



import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * author: radu
 */
@MappedSuperclass
public class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }

}