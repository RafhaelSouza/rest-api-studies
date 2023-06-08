package com.studies.foodorders.domain.repositories.security;

import com.studies.foodorders.domain.models.security.Group;
import com.studies.foodorders.domain.models.security.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {}
