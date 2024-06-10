package com.example.SWEproject.dto;

import com.example.SWEproject.entity.Member;
import com.example.SWEproject.entity.Professor;
import com.example.SWEproject.entity.Student;

public class MemberForm {
    private Long id;
    private String loginId;
    private String email;
    private String password;
    private String name;
    private String memberType;

    public MemberForm(Long id, String loginId, String email, String password, String name, String memberType) {
        this.id = id;
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.memberType = memberType;
    }

    public Member toEntity() {
        if ("student".equals(memberType)) {
            return new Student(id, loginId, email, password, name);
        } else if ("professor".equals(memberType)) {
            return new Professor(id, loginId, email, password, name);
        } else {
            return new Member(id, loginId, email, password, name);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }
}
