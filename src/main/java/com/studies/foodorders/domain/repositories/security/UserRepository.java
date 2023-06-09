package com.studies.foodorders.domain.repositories.security;

import com.studies.foodorders.domain.models.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
