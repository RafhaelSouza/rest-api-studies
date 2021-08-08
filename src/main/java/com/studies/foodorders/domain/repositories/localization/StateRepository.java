package com.studies.foodorders.domain.repositories.localization;

import com.studies.foodorders.domain.models.localization.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {}
