package com.practice.flightbooking.persistence.crud.repositoryinterfaces;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@NoRepositoryBean
public interface BasicCrudRepository<T, ID> extends Repository<T, ID> {

    Optional<T> findById(ID id);

    <S extends T> S save(S entity);

    //<S extends T> S updateById(S entity, ID entityId);

    //void deleteById(ID id);

    boolean existsById(ID id);
}
