package com.example.jpatestapp.Controller;

import com.example.jpatestapp.Entity.Member;
import com.example.jpatestapp.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;

//    @PostMapping
//    public Member createUser(@RequestBody Member user) {
//        return memberService.createUser(user);
//    }
//
//    @GetMapping("/{username}")
//    public Member getUser(@PathVariable String username) {
//        Optional<Member> user = memberService.findByUsername(username);
//        return user.get();
//
//    }
}
