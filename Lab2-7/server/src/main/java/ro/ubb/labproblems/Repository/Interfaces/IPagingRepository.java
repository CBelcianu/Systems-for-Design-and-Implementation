package ro.ubb.labproblems.Repository.Interfaces;

import ro.ubb.labproblems.Domain.BaseEntity;

import java.io.Serializable;

public interface IPagingRepository<ID extends Serializable, T extends BaseEntity<ID>> extends IRepository<ID,T> {
    IPage<T> findAll(IPageable pageable);

}
