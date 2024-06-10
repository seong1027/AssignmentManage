package com.example.SWEproject.dto;

import com.example.SWEproject.entity.Submission;
import jakarta.persistence.Lob;

import java.time.LocalDateTime;


public class SubmissionForm {
    private Long id;
    private LocalDateTime date;
    private Long assignmentId;
    private Long studentId;
    private String studentName;
    private int score;
    private String content;
    @Lob
    private byte[] file;

    public SubmissionForm(Long id, LocalDateTime date, Long assignmentId,
                          Long studentId, String studentName, int score, String content, byte[] file) {
        this.id = id;
        this.date = date;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.score = score;
        this.content = content;
        this.file = file;
    }

    @Override
    public String toString() {
        return "SubmissionForm{" +
                "id=" + id +
                ", date=" + date +
                ", assignmentId=" + assignmentId +
                ", studentId=" + studentId +
                '}';
    }

    public Submission toEntity(){
        return new Submission(id, date, assignmentId, studentId, studentName, 0, content, file);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
