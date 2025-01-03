package com.example.demo.model.database.repository;

import com.example.demo.model.database.entities.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<T extends AbstractEntity> extends CrudRepository<T, Integer> {

    T findEntityById(Integer id);

}
