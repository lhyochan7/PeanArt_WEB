package com.peanart.AI.web;


import groovyjarjarantlr4.v4.runtime.tree.Trees;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class AIController {

    @GetMapping("/AIpage")
    public ResponseEntity<List<Integer>> AIpage(@RequestParam("idx") Integer [] idx, @RequestParam("keywords") String [] keywords) {

        String idxPath = "";
        String keywordsPath = "";

        for (int i = 0; i<idx.length; i++) {
            if(i == 0) {
                idxPath += idx[i];
            } else {
                idxPath += ", " + idx[i];
            }
        }

        for (int i = 0; i<keywords.length; i++) {
            if(i == 0) {
                keywordsPath += keywords[i];
            } else {
                keywordsPath += ", " + keywords[i];
            }
            System.out.println("keywords = " + keywords[i]);
        }


        System.out.println(idxPath);
        System.out.println(keywordsPath);
        // AI 모델 업데이트 실행 (flask 서버)
        String uri = "http://15.164.142.253:5000/AIrecommend/" + idxPath + "/" + keywordsPath;

        System.out.println(uri);
        RestTemplate rt = new RestTemplate();
        List<Integer> result = rt.getForObject(uri, List.class);

        System.out.println(result);


        return new ResponseEntity<List<Integer>>(result, HttpStatus.OK);
    }


}
