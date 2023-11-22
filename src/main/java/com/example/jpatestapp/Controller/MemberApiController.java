package com.example.jpatestapp.Controller;

import com.example.jpatestapp.Entity.Member;
import com.example.jpatestapp.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/regist")
    @ResponseBody
    public String Regist(Member member){
        JSONObject jsonData = new JSONObject();
        try{
            memberService.registMember(member);
            jsonData.put("result", "success");
        }catch(Exception e){
            jsonData.put("result", "fail");
            jsonData.put("msg", e.getMessage());
        }
        return jsonData.toString();
    }

    @PostMapping("/login")
    @ResponseBody
    public String Login(String memberId, String passwordChk, HttpServletRequest request){
        JSONObject jsonData = new JSONObject();
        Member member = memberService.findByMemberId(memberId);
        if(member == null){
            jsonData.put("result", "fail");
            jsonData.put("msg", "없는 회원 아이디입니다.");
            return jsonData.toString();
        }
        boolean passwordMatch = passwordEncoder.matches(passwordChk, member.getPassword());
        if(!passwordMatch){
            jsonData.put("result", "fail");
            jsonData.put("msg", "비밀번호를 확인해주세요.");
            return jsonData.toString();
        }
        memberService.updateLoginDate(member);

        // 로그인 성공 후 세션 저장
        HttpSession session = request.getSession();
        session.setAttribute("loginId", memberId);

        // 로그인 알림 보내기
//        LinkedMultiValueMap bodyMap = new LinkedMultiValueMap<>();
//        bodyMap.add("user_list", new String[]{"shkim11", "shkim11@eltov.com"});
//        bodyMap.add("message", "메시지");
//        bodyMap.add("link","http://www.naver.com");
//        bodyMap.add("mlink", "https://m.hiworks.com");
//        bodyMap.add("solution_name", "제목 테스트");
//        bodyMap.add("solution_image_url", "https://www.hiworks.com/static/images/logo.png");
//
//        Object body = new Object();
//
//        WebClient client = WebClient.builder()
//                .defaultHeader("Content-Type" , "application/x-www-form-urlencoded;charset=utf-8")
//                .defaultHeader("Authorization", "a35fafcc15de3599a1fe91bde803634e")
//                .baseUrl("https://api.hiworks.com")
//                .build();
//        client.post()//client <- 위에서 만든 객체
//                .uri("/office/v2/notify")//base 뒤에 붙은 uri
//                .accept(MediaType.APPLICATION_JSON)//어떤 타입을 허락할지
//                .body(BodyInserters.fromFormData(bodyMap))//만든 body를 넣어줌
//                .retrieve()//받은 데이터를 디코딩 해줌
//                .bodyToMono(JSONObject.class)//어떤 타입으로 디코딩할 것인지
//                .subscribe();//비동기식

        jsonData.put("result","success");
        return jsonData.toString();
    }

}
