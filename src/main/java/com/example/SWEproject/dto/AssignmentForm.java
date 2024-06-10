package com.example.SWEproject.dto;

import com.example.SWEproject.entity.Assignment;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class AssignmentForm {
    private Long id;
    private Long cid;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime date;
    private String title;
    private String content;

    public AssignmentForm(){}

    public AssignmentForm(Long id, Long cid, LocalDateTime date, String title, String content) {
        this.id = id;
        this.cid = cid;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "AssignmentForm{" +
                "id=" + id +
                ", cid=" + cid +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Assignment toEntity() {
        return new Assignment(id, cid, date, title, content);
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
