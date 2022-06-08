package com.peanart.Board.web;

import com.peanart.Board.dao.BoardDAO;
import com.peanart.Board.service.BoardService;
import com.peanart.Board.vo.BoardVO;
import com.peanart.main.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/BoardList")
    public List<BoardVO> getBoardList() {
        return boardService.getBoardList();
    }

    @Value("${spring.servlet.multipart.location}")
    String filePath;

//    @GetMapping("/download")
//    public ResponseEntity<Resource> download(@ModelAttribute FileVO fvo) throws IOException {
//        Path path = Paths.get(filePath + "/" + fvo.getUuid() + "_" + fvo.getFileName());
//        String contentType = Files.probeContentType(path);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fvo.getFileName(), StandardCharsets.UTF_8).build());
//        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
//
//        Resource resource = new InputStreamResource(Files.newInputStream(path));
//
//        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//    }

}
