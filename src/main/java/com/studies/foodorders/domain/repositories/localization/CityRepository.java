package com.studies.foodorders.domain.repositories.localization;

import com.studies.foodorders.domain.models.localization.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {}
