package com.example.jpatestapp.Service;

import com.example.jpatestapp.Controller.BoardSpecification;
import com.example.jpatestapp.Entity.Board;
import com.example.jpatestapp.Entity.Member;
import com.example.jpatestapp.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member registMember(Member member) {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        member.setRegisterDate(formattedDate);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    public Member findByMemberId(String memberId){
        return memberRepository.findByMemberId(memberId);
    }

    public void updateLoginDate(Member member){
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        member.setLastLoginDate(formattedDate);
        memberRepository.save(member);
    }

    public Page<Member> getAllContents(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }


    public boolean checkPassword(String password, String passwordChk) {
        if(password.equals(passwordEncoder.encode(passwordChk))){
            return true;
        }
        return false;
    }
}
