package com.studies.foodorders.infrastructure.repositories.product;

import com.studies.foodorders.domain.models.product.ProductPhoto;
import com.studies.foodorders.domain.repositories.product.ProductRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public ProductPhoto save(ProductPhoto productPhoto) {
		return entityManager.merge(productPhoto);
	}
	
}
