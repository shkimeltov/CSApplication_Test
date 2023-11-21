package com.example.jpatestapp.Static;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;


public class StaticValue {

    private static Map<String, String> boardStaticMap = new HashMap<>();

    public Map<String, String> boardStaticMap(){
        boardStaticMap.put("a", "문의사항");
        boardStaticMap.put("b", "건의사항");
        boardStaticMap.put("c", "리뷰");
        boardStaticMap.put("d", "기타");
        return boardStaticMap;
    }
}
