package ro.ubb.labproblems.Domain;

/**
 * @author Catalin.
 */



import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
    BaseEntity class
    fields: id
 */
@MappedSuperclass
public class BaseEntity<ID> {
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