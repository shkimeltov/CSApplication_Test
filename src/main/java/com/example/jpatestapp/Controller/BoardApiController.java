package com.example.jpatestapp.Controller;

import com.example.jpatestapp.Entity.Board;
import com.example.jpatestapp.Service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/write")
    @ResponseBody
    public String Write(@RequestPart("file") MultipartFile file, @RequestPart("data") Board content) throws JsonProcessingException {
        JSONObject jsonData = new JSONObject();

        try{
            // 파일 저장 처리
            String origin = file.getOriginalFilename();
            //브라우저별로 경로가 포함되서 올라오는 경우가 있기에 간단한 처리.
            String filename=origin.substring(origin.lastIndexOf("\\")+1);
            //폴더생성
            String filepath = "D:\\TestApp\\JPATestApp\\saveFile\\";
            //중복파일의 처리
            String uuid= UUID.randomUUID().toString();
            //최종저장경로
            String saveName = filepath+"\\"+uuid+"_"+filename;
            try {
                File save = new File(saveName); //세이브경로
                file.transferTo(save);
                content.setFileName(saveName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(content.getIdx() == 0){
                boardService.writeContent(content);
            }else{
                boardService.updateContent(content.getIdx(), content);
            }
            jsonData.put("result", "success");
        }catch (Exception e){
            jsonData.put("result", "fail");
            jsonData.put("msg", e.getMessage());
        }
        return jsonData.toString();

    }
    @PostMapping("/delete")
    @ResponseBody
    public String Delete(int idx){
        JSONObject jsonData = new JSONObject();

        try{
            boardService.deleteContent(idx);
            jsonData.put("result", "success");
        }catch (Exception e){
            jsonData.put("result", "fail");
            jsonData.put("msg", e.getMessage());
        }
        return jsonData.toString();
    }
}
