package ro.ubb.labproblems.Domain;

import java.io.Serializable;

/**
 * @author Catalin.
    BaseEntity class
    fields: id
 */
public class BaseEntity<ID> implements Serializable {
    private ID id;

    /**
    Function getId() returns the id of a given entity
    input: -
    output: id
     */
    public ID getId() {
        return id;
    }

    /**
    Function setId() sets the id of a given entity
    input: id
    output: -
     */
    public void setId(ID id) {
        this.id = id;
    }

    /**
    Function toString() returns the toString() representation of the entity
    input: -
    output: String
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}