package com.lucky.fintech.repository;

import com.lucky.fintech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.user_login_id = ?1")
    Optional<User> findByUser_login_id(String user_login_id);
}
