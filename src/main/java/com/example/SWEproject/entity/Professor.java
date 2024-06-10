package com.example.SWEproject.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("professor")
public class Professor extends Member {
    public Professor(){
    }
    public Professor(Long id, String loginId, String email, String password, String name) {
        super(id, loginId, email, password, name);
    }
}
