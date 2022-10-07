package com.daekyo.repo;

import com.daekyo.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepo extends JpaRepository<Test, Integer> {
    public List<Test> findAll();
}
