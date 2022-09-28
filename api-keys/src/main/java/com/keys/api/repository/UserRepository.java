package com.keys.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keys.api.entity.User;

//repository of user entity, inject this interface to persist and retrieve data from table user

@Repository
public interface UserRepository extends JpaRepository< User, Long> {
    Optional< User> findByEmail ( String email );
}
