package com.studies.foodorders.infrastructure.repositories.springcustom;

import com.studies.foodorders.domain.repositories.springcustom.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

    private EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager = entityManager;
    }

    @Override
    public Optional<T> findFirstCustom() {
        var jpql = String.format("from %s", getDomainClass().getName());
        T entity = manager.createQuery(jpql, getDomainClass())
            .setMaxResults(1)
            .getSingleResult();
        return Optional.ofNullable(entity);
    }

}
