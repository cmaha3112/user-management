package com.app.usermanagement.repository;

import com.app.usermanagement.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByNameIgnoreCase(String name);

    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findByActive(boolean active);

    List<User> findByName(String name);

    List<User> findByEmail(String email);
}
