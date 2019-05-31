package ro.ubb.labproblems.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubb.labproblems.core.model.BaseEntity;

import java.io.Serializable;

public interface ProgramRepository<T extends BaseEntity<ID>,
        ID extends Serializable>
        extends JpaRepository<T, ID> {
}
