package com.example.jpatestapp.Controller;

import com.example.jpatestapp.Entity.Member;
import com.example.jpatestapp.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String Login(){
        return "/Member/memberLogin";
    }


}
