package ro.ubb.labproblems.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.labproblems.Domain.BaseEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface ProgramRepository<T extends BaseEntity<ID>, ID extends
        Serializable>
        extends JpaRepository<T, ID> {
}

