package com.daekyo.repo;

import com.daekyo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    public List<User> findAll();

    public Optional<User> findUserByIdAndPw(String id, String pw);
}
