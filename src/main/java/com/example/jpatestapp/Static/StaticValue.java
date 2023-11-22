package com.example.jpatestapp.Static;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class StaticValue {

    private static Map<String, String> boardStaticMap;
    private static Map<String, String> boardStatusMap;

    public Map<String, String> boardStaticMap(){
        Map<String, String> boardStaticMap = new HashMap<>();
        boardStaticMap.put("Q", "문의사항");
        boardStaticMap.put("S", "건의사항");
        boardStaticMap.put("R", "리뷰");
        boardStaticMap.put("E", "기타");
        return boardStaticMap;
    }

    public Map<String, String> boardStatusMap(){
        Map<String, String> boardStatusMap = new HashMap<>();
        boardStatusMap.put("W", "대기");
        boardStatusMap.put("D", "완료");
        boardStatusMap.put("H", "보류");
        return boardStatusMap;
    }
}
