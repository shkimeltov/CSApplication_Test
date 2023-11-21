package com.example.jpatestapp.Controller;

import com.example.jpatestapp.Entity.Board;
import com.example.jpatestapp.Service.BoardService;
import com.example.jpatestapp.Static.StaticValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value={"/write","/write/{str_idx}"})
    public String WritePage(@PathVariable(required = false) String str_idx, Model model){
        if(str_idx != null){
            int idx = Integer.parseInt(str_idx);
            model.addAttribute("content", boardService.getContent(idx));
        }
        StaticValue staticValue = new StaticValue();
        model.addAttribute("contentType", staticValue.boardStaticMap());
        return "/Board/contentWrite";
    }

    @GetMapping("/list")
    public String List(Model model, @PageableDefault(page=0, size=5, sort="registerDate", direction= Sort.Direction.DESC) Pageable pageable
        , @RequestParam(defaultValue = "")String searchType, @RequestParam(defaultValue = "")String keyword){

        Page<Board> contentList = boardService.getAllContents(pageable, searchType, keyword);
        model.addAttribute("contents", contentList);
        StaticValue staticValue = new StaticValue();
        model.addAttribute("contentType", staticValue.boardStaticMap());

        //페이지블럭 처리
        int BAR_LENGTH = 5;
        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
        int nowPage = contentList.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage =  Math.max(nowPage - (BAR_LENGTH-1)/2, 1); //1.5     2
        int endPage = Math.min(startPage+(BAR_LENGTH-1), contentList.getTotalPages());
        startPage = endPage - startPage < (BAR_LENGTH-1) ? Math.max(endPage - (BAR_LENGTH-1), 1) : startPage;

        //페이징 처리를 위한 값 전달
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        //검색 관련 값 전달
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        return "/Board/contentList";
    }

    @GetMapping("/view/{idx}")
    public String ViewByIdx(@PathVariable int idx, Model model){
        model.addAttribute("content", boardService.getContent(idx));
        StaticValue staticValue = new StaticValue();
        model.addAttribute("contentType", staticValue.boardStaticMap());
        return "/Board/contentView";
    }


}
