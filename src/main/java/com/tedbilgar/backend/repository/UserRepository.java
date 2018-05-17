package com.tedbilgar.backend.repository;

import com.tedbilgar.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserById(int id);
    User findUserByEmail(String email);

    List<User> findUsersByActiveOrderByScoreDesc(boolean active);
}
