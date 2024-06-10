package com.example.SWEproject.repository;

import com.example.SWEproject.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findAllByAssignmentId(Long assignmentId);
    Submission findByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
}
