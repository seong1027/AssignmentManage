package com.example.SWEproject.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("student")
public class Student extends Member {
    public Student(){
    }
    public Student(Long id, String loginId, String email, String password, String name) {
        super(id, loginId, email, password, name);
    }

}
