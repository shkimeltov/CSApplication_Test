package com.example.jpatestapp.Controller;

import com.example.jpatestapp.Entity.Board;
import com.example.jpatestapp.Entity.Member;
import com.example.jpatestapp.Service.MemberService;
import com.example.jpatestapp.Static.StaticValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String Login(){
        return "Member/memberLogin";
    }

    @GetMapping("/list")
    public String List(Model model, @PageableDefault(page=0, size=5, sort="registerDate", direction= Sort.Direction.DESC) Pageable pageable){

        Page<Member> memberList = memberService.getAllContents(pageable);
        model.addAttribute("members", memberList);

        //페이지블럭 처리
        int BAR_LENGTH = 5;
        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
        int nowPage = memberList.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage =  Math.max(nowPage - (BAR_LENGTH-1)/2, 1); //1.5     2
        int endPage = Math.min(startPage+(BAR_LENGTH-1), memberList.getTotalPages());
        startPage = endPage - startPage < (BAR_LENGTH-1) ? Math.max(endPage - (BAR_LENGTH-1), 1) : startPage;

        //페이징 처리를 위한 값 전달
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "Member/memberList";
    }

    @GetMapping("/logout")
    public String Logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("loginId") != null){
            session.invalidate();
        }
        return "Member/memberLogin";
    }

}
