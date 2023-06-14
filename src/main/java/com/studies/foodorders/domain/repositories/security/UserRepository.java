package com.studies.foodorders.domain.repositories.security;

import com.studies.foodorders.domain.models.security.User;
import com.studies.foodorders.domain.repositories.springcustom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
