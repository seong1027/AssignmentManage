package com.example.SWEproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Assignment {
    @Id
    @GeneratedValue
    private Long id; //과제 고유 id

    @Column
    private  Long cid; //과제를 만든 사람의 id

    @Column
    private LocalDateTime date;

    @Column
    private String title;

    @Column
    private String content;

    public Assignment(){}

    public Assignment(Long id, Long cid, LocalDateTime date, String title, String content) {
        this.id = id;
        this.cid = cid;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", cid=" + cid +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
