package com.example.SWEproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
public class Submission {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private LocalDateTime date; //제출한 날짜
    @Column
    private Long assignmentId;
    @Column
    private Long studentId;
    @Column
    private String studentName;
    @Column
    private int score;
    @Column
    private String content;
    @Lob
    private byte[] file;

    public Submission(){}

    public Submission(Long id, LocalDateTime date, Long assignmentId, Long studentId, String studentName, int score, String content, byte[] file) {
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
        return "Submission{" +
                "id=" + id +
                ", date=" + date +
                ", assignmentId=" + assignmentId +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", score=" + score +
                ", content='" + content + '\'' +
                ", file=" + Arrays.toString(file) +
                '}';
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
