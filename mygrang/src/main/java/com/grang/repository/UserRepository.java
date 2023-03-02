package com.grang.repository;

import com.grang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);

    @Query(value = "select * from User where User.username LIKE ?1%", nativeQuery = true)
    List<User> findByUsername2(String username);
}
