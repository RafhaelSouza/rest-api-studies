package com.studies.foodorders.domain.repositories.security;

import com.studies.foodorders.domain.models.security.Users;
import com.studies.foodorders.domain.repositories.springcustom.CustomJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CustomJpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

}
