package com.example.SWEproject.repository;

import com.example.SWEproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginId(String loginId);
    boolean existsByEmail(String email);
    boolean existsByLoginId(String loginId);
}
