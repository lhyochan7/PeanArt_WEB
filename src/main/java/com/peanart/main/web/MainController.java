package com.peanart.main.web;

import com.peanart.main.service.MainService;
import com.peanart.main.vo.FileVO;
import com.peanart.main.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @GetMapping("/index")
    public List<UserVO> getTest(String id){
        return mainService.getTest();
    }


    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile[] uploadfile, Model model) throws IllegalStateException, IOException {
        List<FileVO> files = new ArrayList<>();

        for(MultipartFile file : uploadfile) {
            if(!file.isEmpty()) {
                FileVO fvo = new FileVO(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
                files.add(fvo);

                File newFileName = new File(fvo.getUuid() + "_" + fvo.getFileName());

                file.transferTo(newFileName);
            }
        }

        model.addAttribute("files", files);

        System.out.println(files);
        System.out.println("FILES FOUND");
        return "result";
    }
}
