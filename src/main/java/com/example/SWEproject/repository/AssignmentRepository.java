package com.example.SWEproject.repository;

import com.example.SWEproject.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    @Override
    ArrayList<Assignment> findAll();
}
