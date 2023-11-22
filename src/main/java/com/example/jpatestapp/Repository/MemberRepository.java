package com.example.jpatestapp.Repository;

import com.example.jpatestapp.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberId(String memberId);

}
