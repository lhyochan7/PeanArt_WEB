package com.peanart.mypage.web;

import com.peanart.main.vo.FileVO;
import com.peanart.mypage.service.MyPageService;
import com.peanart.mypage.vo.MyPageFileVO;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MyPageController {

    @Value("${spring.servlet.multipart.location}")
    String path;

    @Autowired
    MyPageService myPageService;

    @RequestMapping("/myPage")
    public String getUserInfo(HttpServletRequest req, HttpSession session, ModelMap model){
        //MyPage User Info
        int usrSeq =  1; //Integer.parseInt(session.getAttribute("usrSeq").toString());
        MyPageVO user = myPageService.getUserInfo(usrSeq);
        model.addAttribute("userInfo", user);

        String folderName = user.getFileDirName();
        String fileName = user.getFileName();

        String imgSrc = "http://localhost:8080/imagePath/" + folderName + "/" + fileName;

        model.addAttribute("path", imgSrc);
        System.out.println("imgSrc : " + imgSrc);
        return "result";
    }

    @PostMapping("/profileImg")
    public String profileImg(@RequestParam MultipartFile profileImg, @RequestParam String usrId, Model model) throws IllegalStateException, IOException {

        int usrSeq = 1;
        int isExist = myPageService.isThereImg(usrSeq);
        System.out.println(isExist);
        if(isExist == 0){

            // 고유 폴더 이름 만들기 ( UUID_userId )
            String dirUuid = UUID.randomUUID().toString();
            String folderName = dirUuid + "_" + usrId;
            System.out.println(folderName);

            File directory = new File(path + "/" + folderName);
            if (!directory.exists()) {
                directory.mkdir();
            }

            FileVO fvo = new FileVO(UUID.randomUUID().toString(), profileImg.getOriginalFilename(), profileImg.getContentType());
            File newFileName = new File(path + "/" +folderName + "/" + fvo.getfile_Uuid() + "_" + fvo.getFileName());
            profileImg.transferTo(newFileName);

            model.addAttribute("profileImg", profileImg);

            MyPageFileVO myPageFileVO = new MyPageFileVO();
            myPageFileVO.setFileDirName(folderName);
            myPageFileVO.setFileName(fvo.getfile_Uuid() + "_" + fvo.getFileName());

            // 나중에 session에서 user 분류값 넣기
            myPageFileVO.setUsrSeq(1);

            myPageService.setProfileImg(myPageFileVO);
        }
        else{
            MyPageFileVO userProfile = myPageService.getProfileImg(usrSeq);

            String folderName = userProfile.getFileDirName();

            File directory = new File(path + "/" + folderName);
            if (!directory.exists()) {
                directory.mkdir();
            }

            FileVO fvo = new FileVO(UUID.randomUUID().toString(), profileImg.getOriginalFilename(), profileImg.getContentType());
            File newFileName = new File(path + "/" +folderName + "/" + fvo.getfile_Uuid() + "_" + fvo.getFileName());
            profileImg.transferTo(newFileName);

            model.addAttribute("profileImg", profileImg);

            MyPageFileVO myPageFileVO = new MyPageFileVO();
            myPageFileVO.setFileDirName(folderName);
            myPageFileVO.setFileName(fvo.getfile_Uuid() + "_" + fvo.getFileName());

            // 나중에 session에서 user 분류값 넣기
            myPageFileVO.setUsrSeq(1);

            myPageService.updateProfileImg(myPageFileVO);
        }

        System.out.println("FILES FOUND");
        return "result";
    }

}
